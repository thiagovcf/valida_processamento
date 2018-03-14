package br.com.marketpay.validaapp.service;

import java.util.Date;
import java.util.List;

import br.com.marketpay.validaapp.entity.Processamento;
import br.com.marketpay.validaapp.entity.ProcessamentoArquivo;
import br.com.marketpay.validaapp.entity.ProcessamentoEmissor;

public interface ProcessamentoService {

	Processamento findLastProcessamento();
	
	Processamento findById(Long codigo);
	
	Processamento save(Processamento processamento);
	
//	void save(Processamento processamento);

	Processamento executarRotinaVerificaExistenciaArquivo();

	Processamento buscarUltimoProcessamentoPorData(Date campoPesquisa);
	
	List<Processamento> buscarProcessamentoEntreData(Date dataInicio, Date dataFim);
	
	List<ProcessamentoArquivo> buscarArquivosProcessadosPorEmissor(ProcessamentoEmissor processamentoEmissor);

	List<ProcessamentoEmissor> buscarEmissoresProcessadosPorProcessamento(Long codigoProcessamento);

}
