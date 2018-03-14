package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.service.EmissorService;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("request")
public class EmissorBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Emissor emissor = new Emissor();
	
	private List<Emissor> emissores;
	
	private List<Emissor> emissorAtivo;
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String campoPesquisa;
	
	
	@Autowired
	private EmissorService emissorService;
	
	
	public void init() {
		preencheEmissorAtivo();
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
		if (emissor != null && emissor.getId() != 0) {
			emissor = emissorService.findById(new Long(emissor.getId()));
		}
	}
	
	public String salvar() throws IOException{
		emissorService.save(emissor); 
		
		addMensageRedirect(new Flash("Emissor salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_EMISSOR;
	}
	
	public void pesquisar(){
		emissores = emissorService.listarPorNome(campoPesquisa);
	}
	
	public void preencheEmissorAtivo(){
		emissorAtivo =  emissorService.listarEmissorAtivo(Emissor.STATUS_ATIVA);
	}
	
	public Emissor getEmissorSessao() {
		return emissor;
	}
	
}
