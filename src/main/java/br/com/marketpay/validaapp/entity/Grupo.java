package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_GRUPO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_GRUPO_GENERATOR", sequenceName = "S_GRUPO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_GRUPO_GENERATOR")
	@Column(name = "ID_GRUPO")
	private Long id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DESCRICAO")
	private String descricao;

	@ManyToMany(mappedBy = "grupos", cascade = CascadeType.REFRESH)
	private List<Usuario> usuarios;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinTable(name = "T_GRUPO_PERMISSAO", joinColumns = { @JoinColumn(name = "ID_GRUPO") }, inverseJoinColumns = { @JoinColumn(name = "ID_PERMISSAO") })
	private List<Permissao> permissoes;
	
	public Grupo(){}
	
	public Grupo(Long id){
		this.id = id;
	}
}
