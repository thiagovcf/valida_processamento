package br.com.marketpay.validaapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.ProcessAlterarSenha;
import br.com.marketpay.validaapp.entity.Usuario;

public interface ProcessAlterarSenhaRepository extends CrudRepository<ProcessAlterarSenha, Long> {

	ProcessAlterarSenha findByUsuario(Usuario usuario);

	ProcessAlterarSenha findByToken(String token);

}
