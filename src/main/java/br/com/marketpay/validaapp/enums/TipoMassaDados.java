package br.com.marketpay.validaapp.enums;

import org.junit.Ignore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Ignore
@AllArgsConstructor
@Getter
public enum TipoMassaDados {

	PRIVATE("P"), CONVENIO("C"), API("A"), FROTA("F");

	private String tipo;

}
