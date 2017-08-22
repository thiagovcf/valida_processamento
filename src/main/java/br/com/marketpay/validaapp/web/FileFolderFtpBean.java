package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
import br.com.marketpay.validaapp.entity.FolderFpt;
import br.com.marketpay.validaapp.service.EmissorService;
import br.com.marketpay.validaapp.service.FileFolderFtpEmissorService;
import br.com.marketpay.validaapp.service.FolderFtpService;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("view")
public class FileFolderFtpBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private FileFolderFtpEmissor fileFolderFtpEmissor = new FileFolderFtpEmissor();
	
	private FolderFpt folderFtp = new FolderFpt();
	
	private Emissor emissor = new Emissor();
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private List<String> itensRegra = new ArrayList<String>();
	
	private String valorRegraFixo;
	
	private String valorRegraData;
	
	private String valorRegraAleatorio;
	
	@Autowired
	private FolderFtpService folderFtpEmissorService;
	
	@Autowired
	private EmissorService emissorService;
	
	@Autowired
	private FileFolderFtpEmissorService fileFolderFtpEmissorService;
	
	
	public void preencherAlterar(){
		alterar = true;
		visualizar = false;
		preecherObjeto();
	}
	
	public void preencherVisualizar(){
		alterar = false;
		visualizar = true;
		preecherObjeto();
	}
	
	private void preecherObjeto(){
		folderFtp = folderFtpEmissorService.findById(folderFtp.getId());
		fileFolderFtpEmissor = fileFolderFtpEmissorService.findByFolder(folderFtp);
		
		
		if (fileFolderFtpEmissor == null) {
			fileFolderFtpEmissor = new FileFolderFtpEmissor();
			visualizar = false;
			alterar = true;
		}else {
			executJavaScript("preecherAlterarFile('" + fileFolderFtpEmissor.getTipoPeriodoExecucao() + "','" + fileFolderFtpEmissor.getValorTipoPeriodoExecucao() + "');");
			String[] items = fileFolderFtpEmissor.getRegraNomeArquivo().split(";");
			if(itensRegra == null || itensRegra.isEmpty()){
				itensRegra = new ArrayList<String>(Arrays.asList(items));
			}
		}
	}
	
	public String salvar() throws IOException{
		preencherRegraArquivo();
		
		String radio = (String) getRequestParam("radioPeriodo");
		
		fileFolderFtpEmissor.setTipoPeriodoExecucao(radio);
		
		if(!FileFolderFtpEmissor.PERIODO_DIARIO.equals(radio)){
			String regrasPeriodo = null;
			
			if(FileFolderFtpEmissor.PERIODO_SEMANAL.equals(radio)){
				regrasPeriodo = (String) getRequestParam("regrasSemana");
			}else if(FileFolderFtpEmissor.PERIODO_MENSAL.equals(radio)){
				regrasPeriodo = (String) getRequestParam("regrasMes");
			}else{
				regrasPeriodo = "";
			}
			
			fileFolderFtpEmissor.setValorTipoPeriodoExecucao(regrasPeriodo);
		}
		
		fileFolderFtpEmissor.setFolder(folderFtp);
		fileFolderFtpEmissorService.save(fileFolderFtpEmissor);
		
		addMensageRedirect(new Flash("Arquivo salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_FILE;
	}
	
	public void pesquisar(){
		emissor = emissorService.findById(emissor.getId());
	}
	

	public void adicionarValorARegraFixo() {
		if(StringUtils.isNotBlank(valorRegraFixo)) {
			
			itensRegra.add(FileFolderFtpEmissor.getRegraFixa(valorRegraFixo));
		}
	}
	public void adicionarValorRegraData() {
		if(StringUtils.isNotBlank(valorRegraData)) {
			itensRegra.add(FileFolderFtpEmissor.getRegraData(valorRegraData));
		}
	}
	public void adicionarValorRegraAletatorio() {
		itensRegra.add(FileFolderFtpEmissor.getRegraAleatoria());
	}
	
	public void removeRegra(){
		int indexRegra = Integer.parseInt((String)getRequestParam("indexRegra"));
		itensRegra.remove(indexRegra);
	}
	
	public void removeRegra(Object object){
		itensRegra.remove(object);
	}
	
	public void preencherRegraArquivo() {
		
		StringBuilder regraNomeArquivo = new StringBuilder();
		
		for (String string : itensRegra) {
			regraNomeArquivo.append(string).append(";");
		}
		fileFolderFtpEmissor.setRegraNomeArquivo(regraNomeArquivo.toString());
	}
	
	
}
