package br.com.marketpay.validaapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "T_PROCESS_ALTERAR_SENHA")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
public class ProcessAlterarSenha implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TOKEN_ATIVO = "A";
	public static final String TOKEN_INVALIDO = "I";
	
	public ProcessAlterarSenha(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}
	
	public ProcessAlterarSenha() {
		super();
	}
	
	@Id
	@SequenceGenerator(name = "T_PROCESS_ALTERAR_GENERATOR", sequenceName = "S_PROCESS_ALTERAR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_PROCESS_ALTERAR_GENERATOR")
	@Column(name = "ID_PROCESS_ALTERAR_SENHA")
	private Long id;
	
	@Column(name = "TOKEN")
	private String token;

	@Column(name = "STATUS_TOKEN")
	private String statusToken = TOKEN_ATIVO;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
}