package br.com.marketpay.validaapp.util.components.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import br.com.marketpay.validaapp.util.funcoes.FuncoesArquivo;

public class FuncoesEmail {
	public static void enviaEmail(List<String> emails, String texto) throws IOException {
		 Mail mj = new Mail();

		 mj.setUserMail(FuncoesArquivo.getInstance().getValorProperties("mail.user"));
		 mj.setPassMail(FuncoesArquivo.getInstance().getValorProperties("mail.password"));		         
         mj.setBodyMail(texto);
         mj.setSubjectMail("Valida Processamento");
         mj.setFromNameMail(FuncoesArquivo.getInstance().getValorProperties("mail.from"));
         mj.setDestinatarios(emails);
         
         try {
             new MailSender().senderMail(mj);
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         } catch (MessagingException e) {
             e.printStackTrace(); 
         }
    }
	
	public static void enviaEmail(String texto, String to, List<String> files) throws IOException{
		Mail mj = new Mail();

		mj.setUserMail(FuncoesArquivo.getInstance().getValorProperties("mail.user"));
		mj.setPassMail(FuncoesArquivo.getInstance().getValorProperties("mail.password"));		         
        mj.setBodyMail(texto);
        mj.setSubjectMail("Valida Processamento");
        mj.setFromNameMail(FuncoesArquivo.getInstance().getValorProperties("mail.from"));
        
        List<String> destinatario = new ArrayList<String>();
        destinatario.add(to);
        
        mj.setDestinatarios(destinatario);
        mj.setFileMails(files);
        
        try {
            new MailSender().senderMail(mj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace(); 
        }
    }
	
	/*public static void enviaEmailTest(List<String> emails, String texto) throws IOException {
		Mail mj = new Mail();
		
		mj.setUserMail("AKIAJWDTIQUM7QAE4LBQ");
		mj.setPassMail("Aq45Dygji6AX7lavRymIqq3W9N7UwJUt9PyLV3HWmYvs");		         
		mj.setBodyMail(texto);
		mj.setSubjectMail("Teste");
		mj.setFromNameMail("iContabil@icontabil.net");
		mj.setDestinatarios(emails);
		
		try {
			new MailSender().senderMail(mj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace(); 
		}
	}
	*/
//	public static void main(String[] args) throws IOException {
//		System.out.println("Enviando...");
//		enviaEmailTest(Arrays.asList("andremarllusxs@gmail.com"), "Teste");
//		System.out.println("OK");
//	}
	
}