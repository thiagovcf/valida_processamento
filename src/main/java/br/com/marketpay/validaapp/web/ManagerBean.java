package br.com.marketpay.validaapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Usuario;
import br.com.marketpay.validaapp.service.UsuarioService;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Scope("session")
public class ManagerBean extends BeanAbstract{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Getter
	private String teste = "vai mesmo";
	
	public void teste(){
		List<Usuario> list = usuarioService.listaUsuarios();
		
		for (Usuario usuario : list) {
			log.info(usuario);
		}
		
	}
	
}
