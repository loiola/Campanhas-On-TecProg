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
	private static final String DATABASE_CANDIDATE_TABLE_NAME = "candidato";
	private final String DATABASE_CANDIDATE_ELECTORAL_TITLE = "titulo_eleitoral";
	private final String DATABASE_CANDIDATE_NAME = "nome";
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_CANDIDATE_TABLE_NAME;
	private final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO " + DATABASE_CANDIDATE_TABLE_NAME + " ("
			+ DATABASE_CANDIDATE_ELECTORAL_TITLE + ", " + DATABASE_CANDIDATE_NAME + ") VALUES(?,?)";

	private final String DATABASE_CANDIDATE_NAME_INDEX = "candidato_sk_1";

	// Constructors
	public CandidateDAO() {
		super(DATABASE_CANDIDATE_TABLE_NAME, CompareTwoCandidatesElectoralTitle.ELECTORAL_TITLE);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal candidates through Title Voter
	 */
	public enum CompareTwoCandidatesElectoralTitle implements Comparator<Candidate> {
		ELECTORAL_TITLE {
			@Override
			public int compare(Candidate c1, Candidate c2) {
				
				// Variable that stores the logic state of the comparison between
				// two candidates by Electoral Title
				int comparisonResult = c1.getCandidateElectoralTitle().compareTo(
						c2.getCandidateElectoralTitle());
				
				return comparisonResult;
			}
		};
	}

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
	 * This method prepares a list of candidates to be registered
	 * @param an ArrayList<Candidate>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Candidate> candidateList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		
		for(Candidate candidate : candidateList) {
			daoSQLInstruction.setString(1, candidate.getCandidateElectoralTitle());
			daoSQLInstruction.setString(2, candidate.getCandidateName());
			daoSQLInstruction.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Candidate>
	 * @param an ArrayList<Candidate>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Candidate> candidateList,
			ResultSet sqlResult) throws SQLException {
		
		while(sqlResult.next()) {
			Candidate candidate = new Candidate();
			
			candidate.setCandidateName(sqlResult.getString(DATABASE_CANDIDATE_NAME));
			candidate.setCandidateElectoralTitle(sqlResult.getString(
					DATABASE_CANDIDATE_ELECTORAL_TITLE));
			candidateList.add(candidate);
		}
	}

	/*
	 * This method makes the SQL query command according to the
	 * electoral Title informed
	 * @param a String who define the electoral Title
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForElectoralTitle(final String electoralTitle){
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				+ DATABASE_CANDIDATE_ELECTORAL_TITLE + " = '"+ electoralTitle + "'";
		
		return sqlCommand;
	}
	
	/*
	 * This method retrieves a candidate through the electoral title
	 * @param a String with the electoral title
	 * @return an instance of Class Candidate
	 */
	public Candidate getCandidateByElectoralTitle(String electoralTitle) {
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForElectoralTitle(electoralTitle);
		
		// Returning the list of campaign
		try {
			LinkedList<Candidate> candidateList = new LinkedList<>();
			candidateList = searchCandidateInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
			return candidateList.get(0);
			
		} catch(SQLException e) {
			Candidate candidate = new Candidate();
			candidate.setCandidateElectoralTitle("-1");
			return candidate;
		}
	}
	
	/*
	 * This method makes the SQL query command according to the
	 * name of candidate informed
	 * @param a String who define the name of candidate
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForName(final String candidateName){
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " USE INDEX ("
				+ DATABASE_CANDIDATE_NAME_INDEX + ")" + " WHERE " + DATABASE_CANDIDATE_NAME
				+ " LIKE '%" + candidateName + "%' " + " OR " + DATABASE_CANDIDATE_ELECTORAL_TITLE
				+ " IN (" + this.campaignDAO.getSQLSelectNameOfUrnCommand(candidateName) + ")";
		
		return sqlCommand;
	}

	/*
	 * This method retrieves a list of candidates by name
	 * @param a String with the name
	 * @return a LinkedList<Candidate>
	 */
	public LinkedList<Candidate> getCandidateListByName(String candidateName) {
		this.campaignDAO = new CampaignDAO();
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForName(candidateName);
		
		// Returning the list of campaign
		try {
			LinkedList<Candidate> candidateList = new LinkedList<>();
			candidateList = searchCandidateInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
			return candidateList;
			
		} catch(SQLException e) {
			return null;
		}
	}
	
	/*
	 * This method to connect to the database to query SQL informed
	 * @param a String with the SQL command
	 * @return a result containing commands SQL
	 */
	private ResultSet establishingConnectionToTheDatabaseToQuery(
			final String sqlCommandConfiguredBefore) throws SQLException{
		
		this.connection = new DatabaseConnection().getConnection();

		String sqlCommand = sqlCommandConfiguredBefore;
		this.daoSQLInstruction = this.connection.prepareStatement(sqlCommand);
		ResultSet resultSQL = (ResultSet) daoSQLInstruction.executeQuery();
		
		return resultSQL;
	}

	/*
	 * This method retrieves a complete list of candidates stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Candidate>
	 */
	public LinkedList<Candidate> searchCandidateInDatabaseUsingSQLCommandConfiguredBefore(
			String sqlCommandConfiguredBefore) throws SQLException {
		
		LinkedList<Candidate> candidateList = new LinkedList<>();

		try {
			
			// Preparing connection to the database
			ResultSet resultSQL = establishingConnectionToTheDatabaseToQuery(sqlCommandConfiguredBefore);

			// Iterations search
			while(resultSQL.next()) {
				Candidate candidate = new Candidate();
				candidate.setCandidateName(resultSQL.getString(
						DATABASE_CANDIDATE_NAME));
				candidate.setCandidateElectoralTitle(resultSQL.getString(
						DATABASE_CANDIDATE_ELECTORAL_TITLE));
				if(candidate != null) {
					candidateList.add(candidate);
				}
			}			
		} catch(SQLException e) {
			throw new SQLException("CandidateDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return candidateList;
	}
}