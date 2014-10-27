package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Supplier;
import parse.ParseDAO;

public class SupplierDAO extends BasicDAO<Supplier> implements ParseDAO<Supplier> {
	
	/*
	 * Class for manipulating the data about supplier
	 */
	
	// Constants	
	private static final String DATABASE_SUPPLIER_TABLE_NAME = "fornecedor";
	private static final String DATABASE_SUPPLIER_PERSON_REGISTER = "cpf_cnpj";
	private static final String DATABASE_SUPPLIER_NAME = "nome";
	private static final String DATABASE_SUPPLIER_COUNTRY_STATE = "uf";
	private static final String DATABASE_SUPPLIER_REGISTER_SITUATION = "situacao_cadastral";
	
	private static final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO " + DATABASE_SUPPLIER_TABLE_NAME
			+ " (" + DATABASE_SUPPLIER_PERSON_REGISTER + ", " + DATABASE_SUPPLIER_NAME + ", " + DATABASE_SUPPLIER_COUNTRY_STATE + ", " + DATABASE_SUPPLIER_REGISTER_SITUATION + ") "
			+ "VALUES(?, ?, ?, ?)";
	private static final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_SUPPLIER_TABLE_NAME;
	
	// Constructors
	public SupplierDAO() {
		super(DATABASE_SUPPLIER_TABLE_NAME, CompareTwoSuppliersName.NAME);
	}
	
	// Other methods
	/*
	 * Comparator to check if two instances are equal supplier through name
	 */
	public enum CompareTwoSuppliersName implements Comparator<Supplier> {
		NAME {
			public int compare(Supplier f1, Supplier f2) {
				
				// Variable that stores the logic state of the comparison between
				// two suppliers by Name
				int comparisonResult = f1.getSupplierName().compareToIgnoreCase(f2.getSupplierName());
				
				return comparisonResult;
			}
		}	
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
	 * This method prepares a list of supplier to be registered
	 * @param an ArrayList<Supplier>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Supplier> supplierList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		
		for(Supplier supplier : supplierList) {
			daoSQLInstruction.setString(1, supplier.getSupplierPersonRegister());
			daoSQLInstruction.setString(2, supplier.getSupplierName());
			daoSQLInstruction.setString(3, supplier.getSupplierCountryState());
			daoSQLInstruction.setString(4, supplier.getSupplierRegisterSituation());
			daoSQLInstruction.addBatch();
		}	
	}

	/*
	 * This method populates the ArrayList<supplier>
	 * @param an ArrayList<Supplier>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(
			ArrayList<Supplier> supplierList, ResultSet sqlResult) throws SQLException {
		
		while(sqlResult.next()) {
			Supplier supplier = instantiateNewSupplier();
			supplier.setSupplierPersonRegister(sqlResult.getString(
					DATABASE_SUPPLIER_PERSON_REGISTER));
			supplier.setSupplierName(sqlResult.getString(
					DATABASE_SUPPLIER_NAME));
			supplier.setSupplierCountryState(sqlResult.getString(
					DATABASE_SUPPLIER_COUNTRY_STATE));
			supplier.setSupplierRegisterSituation(sqlResult.getString(
					DATABASE_SUPPLIER_REGISTER_SITUATION));
			
			supplierList.add(supplier);
		}
	}
	
	/*
	 * This method makes the SQL query command according to the supplier informed
	 * @param a supplier
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForSupplier(final Supplier supplier) throws Exception {
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE ";
		
		if(!supplier.getSupplierName().equals(Supplier.EMPTY_TYPE_STRING)) {
			sqlCommand = sqlCommand + DATABASE_SUPPLIER_NAME + " = " 
					+ supplier.getSupplierName();
		} else {
			if(!supplier.getSupplierPersonRegister().equals(Supplier.EMPTY_TYPE_STRING)) {
				sqlCommand = sqlCommand + DATABASE_SUPPLIER_PERSON_REGISTER + " = " 
						+ supplier.getSupplierPersonRegister();
			} else {
				throw new Exception();
			}
		}
		
		return sqlCommand;
	}

	/*
	 * This method retrieves a supplier through the name or CNPJ
	 * @param an instance of Class Supplier
	 * @return an instance of Class Supplier
	 */
	public Supplier getSupplierByNameOfPersonRegister(Supplier supplier) throws Exception {
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForSupplier(supplier);
		
		//Instance to store supplier returned by the bank
		Supplier supplierRecovered;
		supplierRecovered = searchSupplierInDatabaseUsingSQLCommandConfiguredBefore(
				sqlCommand).get(0);
		return supplierRecovered; 
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
	 * This method retrieves a complete list of suppliers stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Supplier>
	 */
	public ArrayList<Supplier> searchSupplierInDatabaseUsingSQLCommandConfiguredBefore(
			String sqlCommandConfiguredBefore) throws SQLException {

		ArrayList<Supplier> supplierList = new ArrayList<>();

		try {
			// Preparing connection to the database
			ResultSet resultSQL = establishingConnectionToTheDatabaseToQuery(sqlCommandConfiguredBefore);

			// Iterations search
			while(resultSQL.next()) {
				Supplier supplier = instantiateNewSupplier();
				supplier.setSupplierName(resultSQL.getString(
						DATABASE_SUPPLIER_NAME));
				supplier.setSupplierPersonRegister(resultSQL.getString(
						DATABASE_SUPPLIER_PERSON_REGISTER));
				supplier.setSupplierRegisterSituation(resultSQL.getString(
						DATABASE_SUPPLIER_REGISTER_SITUATION));
				supplier.setSupplierCountryState(resultSQL.getString(
						DATABASE_SUPPLIER_COUNTRY_STATE));
				if(supplier != null) {
					supplierList.add(supplier);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("SupplierDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return supplierList;
	}
}