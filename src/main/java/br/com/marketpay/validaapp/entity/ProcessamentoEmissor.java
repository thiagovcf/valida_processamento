package br.com.marketpay.validaapp.entity;

import java.io.Serializable;

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

@Entity(name = "T_PROCESSAMENTO_EMISSOR")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class ProcessamentoEmissor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_OK = "S";
	public static final String STATUS_ERRO= "E";
	
	@Id
	@SequenceGenerator(name = "T_PROCESSAMENTO_EMISSOR_GENERATOR", sequenceName = "S_PROCESSAMENTO_EMISSOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_PROCESSAMENTO_EMISSOR_GENERATOR")
	@Column(name = "ID_PROCESSAMENTO_EMISSOR")
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_EMISSOR")
	private Emissor emissor = new Emissor();
	
	@Column(name = "STATUS_PROCESSAMENTO_EMISSOR")
	private String statusProcessamentoEmissor;
	
	@OneToOne
	@JoinColumn(name = "ID_PROCESSAMENTO")
	private Processamento processamento = new Processamento();
	
	
}
