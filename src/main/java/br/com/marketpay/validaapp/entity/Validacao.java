package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_VALIDACAO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Validacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_VALIDACAO_GENERATOR", sequenceName = "S_VALIDACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_VALIDACAO_GENERATOR")
	@Column(name = "ID_VALIDACAO")
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String decricao;
	
	@Column(name = "STATUS")
	private String status;
	
//	@Column(name = "IMAGEM_ERRO")
//	private String imagemErro;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TEMPO_INICIO")
	private LocalDateTime tempoInicio;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TEMPO_FIM")
	private LocalDateTime tempoFim;
	
	@OneToOne
	@JoinColumn(name = "ID_MASSA_DADOS")
	private MassaDados massaDados;
	
	
}
