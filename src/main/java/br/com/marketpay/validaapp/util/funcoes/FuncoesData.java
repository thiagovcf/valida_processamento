package br.com.marketpay.validaapp.util.funcoes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FuncoesData {
	
	private static SimpleDateFormat sdfDataArquivo = new SimpleDateFormat("DDMMyyyy");
	public static SimpleDateFormat sdfData = new SimpleDateFormat("MM/yyyy");
	
	public static final long MILIS_POR_DIA = 86400000;
	
	
	public static String getDataArquivo(){
		return sdfDataArquivo.format(Calendar.getInstance().getTime());
	}
	
	public static boolean isFinalSemana(Date data) {  
	    Calendar gc = Calendar.getInstance();
	    gc.setTime(data);
	    int diaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);  
	    return diaSemana == GregorianCalendar.SATURDAY || diaSemana == GregorianCalendar.SUNDAY;  
	}
	
	public static int getDiasEntreDatas(Date d1, Date d2) {
		long millis1 = d1.getTime();
		long millis2 = d2.getTime();

		long millis = 0;
		if(millis1 > millis2) {
			millis = millis1 - millis2;
		} else {
			millis = millis2 - millis1;
		}

		millis += 3600000;//Adiciona 1 hora por causa do horario de verao
		return (int) (millis / MILIS_POR_DIA);
	}
	
	public static String formatarDataSemHora(Date data) {
		return sdfData.format(data);
	}  
	
	public static Date formataData(String data) throws ParseException{
		return sdfData.parse(data);
	}
}


