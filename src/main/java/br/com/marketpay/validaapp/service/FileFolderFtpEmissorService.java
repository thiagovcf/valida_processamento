package br.com.marketpay.validaapp.service;

import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
import br.com.marketpay.validaapp.entity.FolderFpt;

public interface FileFolderFtpEmissorService {

	void save(FileFolderFtpEmissor fileFolderFtpEmissor);

	FileFolderFtpEmissor findById(Long id);

	FileFolderFtpEmissor findByFolder(FolderFpt folderFtp);


}
