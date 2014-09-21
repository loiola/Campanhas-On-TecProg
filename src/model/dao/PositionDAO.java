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
		super(DATABASE_POSITION_TABLE_NAME, CompareTwoPositionsCode.CODIGO);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal positions through code
	 */
	public enum CompareTwoPositionsCode implements Comparator<Position> {
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
	 * This method retrieves a position through code
	 * @param an Integer with the code of position
	 * @return an instance of Class Position
	 */
	public Position getPositionByCode(Integer positionCode) throws SQLException {
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE " + DATABASE_POSITION_CODE +" = "+positionCode+" ";
		return searchDonorInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
	}
	
	/*
	 * This method retrieves a position by describing
	 * @param a String with the description
	 * @return an instance of Class Position
	 */
	public Position getPositionByDescription(String positionDescription) throws SQLException {
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
						  + DATABASE_POSITION_DESCRIPTION +" like '%"+positionDescription+"%' ";
		return searchDonorInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
	}
	
	/*
	 * This method retrieves an instance of Position stored in the database
	 * @param a String with the SQL command
	 * @return an instance of Class Position
	 */
	public Position searchDonorInDatabaseUsingSQLCommandConfiguredBefore(String sqlCommandConfiguredBefore) throws SQLException {
		Position position = new Position();
		String comandoSQL = sqlCommandConfiguredBefore;
		
		try {
			this.connection = new DatabaseConnection().getConnection();

			this.daoSQLInstruction = this.connection.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) daoSQLInstruction.executeQuery();
			while(resultadoSQL.next()) {
				position.setPositionCode(resultadoSQL.getInt(DATABASE_POSITION_CODE));
				position.setPositionDescription(resultadoSQL.getString(DATABASE_POSITION_DESCRIPTION));
			}
		} catch(SQLException e) {
			throw new SQLException("PositionDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return position;
	}
}