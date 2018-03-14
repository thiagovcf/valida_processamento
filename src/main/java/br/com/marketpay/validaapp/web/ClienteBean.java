package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Cliente;
import br.com.marketpay.validaapp.entity.ProcessAlterarSenha;
import br.com.marketpay.validaapp.service.ClienteService;
import br.com.marketpay.validaapp.service.ProcessAlterarSenhaService;
import br.com.marketpay.validaapp.util.funcoes.FuncoesData;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("request")
public class ClienteBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente = new Cliente();
	
	private Iterable<Cliente> clientes;
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String campoPesquisa;
	
	private String dataValidadeCartao;
	
	@Getter
	@Setter
	private String tokenAlterarSenha;
	
	@Autowired
	private ProcessAlterarSenhaService processAlterarSenhaService;
	
	@Autowired
	private ClienteService clienteService;
	
	public void init() {
		preencheClienteAtivo();
	}
	
	public void preencheClienteAtivo(){
		clientes =  clienteService.listarClientes("");
	}
	
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
		if (cliente != null && cliente.getId() != 0) {
			cliente = clienteService.findById(new Long(cliente.getId()));
			if(dataValidadeCartao!=null) {
				try {
					cliente.setValidadeCartao(FuncoesData.formataData(dataValidadeCartao));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	public String salvar() throws IOException{
		try {
			cliente.setValidadeCartao(FuncoesData.formataData(dataValidadeCartao));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clienteService.save(cliente); 
		
		addMensageRedirect(new Flash("Cliente salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_CLIENTE;
	}
	
	public void pesquisar(){
		clientes = clienteService.listarClientes(campoPesquisa);
	}
	
	public String validaToken() throws IOException {
		if(tokenAlterarSenha == null || (((String) getRequestParam("token") != null) && !tokenAlterarSenha.equals((String) getRequestParam("token")))){
			tokenAlterarSenha = (String) getRequestParam("token");
		}
		if(tokenAlterarSenha != null && !"".equals(tokenAlterarSenha.trim())){
			ProcessAlterarSenha process = processAlterarSenhaService.getProcessToken(tokenAlterarSenha);
			
			if(process == null){
				//getLoginBean().setMessageError(MessageUtil.getMessage(getResourceBundle(), Constantes.MENSAGEM_TOKEN_INVALIDO));
				redirect(contextPathApplication());
			}
		}
		return null;
	}
	public Cliente getClienteSessao() {
		return cliente;
	}
	
}
