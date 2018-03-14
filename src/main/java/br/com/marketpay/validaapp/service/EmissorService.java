package br.com.marketpay.validaapp.service;

import java.util.List;

import br.com.marketpay.validaapp.entity.Emissor;

public interface EmissorService {

	Iterable<Emissor> getEmissores();
	
	void save(Emissor emissor);

	Emissor findById(Long id);

	List<Emissor> listarPorNome(String campoPesquisa);

	List<Emissor> listarEmissorAtivo(String status);
	
}
