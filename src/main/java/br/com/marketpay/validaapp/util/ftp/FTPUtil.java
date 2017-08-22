package br.com.marketpay.validaapp.util.ftp;

import static com.sshtools.j2ssh.authentication.AuthenticationProtocolState.COMPLETE;
import static com.sshtools.j2ssh.authentication.AuthenticationProtocolState.FAILED;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

public class FTPUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> buscarArquivos(String servidor, Integer porta, String usuario, String senha, String pathRemoto, String pathLocal, String pathImportado) throws Exception {
		SshClient ssh = null;
		Map<String, String> files = new TreeMap<String, String>();
		try {
			ssh = new SshClient();
			
			if (porta != null) {
				ssh.connect(servidor, porta, new IgnoreHostKeyVerification());
			} else {
				ssh.connect(servidor, new IgnoreHostKeyVerification());
			}

			PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
			auth.setUsername(usuario);
			auth.setPassword(senha);
			int status = ssh.authenticate(auth);
			
			if (status == AuthenticationProtocolState.COMPLETE) {
				SftpClient ftp = ssh.openSftpClient();
				preExecute(ftp, pathRemoto + pathImportado);
				List<SftpFile> l = ftp.ls(pathRemoto);
				for (SftpFile f : l) {
					if (f.isFile()) {
						ftp.get(f.getAbsolutePath(), pathLocal + File.separator + f.getFilename());
						ftp.put(pathLocal + File.separator + f.getFilename(), pathRemoto + pathImportado);
						ftp.rm(f.getAbsolutePath());
						files.put(f.getFilename(), pathLocal + File.separator + f.getFilename());
					}
				}
			}
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			if ( (ssh != null) && ssh.isConnected() ) {
				ssh.disconnect();
			}
		}
		
		return files;
	}
	
	public static SftpClient getSshConection(String host,String porta, String usuario, String senha) throws Exception{
		SshClient ssh = null;
		ssh = new SshClient();
		
		return conectarSFTP(ssh, porta, host, usuario, senha);
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> buscarArquivoNoFtpPeloPath(String pathRemoto, String ip, String porta, String login, String senha) throws Exception{
		SftpClient ftp = null;
		try {
			ftp = getSshConection(ip,porta,login, senha);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		List<SftpFile> l = null;
		
		try {
			l = ftp.ls(pathRemoto);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		
		List<String> fileName = new ArrayList<String>();
		for (SftpFile f : l) {
			if (f.isFile()) {
				fileName.add(f.getFilename());
			}
		}
		return fileName;
	}
	
	private static void preExecute(SftpClient ftp, String pathImportado) throws Exception {
		try {
			ftp.mkdir(pathImportado);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}
	
	private static SftpClient conectarSFTP(SshClient ssh,String porta, String host, String usuario, String senha) throws Exception {

		SftpClient client = null;
		try {
			if (StringUtils.isNotBlank(porta)) {
				ssh.connect(host, Integer.valueOf(porta), new IgnoreHostKeyVerification());
			} else {
				ssh.connect(host, new IgnoreHostKeyVerification());
			}
			PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
			auth.setUsername(usuario);
			auth.setPassword(senha);
			
			int status = ssh.authenticate(auth);
			
			switch (status) {
			case COMPLETE:
				client = ssh.openSftpClient();
				break;
			case FAILED:
				throw new Exception("Erro ao conectar");
			}
		} catch (IOException e) {
			throw new Exception("error.inicilizacao");
		} 
		return client;
	}
	public static boolean isConnectedFtp(SshClient ssh,String porta, String host, String usuario, String senha){

		try {
			conectarSFTP(ssh, porta, host, usuario, senha);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
		
	public static void moverArquivo(String servidor, Integer porta, String usuario, String senha, String nomeArquivo, String origemArquivoRemoto, String destinoArquivoRemoto, String diretorioLocal) throws Exception {
		
		SshClient ssh = null;
//		Map<String, String> files = new TreeMap<String, String>();
		try {
			ssh = new SshClient();
			
			if (porta != null) {
				ssh.connect(servidor, porta, new IgnoreHostKeyVerification());
			} else {
				ssh.connect(servidor, new IgnoreHostKeyVerification());
			}

			PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
			auth.setUsername(usuario);
			auth.setPassword(senha);
			int status = ssh.authenticate(auth);
			
			if (status == AuthenticationProtocolState.COMPLETE) {
				SftpClient ftp = ssh.openSftpClient();
				preExecute(ftp, destinoArquivoRemoto);
				ftp.put(diretorioLocal + File.separator + nomeArquivo, destinoArquivoRemoto); 
				ftp.rm(origemArquivoRemoto + File.separator + nomeArquivo);
			}
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			if ( (ssh != null) && ssh.isConnected() ) {
				ssh.disconnect();
			}
		}
	}
}