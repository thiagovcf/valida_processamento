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

@Entity(name = "T_FOLDER_FTP")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class FolderFpt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "T_FOLDER_FTP_GENERATOR", sequenceName = "S_FOLDER_FTP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_FOLDER_FTP_GENERATOR")
	@Column(name = "ID_FOLDER_FTP")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "PATH")
	private String path;
	
	@Column(name = "LOGIN")
	private String Login;
	
	@Column(name = "SENHA")
	private String senha;

	@Column(name = "IP")
	private String ip;
	
	@Column(name = "PORTA")
	private String porta;
	/*
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FILE_FOLDER_FTP_EMISSOR")
	private FileFolderFtpEmissor fileFolderFtpEmissor;
	*/
	@OneToOne
	@JoinColumn(name = "ID_EMISSOR")
	private Emissor emissor = new Emissor();

}
