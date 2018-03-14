package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_CLIENTE")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String STATUS_ATIVA = "A";
	public static final String STATUS_INATIVA = "I";
	
	

	@Id
	@SequenceGenerator(name = "T_CLIENTE_GENERATOR", sequenceName = "S_CLIENTE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_CLIENTE_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@NotBlank(message = "Nome é uma informação obrigatória.")
	@Column(name = "NOME")
	private String nome;

	@NotBlank(message = "CPF é uma informação obrigatória.")
	@Column(name = "CPF")
	private String cpf;

	@Column(name = "statusCliente")
	private String statusCliente = STATUS_ATIVA;

	@Column(name = "SENHA")
	private String senha;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "VALIDADE_CARTAO")
	private Calendar validadeCartao;
	
	@Column(name = "SALDO_CLIENTE")
	private String saldoCliente;

}
