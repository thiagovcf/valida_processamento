package br.com.marketpay.validaapp.service;

import org.springframework.stereotype.Service;

import com.sshtools.j2ssh.SshClient;

import br.com.marketpay.validaapp.util.ftp.FTPUtil;

@Service
public class UtilServiceImpl implements UtilService{

	@Override
	public Boolean getStatusConexao(String porta, String host, String usuario, String senha) {
		SshClient ssh = null;
		ssh = new SshClient();
		return FTPUtil.isConnectedFtp(ssh, porta, host, usuario, senha);
	}

}
