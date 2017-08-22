package br.com.marketpay.validaapp.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.marketpay.validaapp.entity.MassaDados;
import br.com.marketpay.validaapp.persistence.util.JDBCUtil;


public class MassaDadosDAO {

	public MassaDados findIdContaByAplicacao(String aplicacao, String urlBanco, String userBanco, String passwordBanco){
		
		Connection connection = new JDBCUtil().getConnection(urlBanco, userBanco, passwordBanco);
		
		//andre vai fazer o serviço de buscar emissor
		String emissor = aplicacao;
			
		StringBuilder sql = new StringBuilder();
		sql.append(" ")
			.append(" SELECT 'IDCONTA' as TIPO, A.idclientepessoafisica AS VALOR ")
			.append(" FROM                                           ")
			.append(emissor).append(".T_CARTAOLOJA A,                ")
			.append(emissor).append(".T_CLIENTETITULARPESSOAFISICA B,")
			.append(emissor).append(".T_PESSOA C,                    ")
			.append(emissor).append(".T_PESSOAFISICA D               ")
			.append(" WHERE                                          ")
			.append("    A.idclientepessoafisica = B.IDCLIENTETITULARPESSOAFISICA")
			.append("    AND  C.idpessoa = A.idclientepessoafisica   ")
			.append("    AND D.IDPESSOAFISICA = C.IDPESSOA           ")
			.append("    AND A.status = 'V'                          ")
			.append("	 AND B.STATUS_CLIENTE = 'A'                  ")
			//conta deve ter ocorrencia
			.append("    AND B.IDCLIENTETITULARPESSOAFISICA IN ")
			.append(" (                                        ")
			.append("  SELECT IDCLIENTETITULARPESSOAFISICA     ")
			.append("  FROM                                    ")
			.append(emissor).append("T_OCORRENCIA              ")
			.append("  WHERE STATUS ='A'                       ")
			.append(" )                                        ")
			//conta deve ter fatura ativa
			.append("	 AND B.IDCLIENTETITULARPESSOAFISICA in ")
			.append(" (                                        ")
			.append("  SELECT F.IDCLIENTETITULARPESSOAFISICA   ")
			.append("  FROM                                    ")
			.append(emissor).append("T_FATURA F                ")
			.append("  where F.ATIVA = 'S'                     ")
			.append("  and F.idfatura > 0                      ")
			.append("  and F.VALORTOTAL > 0)                   ")
			.append("  )                                       ")
			.append("    AND ROWNUM=1                          ");
		
		return executeSql(connection, sql.toString());
	}
	public MassaDados findVendaByAplicacao(String aplicacao, String urlBanco, String userBanco, String passwordBanco){
		
		Connection connection = new JDBCUtil().getConnection(urlBanco, userBanco, passwordBanco);
		
		//andre vai fazer o serviço de buscar emissor
		String emissor = aplicacao;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" ")
		.append("  SELECT                                       ")
		.append("    'CODIGO_VENDA' AS TIPO, DOCUMENTO as VALOR ")
		.append("  FROM                                         ")
		.append(emissor).append("T_VENDA                        ")
		.append("  WHERE                                        ")
		.append("    STATUS ='A'                                ")
		.append("    AND TO_CHAR(DATA, 'dd/MM/yyyy') = TO_CHAR (SYSDATE-7, 'dd/MM/yyyy')")
		.append("    AND ROWNUM =1                              ")
		.append("    ORDER BY IDVENDA DESC                      ");
		
		return executeSql(connection, sql.toString());
	}
	public MassaDados findPagamentoByAplicacao(String aplicacao, String urlBanco, String userBanco, String passwordBanco){
		

		Connection connection = new JDBCUtil().getConnection(urlBanco, userBanco, passwordBanco);
		
		//andre vai fazer o serviço de buscar emissor
		String emissor = aplicacao;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" ")
		.append(" SELECT                                         ")
		.append("     'CODIGO_PAGAMENTO' AS TIPO, IDFATURA VALOR ")
		.append("   FROM                                         ")
		.append(emissor).append("T_PAGAMENTO                     ")
		.append("   WHERE                                        ")
		.append("     TO_CHAR(DATAPAGAMENTO, 'dd/MM/yyyy') = TO_CHAR(SYSDATE-5, 'dd/MM/yyyy')")
		.append("     AND ROWNUM =1                              ");
		
		return executeSql(connection, sql.toString());
	}

	
	private MassaDados executeSql(Connection connection, String sql){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());
		
			rs = ps.executeQuery(); 
		
			MassaDados massaDados = null;
		
			while(rs.next()){
				massaDados = preencheMassaDados(rs);
				return massaDados;
			}
		
			return null;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps != null && !ps.isClosed()){
					ps.close();
				}
				if(rs != null && !rs.isClosed()){
					rs.close();
				}
				if(connection != null && !connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
			return null;
	}
	
	private MassaDados preencheMassaDados(ResultSet rs) throws SQLException {
		
		MassaDados massaDados = new MassaDados();
		massaDados.setStatus("A");
		massaDados.setTipoMassaDados(rs.getString("TIPO"));
		massaDados.setValorMassaDados(rs.getString("VALOR"));
		return massaDados;
	}
}
