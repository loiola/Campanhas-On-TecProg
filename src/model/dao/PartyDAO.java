package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Party;
import parse.ParseDAO;

public class PartyDAO extends BasicDAO<Party> implements ParseDAO<Party> {

	/*
	 * Class for manipulating the data about political parties
	 */
	
	// Constants
	private static final String DATABASE_PARTY_TABLE_NAME = "partido";
	private static final String DATABASE_PARTY_NUMBER = "numero";
	private static final String DATABASE_PARTY_ACRONYM = "sigla";
	private static final String DATABASE_PARTY_NAME = "nome";
	private static final String DATABASE_PARTY_DEFERMENT = "deferimento";
	
	private static final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO " + DATABASE_PARTY_TABLE_NAME
			+ " (" + DATABASE_PARTY_NUMBER + ", " + DATABASE_PARTY_ACRONYM + ", " + DATABASE_PARTY_NAME + ", " + DATABASE_PARTY_DEFERMENT
			+ ") " + "values (?, ?, ?, ?)";
	private static final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_PARTY_TABLE_NAME;

	// Constructors
	public PartyDAO() {
		super(DATABASE_PARTY_TABLE_NAME, CompareTwoPartiesAcronym.ACRONYM);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal parties through acronym
	 */
	public enum CompareTwoPartiesAcronym implements Comparator<Party> {
		ACRONYM {
			@Override
			public int compare(Party p1, Party p2) {
				
				// Variable that stores the logic state of the comparison between
				// two parties by Acronym
				int auxiliaryReturn = p1.getPartyAcronym().compareToIgnoreCase(p2.getPartyAcronym());
				
				return auxiliaryReturn;
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
	 * This method prepares a list of political parties to be registered
	 * @param an ArrayList<Party>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Party> partyList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		for(Party party : partyList) {
			daoSQLInstruction.setInt(1, party.getPartyNumber());
			daoSQLInstruction.setString(2, party.getPartyAcronym());
			daoSQLInstruction.setString(3, party.getPartyName());
			daoSQLInstruction.setString(4, party.getPartyConcession());
			daoSQLInstruction.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Party>
	 * @param an ArrayList<Party>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Party> partyList,
			ResultSet sqlResult) throws SQLException {
		while(sqlResult.next()) {
			Party party = new Party();
			party.setPartyName(sqlResult.getString(DATABASE_PARTY_NAME));
			party.setPartyNumber(sqlResult.getInt(DATABASE_PARTY_NUMBER));
			party.setPartyAcronym(sqlResult.getString(DATABASE_PARTY_ACRONYM));
			party.setPartyConcession(sqlResult.getString(DATABASE_PARTY_DEFERMENT));

			partyList.add(party);
		}
	}

	/*
	 * This method retrieves a party through the acronym
	 * @param a String with the acronym
	 * @return an instance of Class Party
	 */
	public Party getPartyByAcronym(String partyAcronym) throws SQLException {
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT
				+ " WHERE " + DATABASE_PARTY_ACRONYM + " = '" + partyAcronym + "'";
		return searchDonorInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
	}

	/*
	 * This method retrieves a party through the number
	 * @param a String with the number
	 * @return an instance of Class Party
	 */
	public Party getPartyByNumber(String partyNumber) throws SQLException {
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT
				+ " WHERE " + DATABASE_PARTY_NUMBER + " = '" + partyNumber + "'";
		return searchDonorInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
	}

	/*
	 * This method retrieves a complete list of political parties stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Party>
	 */
	private Party searchDonorInDatabaseUsingSQLCommandConfiguredBefore(String sqlCommandConfiguredBefore) throws SQLException{
		
		Party party = new Party();
		
		try {
			this.connection = new DatabaseConnection().getConnection();
	
			this.daoSQLInstruction = this.connection.prepareStatement(sqlCommandConfiguredBefore);
	
			ResultSet sqlResult = (ResultSet) daoSQLInstruction.executeQuery();
	
			while(sqlResult.next()) {
				party.setPartyAcronym(sqlResult.getString(DATABASE_PARTY_ACRONYM));
				party.setPartyName(sqlResult.getString(DATABASE_PARTY_NAME));
				party.setPartyConcession(sqlResult.getString(DATABASE_PARTY_DEFERMENT));
				party.setPartyNumber(sqlResult.getInt(DATABASE_PARTY_NUMBER));
			}
	
			if(this.daoSQLInstruction != null) {
				daoSQLInstruction.close();
			}

			if(this.connection != null) {
				connection.close();
			}
		} catch(SQLException e) {
			throw new SQLException("PartyDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return party;
	}
}