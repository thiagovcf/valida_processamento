/**
 * 
 */
package br.com.marketpay.validaapp.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.marketpay.validaapp.entity.Permissao;
import br.com.marketpay.validaapp.service.PermissaoService;
import br.com.marketpay.validaapp.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
	private PermissaoService permissaoService;
	
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("admin"));
//	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");//aqui fica o layout
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		
//		 http.authorizeRequests().antMatchers("/**").permitAll();
		
		 http.authorizeRequests().
		    antMatchers("/", "/pages/**").permitAll().
		    antMatchers("/alterarSenha").permitAll().
//			antMatchers("/*").access(recuperaAllPermissoes()).
		    //antMatchers("/incluirAlterarGrupo").access("hasRole('ALTERAR_GRUPO')").
			//antMatchers("/listarGrupo").access("hasRole('ALTERAR_GRUPO')").
			anyRequest().fullyAuthenticated().
			anyRequest().authenticated().
			and().
				formLogin().  
	                loginPage("/login").
	                defaultSuccessUrl("/inicio", true).
	                loginProcessingUrl("/processaLogin").
	                usernameParameter("username").
	                passwordParameter("password").
	                permitAll().	
			and().
				logout().    
				logoutUrl("/logout").
				invalidateHttpSession(true).
				logoutSuccessUrl("/login").
			and().exceptionHandling().accessDeniedPage("/negado").
			and().exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler()).
			and().csrf().
			and().rememberMe();
		 
		 
//		 http.authorizeRequests()
//         .anyRequest().authenticated()
//         .and()
//     .formLogin()
//         .loginPage("/login")
//         .defaultSuccessUrl("/inicio", true)
//         .loginProcessingUrl("/processaLogin")
//         .failureUrl("/negado")
//         .permitAll()
//         .and()
//     .logout()                                    
//         .permitAll();
		 
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
	   builder.userDetailsService(usuarioDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	public String recuperaAllPermissoes(){
		List<Permissao> permissoes = permissaoService.findAll();
		
		List<String> listPermissoes = new ArrayList<String>();
		
		for (Permissao permissao : permissoes) {
			listPermissoes.add(String.format("hasRole('ROLE_%s')", permissao.getNome()));
//			listPermissoes.add(String.format("ROLE_%s", permissao.getNome()));
		}
		
		return StringUtils.join(listPermissoes, " or ");
	}
	
//	protected boolean hasRole(String[] roles) {
//	    boolean result = false;
//	    for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
//	        String userRole = authority.getAuthority();
//	        for (String role : roles) {
//	            if (role.equals(userRole)) {
//	                result = true;
//	                break;
//	            }
//	        }
//
//	        if (result) {
//	            break;
//	        }
//	    }
//
//	    return result;
//	}
	
}
