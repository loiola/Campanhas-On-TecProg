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

public class CampaignDAO extends BasicDAO<Campaign> {
	
	/*
	 * Class for manipulating the data about election campaigns
	 */

	//Attributes
	private CandidateDAO candidateDAO;
	private PartyDAO partyDAO;
	private PositionDAO positionDAO;
	private ResultDAO resultDAO;

	// Constants
	private static final String DATABASE_CAMPAIGN_TABLE_NAME = "campanha";
	private final String DATABASE_CAMPAIGN_IDENTIFIER = "id_campanha";
	private final String DATABASE_CAMPAIGN_YEAR = "ano";
	private final String DATABASE_CAMPAIGN_CANDIDATE_NUMBER = "numero_candidato";
	private final String DATABASE_CAMPAIGN_RESULT_CODE = "resultado_cod_resultado";
	private final String DATABASE_CAMPAIGN_POSITION_CODE = "cargo_cod_cargo";
	private final String DATABASE_CAMPAIGN_PARTY_NUMBER = "partido_numero";
	private final String DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE = "candidato_titulo_eleitoral";
	private final String DATABASE_CAMPAIGN_NAME_OF_URN = "nome_de_urna";
	private final String DATABASE_CAMPAIGN_COUNTRY_STATE = "uf";
	private final String DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_DECLARED = "despesa_maxima_declarada";
	private final String DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_CALCULATED = "despesa_maxima_calculada";
	private final String DATABASE_CAMPAIGN_MAXIMUM_REVENUE_CALCULATED = "receita_maxima_calculada";

	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_CAMPAIGN_TABLE_NAME;
	private final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO " + DATABASE_CAMPAIGN_TABLE_NAME + " (" + DATABASE_CAMPAIGN_IDENTIFIER
			+ ", " + DATABASE_CAMPAIGN_YEAR + ", " + DATABASE_CAMPAIGN_CANDIDATE_NUMBER + ", " + DATABASE_CAMPAIGN_RESULT_CODE + ", "
			+ DATABASE_CAMPAIGN_POSITION_CODE + ", " + DATABASE_CAMPAIGN_PARTY_NUMBER + ", " + DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE
			+ ", " + DATABASE_CAMPAIGN_NAME_OF_URN + ", " + DATABASE_CAMPAIGN_COUNTRY_STATE + ", " + DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_DECLARED
			+ ", " + DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_CALCULATED + ", " + DATABASE_CAMPAIGN_MAXIMUM_REVENUE_CALCULATED
			+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String DATABASE_CAMPAIGN_CANDIDATE_INDEX = "campanha_fk_3";
	private final String DATABASE_CAMPAIGN_PARTY_INDEX = "campanha_fk_4";
	private final String DATABASE_CAMPAIGN_YEAR_INDEX = "campanha_sk_1";

	// Constructors
	public CampaignDAO() {
		super(DATABASE_CAMPAIGN_TABLE_NAME, null);
	}

	// Other methods
	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSQLInsertCommand() {
		return DATABASE_SQL_COMMAND_INSERT;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSQLSelectCommand() {
		return DATABASE_SQL_COMMAND_SELECT;
	}

	/*
	 * This method returns an SQL command for selecting a candidate by the name of urn
	 * @param a String with the name of urn
	 * @return a String with the SQL command
	 */
	public String getSQLSelectNameOfUrnCommand(String nameOfUrn) {
		return "SELECT " + DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE + " FROM " + DATABASE_CAMPAIGN_TABLE_NAME
				+ " WHERE " + DATABASE_CAMPAIGN_NAME_OF_URN + " LIKE '%" + nameOfUrn + "%'";
	}

	/*
	 * This method prepares a list of campaigns to be registered
	 * @param an ArrayList<Campaign>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Campaign> campaignList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		for(Campaign campaign : campaignList) {
			daoSQLInstruction.setInt(1, campaign.getCampaignIdentifier());
			daoSQLInstruction.setInt(2, campaign.getCampaignYear());
			daoSQLInstruction.setInt(3, campaign.getCampaignCandidateNumber());
			daoSQLInstruction.setInt(4, campaign.getCampaignResult().getResultType());
			daoSQLInstruction.setInt(5, campaign.getCampaignPosition().getPositionCode());
			daoSQLInstruction.setInt(6, campaign.getCampaignParty().getPartyNumber());
			daoSQLInstruction.setString(7, campaign.getCampaignCandidate()
					.getCandidateElectoralTitle());
			daoSQLInstruction.setString(8, campaign.getCampaignNameOfUrn());
			daoSQLInstruction.setString(9, campaign.getCampaignCountryState());
			daoSQLInstruction.setFloat(10, campaign.getCampaignMaximumExpenseDeclared());
			daoSQLInstruction.setFloat(11, campaign.getCampaignTotalExpenseCalculated());
			daoSQLInstruction.setFloat(12, campaign.getCampaignTotalRevenueCalculated());
			daoSQLInstruction.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Campaigns>
	 * @param an ArrayList<Campaign>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Campaign> campaignList, ResultSet sqlResult) throws SQLException {
		while(sqlResult.next()) {
			Position position = new Position();
			Result result = new Result();
			Party party = new Party();
			Candidate candidate = new Candidate();
			adjustPositionCodeAndResultCode(position, result, sqlResult);
			adjustPartyNumberAndCandidateElectoralTitle(party, candidate, sqlResult);

			Campaign campaign = new Campaign();
			campaign.setCampaignIdentifier(sqlResult.getInt(DATABASE_CAMPAIGN_IDENTIFIER));
			campaign.setCampaignResult(result);
			campaign.setCampaignPosition(position);
			campaign.setCampaignParty(party);
			campaign.setCampaignCandidate(candidate);
			campaign.setCampaignYear(sqlResult.getInt(DATABASE_CAMPAIGN_YEAR));
			campaign.setCampaignCandidateNumber(sqlResult.getInt(DATABASE_CAMPAIGN_CANDIDATE_NUMBER));
			campaign.setCampaignNameOfUrn(sqlResult.getString(DATABASE_CAMPAIGN_NAME_OF_URN));
			campaign.setCampaignCountryState(sqlResult.getString(DATABASE_CAMPAIGN_COUNTRY_STATE));
			campaign.setCampaignMaximumExpenseDeclared(sqlResult.getFloat(DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_DECLARED));
			campaign.setCampaignTotalExpenseCalculated(sqlResult.getFloat(DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_CALCULATED));
			campaign.setCampaignTotalRevenueCalculated(sqlResult.getFloat(DATABASE_CAMPAIGN_MAXIMUM_REVENUE_CALCULATED));
			campaignList.add(campaign);
		}
	}

	/*
	 * This method prepares attributes Position and Results
	 * @param an instance of Class Position
	 * @param an instance of Class Result
	 * @param a SQLresult
	 */
	private void adjustPositionCodeAndResultCode(Position position, Result result,
			ResultSet sqlResult) throws SQLException {
		position.setPositionCode(sqlResult.getInt(DATABASE_CAMPAIGN_POSITION_CODE));
		result.setResultType(sqlResult.getInt(DATABASE_CAMPAIGN_RESULT_CODE));
	}

	/*
	 * This method prepares attributes Candidate and Party
	 * @param an instance of Class Party
	 * @param an instance of Class Candidate
	 * @param a SQLresult
	 */
	private void adjustPartyNumberAndCandidateElectoralTitle(Party party,
			Candidate candidate, ResultSet sqlResult) throws SQLException {
		party.setPartyNumber(sqlResult.getInt(DATABASE_CAMPAIGN_PARTY_NUMBER));
		candidate.setCandidateElectoralTitle(sqlResult.getString(DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE));
	}

	/*
	 * This method retrieves a list of campaigns from a voter
	 * @param an instance of Class Candidate
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> getCampaignArrayDataByCandidateElectoralTitle(
			Candidate candidate) throws SQLException {
		ArrayList<Campaign> campaignList = new ArrayList<>();
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " USE INDEX (" + DATABASE_CAMPAIGN_CANDIDATE_INDEX + ")"
				+ " WHERE " + DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE + " = '"
				+ candidate.getCandidateElectoralTitle() + "' ";
		campaignList = searchCampaignInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
		return campaignList;
	}

	/*
	 * This method retrieves a list of campaigns from an acronym and year
	 * @param a String acronym
	 * @param a String year
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> getCampaignArrayDataByPartyAcronymAndElectionYear(String partyAcronym, String electionYear)
			throws SQLException {
		this.partyDAO = new PartyDAO();

		ArrayList<Campaign> campaignList = new ArrayList<>();
		Party party = this.partyDAO.getPartyByAcronym(partyAcronym);
		String comandoSQL = DATABASE_SQL_COMMAND_SELECT + " USE INDEX (" + DATABASE_CAMPAIGN_PARTY_INDEX + ", "
				+ DATABASE_CAMPAIGN_YEAR_INDEX + ")" + " WHERE " + DATABASE_CAMPAIGN_PARTY_NUMBER + " = '"
				+ party.getPartyNumber() + "' AND " + DATABASE_CAMPAIGN_YEAR + " = '" + electionYear + "' ";
		campaignList = searchCampaignInDatabaseUsingSQLCommandConfiguredBefore(comandoSQL);
		return campaignList;
	}

	/*
	 * This method retrieves information from an instance of Class Campaign
	 * @param an instance of Class Campaign
	 * @return an instance of Class Campaign
	 */
	public Campaign getCampaignDataByElectionYearAndCandidateNumberAndPositionCodeAndCountryState(Campaign campaign)
			throws SQLException {
		ArrayList<Campaign> campaignList = new ArrayList<>();
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE " + DATABASE_CAMPAIGN_YEAR + " = "
				+ campaign.getCampaignYear() + " AND " + DATABASE_CAMPAIGN_COUNTRY_STATE + " = '" + campaign.getCampaignCountryState()
				+ "' AND " + DATABASE_CAMPAIGN_CANDIDATE_NUMBER + " = '"
				+ campaign.getCampaignCandidateNumber() + "' AND " + DATABASE_CAMPAIGN_POSITION_CODE + " = "
				+ campaign.getCampaignPosition().getPositionCode();
		campaignList = searchCampaignInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
		if(campaignList.isEmpty()) {
			return null;
		} else {
			return campaignList.get(0);
		}
	}

	/*
	 * This method retrieves a complete list of campaigns stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> searchCampaignInDatabaseUsingSQLCommandConfiguredBefore(String sqlCommandConfiguredBefore) throws SQLException {
		ArrayList<Campaign> campaignList = new ArrayList<>();
		this.candidateDAO = new CandidateDAO();
		this.positionDAO = new PositionDAO();
		this.partyDAO = new PartyDAO();
		this.resultDAO = new ResultDAO();

		try {
			this.connection = new DatabaseConnection().getConnection();

			String sqlCommand = sqlCommandConfiguredBefore;
			this.daoSQLInstruction = this.connection.prepareStatement(sqlCommand);
			ResultSet resultadoSQL = (ResultSet) daoSQLInstruction.executeQuery();

			while(resultadoSQL.next()) {
				Campaign campaign = new Campaign();
				campaign.setCampaignYear(resultadoSQL.getInt(DATABASE_CAMPAIGN_YEAR));
				campaign.setCampaignPosition(positionDAO.getPeloCod(resultadoSQL.getInt(DATABASE_CAMPAIGN_POSITION_CODE)));
				campaign.setCampaignMaximumExpenseDeclared(resultadoSQL.getFloat(DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_DECLARED));
				campaign.setCampaignTotalExpenseCalculated(resultadoSQL.getFloat(DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_CALCULATED));
				campaign.setCampaignNameOfUrn(resultadoSQL.getString(DATABASE_CAMPAIGN_NAME_OF_URN));
				campaign.setCampaignCandidateNumber(resultadoSQL.getInt(DATABASE_CAMPAIGN_CANDIDATE_NUMBER));
				campaign.setCampaignParty(partyDAO.getPartyByNumber(resultadoSQL.getString(DATABASE_CAMPAIGN_PARTY_NUMBER)));
				campaign.setCampaignTotalRevenueCalculated(resultadoSQL.getFloat(DATABASE_CAMPAIGN_MAXIMUM_REVENUE_CALCULATED));
				campaign.setCampaignResult(resultDAO.getPeloCod(resultadoSQL.getInt(DATABASE_CAMPAIGN_RESULT_CODE)));
				campaign.setCampaignCountryState(resultadoSQL.getString(DATABASE_CAMPAIGN_COUNTRY_STATE));
				campaign.setCampaignCandidate(candidateDAO.getCandidateByElectoralTitle(resultadoSQL.getString(DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE)));
				if(campaign != null) {
					campaignList.add(campaign);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("CampaignDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return campaignList;
	}
	
	/*
	 * This method retrieves a list of campaigns for the functionality of the Top Five Graphics
	 * @param a String with the position
	 * @param an Integer with the year
	 * @return an ArrayList<Campaign>
	 */
	public ArrayList<Campaign> generateTopFiveCampaignListAboutMaximumExpenseDeclared(String position, Integer electionYear) throws SQLException {
		int positionCode = 0;
		switch(position.toLowerCase()) {
		
			case "presidente": 
				positionCode = 1;
				break;
			case "governador": 
				positionCode = 3;
				break;
			case "senador": 
				positionCode = 5;
				break;
			default: 
				return null;
		}
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE " + DATABASE_CAMPAIGN_YEAR + " = " + electionYear + " and " 
		                   +DATABASE_CAMPAIGN_POSITION_CODE + " = " + positionCode + " ORDER BY " + DATABASE_CAMPAIGN_MAXIMUM_EXPENSE_DECLARED
		                   +" DESC LIMIT 5";
		
		return searchCampaignInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
	}
}