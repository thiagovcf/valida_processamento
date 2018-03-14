package br.com.marketpay.validaapp.service;

import br.com.marketpay.validaapp.entity.Cliente;

public interface ClienteService {

	Cliente findById(Long id);

	void save(Cliente cliente);

	Iterable<Cliente> listarClientes(String campoPesquisa);

	
}

