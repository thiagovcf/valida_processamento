package br.com.marketpay.validaapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Permissao;
import br.com.marketpay.validaapp.entity.Usuario;
import br.com.marketpay.validaapp.repository.GrupoRespository;
import br.com.marketpay.validaapp.repository.PermissaoRepository;

@Service
public class GrupoServiceImpl implements GrupoService{
	
	@Autowired
	private GrupoRespository grupoRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public List<Grupo> findByUsuariosIn(Usuario usuario){
		return this.grupoRepository.findByUsuariosIn(usuario);
	}
	
	public List<Grupo> listaGrupos(){
		return this.grupoRepository.findAll();
	}

	@Override
	public void save(Grupo grupo) {
		List<Permissao> permissoes = grupo.getPermissoes();		

		grupo.setPermissoes(null);
		
		List<Permissao> novasPermissoes = new ArrayList<Permissao>();
		
		for (Permissao permissao : permissoes) {
			novasPermissoes.add(permissaoRepository.findByNome(permissao.getNome()));
		}
		
		grupo.setPermissoes(novasPermissoes);		
		
		this.grupoRepository.save(grupo);
	}

	@Override
	public List<Grupo> listarPorNome(String nome) {
		return grupoRepository.findByNomeContaining(nome);
	}

	@Override
	public Grupo findById(Long id) {
		return grupoRepository.findById(id);
	}

	@Override
	public Grupo grupoPorNome(String nome) {
		return grupoRepository.findByNome(nome);
	}

}
