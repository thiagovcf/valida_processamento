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

@Entity(name = "T_APLICACAO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Aplicacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_ATIVA = "A";
	public static final String STATUS_INATIVA = "I";

	@Id
	@SequenceGenerator(name = "T_APLICACAO_GENERATOR", sequenceName = "S_APLICACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_APLICACAO_GENERATOR")
	@Column(name = "ID_APLICACAO")
	private Long id;
	
	@Column(name = "COMMIT")
	private String commit;
	
	@Column(name = "VERSAO")
	private String versao;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "LINK")
	private String link;

	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "IP_INTERNO")
	private String ipInterno;
	
	@Column(name = "IP_EXTERNO")
	private String ipExterno;
	
	@Column(name = "PORTA")
	private String porta;
	
	@Column(name = "STATUS")
	private String status;

}
