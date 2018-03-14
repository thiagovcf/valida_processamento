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
import br.com.marketpay.validaapp.entity.Transacao;
import br.com.marketpay.validaapp.service.ClienteService;
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
@Scope("request")
public class TransacaoBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Transacao transacao  = new Transacao();
	
	private Cliente cliente = new Cliente();
	
	private List<Cliente> clientes = new ArrayList<>();
	
	private List<Transacao> transacoes = new ArrayList<>();
	
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
		
		if(cliente.getId()==0 || cliente == null){
			List<Emissor> listaEmissorAtivo = (List)clienteService.listarClientes(Emissor.STATUS_ATIVA);
			
			/*if(StringUtils.isBlank(pesquisaNomePasta.trim())) {
				transacoes = transacaoService.findAllByEmissorIn(listaEmissorAtivo);
			}else {
				transacoes = transacaoService.findByNomeAndEmissorIn(pesquisaNomePasta,listaEmissorAtivo);
			}*/
		}else {
			cliente = clienteService.findById(cliente.getId());

			/*if(StringUtils.isNotBlank(pesquisaNomePasta.trim())) {
				transacoes = transacaoService.findAllByNomeIgnoreCaseContainingAndEmissor(pesquisaNomePasta, emissor);
			}else {
				transacoes = transacaoService.findByEmissor(emissor);
			}*/
		}
	}
	
}
