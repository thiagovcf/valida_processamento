package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Cliente;
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
	
	private Iterable<Cliente> clientes = new ArrayList<>();
	
	private Iterable	<Transacao> transacoes = new ArrayList<>();
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String pesquisaCliente;
	
	
	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UtilService utilService;

	
	public void init() {
		preencheClienteAtivo();
	}
	
	public void preencheClienteAtivo(){
		clientes =  clienteService.listarClientes();
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
		if (transacao != null && transacao.getId() != 0) {
			transacao = transacaoService.findById(transacao.getId());
		}
	}
	
	public String salvar() throws IOException, ParseException{
		preencherDataTransacaoForm();
		calcularSaldo();
		transacaoService.save(transacao); 
		clienteService.save(cliente);
		
		addMensageRedirect(new Flash("Venda realizada com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_VENDA;
	}
	
	private void calcularSaldo() {
		if(transacao.getCliente().getId() !=null) {
			cliente = clienteService.findById(transacao.getCliente().getId());
			transacao.setCliente(cliente);
		}
		if(transacao.getCliente().getSaldoCliente()>transacao.getValor()) {
			cliente.setSaldoCliente(transacao.getCliente().getSaldoCliente() - transacao.getValor());
		}
		
	}

	public void pesquisar(){
		
		if(cliente.getId()==0 || cliente == null){
			transacoes = transacaoService.findAll();
		}else {
			transacoes = transacaoService.findAllById(cliente.getId());
		}
	}
	private void preencherDataTransacaoForm() {
		Calendar c = Calendar.getInstance();
		Date newDate = c.getTime();
		transacao.setDataTransacao(newDate);
	}
}
