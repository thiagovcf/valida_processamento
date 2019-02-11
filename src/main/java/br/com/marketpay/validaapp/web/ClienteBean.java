package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Cliente;
import br.com.marketpay.validaapp.entity.ProcessAlterarSenha;
import br.com.marketpay.validaapp.service.ClienteService;
import br.com.marketpay.validaapp.service.ProcessAlterarSenhaService;
import br.com.marketpay.validaapp.service.TransacaoService;
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
	
	@Autowired
	private TransacaoService transacaoService;
	
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
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			dataValidadeCartao = sdf.format(cliente.getValidadeCartao());

		}
	}
	
	public String salvar() throws IOException{
		preencherClienteForm();
		clienteService.save(cliente); 
		
		addMensageRedirect(new Flash("Cliente salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_CLIENTE;
	}
	
	private void preencherClienteForm() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		Date newDate = c.getTime();
		cliente.setValidadeCartao(newDate);
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
	
	public String deleteDataBase() {
		transacaoService.deleteAll();
		clienteService.deleteAll();
		addMensageRedirect(new Flash("Base Limpa com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_LISTAR_CLIENTE;
	}
	
}
