package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_PROCESSAMENTO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Processamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String STATUS_EXECUTANDO = "E";
	public static final String STATUS_CONCLUIDO = "C";

	@Id
	@SequenceGenerator(name = "T_PROCESSAMENTO_GENERATOR", sequenceName = "S_PROCESSAMENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_PROCESSAMENTO_GENERATOR")
	@Column(name = "ID_PROCESSAMENTO")
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "TEMPO_INICIO")
	private Date tempoInicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "TEMPO_FIM")
	private Date tempoFim;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "TOTAL_EMISSORES")
	private int totalEmissores;
	
	@Column(name = "TOTAL_EMISSORES_SUCESSO")
	private int totalEmissoreSucesso;
	
	@Column(name = "TOTAL_EMISSORES_FALHO")
	private int totalEmissoreFalho;
	
	public void contaTotalEmissoresFalho(){
		totalEmissoreFalho += 1;
	}
	
	public void contaTotalEmissoresSucesso(){
		totalEmissoreSucesso += 1;
	}
	
	public int getPercentualProc(){
		return (int) ((new Double(String.valueOf(totalEmissoreSucesso)) + new Double(String.valueOf(totalEmissoreFalho))) /totalEmissores * 100);
	}
	
	public String getStatusProcessamento() {
		if(totalEmissoreFalho == 0) {
			return "success";
		}
		return "danger";
	}
}
