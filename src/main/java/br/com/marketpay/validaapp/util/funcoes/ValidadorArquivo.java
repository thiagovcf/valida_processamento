package br.com.marketpay.validaapp.util.funcoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jetty.util.StringUtil;

import br.com.marketpay.validaapp.entity.FileFolderFtpEmissor;
import lombok.Getter;
import lombok.Setter;

public class ValidadorArquivo {
	
	@Getter @Setter
	private boolean isMatcher;
	
	@Getter @Setter
	private boolean isExecutaRotina;
	
	@Getter @Setter
	private int countMatch;
	
	public ValidadorArquivo(){
		super();
	}

	private final static  String TIPO_REGRA_FIXA = "F";
	private final static String TIPO_REGRA_DATA = "D";
	private final static String TIPO_REGRA_ALEATORIA = "A";
	
	private final static  String TIPO_PERIODO_EXECUCAO_DIARIO = "D";
	private final static  String TIPO_PERIODO_EXECUCAO_DIA_MES = "M";
	private final static  String TIPO_PERIODO_EXECUCAO_DIA_SEMANA = "S";
	
	public static Boolean isMatcherFile(String regex, String nomeArquivo) {
			Pattern p = Pattern.compile(regex); 
			Matcher m = p.matcher(nomeArquivo);
			if (m.find())
				return true;
			return false;
	}
	public Boolean isMatcherFile(FileFolderFtpEmissor fileFolderFtpEmissor, List<String> nomesArquivos) {
		String regraRegex = buscarNomeArquivoComBaseNaRegra(fileFolderFtpEmissor);
		Pattern p = Pattern.compile(regraRegex);
		countMatch = 0;
		isMatcher = false;
		for (String nomeArquivo : nomesArquivos) {
			Matcher m = p.matcher(nomeArquivo);
			if (m.find()){
				countMatch++;
				isMatcher = true;
			}
		}
		return isMatcher;
		
	}
	public String buscarNomeArquivoComBaseNaRegra(FileFolderFtpEmissor fileFolderFtpEmissor) {
		String tipoRegra = null;
		String valorRegra = "";
		StringBuilder nomeArquivo = new StringBuilder();
		String regra = fileFolderFtpEmissor.getRegraNomeArquivo().toString();
		
			if(StringUtil.isNotBlank(regra)){
				String[] camposDaRegra = regra.split(";");
				for (int i = 0; i < camposDaRegra.length; i++) {
					tipoRegra = camposDaRegra[i].substring(0,1);
					valorRegra = camposDaRegra[i].substring(1).replaceAll("(\\[|\\])","");
					
					switch (tipoRegra.toUpperCase()) {
					case TIPO_REGRA_FIXA:
						nomeArquivo.append(valorRegra);
						break;
					case TIPO_REGRA_DATA:
						DateTimeFormatter formatador = DateTimeFormatter.ofPattern(valorRegra);
						LocalDateTime data;
						if(fileFolderFtpEmissor.getCheckArquivoDiaMenosUm()) {
							data =LocalDateTime.now().minusDays(1);
						}else {
							data =LocalDateTime.now();
						}
						nomeArquivo.append(data.format(formatador));
						break;
					case TIPO_REGRA_ALEATORIA:
						nomeArquivo.append("[\\w|\\s]*");
						break;
					default:
						nomeArquivo.append("");
						break;
					}
				}
			}
		return nomeArquivo.toString();
	}
	
	public boolean isExecutaValidadorArquivo(String tipoPeriodoExecutado, String periodoExecutado){

		//Colocar o escape como atributo
		Pattern pattern = Pattern.compile(";");
		
		switch (tipoPeriodoExecutado.toUpperCase()) {
		
		case TIPO_PERIODO_EXECUCAO_DIA_MES:
			int dia = LocalDateTime.now().getDayOfMonth();
			return pattern.splitAsStream(periodoExecutado)
					.map(Integer::valueOf).anyMatch(d -> d == dia);
			
		case TIPO_PERIODO_EXECUCAO_DIA_SEMANA:
			String l = LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
			return pattern.splitAsStream(periodoExecutado)
					.map(String::valueOf).anyMatch(d -> d.equalsIgnoreCase(l));
			
		case TIPO_PERIODO_EXECUCAO_DIARIO:
			return true;
		default:
			System.out.println("Tipo execução invalida");
			break;
		}
		return false;
	}
}
