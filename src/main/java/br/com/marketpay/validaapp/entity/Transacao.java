package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_TRANSACAO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_ATIVA = "A";
	public static final String STATUS_CANCELADA = "C";
	
	
	@Id
	@SequenceGenerator(name = "T_TRANSACAO_GENERATOR", sequenceName = "S_TRANSACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_TRANSACAO_GENERATOR")
	@Column(name = "ID")
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente= new Cliente();

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "VALOR")
	private Double valor;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_TRANSACAO")
	private Date dataTransacao;
}
