package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Permissao;

public interface PermissaoRepository extends CrudRepository<Permissao, Long> {
	
	public List<Permissao> findByGruposIn(Grupo grupo);
	
	public List<Permissao> findAll();

	public Permissao findByNome(String nome);
	
}
