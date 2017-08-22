package br.com.marketpay.validaapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_CONFIGURACAO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Configuracao  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final long CONFIGURACAO_PADRAO = 1;

	@Id
	@SequenceGenerator(name = "T_CONFIGURACAO_GENERATOR", sequenceName = "S_CONFIGURACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_CONFIGURACAO_GENERATOR")
	@Column(name = "ID_CONFIGURACAO")
	private Long id;
	
	@Column(name = "EMAIL_DESTINATARIOS")
	private String emailDestinatarios;

	@Column(name = "TOTAL_THREADS")
	private Integer totalThreads;
	
	@Column(name = "URL_BANCO")
	private String urlBanco;
	
	@Column(name = "LOGIN_BANCO")
	private String loginBanco;
	
	@Column(name = "SENHA_BANCO")
	private String senhaBanco;
	
	@Column(name = "CANAL_SLACK")
	private String canalSlack;
	
	@Column(name = "DOMINIO")
	private String dominio;
}
