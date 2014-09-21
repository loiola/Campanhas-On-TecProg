package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	/*
	 * This class contains methods for managing connection to the database
	 */
	
	// Attributes
	private Connection connection;
	private Statement afirmation;
	
	// Constants
	private static String databasePath = "jdbc:mysql://";	
	private static String serverName = "localhost";
	private static String databaseName = "c_on";
	private static String databaseUser = "root";
	private static String databasePassword = "root";
	
	// Constructors
	public DatabaseConnection() {
		
	}
	
	// Other methods
	/*
	 * This method returns a connection to the database
	 * @return an instance of Class Connection
	 */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(databasePath + serverName + "/" + databaseName, databaseUser, databasePassword);
			return connection;
		} catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	/*
	 * This method creates a database
	 * @param a String with the name of database
	 */
	public void createDatabaseSchema(String newDatabaseSchemaName) throws SQLException {
		try {
			this.connection = new DatabaseConnection().getConnection();
			this.afirmation = this.connection.createStatement();

			String comandoSQL = "create database if not exists " + newDatabaseSchemaName;
			
			this.afirmation.executeUpdate(comandoSQL);
		} catch(Exception e) {
			throw new SQLException("DatabaseConnection - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
	}
	
	/*
	 * This method changes the database name
	 * @param a String with the new name
	 */
	public void adjustDatabaseSchemaName(String newDatabaseSchemaName) {
		databaseName = newDatabaseSchemaName;
	}
	
	/*
	 * This method performs an import of SQL commands contained in a file
	 * @param a String with the description of SQL file
	 */
	public void readSQLCommandFromFile(String sqlFile) throws SQLException {
		try {
			this.connection = new DatabaseConnection().getConnection();
			this.afirmation = this.connection.createStatement();
			
			String command[] = getLinesFromFile(sqlFile);
			for(String linha : command) {
				this.afirmation.execute(linha);
			}	
		} catch(Exception e) {
			throw new SQLException("DatabaseConnection - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
	}
	
	/*
	 * This method performs the deletion of a database
	 */
	public void dropDatabaseName() throws SQLException {
		try {
			this.connection = new DatabaseConnection().getConnection();
			this.afirmation = this.connection.createStatement();
			
			String comandoSQL = "drop database if exists " + databaseName;
			
			this.afirmation.executeUpdate(comandoSQL);
		} catch(Exception e) {
			throw new SQLException("DatabaseConnection - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
	}

	/*
	 * This method modifies the location of the database
	 * @param a String with the new place for database
	 */
	public void setDatabasePath(String newDatabasePath) {
		databasePath = newDatabasePath;
	}
	
	/*
	 * This method retrieves the current location of the database
	 * @return a String with the place of database
	 */
	public String getDatabasePath() {
		return databasePath;
	}
	
	/*
	 * This method closes the connection to the database
	 */
	private void closeDatabaseConnection() throws SQLException {
		if(this.connection != null) {
			this.connection.close();
		}
	}
	
	/*
	 * This method performs the reading of a file and stores the command
	 * @param an array of strings with the command
	 */
	private String[] getLinesFromFile(String sqlFile) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(sqlFile)));
		String commandLine = "";
		String command[];
		String readLine;
		while((readLine = bufferedReader.readLine()) != null) {
			if(!readLine.isEmpty()) {
				commandLine += readLine;
			}
		}
		command = commandLine.split(";");
		bufferedReader.close();
		return command;
	}
}