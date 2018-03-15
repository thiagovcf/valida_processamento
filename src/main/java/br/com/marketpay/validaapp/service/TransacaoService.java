package br.com.marketpay.validaapp.service;

import java.util.List;

import br.com.marketpay.validaapp.entity.Transacao;

public interface TransacaoService {

	Transacao findById(Long id);

	void save(Transacao transacao);

	Iterable<Transacao> listarTransacoes(String campoPesquisa);

	List<Transacao> findAllById(Long id);

	Iterable<Transacao> findAll();


	
}

