package br.com.marketpay.validaapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Aplicacao;
import br.com.marketpay.validaapp.entity.Configuracao;
import br.com.marketpay.validaapp.entity.MassaDados;
import br.com.marketpay.validaapp.persistence.dao.MassaDadosDAO;
import br.com.marketpay.validaapp.repository.MassaDadosRepository;

@Service
public class MassaDadosServiceImpl implements MassaDadosService {

	@Autowired
	private MassaDadosRepository massaDadosRepository;
	
	@Autowired
	private ConfiguracaoService configuracaoService;
	
	@Autowired
	private AplicacaoService aplicacaoService;
	
	public void atualizarDados(){
		Iterable<MassaDados> massaDeDados= massaDadosRepository.findAllByStatus(MassaDados.STATUS_ATIVA);
		
		massaDeDados.forEach(massa -> atualizarStatusAplicacao(massa));
		
		Iterable<Aplicacao> aplicacoes = aplicacaoService.getAplicacoes(); 
		
		aplicacoes.forEach(app -> {
			massaDadosRepository.save(getMassaDados(app));
		});
	}
	
	public List<MassaDados> getMassaDados(Aplicacao aplicacao){
		// metodo limpar/cancelar/inativar todas as massas de dados Ativas
		
		Configuracao configuracao = configuracaoService.getConfiguracaoPadrao();

		MassaDadosDAO massaDadosDAO = new MassaDadosDAO();
		List<MassaDados> massaDados = new ArrayList<MassaDados>();
		
		massaDados.add(massaDadosDAO.findIdContaByAplicacao(aplicacao.getNome(), configuracao.getUrlBanco(),configuracao.getLoginBanco(), configuracao.getSenhaBanco()));
		massaDados.add(massaDadosDAO.findPagamentoByAplicacao(aplicacao.getNome(), configuracao.getUrlBanco(),configuracao.getLoginBanco(), configuracao.getSenhaBanco()));
		massaDados.add(massaDadosDAO.findVendaByAplicacao(aplicacao.getNome(), configuracao.getUrlBanco(),configuracao.getLoginBanco(), configuracao.getSenhaBanco()));
		
		return massaDados;
	}

	public void atualizarStatusAplicacao(MassaDados massaDados){
		massaDados.setStatus(MassaDados.STATUS_INATIVA);
		massaDadosRepository.save(massaDados);
	}

	@Override
	public Iterable<MassaDados> getMassaDadosInativa() {
		return massaDadosRepository.findAllByStatus(MassaDados.STATUS_ATIVA);
	}
	

}
