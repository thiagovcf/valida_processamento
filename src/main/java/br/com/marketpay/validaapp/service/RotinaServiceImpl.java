package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.repository.RotinaRepository;

@Service
public class RotinaServiceImpl implements RotinaService {

	@Autowired
	private RotinaRepository rotinaRepository;

}
