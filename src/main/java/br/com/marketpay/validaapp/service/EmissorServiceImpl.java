package br.com.marketpay.validaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.repository.EmissorRepository;
import br.com.marketpay.validaapp.util.components.transacao.Transacional;

@Service
public class EmissorServiceImpl implements EmissorService {

	@Autowired
	private EmissorRepository emissorRepository;
	

	@Override
	public Iterable<Emissor> getEmissores() {
		return emissorRepository.findAll();
	}
	
	@Transacional
	public void save(Emissor emissor) {
		this.emissorRepository.save(emissor);
	}

	@Override
	public Emissor findById(Long id) {
		return emissorRepository.findOne(id);
	}

	@Override
	public List<Emissor> listarPorNome(String campoPesquisa) {
		return this.emissorRepository.findAllByNomeContaining(campoPesquisa);
	}

	@Override
	public List<Emissor> listarEmissorAtivo(String status) {
		return this.emissorRepository.findAllByStatus(status);
	}
}
