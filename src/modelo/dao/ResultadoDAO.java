package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import modelo.beans.Resultado;
import parse.ParseDAO;

public class ResultadoDAO extends BasicoDAO<Resultado> implements ParseDAO<Resultado> {

	/*
	 * Class for manipulating the data about political result
	 */
	
	// Constants
	private static final String NOME_TABELA = "resultado";
	private static final String CODIGO = "cod_resultado";
	private static final String DESCRICAO = "descricao";
	
	private static final String SQL_INSERCAO = "INSERT INTO "+ NOME_TABELA
			+" (" +CODIGO+", "+ DESCRICAO + ") values (?, ?)" ;
	private static final String SQL_SELECAO = "SELECT * FROM " + NOME_TABELA;
	
	// Constructors
	public ResultadoDAO() {
		super(NOME_TABELA, Comparacao.CODIGO);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal result through Title code
	 */
	public enum Comparacao implements Comparator<Resultado> {
		CODIGO {
			@Override
			public int compare(Resultado r1, Resultado r2) {
				return r1.getCodigo().compareTo(r2.getCodigo());
			}
		};
	}

	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlInsert() {
		return SQL_INSERCAO;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlSelect() {
		return SQL_SELECAO;
	}

	/*
	 * This method prepares a list of result to be registered
	 * @param an ArrayList<result>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Resultado> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Resultado resultado : lista) {
			instrucaoSQL.setInt(1, resultado.getCodigo());
			instrucaoSQL.setString(2, resultado.getDescricao());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<result>
	 * @param an ArrayList<result>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Resultado> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Resultado resultado = new Resultado();
			resultado.setCodigo(resultadoSQL.getInt(CODIGO));
			resultado.setDescricao(resultadoSQL.getString(DESCRICAO));
			
			lista.add(resultado);
		}
	}

	/*
	 * This method retrieves a receipt through the code
	 * @param an integer representing a code
	 * @return an instance of Class result
	 */
	public Resultado getPeloCod(Integer codigo) throws SQLException {
		Resultado resultado = new Resultado();
		String comandoSQL = SQL_SELECAO + " WHERE " + CODIGO +" = "+ codigo +" ";
		try {
			this.conexao = new ConexaoBancoDados().getConexao();
	
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
	
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();
			
			while(resultadoSQL.next()) {
				resultado.setCodigo(resultadoSQL.getInt(CODIGO));
				resultado.setDescricao(resultadoSQL.getString(DESCRICAO));
			}

		} catch(SQLException e) {
			throw new SQLException("ResultadoDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return resultado;
	}
}