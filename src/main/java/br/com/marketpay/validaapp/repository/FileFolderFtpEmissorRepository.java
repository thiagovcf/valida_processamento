package br.com.marketpay.validaapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
import br.com.marketpay.validaapp.entity.FolderFpt;

public interface FileFolderFtpEmissorRepository extends CrudRepository<FileFolderFtpEmissor, Long> {

	FileFolderFtpEmissor findByFolder(FolderFpt pasta);

}
