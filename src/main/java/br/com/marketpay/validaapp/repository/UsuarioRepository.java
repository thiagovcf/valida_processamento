package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findByLogin(String nome);
	
//	@Secured("ROLE_ADMIN")
	List<Usuario> findAll();

	List<Usuario> findByNomeContaining(String nome);

}