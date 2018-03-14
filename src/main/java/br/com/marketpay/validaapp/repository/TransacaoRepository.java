package br.com.marketpay.validaapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Transacao;


public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

	Transacao findById(Long id);
	
	


}