package br.com.marketpay.validaapp.service;

import br.com.marketpay.validaapp.entity.Configuracao;

public interface ConfiguracaoService {

	Configuracao getConfiguracaoPadrao();
	
	void save(Configuracao configuracao);

}
