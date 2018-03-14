package br.com.marketpay.validaapp.service;

import javax.faces.context.FacesContext;

import br.com.marketpay.validaapp.entity.ProcessAlterarSenha;
import br.com.marketpay.validaapp.entity.Usuario;

public interface ProcessAlterarSenhaService {
	
	ProcessAlterarSenha getProcessUsuario(Usuario usuario);
	
	ProcessAlterarSenha getProcessToken(String token) ;
	
	void resetSenha(Usuario usuario, FacesContext facesContext);
}
