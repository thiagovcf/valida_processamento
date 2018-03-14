package br.com.marketpay.validaapp.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Processamento;
import br.com.marketpay.validaapp.entity.ProcessamentoArquivo;
import br.com.marketpay.validaapp.entity.ProcessamentoEmissor;
import br.com.marketpay.validaapp.service.ProcessamentoService;
import br.com.marketpay.validaapp.util.funcoes.FuncoesData;
import br.com.marketpay.validaapp.web.util.Flash;
import br.com.marketpay.validaapp.web.util.Flash.FlashTipo;
import br.com.marketpay.validaapp.web.util.ValidaAppPages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Service
@Data
@EqualsAndHashCode(callSuper = false)
@Scope("request")
public class ProcessamentoBean extends BeanAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProcessamentoService processamentoService;
	
	private List<Processamento> processamentos;
	
	private Processamento processamento = new Processamento();
	
	private List<ProcessamentoEmissor> emissoresProcessados;
	
//	private List<ProcessamentoArquivo> arquivosProcessados;
	
	private Date campoPesquisaDataIncio;
	
	private Date campoPesquisaDataFim;
	
	public void init(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		
		campoPesquisaDataIncio = calendar.getTime();
		setValueCampo("dataInicio", FuncoesData.formatarDataSemHora(campoPesquisaDataIncio));
		
		campoPesquisaDataFim = Calendar.getInstance().getTime();
		setValueCampo("dataFim", FuncoesData.formatarDataSemHora(campoPesquisaDataFim));
	}
	
	public String executarRotina() throws IOException{
		processamento = processamentoService.executarRotinaVerificaExistenciaArquivo();
		
		addMensageRedirect(new Flash("Execução iniciada com sucesso", FlashTipo.success));
		
		return ValidaAppPages.PAGINA_VISUALIZAR_PROCESSAMENTO;
	}
	
	public void listarProcessamentos() throws ParseException{
		String campoInicio = (String) getRequestParam("dataInicio"); 
		
		if(campoInicio != null && !campoInicio.equals("")){
			campoPesquisaDataIncio = FuncoesData.formataData(campoInicio);
		}
		
		String campoFim = (String) getRequestParam("dataFim"); 
		
		if(campoInicio != null && !campoInicio.equals("")){
			campoPesquisaDataFim = FuncoesData.formataData(campoFim);
		}
		
		
		processamentos = processamentoService.buscarProcessamentoEntreData(campoPesquisaDataIncio, campoPesquisaDataFim);
	}
	
	/*public void preencherProcessamento() {
		emissoresProcessados = processamentoService.buscarEmissoresProcessadosPorProcessamento(processamento.getId());
	}*/
	
	public void pesquisarEmissoresProcessados() {
		processamento = processamentoService.findById(processamento.getId());
		emissoresProcessados = processamentoService.buscarEmissoresProcessadosPorProcessamento(processamento.getId());
	}
	
	public List<ProcessamentoArquivo> pesquisarArquivoPorEmissor(ProcessamentoEmissor processamentoEmissor) {
		List<ProcessamentoArquivo> arquivos = processamentoService.buscarArquivosProcessadosPorEmissor(processamentoEmissor);
		return arquivos;
	}

	
}
