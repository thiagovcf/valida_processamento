package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Permissao;
import br.com.marketpay.validaapp.service.GrupoService;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("request")
public class GrupoBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Grupo grupo = new Grupo();
	
	private List<Grupo> grupos;
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String campoPesquisa;
	
	@Autowired
	private GrupoService grupoService;
	
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
		if (grupo != null && grupo.getId() != 0) {
			grupo = grupoService.findById(new Long(grupo.getId()));
			for (Permissao permissao : grupo.getPermissoes()) {
				checkedRadio("radio_" + permissao.getNome().toLowerCase());
			}
		}
	}
	
	public String salvar() throws IOException{
		grupo.setPermissoes(recuperaPermissao());
		
		grupoService.save(grupo);
		
		addMensageRedirect(new Flash("Grupo salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_GRUPO;
	}
	
	public void pesquisar(){
		grupos = grupoService.listarPorNome(campoPesquisa);
	}
	
	public List<Grupo> getListarGrupos(){
		return grupoService.listarPorNome("");
	}
	
	public List<Permissao> recuperaPermissao(){
		List<Permissao> permissoes = new ArrayList<Permissao>();
		
		permissoes.add(new Permissao((String) getRequestParam("grupo")));
		permissoes.add(new Permissao((String) getRequestParam("usuario")));
		
		return permissoes;
	}
	
	public void verificaDiponibilidadeGrupo() {
		Grupo grupoPesquisa = grupoService.grupoPorNome(grupo.getNome());
		
		if(grupoPesquisa != null){
			
			if(grupoPesquisa.getId() != grupo.getId()){
				addMessageValidacao("formIncluirAlterarGrupo", "nome", "Já existe um grupo com este nome, favor alterar");
			}
		}
	}
	
}
