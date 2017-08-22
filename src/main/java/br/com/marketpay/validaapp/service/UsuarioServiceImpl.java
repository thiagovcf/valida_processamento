package br.com.marketpay.validaapp.service;

import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Usuario;
import br.com.marketpay.validaapp.repository.UsuarioRepository;
import br.com.marketpay.validaapp.util.components.transacao.Transacional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProcessAlterarSenhaService processAlterarSenhaService;

	public Usuario findByLogin(String login) {
		return this.usuarioRepository.findByLogin(login);
	}
	
	public List<Usuario> listaUsuarios(){
		return this.usuarioRepository.findAll();
	}
	
	public Usuario findById(Long idUsuario){
		return this.usuarioRepository.findOne(idUsuario);
	}

	@Transacional
	public void save(Usuario usuario, FacesContext facesContext) {
		this.usuarioRepository.save(usuario);
		
		processAlterarSenhaService.resetSenha(usuario, FacesContext.getCurrentInstance());
	}

	@Override
	public List<Usuario> listarPorNome(String nome) {
		return usuarioRepository.findByNomeContaining(nome);
	}

	@Override
	public Usuario usuarioPorLogin(String nome) {
		return usuarioRepository.findByLogin(nome);
	}
}
