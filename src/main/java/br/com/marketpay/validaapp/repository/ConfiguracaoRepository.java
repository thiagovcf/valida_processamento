package br.com.marketpay.validaapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Configuracao;

public interface ConfiguracaoRepository extends CrudRepository<Configuracao, Long> {

	Configuracao findById(Long id);
	
}
