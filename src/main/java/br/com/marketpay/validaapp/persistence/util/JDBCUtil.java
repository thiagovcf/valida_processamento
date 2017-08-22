package br.com.marketpay.validaapp.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.marketpay.validaapp.entity.Configuracao;

public class JDBCUtil {

	  @Autowired
	  private Configuracao configuracao;
	  
	public Connection getConnection(String url, String user, String password) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
