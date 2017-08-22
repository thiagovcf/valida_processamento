package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Processamento;
import br.com.marketpay.validaapp.entity.ProcessamentoEmissor;

public interface ProcessamentoEmissorRepository extends CrudRepository<ProcessamentoEmissor, Long> {

	Processamento findById(Long id);
	
	List<ProcessamentoEmissor> findByProcessamentoOrderByStatusProcessamentoEmissor(Processamento processamento);
}
