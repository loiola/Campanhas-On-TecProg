package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import parse.ParseDAO;
import parse.ParseException;

public abstract class BasicDAO<O> implements ParseDAO<O> {
	
	/*
	 * Abstract class with generic methods for data management by the subclasses in DAO package
	 */

	// Attributes
	protected Connection connection;
	protected PreparedStatement daoSQLInstruction;
	private Comparator<O> daoComparator;
	private String tableName;
	
	// Constructors
	public BasicDAO(String tableName, Comparator<O> daoComparator) {
		this.tableName = tableName;
		this.daoComparator = daoComparator;
	}
	
	/*
	 * This method registers a list of parse, getting a list of objects of any class
	 * @param an ArrayList<O>
	 */
	@Override
	public void registerObjectArrayListOnParse(ArrayList<O> objectList) throws ParseException {
		try {
			registerUnregisteredObjectArrayListOnDatabase(objectList);
		} catch(Exception e) {
			throw new ParseException(e.getMessage());
		}
	}
	
	/*
	 * This method retrieves a list registered in the parse
	 * @return an ArrayList<O>
	 */
	@Override
	public ArrayList<O> getObjectArrayListFromParse() throws ParseException {
		ArrayList<O> objectList = new ArrayList<>();
		
		try {
			objectList = getObjectArrayListFromDatabase();
		} catch (Exception e) {
			throw new ParseException(e.getMessage());
		}
		return objectList;
	}
	
	/*
	 * This method is responsible for monitoring objects not registered in the parse
	 * and register them
	 * @param an ArrayList<O>
	 */
	public void registerUnregisteredObjectArrayListOnDatabase(ArrayList<O> referenceFromParseObjectList) throws SQLException {
		try {
			ArrayList<O> unregisteredObjectList = getUnregisteredObjectArrayListInDatabase(referenceFromParseObjectList);

			this.connection = new DatabaseConnection().getConexao();
			this.daoSQLInstruction = getSQLInstruction(getSQLInsertCommand());
			this.connection.setAutoCommit(false);
	
			registerObjectArrayListOnBatch(unregisteredObjectList, daoSQLInstruction);
	
			this.daoSQLInstruction.executeBatch();
			this.connection.commit();			
		} catch (Exception e) {
			throw new SQLException(tableName + " - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
	}
	
	/*
	 * This method retrieves a list of registered objects
	 * @param an ArrayList<O>
	 */
	public ArrayList<O> getObjectArrayListFromDatabase() throws SQLException {
		ArrayList<O> objectList = new ArrayList<>();
		
		try {
			this.connection = new DatabaseConnection().getConexao();
			
			String sqlCommand = getSQLSelectCommand();
			
			this.daoSQLInstruction = this.connection.prepareStatement(sqlCommand);
			
			ResultSet sqlResult = (ResultSet) this.daoSQLInstruction.executeQuery();
			
			registerResultSetOnObjectArrayList(objectList, sqlResult);
		} catch(Exception e) {
			throw new SQLException(tableName + " - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return objectList;
	}
	
	/*
	 * This method retrieves a list of objects not registered
	 * @param an ArrayList<O>
	 * @return an ArrayList<O>
	 */
	protected ArrayList<O> getUnregisteredObjectArrayListInDatabase(ArrayList<O> referenceObjectList) throws SQLException {
		ArrayList<O> unregisteredObjectList = new ArrayList<>();
		ArrayList<O> registeredObjectList = getObjectArrayListFromDatabase();
		
		if(this.daoComparator != null) {
			Collections.sort(registeredObjectList, this.daoComparator);
			for(O object : referenceObjectList) {
				if(Collections.binarySearch(registeredObjectList, object, this.daoComparator) < 0) {
					unregisteredObjectList.add(object);
				}
			}
		} else {
			for(O object : referenceObjectList) {
				if(!registeredObjectList.contains(object)) {
					unregisteredObjectList.add(object);
				}
			}
		}
		return unregisteredObjectList;
	}
	
	// Signature of the method to recover the insert SQL command
	protected abstract String getSQLInsertCommand();
	
	// Signature of the method to recover the select SQL command
	protected abstract String getSQLSelectCommand();
	
	// Method signature for formalization joined a list of instances in the database
	protected abstract void registerObjectArrayListOnBatch(ArrayList<O> objectList, PreparedStatement daoSQLInstruction) throws SQLException;
	
	// Signature of the method to populates the ArrayList<O>
	protected abstract void registerResultSetOnObjectArrayList(ArrayList<O> objectList, ResultSet sqlResult) throws SQLException ;
	
	/*
	 * This method closes the connection with the database
	 */
	protected void closeDatabaseConnection() throws SQLException {
		if(this.daoSQLInstruction != null) {
			this.daoSQLInstruction.close();
		}
		if(this.connection != null) {
			this.connection.close();
		}
	}
	
	/*
	 * This method prepares statement for transactions in the database
	 */
	protected PreparedStatement getSQLInstruction(String sqlCommand) throws SQLException {		
		return this.connection.prepareStatement(sqlCommand);
	}
}