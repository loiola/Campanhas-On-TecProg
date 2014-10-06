package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Result;
import parse.ParseDAO;

public class ResultDAO extends BasicDAO<Result> implements ParseDAO<Result> {

	/*
	 * Class for manipulating the data about political result
	 */
	
	// Constants
	private static final String DATABASE_RESULT_TABLE_NAME = "resultado";
	private static final String DATABASE_RESULT_CODE = "cod_resultado";
	private static final String DATABASE_RESULT_DESCRIPTION = "descricao";
	
	private static final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO "+ DATABASE_RESULT_TABLE_NAME
			+" (" +DATABASE_RESULT_CODE+", "+ DATABASE_RESULT_DESCRIPTION + ") values (?, ?)" ;
	private static final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_RESULT_TABLE_NAME;
	
	// Constructors
	public ResultDAO() {
		super(DATABASE_RESULT_TABLE_NAME, CompareTwoResultsType.RESULT_TYPE);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal result through Title code
	 */
	public enum CompareTwoResultsType implements Comparator<Result> {
		RESULT_TYPE {
			@Override
			public int compare(Result r1, Result r2) {
				
				// Variable that stores the logic state of the comparison between
				// two results by Code
				int auxiliaryReturn = r1.getResultType().compareTo(r2.getResultType());
				
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
	 * This method prepares a list of result to be registered
	 * @param an ArrayList<Result>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Result> resultList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		for(Result result : resultList) {
			daoSQLInstruction.setInt(1, result.getResultType());
			daoSQLInstruction.setString(2, result.getResultDescription());
			daoSQLInstruction.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<result>
	 * @param an ArrayList<Result>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Result> resultList,
			ResultSet sqlResult) throws SQLException {
		while(sqlResult.next()) {
			Result result = new Result();
			result.setResultType(sqlResult.getInt(DATABASE_RESULT_CODE));
			result.setResultDescription(sqlResult.getString(DATABASE_RESULT_DESCRIPTION));
			resultList.add(result);
		}
	}

	/*
	 * This method retrieves a receipt through the code
	 * @param an Integer representing a code
	 * @return an instance of Class Result
	 */
	public Result getResultByCode(Integer resultCode) throws SQLException {
		Result result = new Result();
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE " + DATABASE_RESULT_CODE +" = "+ resultCode +" ";
		try {
			this.connection = new DatabaseConnection().getConnection();
	
			this.daoSQLInstruction = this.connection.prepareStatement(sqlCommand);
	
			ResultSet sqlResult = (ResultSet) daoSQLInstruction.executeQuery();
			
			while(sqlResult.next()) {
				result.setResultType(sqlResult.getInt(DATABASE_RESULT_CODE));
				result.setResultDescription(sqlResult.getString(DATABASE_RESULT_DESCRIPTION));
			}

		} catch(SQLException e) {
			throw new SQLException("ResultDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return result;
	}
}