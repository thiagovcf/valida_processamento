package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Transacao;
import br.com.marketpay.validaapp.repository.TransacaoRepository;

@Service
public class TransacaoServiceImpl implements TransacaoService{

	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Override
	public Transacao findById(Long id) {
		return transacaoRepository.findById(id);
	}

	@Override
	public void save(Transacao transacao) {
		transacaoRepository.save(transacao);
	}

	@Override
	public Iterable<Transacao>  listarTransacoes(String campoPesquisa) {
		return transacaoRepository.findAll();
	}

	
}
