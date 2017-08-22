//package br.com.marketpay.validaapp;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.com.marketpay.validaapp.entity.Emissor;
//import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
//import br.com.marketpay.validaapp.entity.FolderFpt;
//import br.com.marketpay.validaapp.util.ftp.FTPUtil;
//import br.com.marketpay.validaapp.util.funcoes.ValidadorArquivo;
//
//public class Main {
//	public static void main(String[] args) {
//
//		Emissor emissor  = getEmissor();
//		List<String> resultadoFinal = new ArrayList<String>();
//		
//		
//		/*emissor.getFolders().forEach(pasta -> {
//			List<String> files = null;
//			
//			try {
//				files = FTPUtil.buscarArquivoNoFtpPeloPath(pasta.getPath(), pasta.getIp(), pasta.getPorta(), pasta.getLogin(), pasta.getSenha());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			ValidadorArquivo validadorArquivo= new ValidadorArquivo();
//			boolean isSearch = false;
//			if(validadorArquivo.isExecutaValidadorArquivo(pasta.getFileFolderFtpEmissor().getTipoPeriodoExecucao(),pasta.getFileFolderFtpEmissor().getValorTipoPeriodoExecucao())){
//			
//				isSearch = validadorArquivo.isMatcherFile(pasta.getFileFolderFtpEmissor(),files);
//				if(isSearch){
//					resultadoFinal.add("Emissor: "+emissor.getNome()+" - " +pasta.getFileFolderFtpEmissor().getNome()+": "+isSearch);
//				}else{
//					resultadoFinal.add("Emissor: "+emissor.getNome()+" - " +pasta.getFileFolderFtpEmissor().getNome()+": "+isSearch);
//				}
//			}else{
//				resultadoFinal.add("Emissor: "+emissor.getNome()+" - " +pasta.getFileFolderFtpEmissor().getNome()+": Não agendado para hoje");
//			}
//		});*/
//		
//		resultadoFinal.forEach(resultado -> {
//			System.out.println(resultado.toString());
//		});
//	}
//	
//	
//	
//	
//	public static Emissor getEmissor(){
//		Emissor emissor = new Emissor();
//		emissor.setId(new Long(1));
//		emissor.setNome("TAVANTI");
////		emissor.setFolders(getFolderEmissor());
//		return emissor;
//	}
//	
//	
//	public static ArrayList<FolderFpt> getFolderEmissor(){
//		ArrayList<FolderFpt> foldersFptEmissor = new ArrayList<>();
//		//*****************************************************************************		
//		/**
//		 * Configuracao 1 - Regra para validar um arquivo
//		 */
//		FolderFpt folderFptEmissor = new FolderFpt();
//		folderFptEmissor.setId(new Long(1));
//		folderFptEmissor.setIp("192.168.10.240");
//		folderFptEmissor.setPorta(null);
//		folderFptEmissor.setLogin("homologacao");
//		folderFptEmissor.setSenha("cdt@123.");
//		folderFptEmissor.setPath("/homologacao/avanti/conciliacao");
//		
//		
//		FileFolderFtpEmissor fileFolderFtpEmissor = new FileFolderFtpEmissor();
//		fileFolderFtpEmissor.setId(new Long(1));
//		
////		tipo periodo execucao
//		fileFolderFtpEmissor.setTipoPeriodoExecucao("M");//D=diario;M=dias do mes(1;2;3;4;5...;31); S= dias da semana(Seg;Ter;Qua;...;Dom)
//		fileFolderFtpEmissor.setValorTipoPeriodoExecucao("1;2;5;10;15;20");
////		regra do arquivo
//		fileFolderFtpEmissor.setRegraNomeArquivo("F{DEV_AVANTI};D{YYMMdd};F{.txt}");//DEV_AVANTI170720.txt
//		fileFolderFtpEmissor.setNome("Arquivo Avanti Fatura");
////		folderFptEmissor.setFileFolderFtpEmissor(fileFolderFtpEmissor);
//		foldersFptEmissor.add(folderFptEmissor);
//		
//		/**
//		 * Configuracao 2 - Regra para validar um arquivo
//		 */
//		//*****************************************************************************
//		folderFptEmissor = new FolderFpt();
//		folderFptEmissor.setId(new Long(2));
//		folderFptEmissor.setIp("192.168.10.240");
//		folderFptEmissor.setPorta(null);
//		folderFptEmissor.setLogin("homologacao");
//		folderFptEmissor.setSenha("cdt@123.");
//		folderFptEmissor.setPath("/homologacao/avantisys2/embossing");
//		
//		fileFolderFtpEmissor = new FileFolderFtpEmissor();
//		fileFolderFtpEmissor.setId(new Long(2));
//		
//		//tipo periodo execucao
//		fileFolderFtpEmissor.setTipoPeriodoExecucao("D");//D=diario;M=dias do mes(1;2;3;4;5...;31); S= dias da semana(Seg;Ter;Qua;...;Dom)
//		fileFolderFtpEmissor.setValorTipoPeriodoExecucao("");
//		//regra do arquivo
//		fileFolderFtpEmissor.setRegraNomeArquivo("A{*};D[YYYYMMdd];A{*};F[.zip]");//77729 20170627_113058.zip
//		
//		fileFolderFtpEmissor.setNome("Arquivo Embossing avanti");
////		folderFptEmissor.setFileFolderFtpEmissor(fileFolderFtpEmissor);
//
//		foldersFptEmissor.add(folderFptEmissor);
//		//*****************************************************************************
//		/**
//		 * Configuracao 3 - Regra para validar um arquivo
//		 */
//		folderFptEmissor = new FolderFpt();
//		folderFptEmissor.setId(new Long(3));
//		folderFptEmissor.setIp("192.168.10.240");
//		folderFptEmissor.setPorta(null);
//		folderFptEmissor.setLogin("homologacao");
//		folderFptEmissor.setSenha("cdt@123.");
//		folderFptEmissor.setPath("/homologacao/aceconfianca/movimentos");
//		
//		fileFolderFtpEmissor = new FileFolderFtpEmissor();
//		fileFolderFtpEmissor.setId(new Long(3));
//		
//		fileFolderFtpEmissor.setRegraNomeArquivo("F{ACE_};D{yyyyMMdd};F{_};A{*};F{_MOV.txt}");//ACE_20170721_387_MOV.txt
//		fileFolderFtpEmissor.setTipoPeriodoExecucao("S");//D=diario;M=dias do mes(1;2;3;4;5...;31); S= dias da semana(Seg;Ter;Qua;...;Dom)
//		fileFolderFtpEmissor.setValorTipoPeriodoExecucao("Qui");
//		fileFolderFtpEmissor.setNome("Arquivo de Movimentos");
////		folderFptEmissor.setFileFolderFtpEmissor(fileFolderFtpEmissor);
//		
//		foldersFptEmissor.add(folderFptEmissor);
//		
//		return foldersFptEmissor;
//		
//	}
//	
//}
