package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.repository.ValidacaoRepository;

@Service
public class ValidacaoServiceImpl implements ValidacaoService{

	@Autowired
	private ValidacaoRepository validacaoRepository ;

	
}
