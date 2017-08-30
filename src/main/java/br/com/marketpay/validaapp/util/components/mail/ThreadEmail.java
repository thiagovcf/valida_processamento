package br.com.marketpay.validaapp.util.components.mail;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.velocity.VelocityContext;

public class ThreadEmail extends Thread implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String TEMPLATE = "/pages/secure/email/email.html";
	
	private String linkAcesso;
	private String cabecalho1;
	private String cabecalho2;
	private String mensagemLink;
	private List<String> mesagens;
	private List<String> recipients;
	private String diretorio;
	
	public ThreadEmail(String diretorio, String linkAcesso, List<String> recipients, String cabecalho1, String cabecalho2, List<String> mesagens, String mensagemLink) {
		this.diretorio = diretorio;
		this.linkAcesso = linkAcesso;
		this.recipients = recipients;
		this.cabecalho1 = cabecalho1;
		this.cabecalho2 = cabecalho2;
		this.mensagemLink = mensagemLink;
		this.mesagens = mesagens;
	}

	@Override
	public void run() {
		try {
			
			VelocityContext veloContext = new VelocityContext();
            veloContext.put("cabecalho1", cabecalho1);
            veloContext.put("cabecalho2", cabecalho2);
            veloContext.put("link", linkAcesso);
            veloContext.put("mensagens", mesagens);
            veloContext.put("mensagemLink", mensagemLink);
            
            veloContext.put("informacaoContato", "Contato");
            veloContext.put("telefone", "telefone");
            
            VelocityUtil.getInstancia().init(diretorio);
            
            String corpoEmail = VelocityUtil.getInstancia().merge(TEMPLATE, veloContext);
            
            FuncoesEmail.enviaEmail(recipients, corpoEmail);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public String getBody() {
		return linkAcesso;
	}

	public void setBody(String body) {
		this.linkAcesso = body;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
}
