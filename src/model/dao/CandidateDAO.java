package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

import model.beans.Candidate;

public class CandidateDAO extends BasicDAO<Candidate> {
	
	/*
	 * Class for manipulating the data about candidates
	 */
	
	// Attributes
	private CampaignDAO campaignDAO;

	// Constants
	private static final String NOME_TABELA = "candidato";
	private final String TITULO_ELEITORAL = "titulo_eleitoral";
	private final String NOME = "nome";
	private final String SQL_SELECT = "SELECT * FROM " + NOME_TABELA;
	private final String SQL_INSERT = "INSERT INTO " + NOME_TABELA + " ("
			+ TITULO_ELEITORAL + ", " + NOME + ") VALUES(?,?)";

	private final String INDEX_NOME = "candidato_sk_1";

	// Constructors
	public CandidateDAO() {
		super(NOME_TABELA, Comparacao.TITULO_ELEITORAL);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal candidates through Title Voter
	 */
	public enum Comparacao implements Comparator<Candidate> {
		TITULO_ELEITORAL {
			@Override
			public int compare(Candidate c1, Candidate c2) {
				return c1.getCandidateElectoralTitle().compareTo(
						c2.getCandidateElectoralTitle());
			}
		};
	}

	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT;
	}

	/*
	 * This method prepares a list of candidates to be registered
	 * @param an ArrayList<Candidate>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Candidate> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Candidate candidate : lista) {
			instrucaoSQL.setString(1, candidate.getCandidateElectoralTitle());
			instrucaoSQL.setString(2, candidate.getCandidateName());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Candidate>
	 * @param an ArrayList<Candidate>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Candidate> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Candidate candidate = new Candidate();
			candidate.setCandidateName(resultadoSQL.getString(NOME));
			candidate.setCandidateElectoralTitle(resultadoSQL
					.getString(TITULO_ELEITORAL));
			lista.add(candidate);
		}
	}

	/*
	 * This method retrieves a candidate through the electoral title
	 * @param a String with the electoral title
	 * @return an instance of Class Candidate
	 */
	public Candidate getCandidatoPeloTitulo(String tituloEleitoral) {
		LinkedList<Candidate> listaCandidato = new LinkedList<>();
		String comandoSQL = SQL_SELECT + " WHERE " + TITULO_ELEITORAL + " = '"
				+ tituloEleitoral + "'";
		
		try {
			listaCandidato = buscaBD(comandoSQL);
			return listaCandidato.get(0);
		} catch(SQLException e) {
			Candidate cand = new Candidate();
			cand.setCandidateElectoralTitle("-1");
			return cand;
		}
	}

	/*
	 * This method retrieves a list of candidates by name
	 * @param a String with the name
	 * @return a LinkedList<Candidate>
	 */
	public LinkedList<Candidate> getListaPeloNome(String nome) {
		this.campaignDAO = new CampaignDAO();

		LinkedList<Candidate> listaCandidato = new LinkedList<>();
		String comandoSQL = SQL_SELECT + " USE INDEX (" + INDEX_NOME + ")"
				+ " WHERE " + NOME + " LIKE '%" + nome + "%' "
				+ " OR "
				+ TITULO_ELEITORAL + " IN (" + this.campaignDAO.getSqlSelectNomeUrna(nome)
				+ ")";
		
		try {
			listaCandidato = buscaBD(comandoSQL);
		} catch(SQLException e) {
			return null;
		}
		return listaCandidato;
	}

	/*
	 * This method retrieves a complete list of candidates stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Candidate>
	 */
	public LinkedList<Candidate> buscaBD(String SQL) throws SQLException {
		LinkedList<Candidate> listaCandidato = new LinkedList<>();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Candidate candidate = new Candidate();
				candidate.setCandidateName(resultadoSQL.getString(NOME));
				candidate.setCandidateElectoralTitle(resultadoSQL.getString(TITULO_ELEITORAL));

				if(candidate != null) {
					listaCandidato.add(candidate);
				}
			}			
		} catch(SQLException e) {
			throw new SQLException("CandidateDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaCandidato;
	}
}