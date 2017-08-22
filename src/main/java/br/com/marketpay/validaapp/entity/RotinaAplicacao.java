package br.com.marketpay.validaapp.entity;

import java.io.Serializable;
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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "T_ROTINA_APLICACAO")
@Data
@EqualsAndHashCode(of = { "id" }, callSuper = false)
@ToString(exclude = "id")
public class RotinaAplicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "T_ROTINA_APLICACAO_GENERATOR", sequenceName = "S_ROTINA_APLICACAO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "T_ROTINA_APLICACAO_GENERATOR")
	@Column(name = "ID_ROTINA_APLICACAO")
	private Long id;
	
	@Column(name = "TOTAL_TESTE")
	private Integer totalTeste;
	
	@Column(name = "TOTAL_ERRO")
	private Integer totalErro;
	
	@Column(name = "TOTAL_SUCESSO")
	private Integer totalSucesso;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "T_ROTINA_APLICACAO_VALIDACAO", 
			joinColumns = {	@JoinColumn(name = "ID_ROTINA_APLICACAO", referencedColumnName = "ID_ROTINA_APLICACAO")}, 
			inverseJoinColumns = { @JoinColumn(name = "ID_VALIDACAO", referencedColumnName = "ID_VALIDACAO") 
	})
	private List<Validacao> validacoes;
	

}


