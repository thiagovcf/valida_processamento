/**
 * 
 */
package br.com.marketpay.validaapp.service;

import java.util.List;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Usuario;

public interface GrupoService {

	List<Grupo> findByUsuariosIn(Usuario usuario);
	
	List<Grupo> listaGrupos();
	
	void save(Grupo grupo);
	
	Grupo grupoPorNome(String nome);
	
	List<Grupo> listarPorNome(String nome);
	
	Grupo findById(Long id);
}
