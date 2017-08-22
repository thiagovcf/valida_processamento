package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Configuracao;
import br.com.marketpay.validaapp.service.AplicacaoService;
import br.com.marketpay.validaapp.service.ConfiguracaoService;
import br.com.marketpay.validaapp.service.MassaDadosService;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("request")
public class ConfiguracaoBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;

	private Configuracao configuracao;
	
	private boolean visualizar;
	
	private boolean alterar;
	
	@Autowired
	private ConfiguracaoService configuracaoService;
	
	@Autowired
	private MassaDadosService massDadosService;
	
	@Autowired
	private AplicacaoService aplicacaoService;
	
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
		configuracao = configuracaoService.getConfiguracaoPadrao();
		if(configuracao == null){
			configuracao = new Configuracao();
		}
	}
	
	public void teste(){
		massDadosService.atualizarDados();
	}
	
	public void teste2() throws IOException{
		aplicacaoService.initRecuperaTomcatsPRD();
	}
	
	public String salvar() throws IOException{
		configuracaoService.save(configuracao); 
		
		addMensageRedirect(new Flash("Configuração salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_CONFIGURACAO;
	}
	
}
