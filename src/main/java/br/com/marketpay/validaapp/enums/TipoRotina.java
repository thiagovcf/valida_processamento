package br.com.marketpay.validaapp.enums;

import org.junit.Ignore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Ignore
@AllArgsConstructor
@Getter
public enum TipoRotina {

	MANUAL("M"), AUTOMATICO("A");

	private String tipo;

}
