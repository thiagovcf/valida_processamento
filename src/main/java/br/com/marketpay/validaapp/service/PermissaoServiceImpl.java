package br.com.marketpay.validaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Permissao;
import br.com.marketpay.validaapp.repository.PermissaoRepository;

@Service
public class PermissaoServiceImpl implements PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public List<Permissao> findByGruposIn(Grupo grupo) {
		return permissaoRepository.findByGruposIn(grupo);
	}

	@Override
	public List<Permissao> findAll() {
		return permissaoRepository.findAll();
	}

	@Override
	public Permissao findByNome(String nome) {
		return permissaoRepository.findByNome(nome);
	}

}
