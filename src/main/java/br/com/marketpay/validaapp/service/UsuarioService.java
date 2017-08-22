package br.com.marketpay.validaapp.service;

import java.util.List;

import javax.faces.context.FacesContext;

import br.com.marketpay.validaapp.entity.Usuario;

public interface UsuarioService {

	Usuario findByLogin(String login);

	List<Usuario> listaUsuarios();

	Usuario findById(Long idUsuario);

	void save(Usuario usuario, FacesContext facesContext);

	List<Usuario> listarPorNome(String nome);

	Usuario usuarioPorLogin(String nome);
}
