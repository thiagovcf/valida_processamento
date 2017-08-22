package br.com.marketpay.validaapp.service;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Aplicacao;
import br.com.marketpay.validaapp.entity.Configuracao;
import br.com.marketpay.validaapp.repository.AplicacaoRepository;
import br.com.marketpay.validaapp.util.funcoes.FuncoesArquivo;
import br.com.marketpay.validaapp.util.funcoes.ShellUtil;

@Service
public class AplicacaoServiceImpl implements AplicacaoService {

	@Autowired
	private AplicacaoRepository aplicacaoRepository;
	
	@Autowired
	private ConfiguracaoService configuracaoService;

	public void initRecuperaTomcatsPRD() throws IOException{
		String ips = FuncoesArquivo.getInstance().getValorProperties("server.session");
		
		if (ips != null && !ips.equals("")) {
			
			String[] ipsApp = ips.split("\\|");
			
			for (String ip : ipsApp) {
				saveAplicacao(ip.split("#")[1], ip.split("#")[1], ip.split("#")[2], "/chaves/"+ip.split("#")[3]);
			}
		}
		
		Iterable<Aplicacao> aplicacoes = aplicacaoRepository.findAll();
		
		aplicacoes.forEach(app -> atualizaStatusAplicacao(app));
	}
	
	public void saveAplicacao(String ipPriv, String ipPub, String usuario, String pathKey) {
		
		ShellUtil shellUtil = new ShellUtil();
		File key = new File(this.getClass().getResource(pathKey).getFile());

		Configuracao configuracao = configuracaoService.getConfiguracaoPadrao();
		
		try {
			List<String> tomcats = shellUtil.executeCommandWithKey(ipPriv, usuario, key, "ls -lt /usr/local/tomcat | grep tomcat | grep ^d | grep -v grep |awk '{print $9}'");
			
			for (String tomcat : tomcats) {
				if(tomcat.startsWith("tomcat") && !(tomcat.startsWith("tomcat-lb"))){
					String comandoPorta = "echo $(sudo cat /usr/local/tomcat/$EMISSOR/conf/server.xml | grep \"<Connector\" | grep \"port=\" | grep \"HTTP/\" | awk '{print $2}' FS='\"')  | awk '{split($0,a,\" \"); print a[1]}'";
					
					List<String> port = shellUtil.executeCommandWithKey(ipPriv, usuario, key, comandoPorta.replace("$EMISSOR", tomcat));
					
					String portaTomcat = "";
					
					if(!port.isEmpty()){
						portaTomcat = port.get(0);
					}
					
					Aplicacao aplicacao = preencheAplicacao(tomcat, portaTomcat, ipPriv, ipPub, configuracao);
					
					aplicacaoRepository.save(aplicacao);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizaStatusAplicacao(Aplicacao aplicacao){
		try {
			URL url = new URL("http://" + aplicacao.getIpExterno() + ":" + aplicacao.getPorta() + "/" + aplicacao.getNome());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(1000);
			connection.connect();

			int code = connection.getResponseCode();
			
			if(code != HttpURLConnection.HTTP_OK){
				aplicacao.setStatus(Aplicacao.STATUS_INATIVA);
				aplicacaoRepository.save(aplicacao);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			aplicacao.setStatus(Aplicacao.STATUS_INATIVA);
			aplicacaoRepository.save(aplicacao);
		}
	}
	
	private Aplicacao preencheAplicacao(String tomcat, String portaTomcat, String ipPriv, String ipPub, Configuracao configuracao) {
		Aplicacao aplicacao = new Aplicacao();
		
		aplicacao.setIpInterno(ipPriv);
		aplicacao.setIpExterno(ipPub);
		aplicacao.setNome(tomcat.replace("tomcat-", ""));
		aplicacao.setPorta(portaTomcat);
		aplicacao.setStatus(Aplicacao.STATUS_ATIVA);
		
		if(configuracao != null){
			aplicacao.setLink(configuracao.getDominio());
		}
		return aplicacao;
	}

	@Override
	public Iterable<Aplicacao> getAplicacoes() {
		return aplicacaoRepository.findAll();
	}
	
}
