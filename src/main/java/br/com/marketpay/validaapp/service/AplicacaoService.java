package br.com.marketpay.validaapp.service;

import java.io.IOException;

import br.com.marketpay.validaapp.entity.Aplicacao;

public interface AplicacaoService {

	Iterable<Aplicacao> getAplicacoes();
	
	void initRecuperaTomcatsPRD() throws IOException;
	
}
