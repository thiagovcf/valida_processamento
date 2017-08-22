package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_USUARIO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "grupos")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_USUARIO_GENERATOR", sequenceName = "S_USUARIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_USUARIO_GENERATOR")
	@Column(name = "ID_USUARIO")
	private Long id;

	@NotBlank(message = "Nome é uma informação obrigatória.")
	@Column(name = "NOME")
	private String nome;

	@NotBlank(message = "Login é uma informação obrigatória.")
	@Column(name = "LOGIN")
	private String login;

	@NotBlank(message = "E-mail é uma informação obrigatória.")
	@Column(name = "EMAIL")
	private String email;

	//@NotBlank(message = "Senha é uma informação obrigatória.")
	@Column(name = "SENHA")
	private String senha;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO")
	private Calendar dataCadastro = Calendar.getInstance();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "T_USUARIO_GRUPO", joinColumns = { @JoinColumn(name = "ID_USUARIO") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_GRUPO") })
	private List<Grupo> grupos;
	
	
	public void adicionarGrupo(Grupo grupo){
		if(grupos == null){
			grupos = new ArrayList<Grupo>();
		}
		grupos.add(grupo);
	}
	

	// @ManyToMany
	// @JoinTable(name = "USUARIO_PERMISSAO", joinColumns = { @JoinColumn(name =
	// "ID_USUARIO") }, inverseJoinColumns = {
	// @JoinColumn(name = "ID_PERMISSAO") })
	// private List<Permissao> permissoes;
}
