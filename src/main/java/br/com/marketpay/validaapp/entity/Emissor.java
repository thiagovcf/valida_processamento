package br.com.marketpay.validaapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_EMISSOR")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Emissor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_ATIVA = "A";
	public static final String STATUS_INATIVA = "I";

	@Id
	@SequenceGenerator(name = "T_EMISSOR_GENERATOR", sequenceName = "S_EMISSOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_EMISSOR_GENERATOR")
	@Column(name = "ID_EMISSOR")
	private Long id;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "STATUS")
	private String status = STATUS_ATIVA;
	
	/*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "T_EMISSOR_FOLDER_FTP", 
			joinColumns = {	@JoinColumn(name = "ID_EMISSOR", referencedColumnName = "ID_EMISSOR")}, 
			inverseJoinColumns = { @JoinColumn(name = "ID_FOLDER_FTP", referencedColumnName = "ID_FOLDER_FTP") 
	})
	private List<FolderFpt> folders;

	public void adicionarPasta(FolderFpt folderFptEmissor){
		if(folders == null){
			folders = new ArrayList<FolderFpt>();
		}
		folders.add(folderFptEmissor);
	}*/
}
