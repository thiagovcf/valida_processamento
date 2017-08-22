package br.com.marketpay.validaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FolderFpt;
import br.com.marketpay.validaapp.repository.FolderFtpRepository;

@Service
public class FolderFtpServiceImpl implements FolderFtpService {

	@Autowired
	private FolderFtpRepository folderFtpRepository;
	
	@Override
	public FolderFpt findById(Long id) {
		return folderFtpRepository.findById(id);
	}

	@Override
	public List<FolderFpt> listarPorNome( String pesquisaEmissor,String pesquisaNomePasta) {
		return null;
	}

	@Override
	public void save(FolderFpt folderFpt) {
		folderFtpRepository.save(folderFpt);
	}

	@Override
	public List<FolderFpt> findByEmissor(Emissor emissor) {
		return folderFtpRepository.findAllByEmissor(emissor);
	}

	@Override
	public List<FolderFpt> findByNomeAndEmissorIn(String pesquisaNomePasta, List<Emissor> listaEmissorAtivo) {
		return folderFtpRepository.findAllByNomeIgnoreCaseContainingAndEmissorIn(pesquisaNomePasta,listaEmissorAtivo);
	}

	@Override
	public List<FolderFpt> findAllByNomeIgnoreCaseContainingAndEmissor(String pesquisaNomePasta, Emissor emissor) {
		return folderFtpRepository.findAllByNomeIgnoreCaseContainingAndEmissor(pesquisaNomePasta,emissor);
	}

	@Override
	public List<FolderFpt> findAllByEmissorIn(List<Emissor> listaEmissorAtivo) {
		return folderFtpRepository.findAllByEmissorIn(listaEmissorAtivo);
	}

}
