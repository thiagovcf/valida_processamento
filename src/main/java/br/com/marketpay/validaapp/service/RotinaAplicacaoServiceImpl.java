package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.repository.RotinaAplicacaoRepository;

@Service
public class RotinaAplicacaoServiceImpl implements RotinaAplicacaoService {

	@Autowired
	private RotinaAplicacaoRepository rotinaAplicacaoRepository;

}
