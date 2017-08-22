package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_ROTINA")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class Rotina  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_ROTINA_GENERATOR", sequenceName = "S_ROTINA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_ROTINA_GENERATOR")
	@Column(name = "ID_ROTINA")
	private Long id;
	
	@Column(name = "TIPO_ROTINA")
	private String tipoRotina;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA")
	private LocalDateTime data;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "T_ROTINA_ROTINA_APLICACAO", 
			joinColumns = {	@JoinColumn(name = "ID_ROTINA", referencedColumnName = "ID_ROTINA") }, 
			inverseJoinColumns = { @JoinColumn(name = "ID_ROTINA_APLICACAO", referencedColumnName = "ID_ROTINA_APLICACAO") 
	})
	private List<RotinaAplicacao> rotinasAplicacao;
	
}
