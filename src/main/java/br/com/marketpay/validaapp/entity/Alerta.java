package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_ALERTA")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Alerta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_ALERTA_GENERATOR", sequenceName = "S_ALERTA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_ALERTA_GENERATOR")
	@Column(name = "ID_ALERTA")
	private Long id;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA")
	private LocalDateTime data;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ROTINA")
	private Rotina rotina;
}
