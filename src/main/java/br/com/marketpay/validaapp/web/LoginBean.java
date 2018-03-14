package br.com.marketpay.validaapp.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.security.UsuarioSistema;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Scope("session")
public class LoginBean extends BeanAbstract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private UsuarioSistema usuario;

	public LoginBean() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuario = ((UsuarioSistema) authentication.getPrincipal());
			}
		}
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void teste1(){
		log.info("teste1");
	}
	
	@PreAuthorize("hasAuthority('ROLE_USUARIO')")
	public void teste2(){
		log.info("teste2");
	}
	
	@PreAuthorize("hasAuthority('ROLE_VISITANTE')")
	public void teste3(){
		log.info("teste2");
	}

}
