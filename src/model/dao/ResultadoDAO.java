package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Result;
import parse.ParseDAO;

public class ResultadoDAO extends BasicDAO<Result> implements ParseDAO<Result> {

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
	public enum Comparacao implements Comparator<Result> {
		CODIGO {
			@Override
			public int compare(Result r1, Result r2) {
				return r1.getResultType().compareTo(r2.getResultType());
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
	 * @param an ArrayList<Result>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Result> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Result result : lista) {
			instrucaoSQL.setInt(1, result.getResultType());
			instrucaoSQL.setString(2, result.getResultDescription());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<result>
	 * @param an ArrayList<Result>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Result> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Result result = new Result();
			result.setResultType(resultadoSQL.getInt(CODIGO));
			result.setResultDescription(resultadoSQL.getString(DESCRICAO));
			lista.add(result);
		}
	}

	/*
	 * This method retrieves a receipt through the code
	 * @param an Integer representing a code
	 * @return an instance of Class Result
	 */
	public Result getPeloCod(Integer codigo) throws SQLException {
		Result result = new Result();
		String comandoSQL = SQL_SELECAO + " WHERE " + CODIGO +" = "+ codigo +" ";
		try {
			this.conexao = new DatabaseConnection().getConexao();
	
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
	
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();
			
			while(resultadoSQL.next()) {
				result.setResultType(resultadoSQL.getInt(CODIGO));
				result.setResultDescription(resultadoSQL.getString(DESCRICAO));
			}

		} catch(SQLException e) {
			throw new SQLException("ResultadoDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return result;
	}
}