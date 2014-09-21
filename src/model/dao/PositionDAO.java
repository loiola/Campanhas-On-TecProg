package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Position;
import parse.ParseDAO;

public class PositionDAO extends BasicDAO<Position> implements ParseDAO<Position> {
	
	/*
	 * Class for manipulating the data about positions
	 */

	// Constants
	private static final String NOME_TABELA = "cargo";
	private static final String CODIGO = "cod_cargo";
	private static final String DESCRICAO = "descricao";
	private static final String SQL_INSERCAO = "INSERT INTO " + NOME_TABELA
			+ " (" + CODIGO + ", " + DESCRICAO + ") "
			+ "values (?, ?)";
	private static final String SQL_SELECAO = "SELECT * FROM " + NOME_TABELA;

	// Constructors
	public PositionDAO() {
		super(NOME_TABELA, Comparacao.CODIGO);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal positions through code
	 */
	public enum Comparacao implements Comparator<Position> {
		CODIGO {
			@Override
			public int compare(Position c1, Position c2) {
				return c1.getPositionCode().compareTo(c2.getPositionCode());
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
	 * This method prepares a list of positions to be registered
	 * @param an ArrayList<Position>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Position> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Position position : lista) {
			instrucaoSQL.setInt(1, position.getPositionCode());
			instrucaoSQL.setString(2, position.getPositionDescription());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Position>
	 * @param an ArrayList<Position>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Position> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Position position = new Position();
			position.setPositionCode(resultadoSQL.getInt(CODIGO));
			position.setPositionDescription(resultadoSQL.getString(DESCRICAO));
			lista.add(position);
		}
	}
	
	/*
	 * This method retrieves a position through code
	 * @param an Integer with the code of position
	 * @return an instance of Class Position
	 */
	public Position getPeloCod(Integer codigo) throws SQLException {
		String comandoSQL = SQL_SELECAO + " WHERE " + CODIGO +" = "+codigo+" ";
		return buscaBD(comandoSQL);
	}
	
	/*
	 * This method retrieves a position by describing
	 * @param a String with the description
	 * @return an instance of Class Position
	 */
	public Position getPelaDescricao(String descricao) throws SQLException {
		String comandoSQL = SQL_SELECAO + " WHERE "
						  + DESCRICAO +" like '%"+descricao+"%' ";
		return buscaBD(comandoSQL);
	}
	
	/*
	 * This method retrieves an instance of Position stored in the database
	 * @param a String with the SQL command
	 * @return an instance of Class Position
	 */
	public Position buscaBD(String SQL) throws SQLException {
		Position position = new Position();
		String comandoSQL = SQL;
		
		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();
			while(resultadoSQL.next()) {
				position.setPositionCode(resultadoSQL.getInt(CODIGO));
				position.setPositionDescription(resultadoSQL.getString(DESCRICAO));
			}
		} catch(SQLException e) {
			throw new SQLException("PositionDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return position;
	}
}