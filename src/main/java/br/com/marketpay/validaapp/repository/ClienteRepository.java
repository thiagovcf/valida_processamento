package br.com.marketpay.validaapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Cliente findById(Long id);
	
	


}