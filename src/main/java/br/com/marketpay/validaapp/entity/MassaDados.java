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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_MASSA_DADOS")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class MassaDados implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_ATIVA = "A";
	public static final String STATUS_INATIVA = "I";
	
	@Id
	@SequenceGenerator(name = "T_MASSA_DADOS_GENERATOR", sequenceName = "S_MASSA_DADOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_MASSA_DADOS_GENERATOR")
	@Column(name = "ID_MASSA_DADOS")
	private Long id;
	
	@Column(name = "STATUS")
	private String status;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ATUALIZACAO")
	private LocalDateTime dataAtualizacao;
	
	@Column(name = "TIPO_MASSA_DADOS")
	private String tipoMassaDados;
	
	@Column(name = "VALOR_MASSA_DADOS")
	private String valorMassaDados;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_APLICACAO")
	private Aplicacao aplicacao;
	
}
