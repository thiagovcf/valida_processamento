package br.com.marketpay.validaapp.util.funcoes;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FuncoesNumeros {
	public static String formataNumero(BigDecimal numero) {
		DecimalFormat format = new DecimalFormat("##,###,###,##0.00",
				new DecimalFormatSymbols(new Locale("pt", "BR")));
		format.setMinimumFractionDigits(2);
		format.setParseBigDecimal(true);

		return "R$ " + format.format(numero);
	}
}