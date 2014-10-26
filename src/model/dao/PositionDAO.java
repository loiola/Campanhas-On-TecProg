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
	private static final String DATABASE_POSITION_TABLE_NAME = "cargo";
	private static final String DATABASE_POSITION_CODE = "cod_cargo";
	private static final String DATABASE_POSITION_DESCRIPTION = "descricao";
	private static final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO " + DATABASE_POSITION_TABLE_NAME
			+ " (" + DATABASE_POSITION_CODE + ", " + DATABASE_POSITION_DESCRIPTION + ") "
			+ "values (?, ?)";
	private static final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_POSITION_TABLE_NAME;

	// Constructors
	public PositionDAO() {
		super(DATABASE_POSITION_TABLE_NAME, CompareTwoPositionsCode.POSITION_CODE);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal positions through code
	 */
	public enum CompareTwoPositionsCode implements Comparator<Position> {
		POSITION_CODE {
			@Override
			public int compare(Position c1, Position c2) {
				
				// Variable that stores the logic state of the comparison between
				// two positions by Code
				int comparisonResult = c1.getPositionCode().compareTo(c2.getPositionCode());
				
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
	 * This method prepares a list of positions to be registered
	 * @param an ArrayList<Position>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Position> positionList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		
		for(Position position : positionList) {
			daoSQLInstruction.setInt(1, position.getPositionCode());
			daoSQLInstruction.setString(2, position.getPositionDescription());
			daoSQLInstruction.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Position>
	 * @param an ArrayList<Position>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Position> positionList,
			ResultSet sqlResult) throws SQLException {
		
		while(sqlResult.next()) {
			Position position = new Position();
			position.setPositionCode(sqlResult.getInt(DATABASE_POSITION_CODE));
			position.setPositionDescription(sqlResult.getString(DATABASE_POSITION_DESCRIPTION));
			positionList.add(position);
		}
	}
	
	/*
	 * This method makes the SQL query command according to the position code informed
	 * @param an Integer who define the position code of candidate
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForPosition(
			final Integer positionCode) throws SQLException {
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				+ DATABASE_POSITION_CODE + " = " + positionCode + " ";
		
		return sqlCommand;
	}
	
	/*
	 * This method retrieves a position through code
	 * @param an Integer with the code of position
	 * @return an instance of Class Position
	 */
	public Position getPositionByCode(Integer positionCode) throws SQLException {
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForPosition(positionCode);
		
		// Returning the Party
		Position positionRecovered;
		positionRecovered = searchPositionInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
		return positionRecovered; 
	}
	
	/*
	 * This method makes the SQL query command according to the position description informed
	 * @param a String who define the position description of candidate
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForDescription(
			final String positionDescription) throws SQLException {
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				  + DATABASE_POSITION_DESCRIPTION + " like '%" + positionDescription + "%' ";
		
		return sqlCommand;
	}
	
	/*
	 * This method retrieves a position by describing
	 * @param a String with the description
	 * @return an instance of Class Position
	 */
	public Position getPositionByDescription(String positionDescription) throws SQLException {
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForDescription(positionDescription);
		
		// Returning the Party
		Position positionRecovered;
		positionRecovered = searchPositionInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
		return positionRecovered; 
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
	 * This method retrieves an instance of Position stored in the database
	 * @param a String with the SQL command
	 * @return an instance of Class Position
	 */
	public Position searchPositionInDatabaseUsingSQLCommandConfiguredBefore(
			String sqlCommandConfiguredBefore) throws SQLException {
		
		Position position = new Position();
		
		try {
			// Preparing connection to the database
			ResultSet resultSQL = establishingConnectionToTheDatabaseToQuery(sqlCommandConfiguredBefore);

			// Iterations search
			while(resultSQL.next()) {
				position.setPositionCode(resultSQL.getInt(DATABASE_POSITION_CODE));
				position.setPositionDescription(resultSQL.getString(DATABASE_POSITION_DESCRIPTION));
			}
		} catch(SQLException e) {
			throw new SQLException("PositionDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return position;
	}
}