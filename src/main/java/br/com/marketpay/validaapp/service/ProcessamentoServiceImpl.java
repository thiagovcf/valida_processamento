package br.com.marketpay.validaapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marketpay.validaapp.entity.Emissor;
import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
import br.com.marketpay.validaapp.entity.FolderFpt;
import br.com.marketpay.validaapp.entity.Processamento;
import br.com.marketpay.validaapp.entity.ProcessamentoArquivo;
import br.com.marketpay.validaapp.entity.ProcessamentoEmissor;
import br.com.marketpay.validaapp.repository.EmissorRepository;
import br.com.marketpay.validaapp.repository.FileFolderFtpEmissorRepository;
import br.com.marketpay.validaapp.repository.FolderFtpRepository;
import br.com.marketpay.validaapp.repository.ProcessamentoArquivoRepository;
import br.com.marketpay.validaapp.repository.ProcessamentoEmissorRepository;
import br.com.marketpay.validaapp.repository.ProcessamentoRepository;
import br.com.marketpay.validaapp.util.ftp.FTPUtil;
import br.com.marketpay.validaapp.util.funcoes.ValidadorArquivo;

@Service
public class ProcessamentoServiceImpl implements ProcessamentoService{

	@Autowired
	private EmissorRepository emissorRepository;
	
	@Autowired
	private FolderFtpRepository folderFtpRepository;

	@Autowired
	private FileFolderFtpEmissorRepository fileFolderFtpEmissorRepository;

	@Autowired
	private ProcessamentoRepository processamentoRepository;
	
	@Autowired
	private ProcessamentoArquivoRepository processamentoArquivoRepository;
	
	@Autowired
	private ProcessamentoEmissorRepository processamentoEmissorRepository;
	
	@Override
	public Processamento executarRotinaVerificaExistenciaArquivo(){
		Processamento processamento = new Processamento();
		
		Iterable<Emissor> emissores = emissorRepository.getEmissoresAtivoComArquivo();
		
		processamento.setTotalEmissores(new Long(emissores.spliterator().getExactSizeIfKnown()).intValue());
		
		salvarInicioDoProcessamento(processamento);
		
		new Thread(){

			@Override
			public void run() {
				
				for(Emissor emissor: emissores) {
					ProcessamentoEmissor processamentoEmissor = preencherProcessamentoEmissor(processamento, emissor);
					
					ProcessamentoArquivo processamentoArquivo = new ProcessamentoArquivo();
					try {
//						Thread.sleep(2000); //usado para testes
						salvarProcessamentoEmissor(processamentoEmissor);
						
						ArrayList<ProcessamentoArquivo> arquivosProcessados = new ArrayList<>();
						
						for(FolderFpt pasta : folderFtpRepository.findAllByEmissor(emissor)) {
							
							processamentoArquivo = processamentoRotinaCheckArquivo(pasta, processamentoEmissor);
							
							salvarArquivoProcessado(processamentoArquivo);
							
							arquivosProcessados.add(processamentoArquivo);
						}
						
						boolean present = arquivosProcessados.stream().filter(
								arquivoProcessado -> arquivoProcessado.getStatus().equals(ProcessamentoArquivo.STATUS_PROCESSAMENTO_FALHA)).findFirst().isPresent();
						
						if (present) {
							processamentoEmissor.setStatusProcessamentoEmissor(ProcessamentoEmissor.STATUS_ERRO);
							processamento.contaTotalEmissoresFalho();
						}else {
							processamentoEmissor.setStatusProcessamentoEmissor(ProcessamentoEmissor.STATUS_OK);
							processamento.contaTotalEmissoresSucesso();
						}
						
						//contando o total de emissores com sucesso
						processamentoRepository.save(processamento);
						salvarProcessamentoEmissor(processamentoEmissor);
					} catch (Exception e) {
						
						//contando o total de emissores com falha
						processamento.contaTotalEmissoresFalho();
						processamentoRepository.save(processamento);
						salvarProcessamentoEmissor(processamentoEmissor);
						e.printStackTrace();
					}
				}
				preencherObjetoFimDoProcessamento(processamento);
			};
		}.start();
		
		return processamento;
	}
	
	public ProcessamentoArquivo processamentoRotinaCheckArquivo(FolderFpt pasta, ProcessamentoEmissor processamentoEmissor) {
		
		ValidadorArquivo validadorArquivo= new ValidadorArquivo();
		boolean isErro = false;
		List<String> files = null;
		ProcessamentoArquivo processamentoArquivo;
		
		try {
			files = FTPUtil.buscarArquivoNoFtpPeloPath(pasta.getPath(), pasta.getIp(), pasta.getPorta(), pasta.getLogin(), pasta.getSenha());
		} catch (Exception e) {
			e.printStackTrace();
			isErro = true;
			
		}
	 	FileFolderFtpEmissor fileFolderFtpEmissor = fileFolderFtpEmissorRepository.findByFolder(pasta);
	 	//SE A PASTA NAO TIVER REGRA(ARQUIVO) CONFIGURADO
	 	if(fileFolderFtpEmissor == null) {
	 		processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_PROCESSAMENTO_FALHA,LocalDateTime.now(),ProcessamentoArquivo.DESCRICAO_CONFIG_SEM_PASTA.concat("[ "+pasta.getNome()+"]"), processamentoEmissor, fileFolderFtpEmissor);
	 		return processamentoArquivo;
	 	}
	 	
		if(!isErro) {
			validadorArquivo.isMatcherFile(fileFolderFtpEmissor, files);
			if(validadorArquivo.isExecutaValidadorArquivo(fileFolderFtpEmissor.getTipoPeriodoExecucao(),fileFolderFtpEmissor.getValorTipoPeriodoExecucao())){
				if(validadorArquivo.isMatcher()){
					if(validadorArquivo.getCountMatch()==1) {
						processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_PROCESSAMENTO_SUCESSO,LocalDateTime.now(), ProcessamentoArquivo.DESCRICAO_OK, processamentoEmissor, fileFolderFtpEmissor) ;
					}else {
						processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_PROCESSAMENTO_FALHA,LocalDateTime.now(), ProcessamentoArquivo.DESCRICAO_MAIS_DE_UM_ARQUIVO, processamentoEmissor, fileFolderFtpEmissor);
					}
				}else{
					processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_PROCESSAMENTO_FALHA,LocalDateTime.now(), ProcessamentoArquivo.DESCRICAO_ARQUIVO_NAO_ENCONTRADO, processamentoEmissor, fileFolderFtpEmissor);
				}
			}else{
				if(validadorArquivo.isMatcher()){
					processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_PROCESSAMENTO_FALHA,LocalDateTime.now(), ProcessamentoArquivo.DESCRICAO_EXECUTOU_SEM_AGENDAMENTO, processamentoEmissor, fileFolderFtpEmissor);
				}else {
					processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_NAO_PROCESSADO,LocalDateTime.now(), ProcessamentoArquivo.DESCRICAO_NAO_AGENDADO, processamentoEmissor, fileFolderFtpEmissor);
				}
			}
		}else {
			processamentoArquivo = preencheObjetoArquivo(ProcessamentoArquivo.STATUS_PROCESSAMENTO_FALHA,LocalDateTime.now(),ProcessamentoArquivo.DESCRICAO_ERRO_FPT, processamentoEmissor, fileFolderFtpEmissor);
		}
		return processamentoArquivo;
	}
	
	
	private ProcessamentoArquivo preencheObjetoArquivo(String statusProcessamentoSucesso, LocalDateTime localDateTime, String descricao, ProcessamentoEmissor processamentoEmissor, FileFolderFtpEmissor fileFolderFtpEmissor) {
		ProcessamentoArquivo processamentoArquivo = new ProcessamentoArquivo();
		processamentoArquivo.setStatus(statusProcessamentoSucesso);
		processamentoArquivo.setDataExecucao(Calendar.getInstance().getTime());
		processamentoArquivo.setDescricao(descricao);
		processamentoArquivo.setArquivo(fileFolderFtpEmissor);
		processamentoArquivo.setProcessamentoEmissor(processamentoEmissor);
		return processamentoArquivo;
	}
	
	private void salvarArquivoProcessado(ProcessamentoArquivo processamentoArquivo) {
		processamentoArquivoRepository.save(processamentoArquivo);
	}
	
	private void salvarInicioDoProcessamento( Processamento processamento) {
		processamento.setStatus(Processamento.STATUS_EXECUTANDO);
		processamento.setTempoInicio(Calendar.getInstance().getTime());
		save(processamento);
	}
	
	private void salvarProcessamentoEmissor(ProcessamentoEmissor processamentoEmissor) {
		processamentoEmissorRepository.save(processamentoEmissor);
	}
	
	private ProcessamentoEmissor preencherProcessamentoEmissor(Processamento processamento, Emissor emissor) {
		ProcessamentoEmissor processamentoEmissor = new ProcessamentoEmissor();
		processamentoEmissor.setEmissor(emissor);
		processamentoEmissor.setProcessamento(processamento);
		return processamentoEmissor;
	}
	
	private void preencherObjetoFimDoProcessamento(Processamento processamento) {
		processamento.setTempoFim(Calendar.getInstance().getTime());
		processamento.setStatus(Processamento.STATUS_CONCLUIDO);
		save(processamento);
	}

	@Override
	public Processamento save(Processamento processamento) {
		return processamentoRepository.save(processamento);
	}

	@Override
	public Processamento buscarUltimoProcessamentoPorData(Date campoPesquisa) {
		return processamentoRepository.findTopByTempoInicioOrderByIdDesc(campoPesquisa);
	}
	
	@Override
	public List<ProcessamentoEmissor> buscarEmissoresProcessadosPorProcessamento(Long codigoProcessamento) {
		Processamento processamento =  processamentoRepository.findById(codigoProcessamento);
		return processamentoEmissorRepository.findByProcessamentoOrderByStatusProcessamentoEmissor(processamento);
	}

	@Override
	public List<ProcessamentoArquivo> buscarArquivosProcessadosPorEmissor(ProcessamentoEmissor processamentoEmissor) {
		return processamentoArquivoRepository.findByProcessamentoEmissorOrderByStatus(processamentoEmissor);
	}

	@Override
	public List<Processamento> buscarProcessamentoEntreData(Date dataInicio, Date dataFim) {
		return processamentoRepository.findByTempoInicioBetweenOrderByIdDesc(dataInicio, dataFim);
	}

	@Override
	public Processamento findById(Long codigo) {
		return processamentoRepository.findById(codigo);
	}

	@Override
	public Processamento findLastProcessamento() {
		return processamentoRepository.findTopByTempoInicioOrderByIdDesc(Calendar.getInstance().getTime())
;	}

}
