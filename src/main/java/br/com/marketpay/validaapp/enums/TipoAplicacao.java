package br.com.marketpay.validaapp.enums;

import org.junit.Ignore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoAplicacao {

	PRIVATE("P"), CONVENIO("C"), API("A"), FROTA("F");

	private String tipo;

}
