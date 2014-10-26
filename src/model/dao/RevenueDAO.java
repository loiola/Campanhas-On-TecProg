package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Campaign;
import model.beans.Donor;
import model.beans.Position;
import model.beans.Revenue;
import parse.ParseDAO;

public class RevenueDAO extends BasicDAO<Revenue> implements ParseDAO<Revenue> {

	/*
	 * Class for manipulating the data about revenue
	 */
	
	// Constants
	private static final String DATABASE_REVENUE_TABLE_NAME = "receita";
	private final String DATABASE_REVENUE_IDENTIFIER = "id_receita";
	private final String DATABASE_REVENUE_CAMPAIGN_YEAR = "campanha_ano";
	private final String DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER = "campanha_numero_candidato";
	private final String DATABASE_REVENUE_VALUE = "valor";
	private final String DATABASE_REVENUE_PAYMENT_TYPE = "forma_pagamento";
	private final String DATABASE_REVENUE_DESCRIPTION = "descricao";
	private final String DATABASE_REVENUE_DATE = "data_receita";
	private final String DATABASE_REVENUE_TYPE = "tipo_movimentacao";
	private final String DATABASE_REVENUE_ELECTORAL_RECEIPT = "recibo_eleitoral";
	private final String DATABASE_REVENUE_DOCUMENT_NUMBER = "numero_documento";
	private final String DATABASE_REVENUE_DONOR_NAME = "doador_nome";
	private final String DATABASE_REVENUE_DONOR_PERSON_REGISTER = "doador_cpf_cnpj";
	private final String DATABASE_REVENUE_CAMPAIGN_POSITION = "cargo";
	private final String DATABASE_REVENUE_CAMPAIGN_COUNTRY_STATE = "campanha_uf";
	
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_REVENUE_TABLE_NAME;
	private final String DATABASE_SQL_COMMAND_INSERT = "INSERT INTO "
					   + DATABASE_REVENUE_TABLE_NAME + " (" + DATABASE_REVENUE_IDENTIFIER + ", " + DATABASE_REVENUE_CAMPAIGN_YEAR + ", "
					   + DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER + ", " + DATABASE_REVENUE_VALUE + ", " 
					   + DATABASE_REVENUE_PAYMENT_TYPE + ", " + DATABASE_REVENUE_DESCRIPTION + ", " + DATABASE_REVENUE_DATE
					   + ", " + DATABASE_REVENUE_TYPE + ", " + DATABASE_REVENUE_ELECTORAL_RECEIPT 
					   + ", " + DATABASE_REVENUE_DOCUMENT_NUMBER + ", "
					   + DATABASE_REVENUE_DONOR_NAME + ", " + DATABASE_REVENUE_DONOR_PERSON_REGISTER + ", " 
					   + DATABASE_REVENUE_CAMPAIGN_POSITION + ", " + DATABASE_REVENUE_CAMPAIGN_COUNTRY_STATE
					   + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	

	// Constructors
	public RevenueDAO() {
		super(DATABASE_REVENUE_TABLE_NAME, null);
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
	 * This method prepares a list of receipt to be registered
	 * @param an ArrayList<Revenue>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Revenue> revenueList,
			PreparedStatement daoSQLInstruction) throws SQLException {
		
		for(Revenue revenue : revenueList) {
			daoSQLInstruction.setInt(1, revenue.getFinancialTransactionIdentifier());
			daoSQLInstruction.setInt(2, revenue.getFinancialTransactionCampaign().getCampaignYear());
			daoSQLInstruction.setInt(3, revenue.getFinancialTransactionCampaign().getCampaignCandidateNumber());
			daoSQLInstruction.setFloat(4, revenue.getFinancialTransactionPrice());
			daoSQLInstruction.setString(5, revenue.getFinancialTransactionPaymentType());
			daoSQLInstruction.setString(6, revenue.getFinancialTransactionDescription());
			daoSQLInstruction.setString(7, revenue.getFinancialTransactionDate());
			daoSQLInstruction.setString(8, revenue.getFinancialTransactionType());
			daoSQLInstruction.setString(9, revenue.getRevenueElectoralReceipt());
			daoSQLInstruction.setString(10, revenue.getFinancialTransactionDocumentNumber());
			daoSQLInstruction.setString(11, revenue.getRevenueDonor().getDonorName());
			daoSQLInstruction.setString(12, revenue.getRevenueDonor().getDonorPersonRegister());
			daoSQLInstruction.setString(13, revenue.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
			daoSQLInstruction.setString(14, revenue.getFinancialTransactionCampaign().getCampaignCountryState());
			daoSQLInstruction.addBatch();
		}	
	}

	/*
	 * This method populates the ArrayList<Revenue>
	 * @param an ArrayList<Revenue>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Revenue> revenueList,
			ResultSet sqlResult) throws SQLException {
		
		while(sqlResult.next()) {
			
			// Working with Campaign
			Campaign campaign = new Campaign();
			Position position = new Position();
			position.setPositionDescription(sqlResult.getString(
					DATABASE_REVENUE_CAMPAIGN_POSITION));
			campaign.setCampaignYear(sqlResult.getInt(
					DATABASE_REVENUE_CAMPAIGN_YEAR));	
			campaign.setCampaignCandidateNumber(sqlResult.getInt(
					DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER));	
			campaign.setCampaignPosition(position);
			campaign.setCampaignCountryState(sqlResult.getString(
					DATABASE_REVENUE_CAMPAIGN_COUNTRY_STATE));
			
			// Working with Donor
			Donor donor = new Donor();
			donor.setDonorName(sqlResult.getString(
					DATABASE_REVENUE_DONOR_NAME));
			donor.setDonorPersonRegister(sqlResult.getString(
					DATABASE_REVENUE_DONOR_PERSON_REGISTER));

			// Working with Revenue
			Revenue revenue = new Revenue();
			revenue.setFinancialTransactionIdentifier(sqlResult.getInt(
					DATABASE_REVENUE_IDENTIFIER));
			revenue.setFinancialTransactionType(sqlResult.getString(
					DATABASE_REVENUE_TYPE));
			revenue.setFinancialTransactionPaymentType(sqlResult.getString(
					DATABASE_REVENUE_PAYMENT_TYPE));
			revenue.setFinancialTransactionCampaign(campaign);
			revenue.setRevenueDonor(donor);
			revenue.setRevenueElectoralReceipt(sqlResult.getString(
					DATABASE_REVENUE_ELECTORAL_RECEIPT));
			revenue.setFinancialTransactionDocumentNumber(sqlResult.getString(
					DATABASE_REVENUE_DOCUMENT_NUMBER));
			revenue.setFinancialTransactionDate(sqlResult.getString(
					DATABASE_REVENUE_DATE));
			revenue.setFinancialTransactionPrice(sqlResult.getFloat(
					DATABASE_REVENUE_VALUE));
			revenue.setFinancialTransactionDescription(sqlResult.getString(
					DATABASE_REVENUE_DESCRIPTION));
			
			revenueList.add(revenue);
		}
	}
	
	/*
	 * This method makes the SQL query command according to the year,
	 * country state and position of candidate informed
	 * @param a String who define the party acronym of campaign
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForAttributes(
			final Campaign campaign) throws SQLException{
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				  + DATABASE_REVENUE_CAMPAIGN_YEAR + " = " + campaign.getCampaignYear()
				  + " AND " + DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER + " = "
				  + campaign.getCampaignCandidateNumber() + " AND "
				  + DATABASE_REVENUE_CAMPAIGN_COUNTRY_STATE + " = '"
				  + campaign.getCampaignCountryState() + "' AND "
				  + DATABASE_REVENUE_CAMPAIGN_POSITION + " LIKE '%"
				  + campaign.getCampaignPosition().getPositionDescription() + "%'";
		
		return sqlCommand;
	}
	
	/*
	 * This method retrieves a receipt through the year or number or position or UF
	 * @param an instance of Class Campaign
	 * @return an instance of Class Revenue
	 */
	public ArrayList<Revenue> getRevenueByCampaignPositionAndCampaignCountryStateAndCampaignYear(
			Campaign campaign) throws Exception {
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForAttributes(campaign);
		
		// Returning the list of Revenue
		ArrayList<Revenue> RevenueList = new ArrayList<>();
		RevenueList = searchRevenueInDatabaseUsingSQLCommandConfiguredBefore(sqlCommand);
		return RevenueList;
	}
	
	/*
	 * This method makes the SQL query command according to the identifier informed
	 * @param a integer who define the number identifier the revenue
	 * @result the command of consultation
	 */
	private String mountingSQLConsultationForIdentifier(
			final int revenueIdentifier) throws SQLException{
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				  + DATABASE_REVENUE_IDENTIFIER + " = " + revenueIdentifier;
		
		return sqlCommand;
	}
	
	/*
	 * This method retrieves a receipt through the DATABASE_REVENUE_IDENTIFIER
	 * @param an Integer with the DATABASE_REVENUE_IDENTIFIER
	 * @return an instance of Class Revenue
	 */
	public Revenue getRevenueByIdentifier(int revenueIdentifier) throws Exception {
		
		// Riding the command SQL
		String sqlCommand = mountingSQLConsultationForIdentifier(revenueIdentifier);
				
		// Returning the Revenue
		Revenue revenueRecovered;
		revenueRecovered = searchRevenueInDatabaseUsingSQLCommandConfiguredBefore(
				sqlCommand).get(0);
		return revenueRecovered;
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
	 * This method retrieves a complete list of revenue stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Revenue>
	 */
	public ArrayList<Revenue> searchRevenueInDatabaseUsingSQLCommandConfiguredBefore(String sqlCommandConfiguredBefore) throws Exception {

		ArrayList<Revenue> revenueList = new ArrayList<>();

		try {
			// Preparing connection to the database
			ResultSet resultSQL = establishingConnectionToTheDatabaseToQuery(sqlCommandConfiguredBefore);

			// Iterations search
			while(resultSQL.next()) {
				
				Revenue revenue = new Revenue();
				Position position = new Position();
				position.setPositionDescription(resultSQL.getString(DATABASE_REVENUE_CAMPAIGN_POSITION));

				Campaign campaign = new Campaign();
				campaign.setCampaignYear(resultSQL.getInt(DATABASE_REVENUE_CAMPAIGN_YEAR));
				campaign.setCampaignCandidateNumber(resultSQL.getInt(DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER));
				campaign.setCampaignPosition(position);
				revenue.setFinancialTransactionCampaign(campaign);
				
				Donor donor = new Donor();
				donor.setDonorName(resultSQL.getString(DATABASE_REVENUE_DONOR_NAME));
				donor.setDonorPersonRegister(resultSQL.getString(DATABASE_REVENUE_DONOR_PERSON_REGISTER));
				revenue.setRevenueDonor(donor);

				revenue.setFinancialTransactionDate(resultSQL.getString(DATABASE_REVENUE_DATE));
				revenue.setFinancialTransactionDescription(resultSQL.getString(DATABASE_REVENUE_DESCRIPTION));
				revenue.setFinancialTransactionPaymentType(resultSQL.getString(DATABASE_REVENUE_PAYMENT_TYPE));
				revenue.setFinancialTransactionIdentifier(resultSQL.getInt(DATABASE_REVENUE_IDENTIFIER));
				revenue.setFinancialTransactionDocumentNumber(resultSQL.getString(DATABASE_REVENUE_DOCUMENT_NUMBER));
				revenue.setRevenueElectoralReceipt(resultSQL.getString(DATABASE_REVENUE_ELECTORAL_RECEIPT));
				revenue.setFinancialTransactionType(resultSQL.getString(DATABASE_REVENUE_TYPE));
				revenue.setFinancialTransactionPrice(resultSQL.getFloat(DATABASE_REVENUE_VALUE));
				
				if(revenue != null) {
					revenueList.add(revenue);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("RevenueDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return revenueList;
	}
}