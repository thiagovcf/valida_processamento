package br.com.marketpay.validaapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.marketpay.validaapp.entity.Processamento;
import br.com.marketpay.validaapp.entity.ProcessamentoArquivo;
import br.com.marketpay.validaapp.entity.ProcessamentoEmissor;

public interface ProcessamentoArquivoRepository extends CrudRepository<ProcessamentoArquivo, Long> {

	Processamento findById(Long id);
	
	List<ProcessamentoArquivo> findByProcessamentoEmissorOrderByStatus(ProcessamentoEmissor processamentoEmissor);
}
