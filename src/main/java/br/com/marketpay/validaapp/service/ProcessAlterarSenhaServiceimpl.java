package br.com.marketpay.validaapp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.ProcessAlterarSenha;
import br.com.marketpay.validaapp.entity.Usuario;
import br.com.marketpay.validaapp.repository.ProcessAlterarSenhaRepository;
import br.com.marketpay.validaapp.security.PasswordGenerator;
import br.com.marketpay.validaapp.util.components.mail.ThreadEmail;

@Service
public class ProcessAlterarSenhaServiceimpl implements ProcessAlterarSenhaService{
	
	@Autowired
	private ProcessAlterarSenhaRepository processAlterarSenhaRepository;
	
	@Autowired
    ThreadPoolTaskExecutor executor;

	public ProcessAlterarSenha getProcessUsuario(Usuario usuario) {
		return processAlterarSenhaRepository.findByUsuario(usuario);
	}

	public ProcessAlterarSenha getProcessToken(String token) {
		return processAlterarSenhaRepository.findByToken(token);
	}

	public void resetSenha(Usuario usuario, FacesContext facesContext) {
		ProcessAlterarSenha processExist = getProcessUsuario(usuario);
		
		if(processExist != null){
			processAlterarSenhaRepository.delete(processExist);
		}
		
		ProcessAlterarSenha process = new ProcessAlterarSenha(PasswordGenerator.gerarToken(), usuario);
		
		processAlterarSenhaRepository.save(process);
		
		ExternalContext Externalcontext = FacesContext.getCurrentInstance().getExternalContext();  
		HttpServletRequest request = (HttpServletRequest)Externalcontext.getRequest();  

		String link = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() +  request.getContextPath() + File.separator + 
				"alterarSenha?token=" + process.getToken();			
		
		String cabecalho1 = "Oi, " + usuario.getNome();
		String cabecalho2 = "Este é um email enviado automaticamente ao resetar senha";
		
		List<String> mensagens = new ArrayList<String>();
		
		mensagens.add("Acesse o sistema e altere sua senha.");
		
		String mensagemLink = "Alterar senha agora";
		
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		List<String> emails = new ArrayList<String>();
		emails.add(usuario.getEmail());
			
		executor.execute(new ThreadEmail(context.getRealPath("/"), link, emails, cabecalho1, cabecalho2, mensagens, mensagemLink));
		
	}
}
