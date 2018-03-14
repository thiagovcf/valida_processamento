package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Cliente;
import br.com.marketpay.validaapp.repository.ClienteRepository;

@Service
public class TransacaoServiceImpl implements ClienteService{

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Iterable<Cliente>  listarClientes(String campoPesquisa) {
		return clienteRepository.findAll();
	}

	
}
