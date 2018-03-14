package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.component.password.Password;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.ProcessAlterarSenha;
import br.com.marketpay.validaapp.entity.Usuario;
import br.com.marketpay.validaapp.security.PasswordGenerator;
import br.com.marketpay.validaapp.service.ProcessAlterarSenhaService;
import br.com.marketpay.validaapp.service.UsuarioService;
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
public class UsuarioBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> usuarios;
	
	private boolean visualizar;
	
	private boolean alterar;
	
	private String campoPesquisa;
	
	@Getter
	@Setter
	private String tokenAlterarSenha;
	
	@Autowired
	private ProcessAlterarSenhaService processAlterarSenhaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public void preencherAlterar(){
		alterar = true;
		visualizar = false;
		preecherObjeto();
	}
	
	public void preencherVisualizar(){
		alterar = false;
		visualizar = true;
		preecherObjeto();
		RequestContext.getCurrentInstance().execute("$('.bootstrap-duallistbox-container').find('*').prop('disabled', true);");
	}
	
	private void preecherObjeto(){
		if (usuario != null && usuario.getId() != 0) {
			usuario = usuarioService.findById(new Long(usuario.getId()));
			preencheGrupos();
		}
	}
	
	public String salvar() throws IOException{
		String paramGrupos = (String) getRequestParam("hiddenGruposUsuario");

		usuario.setGrupos(null);

		usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
		
		if(paramGrupos != null && !paramGrupos.isEmpty()){
			String[] grupos = paramGrupos.split(",");
			
			for (String idGrupo : grupos) {
				usuario.adicionarGrupo(new Grupo(new Long(idGrupo)));
			}
		}
		
		usuarioService.save(usuario, FacesContext.getCurrentInstance()); 
		
		addMensageRedirect(new Flash("Usuário salvo com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_USUARIO;
	}
	
	public void pesquisar(){
		usuarios = usuarioService.listarPorNome(campoPesquisa);
	}
	
	public void preencheGrupos() {
		List<Long> gruposUsuario = new ArrayList<Long>();
		
		for (Grupo group : usuario.getGrupos()) {
			RequestContext.getCurrentInstance().execute("$('#select_" + group.getId() + "').attr('selected', 'selected')");
			gruposUsuario.add(group.getId());
		}
		RequestContext.getCurrentInstance().execute("initBootstrapDualListbox()");
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
	
	public void verificaDiponibilidadeGrupo() {
		Usuario usuarioPesquisa = usuarioService.usuarioPorLogin(usuario.getLogin());
		
		if(usuarioPesquisa != null){
			
			if(usuarioPesquisa.getId() != usuario.getId()){
				addMessageValidacao("formIncluirAlterarUsuario", "login", "Já existe um usuário com este login, favor alterar");
			}
		}
	}
	
}
