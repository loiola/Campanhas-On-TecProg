package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Donor;
import model.beans.Supplier;
import parse.ParseDAO;

public class DonorDAO extends BasicDAO<Donor> implements ParseDAO<Donor> {

	/*
	 * Class for manipulating the data about donor
	 */
	
	// Constants
	private static final String DATABASE_DONOR_TABLE_NAME = "doador";
	private static final String DATABASE_DONOR_PERSON_REGISTER = "cpf_cnpj";
	private static final String DATABASE_DONOR_NAME = "nome";
	private static final String DATABASE_DONOR_COUNTRY_STATE = "uf";
	private static final String DATABASE_DONOR_REGISTER_SITUATION = "situacao_cadastral";
	private static final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO " + DATABASE_DONOR_TABLE_NAME
			+ " (" + DATABASE_DONOR_PERSON_REGISTER + ", " + DATABASE_DONOR_NAME + ", " + DATABASE_DONOR_COUNTRY_STATE + ", " + DATABASE_DONOR_REGISTER_SITUATION + ") "
			+ "values (?, ?, ?, ?)";
	private static final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_DONOR_TABLE_NAME;
	
	// Constructors
	public DonorDAO() {
		super(DATABASE_DONOR_TABLE_NAME, CompareTwoDonorsPersonRegister.NOME);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal donor through CNPJ
	 */
	public enum CompareTwoDonorsPersonRegister implements Comparator<Donor> {
		NOME {
			public int compare(Donor d1, Donor d2) {
				return d1.getDonorPersonRegister().compareToIgnoreCase(d2.getDonorPersonRegister());
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
	 * This method prepares a list of donor to be registered
	 * @param an ArrayList<Donor>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Donor> donorList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		for(Donor donor : donorList) {
			daoSQLInstruction.setString(1, donor.getDonorPersonRegister());
			daoSQLInstruction.setString(2, donor.getDonorName());
			daoSQLInstruction.setString(3, donor.getDonorCountryState());
			daoSQLInstruction.setString(4, donor.getDonorRegisterSituation());
			daoSQLInstruction.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Donor>
	 * @param an ArrayList<Donor>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Donor> donorList,
			ResultSet sqlResult) throws SQLException {
		while(sqlResult.next()) {
			Donor donor = new Donor();
			donor.setDonorPersonRegister(sqlResult.getString(DATABASE_DONOR_PERSON_REGISTER));
			donor.setDonorName(sqlResult.getString(DATABASE_DONOR_NAME));
			donor.setDonorCountryState(sqlResult.getString(DATABASE_DONOR_COUNTRY_STATE));
			donor.setDonorRegisterSituation(sqlResult.getString(DATABASE_DONOR_REGISTER_SITUATION));
			
			donorList.add(donor);
		}
	}

	/*
	 * This method retrieves a complete list of donor stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Donor>
	 */
	public ArrayList<Donor> searchDonorInDatabaseUsingSQLCommandConfiguredBefore(String sqlCommandConfiguredBefore) throws SQLException {

		ArrayList<Donor> donorList = new ArrayList<>();
		
		try {
			this.connection = new DatabaseConnection().getConnection();

			String sqlCommand = sqlCommandConfiguredBefore;
			this.daoSQLInstruction = this.connection.prepareStatement(sqlCommand);
			ResultSet sqlResult = (ResultSet) daoSQLInstruction.executeQuery();

			while(sqlResult.next()) {
				Donor donor = new Donor();
				donor.setDonorName(sqlResult.getString(DATABASE_DONOR_NAME));
				donor.setDonorPersonRegister(sqlResult.getString(DATABASE_DONOR_PERSON_REGISTER));
				donor.setDonorRegisterSituation(sqlResult.getString(DATABASE_DONOR_REGISTER_SITUATION));
				donor.setDonorCountryState(sqlResult.getString(DATABASE_DONOR_COUNTRY_STATE));

				if(donor != null) {
					donorList.add(donor);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("DonorDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return donorList;
	}
	
	/*
	 * This method retrieves a donor through the name or CNPJ
	 * @param an instance of Class Donor
	 * @return an instance of Class Donor
	 */
	public Donor getDonorByNameAndPersonRegister(Donor donor) throws Exception {
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE ";
		if(!donor.getDonorName().equals(Supplier.EMPTY_TYPE_STRING)) {
			sqlCommand = sqlCommand + DATABASE_DONOR_NAME + " = " 
		  + donor.getDonorName();
		}
		else if(!donor.getDonorPersonRegister().equals(Supplier.EMPTY_TYPE_STRING)) {
			sqlCommand = sqlCommand + DATABASE_DONOR_PERSON_REGISTER + " = " 
		  + donor.getDonorPersonRegister();
		} else {
			throw new Exception();
		}
		return searchDonorInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand).get(0);
	}
}