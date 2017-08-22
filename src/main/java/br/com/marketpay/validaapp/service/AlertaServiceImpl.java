package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.repository.AlertaRepository;

@Service
public class AlertaServiceImpl implements AlertaService {

	@Autowired
	private AlertaRepository alertaRepository;

}
