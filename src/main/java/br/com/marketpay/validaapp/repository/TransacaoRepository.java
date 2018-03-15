package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Transacao;


public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

	Transacao findById(Long id);

	List<Transacao> findAllById(Long id);
	
	


}