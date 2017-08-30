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

@Entity(name = "T_FILE_FOLDER_FTP_EMISSOR")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class FileFolderFtpEmissor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PERIODO_DIARIO = "D";
	public static final String PERIODO_MENSAL = "M";
	public static final String PERIODO_SEMANAL = "S";
	
	@Id
	@SequenceGenerator(name = "T_FILE_FOLDER_FTP_EMISSOR_GENERATOR", sequenceName = "S_FILE_FOLDER_FTP_EMISSOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_FILE_FOLDER_FTP_EMISSOR_GENERATOR")
	@Column(name = "ID_FILE_FOLDER_FTP_EMISSOR")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA_ARQUIVO")
	private LocalDateTime dataArquivo;

	@Column(name = "REGRA_NOME_ARQUIVO")
	private String regraNomeArquivo;
	
	@Column(name = "TIPO_PERIODO_EXECUCAO")
	private String tipoPeriodoExecucao;

	@Column(name = "VALOR_TIPO_PERIODO_EXECUCAO")
	private String valorTipoPeriodoExecucao;

	@Column(name = "CHECK_ARQUIVO_DIA_MENOS_UM")
	private Boolean checkArquivoDiaMenosUm ;
	
	@OneToOne
	@JoinColumn(name = "ID_FOLDER_FTP")
	private FolderFpt folder;

	
	public static String getRegraFixa(String valorRegra) {
		return "F["+valorRegra+"]";
	}
	
	public static String getRegraData(String valorRegra) {
		return "D["+valorRegra+"]";
	}
	
	public static String getRegraAleatoria() {
		return "A[*]";
	}
}
