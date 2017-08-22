package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Usuario;

public interface GrupoRespository extends CrudRepository<Grupo, Long> {
	
	List<Grupo> findAll();
	
	List<Grupo> findByUsuariosIn(Usuario usuario);

	Grupo findByNome(String nome);
	
	List<Grupo> findByNomeContaining(String nome);

	Grupo findById(Long id);
}
