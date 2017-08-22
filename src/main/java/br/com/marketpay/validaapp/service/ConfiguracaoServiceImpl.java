package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Configuracao;
import br.com.marketpay.validaapp.repository.ConfiguracaoRepository;

@Service
public class ConfiguracaoServiceImpl implements ConfiguracaoService{

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;

	@Override
	public void save(Configuracao configuracao) {
		configuracaoRepository.save(configuracao);
	}

	@Override
	public Configuracao getConfiguracaoPadrao() {
		return configuracaoRepository.findOne(Configuracao.CONFIGURACAO_PADRAO);
	}
	
}
