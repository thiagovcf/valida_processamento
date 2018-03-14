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

@Entity(name = "T_PROCESSAMENTO_ARQUIVO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class ProcessamentoArquivo implements Serializable{


	private static final long serialVersionUID = 1L;

	public static final String STATUS_PROCESSAMENTO_SUCESSO = "S";
	public static final String STATUS_PROCESSAMENTO_FALHA = "F";
	public static final String STATUS_NAO_PROCESSADO = "N";
	
	
	public static final String DESCRICAO_OK = "OK";
	public static final String DESCRICAO_ERRO_FPT = "ERRO NA CONFIGURACAO DA PASTA. Verifique se possui conexao com o ftp.";
	public static final String DESCRICAO_ARQUIVO_NAO_ENCONTRADO = "NAO FOI ENCONTRADO O ARQUIVO NO DIRETORIO";
	public static final String DESCRICAO_EXECUTOU_SEM_AGENDAMENTO = "ARQUIVO NÃO DEVERIA SER GERADO COM BASE NA REGRA";
	public static final String DESCRICAO_NAO_AGENDADO = "NAO AGENDADO";
	public static final String DESCRICAO_CONFIG_SEM_PASTA = "NAO FOI CRIADO UMA REGRA PARA ESSA PASTA";
	public static final String DESCRICAO_MAIS_DE_UM_ARQUIVO = "MAIS DE UM ARQUIVO ENCONTRADO";

	@Id
	@SequenceGenerator(name = "T_PROCESSAMENTO_ARQUIVO_GENERATOR", sequenceName = "S_PROCESSAMENTO_ARQUIVO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_PROCESSAMENTO_ARQUIVO_GENERATOR")
	@Column(name = "ID_PROCESSAMENTO_ARQUIVO")
	private Long id;

	@Column(name = "STATUS")
	private String status;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_EXECUCAO")
	private Date dataExecucao;
	
	@Column(name = "DESCRICAO")
	private String descricao;

	@OneToOne
	@JoinColumn(name = "ID_FILE_FOLDER")
	private FileFolderFtpEmissor arquivo;
	
	@Column(name = "QUATIDADE")
	private Integer quatidade;
	
	@OneToOne
	@JoinColumn(name = "ID_PROCESSAMENTO_EMISSOR")
	private ProcessamentoEmissor processamentoEmissor = new ProcessamentoEmissor();
	
	
	public String getNomeProcessamentoArquivo() {
		if(arquivo==null) {
			return "VAZIO";
		}else {
			return arquivo.getNome().toUpperCase();
		}
		
	}
}
