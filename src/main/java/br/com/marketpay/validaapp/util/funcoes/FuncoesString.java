package br.com.marketpay.validaapp.util.funcoes;

import java.text.Normalizer;

public class FuncoesString {

	public static String removeAcentos(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	public static String formataCampo(String str) {
		if (str != null) {
			str = str.toUpperCase();
			str = removeAcentos(str);
		}
		return str;
	}

	public static boolean checkAtributo(String valor) {
		if (valor != null && valor.length() > 0) {
			return true;
		}
		return false;
	}
}
