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

@Entity(name = "T_METODO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Metodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_METODO_GENERATOR", sequenceName = "S_METODO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_METODO_GENERATOR")
	@Column(name = "ID_METODO")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "TIPO")
	private String tipo;

}
