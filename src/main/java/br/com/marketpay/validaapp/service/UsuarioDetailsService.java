/**
 * 
 */
package br.com.marketpay.validaapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.marketpay.validaapp.entity.Grupo;
import br.com.marketpay.validaapp.entity.Permissao;
import br.com.marketpay.validaapp.entity.Usuario;
import br.com.marketpay.validaapp.security.UsuarioSistema;


@Component
public class UsuarioDetailsService  implements UserDetailsService {
	
	  @Autowired
	  private UsuarioService usuarios;

	  @Autowired
	  private GrupoService grupos;
		
	  @Autowired
	  private PermissaoService permissoes;

	  @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Usuario usuario = usuarios.findByLogin(username);
			
	    if (usuario == null) {
	      throw new UsernameNotFoundException("Usuário não encontrado!");
	    }
			
	    return new UsuarioSistema(usuario.getNome(), usuario.getLogin(), usuario.getSenha(), authorities(usuario));
	  }
		
	  public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
	    return authorities(grupos.findByUsuariosIn(usuario));
	  }
		
	  public Collection<? extends GrantedAuthority> authorities(List<Grupo> grupos) {
	    Collection<GrantedAuthority> auths = new ArrayList<>();
			
	    for (Grupo grupo: grupos) {
	      List<Permissao> lista = permissoes.findByGruposIn(grupo);
			
	      for (Permissao permissao: lista) {
	        auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
	      }
	    }
	    return auths;
	  }
}
