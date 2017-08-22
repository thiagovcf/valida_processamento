package br.com.marketpay.validaapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.MassaDados;

public interface MassaDadosRepository extends CrudRepository<MassaDados, Long> {

	Iterable<MassaDados> findAllByStatus(String status);
	
}
