package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Party;
import parse.ParseDAO;

public class PartidoDAO extends BasicoDAO<Party> implements ParseDAO<Party> {

	/*
	 * Class for manipulating the data about political parties
	 */
	
	// Constants
	private static final String NOME_TABELA = "partido";
	private static final String NUMERO = "numero";
	private static final String SIGLA = "sigla";
	private static final String NOME = "nome";
	private static final String DEFERIMENTO = "deferimento";
	
	private static final String SQL_INSERCAO = "INSERT INTO " + NOME_TABELA
			+ " (" + NUMERO + ", " + SIGLA + ", " + NOME + ", " + DEFERIMENTO
			+ ") " + "values (?, ?, ?, ?)";
	private static final String SQL_SELECAO = "SELECT * FROM " + NOME_TABELA;

	// Constructors
	public PartidoDAO() {
		super(NOME_TABELA, Comparacao.SIGLA);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal parties through acronym
	 */
	public enum Comparacao implements Comparator<Party> {
		SIGLA {
			@Override
			public int compare(Party p1, Party p2) {
				return p1.getPartyAcronym().compareToIgnoreCase(p2.getPartyAcronym());
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
	 * This method prepares a list of political parties to be registered
	 * @param an ArrayList<Party>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Party> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Party party : lista) {
			instrucaoSQL.setInt(1, party.getPartyNumber());
			instrucaoSQL.setString(2, party.getPartyAcronym());
			instrucaoSQL.setString(3, party.getPartyName());
			instrucaoSQL.setString(4, party.getPartyConcession());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Party>
	 * @param an ArrayList<Party>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Party> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Party party = new Party();
			party.setPartyName(resultadoSQL.getString(NOME));
			party.setPartyNumber(resultadoSQL.getInt(NUMERO));
			party.setPartyAcronym(resultadoSQL.getString(SIGLA));
			party.setPartyConcession(resultadoSQL.getString(DEFERIMENTO));

			lista.add(party);
		}
	}

	/*
	 * This method retrieves a party through the acronym
	 * @param a String with the acronym
	 * @return an instance of Class Party
	 */
	public Party getPelaSigla(String sigla) throws SQLException {
		String comandoSQL = SQL_SELECAO
				+ " WHERE " + SIGLA + " = '" + sigla + "'";
		return BuscaBD(comandoSQL);
	}

	/*
	 * This method retrieves a party through the number
	 * @param a String with the number
	 * @return an instance of Class Party
	 */
	public Party getPeloNumero(String numero) throws SQLException {
		String comandoSQL = SQL_SELECAO
				+ " WHERE " + NUMERO + " = '" + numero + "'";
		return BuscaBD(comandoSQL);
	}

	/*
	 * This method retrieves a complete list of political parties stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Party>
	 */
	private Party BuscaBD(String comandoSQL) throws SQLException{
		
		Party party = new Party();
		
		try {
			this.conexao = new ConexaoBancoDados().getConexao();
	
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
	
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();
	
			while(resultadoSQL.next()) {
				party.setPartyAcronym(resultadoSQL.getString(SIGLA));
				party.setPartyName(resultadoSQL.getString(NOME));
				party.setPartyConcession(resultadoSQL.getString(DEFERIMENTO));
				party.setPartyNumber(resultadoSQL.getInt(NUMERO));
			}
	
			if(this.instrucaoSQL != null) {
				instrucaoSQL.close();
			}

			if(this.conexao != null) {
				conexao.close();
			}
		} catch(SQLException e) {
			throw new SQLException("PartidoDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return party;
	}
}