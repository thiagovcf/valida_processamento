package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.repository.MetodoRepository;

@Service
public class MetodoServiceImpl implements MetodoService {

	@Autowired
	private MetodoRepository metodoRepository;

}
