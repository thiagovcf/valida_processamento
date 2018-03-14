package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FolderFpt;

public interface FolderFtpRepository extends CrudRepository<FolderFpt, Long> {

	FolderFpt findById(Long id);

	List<FolderFpt> findAllByEmissor(Emissor emissor);

	List<FolderFpt> findAllByNome(String pesquisaNomePasta);

	List<FolderFpt> findAllByNomeIgnoreCaseContainingAndEmissorIn(String pesquisaNomePasta, List<Emissor> listaEmissorAtivo);

	List<FolderFpt> findAllByNomeIgnoreCaseContainingAndEmissor(String pesquisaNomePasta, Emissor emissor);

	List<FolderFpt> findAllByEmissorIn(List<Emissor> listaEmissorAtivo);
	
}
