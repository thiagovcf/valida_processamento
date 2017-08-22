package br.com.marketpay.validaapp.util.ftp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import br.com.marketpay.validaapp.util.funcoes.ConversorTipos;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

public class FileUtils {
	
	
	public static byte[] parseFileToByte(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		Long length = file.length();

		byte[] bytes = new byte[length.intValue()];

		Integer offset = 0;
		Integer numRead = 0;
		while(offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if(offset < bytes.length) {
			throw new IOException("LEITURA INCOMPLETA DO ARQUIVO " + file.getName());
		}

		is.close();

		return bytes;
	}
	
	
	/**
	 * Copia os bytes de um InputStream para um byte[]
	 * @author marcio
	 */
	public static byte[] parseToByte(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copyStream(in, out);
		return out.toByteArray(); 
	}
	
	
	/**
	 * Copia os bytes de um InputStream para um OutputStream
	 * @author marcio
	 */
	public static final void copyStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);
		in.close();
		out.close();
	}
		

	/**
	 * Descompacta arquivo zip
	 * @param zipFile
	 * @param dirDest
	 * @param criarDiretorios- se � para criar diretorios ou extrair todos arquivos
	 * no mesmo diretorio adicionando '(n� de copia)' para arquivos com mesmo nome 
	 * @throws IOException
	 * @author marcio
	 */
	public static void descompactarZip(File zipFile, File dirDest,
			boolean criarDiretorios) throws IOException {

		ZipFile zip = new ZipFile(zipFile);
		Enumeration entries = zip.entries();
		String pathDest = dirDest.getAbsolutePath() + File.separator;
		dirDest.mkdirs();

		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			if (entry.isDirectory()) {
				if (criarDiretorios)
					(new File(pathDest + entry.getName())).mkdir();
				continue;
			}

			File fileOut = new File(pathDest + entry.getName());
			if (criarDiretorios) {
				fileOut = new File(pathDest + entry.getName());
			} else {

				String fileName = entry.getName();
				if (fileName.lastIndexOf('/') >= 0)
					fileName = fileName
							.substring(fileName.lastIndexOf('/'));

				String ext = "";
				if (fileName.lastIndexOf('.') >= 0) {
					ext = fileName.substring(fileName.lastIndexOf('.'));
					fileName = fileName.substring(0, fileName
							.lastIndexOf('.'));
				}

				fileOut = new File(pathDest + fileName + ext);
				int i = 0;
				while (fileOut.exists()) {
					fileOut = new File(pathDest + fileName + "(" + i + ")"
							+ ext);
					i++;
				}
			}

			copyStream(zip.getInputStream(entry), new BufferedOutputStream(
					new FileOutputStream(fileOut)));
		}
		zip.close();
	}


	public static String getExtensao(File arquivo) {
		return getExtensao(arquivo.getName());
	}
	
	public static String getExtensao(String nomeArquivo) {
		return nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1);
	}

	public static boolean uploadArquivoSFTP(String servidor, Integer porta, String usuario, String senha, String path, File arquivo, Class<?> clazz) throws Exception {
		validateConfiguracoesSFTP(servidor, usuario, senha, path);
		FileInputStream fis = null;
		SshClient ssh = null;
		boolean isOk = false;
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
				SftpClient sftpClient = ssh.openSftpClient();
				
				sftpClient.mkdirs(path);
				sftpClient.cd(path);
				try {
					fis = new FileInputStream(arquivo);
					sftpClient.put(fis, arquivo.getName());
					isOk = true;
				} finally {
					fis.close();
				}
			}
		} finally {
			if ( (ssh != null) && ssh.isConnected() ) {
				ssh.disconnect();
			}
		}
		return isOk;
	}
	
	public static boolean uploadArquivoSFTPComErro(String servidor, Integer porta, String usuario, String senha, String path, File arquivo, Class<?> clazz) throws Exception {
		validateConfiguracoesSFTP(servidor, usuario, senha, path);
		FileInputStream fis = null;
		SshClient ssh = null;
		boolean isOk = false;
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
				SftpClient sftpClient = ssh.openSftpClient();
				
				sftpClient.mkdirs(path);
				sftpClient.cd(path);
				try {
					fis = new FileInputStream(arquivo);
					sftpClient.put(fis, arquivo.getName());
					isOk = true;
				} finally {
					fis.close();
				}
			} else {
				throw new Exception("Erro no envio do arquivo para o FTP "+arquivo.getAbsolutePath());
			}
		} finally {
			if ( (ssh != null) && ssh.isConnected() ) {
				ssh.disconnect();
			}
		}
		return isOk;
	}
	
	private static void validateConfiguracoesSFTP(String servidor, String usuario, String senha, String path) throws Exception {
		if (!ConversorTipos.check(servidor)) {
			throw new Exception("Configuracoes de SFTP nao informadas corretamente: Favor validar campo SERVIDOR");
		}
		if (!ConversorTipos.check(usuario)) {
			throw new Exception("Configuracoes de SFTP nao informadas corretamente: Favor validar campo USUARIO");
		}
		if (!ConversorTipos.check(senha)) {
			throw new Exception("Configuracoes de SFTP nao informadas corretamente: Favor validar campo SENHA");
		}
		if (!ConversorTipos.check(path)) {
			throw new Exception("Configuracoes de SFTP nao informadas corretamente: Favor validar campo PATH");
		}
	}


	public static List<String> buscarArquivos(String servidor, Integer porta, String usuario, String senha, String pathLocal,String path,String nomeArquivo) throws Exception{
		List<String> arquivos = new ArrayList<>();
		validateConfiguracoesSFTP(servidor, usuario, senha, path);
		FileInputStream fis = null;
		SshClient ssh = null;
		boolean isOk = false;
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
				List<SftpFile> l = ftp.ls(path);
				for (SftpFile f : l) {
					if (f.isFile() && f.getFilename().startsWith(nomeArquivo)) {
						String fileName = pathLocal + f.getFilename();
						ftp.get(f.getAbsolutePath(), fileName);
						arquivos.add(fileName);
					}
				}
			} else {
				throw new Exception("Erro ao ler arquivos do FTP: autenticacao invalida.");
			}
		} finally {
			if ( (ssh != null) && ssh.isConnected() ) {
				ssh.disconnect();
			}
		}
		return arquivos;
	}
}
