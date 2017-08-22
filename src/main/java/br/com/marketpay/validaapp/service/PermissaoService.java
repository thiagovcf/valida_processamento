package br.com.marketpay.validaapp.service;

import java.util.List;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Permissao;

public interface PermissaoService {
	
	public Permissao findByNome(String nome);
	
	public List<Permissao> findByGruposIn(Grupo grupo);
	
	public List<Permissao> findAll();
	
}
