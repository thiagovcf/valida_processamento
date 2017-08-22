package br.com.marketpay.validaapp.service;

import java.util.List;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FolderFpt;

public interface FolderFtpService {

	FolderFpt findById(Long id);

	List<FolderFpt> listarPorNome(String pesquisaEmissor, String pesquisaNomePasta);

	void save(FolderFpt folderFpt);

	List<FolderFpt> findByEmissor(Emissor emissor);

	List<FolderFpt> findByNomeAndEmissorIn(String pesquisaNomePasta, List<Emissor> listaEmissorAtivo);

	List<FolderFpt> findAllByNomeIgnoreCaseContainingAndEmissor(String pesquisaNomePasta, Emissor emissor);

	List<FolderFpt> findAllByEmissorIn(List<Emissor> listaEmissorAtivo);

}
