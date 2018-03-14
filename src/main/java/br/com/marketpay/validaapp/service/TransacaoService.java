package br.com.marketpay.validaapp.service;

import br.com.marketpay.validaapp.entity.Transacao;

public interface TransacaoService {

	Transacao findById(Long id);

	void save(Transacao transacao);

	Iterable<Transacao> listarTransacoes(String campoPesquisa);

	
}

