package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Cliente;
import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FolderFpt;
import br.com.marketpay.validaapp.entity.Transacao;
import br.com.marketpay.validaapp.service.ClienteService;
import br.com.marketpay.validaapp.service.EmissorService;
import br.com.marketpay.validaapp.service.FolderFtpService;
import br.com.marketpay.validaapp.service.TransacaoService;
import br.com.marketpay.validaapp.service.UtilService;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("view")
public class TransacaoBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Transacao transacao  = new Transacao();
	
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes = new ArrayList<>();
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String pesquisaCliente;
	
	
	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UtilService utilService;
	
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
		if (transacao != null && transacao.getId() != 0) {
			transacao = transacaoService.findById(transacao.getId());
		}
	}
	
	public String salvar() throws IOException{
		transacaoService.save(transacao); 
		
		addMensageRedirect(new Flash("Venda realizada com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_FOLDER;
	}
	
	public void pesquisar(){
		clientes = null;
		
		if(cliente.getId()==0 || cliente == null){
			Iterable<Transacao> listaTransacao = transacaoService.listarTransacoes("");
			
			/*if(StringUtils.isBlank(pesquisaNomePasta.trim())) {
				clientes = transacaoService.findAllByEmissorIn(listaEmissorAtivo);
			}else {
				clientes = transacaoService.findByNomeAndEmissorIn(pesquisaNomePasta,listaEmissorAtivo);
			}*/
		}else {
			cliente = clienteService.findById(cliente.getId());

//			if(StringUtils.isNotBlank(pesquisaNomePasta.trim())) {
//				clientes = transacaoService.findAllByNomeIgnoreCaseContainingAndEmissor(pesquisaNomePasta, cliente);
//			}else {
//				clientes = transacaoService.findByEmissor(cliente);
//			}
		}
	}
	
	/*public void testeConexao() throws IOException{
		Boolean statusConexao;
		statusConexao = utilService.getStatusConexao(folderFtp.getPorta(),folderFtp.getIp(), folderFtp.getLogin(), folderFtp.getSenha());
		if (statusConexao) {
			addMensageRedirect(new Flash("Conexão realizada com sucesso", FlashTipo.success));
		}else {
			addMensageRedirect(new Flash("Falha na conexão", FlashTipo.danger));
		}
	}*/
	
}
