package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Party;
import model.beans.Position;
import model.beans.Result;

public class CampanhaDAO extends BasicoDAO<Campaign> {
	
	/*
	 * Class for manipulating the data about election campaigns
	 */

	//Attributes
	private CandidatoDAO candidatoDAO;
	private PartidoDAO partidoDAO;
	private CargoDAO cargoDAO;
	private ResultadoDAO resultadoDAO;

	// Constants
	private static final String NOME_TABELA = "campanha";
	private final String ID = "id_campanha";
	private final String ANO = "ano";
	private final String NUM_CANDIDATO = "numero_candidato";
	private final String COD_RESULTADO = "resultado_cod_resultado";
	private final String COD_CARGO = "cargo_cod_cargo";
	private final String NUMERO_PARTIDO = "partido_numero";
	private final String TITULO_CANDIDATO = "candidato_titulo_eleitoral";
	private final String NOME_URNA = "nome_de_urna";
	private final String UF = "uf";
	private final String DESPESA_MAX_DECLARADA = "despesa_maxima_declarada";
	private final String DESPESA_MAX_CALCULADA = "despesa_maxima_calculada";
	private final String RECEITA_MAX_CALCULADA = "receita_maxima_calculada";

	private final String SQL_SELECT = "SELECT * FROM " + NOME_TABELA;
	private final String SQL_INSERT = "INSERT INTO " + NOME_TABELA + " (" + ID
			+ ", " + ANO + ", " + NUM_CANDIDATO + ", " + COD_RESULTADO + ", "
			+ COD_CARGO + ", " + NUMERO_PARTIDO + ", " + TITULO_CANDIDATO
			+ ", " + NOME_URNA + ", " + UF + ", " + DESPESA_MAX_DECLARADA
			+ ", " + DESPESA_MAX_CALCULADA + ", " + RECEITA_MAX_CALCULADA
			+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String INDEX_CANDIDATO = "campanha_fk_3";
	private final String INDEX_PARTIDO = "campanha_fk_4";
	private final String INDEX_ANO = "campanha_sk_1";

	// Constructors
	public CampanhaDAO() {
		super(NOME_TABELA, null);
	}

	// Other methods
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
	 * This method returns an SQL command for selecting a candidate by the name of urn
	 * @param a String with the name of urn
	 * @return a String with the SQL command
	 */
	public String getSqlSelectNomeUrna(String nome) {
		return "SELECT " + TITULO_CANDIDATO + " FROM " + NOME_TABELA
				+ " WHERE " + NOME_URNA + " LIKE '%" + nome + "%'";
	}

	/*
	 * This method prepares a list of campaigns to be registered
	 * @param an ArrayList<Campaign>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Campaign> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Campaign campaign : lista) {
			instrucaoSQL.setInt(1, campaign.getCampaignIdentifier());
			instrucaoSQL.setInt(2, campaign.getCampaignYear());
			instrucaoSQL.setInt(3, campaign.getCampaignCandidateNumber());
			instrucaoSQL.setInt(4, campaign.getCampaignResult().getResultType());
			instrucaoSQL.setInt(5, campaign.getCampaignPosition().getPositionCode());
			instrucaoSQL.setInt(6, campaign.getCampaignParty().getPartyNumber());
			instrucaoSQL.setString(7, campaign.getCampaignCandidate()
					.getCandidateElectoralTitle());
			instrucaoSQL.setString(8, campaign.getCampaignNameOfUrn());
			instrucaoSQL.setString(9, campaign.getCampaignCountryState());
			instrucaoSQL.setFloat(10, campaign.getCampaignMaximumExpenseDeclared());
			instrucaoSQL.setFloat(11, campaign.getCampaignTotalExpenseCalculated());
			instrucaoSQL.setFloat(12, campaign.getCampaignTotalRevenueCalculated());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Campaigns>
	 * @param an ArrayList<Campaign>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Campaign> lista, ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Position position = new Position();
			Result result = new Result();
			Party party = new Party();
			Candidate candidate = new Candidate();
			PreparaCamposCargoEResultado(position, result, resultadoSQL);
			PreparaCamposCandidatoEPartido(party, candidate, resultadoSQL);

			Campaign campaign = new Campaign();
			campaign.setCampaignIdentifier(resultadoSQL.getInt(ID));
			campaign.setCampaignResult(result);
			campaign.setCampaignPosition(position);
			campaign.setCampaignParty(party);
			campaign.setCampaignCandidate(candidate);
			campaign.setCampaignYear(resultadoSQL.getInt(ANO));
			campaign.setCampaignCandidateNumber(resultadoSQL.getInt(NUM_CANDIDATO));
			campaign.setCampaignNameOfUrn(resultadoSQL.getString(NOME_URNA));
			campaign.setCampaignCountryState(resultadoSQL.getString(UF));
			campaign.setCampaignMaximumExpenseDeclared(resultadoSQL.getFloat(DESPESA_MAX_DECLARADA));
			campaign.setCampaignTotalExpenseCalculated(resultadoSQL.getFloat(DESPESA_MAX_CALCULADA));
			campaign.setCampaignTotalRevenueCalculated(resultadoSQL.getFloat(RECEITA_MAX_CALCULADA));
			lista.add(campaign);
		}
	}

	/*
	 * This method prepares attributes Position and Results
	 * @param an instance of Class Position
	 * @param an instance of Class Result
	 * @param a SQLresult
	 */
	private void PreparaCamposCargoEResultado(Position position, Result result,
			ResultSet resultadoSQL) throws SQLException {
		position.setPositionCode(resultadoSQL.getInt(COD_CARGO));
		result.setResultType(resultadoSQL.getInt(COD_RESULTADO));
	}

	/*
	 * This method prepares attributes Candidate and Party
	 * @param an instance of Class Party
	 * @param an instance of Class Candidate
	 * @param a SQLresult
	 */
	private void PreparaCamposCandidatoEPartido(Party party,
			Candidate candidate, ResultSet resultadoSQL) throws SQLException {
		party.setPartyNumber(resultadoSQL.getInt(NUMERO_PARTIDO));
		candidate.setCandidateElectoralTitle(resultadoSQL.getString(TITULO_CANDIDATO));
	}

	/*
	 * This method retrieves a list of campaigns from a voter
	 * @param an instance of Class Candidate
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> getCampanhasPeloTituloEleitoral(
			Candidate candidate) throws SQLException {
		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		String comandoSQL = SQL_SELECT + " USE INDEX (" + INDEX_CANDIDATO + ")"
				+ " WHERE " + TITULO_CANDIDATO + " = '"
				+ candidate.getCandidateElectoralTitle() + "' ";
		listaCampanha = buscaBD(comandoSQL);
		return listaCampanha;
	}

	/*
	 * This method retrieves a list of campaigns from an acronym and year
	 * @param a String acronym
	 * @param a String year
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> getCampanhasPorSiglaEAno(String sigla, String ano)
			throws SQLException {
		this.partidoDAO = new PartidoDAO();

		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		Party party = this.partidoDAO.getPelaSigla(sigla);
		String comandoSQL = SQL_SELECT + " USE INDEX (" + INDEX_PARTIDO + ", "
				+ INDEX_ANO + ")" + " WHERE " + NUMERO_PARTIDO + " = '"
				+ party.getPartyNumber() + "' AND " + ANO + " = '" + ano + "' ";
		listaCampanha = buscaBD(comandoSQL);
		return listaCampanha;
	}

	/*
	 * This method retrieves information from an instance of Class Campaign
	 * @param an instance of Class Campaign
	 * @return an instance of Class Campaign
	 */
	public Campaign getPeloAnoNumeroCodCargoEUf(Campaign campaign)
			throws SQLException {
		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		String comandoSQL = SQL_SELECT + " WHERE " + ANO + " = "
				+ campaign.getCampaignYear() + " AND " + UF + " = '" + campaign.getCampaignCountryState()
				+ "' AND " + NUM_CANDIDATO + " = '"
				+ campaign.getCampaignCandidateNumber() + "' AND " + COD_CARGO + " = "
				+ campaign.getCampaignPosition().getPositionCode();
		listaCampanha = buscaBD(comandoSQL);
		if(listaCampanha.isEmpty()) {
			return null;
		} else {
			return listaCampanha.get(0);
		}
	}

	/*
	 * This method retrieves a complete list of campaigns stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> buscaBD(String SQL) throws SQLException {
		ArrayList<Campaign> listaCampanha = new ArrayList<>();
		this.candidatoDAO = new CandidatoDAO();
		this.cargoDAO = new CargoDAO();
		this.partidoDAO = new PartidoDAO();
		this.resultadoDAO = new ResultadoDAO();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Campaign campaign = new Campaign();
				campaign.setCampaignYear(resultadoSQL.getInt(ANO));
				campaign.setCampaignPosition(cargoDAO.getPeloCod(resultadoSQL.getInt(COD_CARGO)));
				campaign.setCampaignMaximumExpenseDeclared(resultadoSQL.getFloat(DESPESA_MAX_DECLARADA));
				campaign.setCampaignTotalExpenseCalculated(resultadoSQL.getFloat(DESPESA_MAX_CALCULADA));
				campaign.setCampaignNameOfUrn(resultadoSQL.getString(NOME_URNA));
				campaign.setCampaignCandidateNumber(resultadoSQL.getInt(NUM_CANDIDATO));
				campaign.setCampaignParty(partidoDAO.getPeloNumero(resultadoSQL.getString(NUMERO_PARTIDO)));
				campaign.setCampaignTotalRevenueCalculated(resultadoSQL.getFloat(RECEITA_MAX_CALCULADA));
				campaign.setCampaignResult(resultadoDAO.getPeloCod(resultadoSQL.getInt(COD_RESULTADO)));
				campaign.setCampaignCountryState(resultadoSQL.getString(UF));
				campaign.setCampaignCandidate(candidatoDAO.getCandidatoPeloTitulo(resultadoSQL.getString(TITULO_CANDIDATO)));
				if(campaign != null) {
					listaCampanha.add(campaign);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("CampanhaDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaCampanha;
	}
	
	/*
	 * This method retrieves a list of campaigns for the functionality of the Top Five Graphics
	 * @param a String with the position
	 * @param an Integer with the year
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> TopFive(String cargo, Integer ano) throws SQLException {
		int codigo = 0;
		switch(cargo.toLowerCase()) {
		
			case "presidente": 
				codigo = 1;
				break;
			case "governador": 
				codigo = 3;
				break;
			case "senador": 
				codigo = 5;
				break;
			default: 
				return null;
		}
		
		String comandoSQL = SQL_SELECT + " WHERE " + ANO + " = " + ano + " and " 
		                   +COD_CARGO + " = " + codigo + " ORDER BY " + DESPESA_MAX_DECLARADA
		                   +" DESC LIMIT 5";
		
		return buscaBD(comandoSQL);
	}
}