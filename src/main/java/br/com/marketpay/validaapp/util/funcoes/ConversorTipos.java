/*
 * #source  ConversorTipos.java
 * #date    14/02/2002
 * #version 1.0
 *
 * Copyright (c) 2002 by NEUS Tecnologia da Informacao Ltda. All Rights Reserved.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. NEUS AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL NEUS OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF NEUS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 */

package br.com.marketpay.validaapp.util.funcoes;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;


/**
 * Conversor de tipos primitivos
 * 
 * @author marcelo(marcelo@neus.com.br)
 */
@Log4j
public class ConversorTipos {

	/**
	 * Verifica se a string � n�o-nula e n�o-vazia
	 */
	public static boolean check(String s) {
		return (s != null) && (!s.equals(""));
	}
	
	/**
	 * Verifica se a string � n�o-nula e n�o-vazia
	 */
	public static boolean checkComTrim(String s) {
		return (s != null) && (!s.trim().equals(""));
	}

	/**
	 * Verifica se o array de bytes � n�o-nulo e n�o-vazio
	 */
	public static boolean check(byte[] s) {
		return (s != null) && (s.length != 0);
	}

	/**
	 * Verifica se a string pode ser convertida em um inteiro
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException error) {
			return false;
		}
	}

	/**
	 * Verifica se a string pode ser convertida em um double
	 */
	public static boolean isDouble(String s) {
		try {
			if(s == null)
				return false;
			Double.parseDouble(s);
			return true;
		} catch(NumberFormatException error) {
			return false;
		}
	}

	/**
	 * Verifica se a string pode ser convertida em um long
	 */
	public static boolean isLong(String s) {
		try {
			if(s == null)
				return false;
			Long.parseLong(s);
			return true;
		} catch(NumberFormatException error) {
			return false;
		}
	}

	/**
	 * Verifica se a string pode ser convertida em um double
	 */
	public static boolean isDouble(String s, NumberFormat nf) {
		try {
			if(s == null)
				return false;
			nf.parse(s);
			return true;
		} catch(ParseException error) {
			return false;
		}
	}

	/**
	 * Verifica se a data � v�lido
	 */
	public static boolean isDate(String s, SimpleDateFormat sdf) {
		try {
			if(s != null) {
				if(sdf.toPattern().trim().length() == s.trim().length()) {
					sdf.setLenient(false);
					sdf.parse(s);
					return true;
				}
			}
			return false;
		} catch(ParseException error) {
			return false;
		}
	}

	/**
	 * Verifica se a hora � v�lido
	 */
	public static boolean isHour(String s, SimpleDateFormat sdf) {
		try {
			sdf.parse(s);
			return true;
		} catch(ParseException error) {
			return false;
		}
	}

	/**
	 * Retorna o valor inteiro do parametro s no request.
	 * Caso o parametro seja nulo, retorna 0
	 */
	public static int getInt(HttpServletRequest request, String s) {
		s = request.getParameter(s);
		if((s == null) || (s.equals("")))
			return 0;
		else
			return Integer.parseInt(s);
	}

	/**
	 * Faz o arredondamento de um double para que este fique
	 * com precisao de duas casa decimais
	 * 
	 * @param f
	 *            o double que sera arredondado
	 * @return Retorna o parametro arredondado
	 */
	public static double getDoublePrecisaoDoisDigitos(double f) {

		double doubleRetorno = 0;
		NumberFormat nf2digitosDecimais = NumberFormat.getNumberInstance(Locale.ITALY);

		nf2digitosDecimais.setMaximumFractionDigits(2);
		nf2digitosDecimais.setMinimumFractionDigits(2);

		String aux = nf2digitosDecimais.format(f);

		try {
			doubleRetorno = (nf2digitosDecimais.parse(aux)).doubleValue();
		} catch(ParseException erro) {
			log.info("ERRO DE PARSE - " + erro.getMessage());
			erro.printStackTrace();
		}

		return doubleRetorno;

	}

	/**
	 * Faz o arredondamento de um double para que este fique
	 * com precisao de quatro casas decimais
	 * 
	 * @param f
	 *            o double que sera arredondado
	 * @return Retorna o parametro arredondado
	 */
	public static double getDoublePrecisaoQuatroDigitos(double f) {

		double doubleRetorno = 0;
		NumberFormat nf4digitosDecimais = NumberFormat.getNumberInstance(Locale.ITALY);

		nf4digitosDecimais.setMaximumFractionDigits(4);
		nf4digitosDecimais.setMinimumFractionDigits(4);

		String aux = nf4digitosDecimais.format(f);

		try {
			doubleRetorno = (nf4digitosDecimais.parse(aux)).doubleValue();
		} catch(ParseException erro) {
			log.info("ERRO DE PARSE - " + erro.getMessage());
			erro.printStackTrace();
		}

		return doubleRetorno;

	}

	/**
	 * Retorna os elementos de um iterator em forma de ArrayList.
	 * 
	 * @param it
	 *            Iterator que tera os seus elemetos transferidos para um ArrayList
	 * @return Retorna o ArrayList contendo os elementos do Iterator
	 */
	public static <T> ArrayList<T> getArrayList(Iterator<T> it) {
		ArrayList<T> al = new ArrayList();

		while(it.hasNext()) {
			al.add(it.next());
		}

		return al;
	}

	public static <T> List<T> getList(Iterator<T> it) {
		List<T> al = new ArrayList<T>();

		while(it.hasNext()) {
			al.add(it.next());
		}

		return al;
	}

	public static ArrayList getArrayList(Object[] array) {
		ArrayList al = new ArrayList();
		for(int i = 0; i < array.length; i++) {
			al.add(array[i]);
		}
		return al;
	}

}
