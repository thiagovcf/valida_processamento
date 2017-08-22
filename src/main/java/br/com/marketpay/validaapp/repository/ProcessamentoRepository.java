package br.com.marketpay.validaapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Processamento;

public interface ProcessamentoRepository extends CrudRepository<Processamento, Long> {

	Processamento findById(Long id);

	Processamento findTopByTempoInicioOrderByIdDesc(Date campoPesquisa);
	
	List<Processamento> findByTempoInicioBetweenOrderByIdDesc(Date dataInicio, Date dataFim);
}
