package br.com.marketpay.validaapp.service;

import br.com.marketpay.validaapp.entity.MassaDados;

public interface MassaDadosService {

	Iterable<MassaDados> getMassaDadosInativa();
	
	void atualizarDados();
}
