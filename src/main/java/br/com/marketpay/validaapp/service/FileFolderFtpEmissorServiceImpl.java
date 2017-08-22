package br.com.marketpay.validaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
import br.com.marketpay.validaapp.entity.FolderFpt;
import br.com.marketpay.validaapp.repository.FileFolderFtpEmissorRepository;

@Service
public class FileFolderFtpEmissorServiceImpl implements FileFolderFtpEmissorService {

	@Autowired
	FileFolderFtpEmissorRepository fileFolderEmissorRepository;
	
	
	@Override
	public FileFolderFtpEmissor findById(Long  id) {
		return fileFolderEmissorRepository.findOne(id);
	}


	@Override
	public void save(FileFolderFtpEmissor fileFolderFtpEmissor) {
		fileFolderEmissorRepository.save(fileFolderFtpEmissor);
	}


	@Override
	public FileFolderFtpEmissor findByFolder(FolderFpt folderFtp) {
		return fileFolderEmissorRepository.findByFolder(folderFtp);
	}


}
