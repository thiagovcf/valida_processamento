package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_PERMISSAO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Permissao implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PERMISSAO_ALTERAR_GRUPO = "ALTERAR_GRUPO";
	public static final String PERMISSAO_VISUALIZAR_GRUPO = "VISUALIZAR_GRUPO";

	public static final String PERMISSAO_ALTERAR_USUARIO = "ALTERAR_USUARIO";
	public static final String PERMISSAO_VISUALIZAR_USUARIO = "VISUALIZAR_USUARIO";

	@Id
	@SequenceGenerator(name = "T_PERMISSAO_GENERATOR", sequenceName = "S_PERMISSAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_PERMISSAO_GENERATOR")
	@Column(name = "ID_PERMISSAO")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	// @ManyToMany(mappedBy = "permissoes")
	// private List<Usuario> usuarios;

	@ManyToMany(mappedBy = "permissoes", cascade = CascadeType.REFRESH)
	private List<Grupo> grupos;

	public Permissao() {}
	
	public Permissao(String nome) {
		super();
		this.nome = nome;
	}

}
