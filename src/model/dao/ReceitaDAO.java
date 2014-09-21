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

public class ReceitaDAO extends BasicDAO<Revenue> implements ParseDAO<Revenue> {

	/*
	 * Class for manipulating the data about revenue
	 */
	
	// Constants
	private static final String NOME_TABELA = "revenue";
	private final String ID = "id_receita";
	private final String CAMPANHA_ANO = "campanha_ano";
	private final String CAMPANHA_NUMERO = "campanha_numero_candidato";
	private final String VALOR = "valor";
	private final String FORMA_PAGAMENTO = "forma_pagamento";
	private final String DESCRICAO = "descricao";
	private final String DATA  = "data_receita";
	private final String TIPO_MOVIMENTACAO = "tipo_movimentacao";
	private final String RECIBO_ELEITORAL = "recibo_eleitoral";
	private final String NUMERO_DOCUMENTO = "numero_documento";
	private final String NOME_DOADOR = "doador_nome";
	private final String CPF_CNPJ_DOADOR = "doador_cpf_cnpj";
	private final String CAMPANHA_CARGO = "cargo";
	private final String CAMPANHA_UF = "campanha_uf";
	
	private final String SQL_SELECT = "SELECT * FROM " + NOME_TABELA;
	private final String SQL_INSERT = "INSERT INTO "
					   + NOME_TABELA + " (" + ID + ", " + CAMPANHA_ANO + ", "
					   + CAMPANHA_NUMERO + ", " + VALOR + ", " 
					   + FORMA_PAGAMENTO + ", " + DESCRICAO + ", " + DATA
					   + ", " + TIPO_MOVIMENTACAO + ", " + RECIBO_ELEITORAL 
					   + ", " + NUMERO_DOCUMENTO + ", "
					   + NOME_DOADOR + ", " + CPF_CNPJ_DOADOR + ", " 
					   + CAMPANHA_CARGO + ", " + CAMPANHA_UF
					   + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	

	// Constructors
	public ReceitaDAO() {
		super(NOME_TABELA, null);
	}

	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT;
	}

	/*
	 * This method prepares a list of receipt to be registered
	 * @param an ArrayList<Revenue>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Revenue> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Revenue revenue : lista) {
			instrucaoSQL.setInt(1, revenue.getFinancialTransactionIdentifier());
			instrucaoSQL.setInt(2, revenue.getFinancialTransactionCampaign().getCampaignYear());
			instrucaoSQL.setInt(3, revenue.getFinancialTransactionCampaign().getCampaignCandidateNumber());
			instrucaoSQL.setFloat(4, revenue.getFinancialTransactionPrice());
			instrucaoSQL.setString(5, revenue.getFinancialTransactionPaymentType());
			instrucaoSQL.setString(6, revenue.getFinancialTransactionDescription());
			instrucaoSQL.setString(7, revenue.getFinancialTransactionDate());
			instrucaoSQL.setString(8, revenue.getFinancialTransactionType());
			instrucaoSQL.setString(9, revenue.getRevenueElectoralReceipt());
			instrucaoSQL.setString(10, revenue.getFinancialTransactionDocumentNumber());
			instrucaoSQL.setString(11, revenue.getRevenueDonor().getDonorName());
			instrucaoSQL.setString(12, revenue.getRevenueDonor().getDonorPersonRegister());
			instrucaoSQL.setString(13, revenue.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
			instrucaoSQL.setString(14, revenue.getFinancialTransactionCampaign().getCampaignCountryState());
			instrucaoSQL.addBatch();
		}	
	}

	/*
	 * This method populates the ArrayList<Revenue>
	 * @param an ArrayList<Revenue>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Revenue> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Campaign campaign = new Campaign();
			Position position = new Position();
			position.setPositionDescription(resultadoSQL.getString(CAMPANHA_CARGO));
			campaign.setCampaignYear(resultadoSQL.getInt(CAMPANHA_ANO));	
			campaign.setCampaignCandidateNumber(resultadoSQL.getInt(CAMPANHA_NUMERO));	
			campaign.setCampaignPosition(position);
			campaign.setCampaignCountryState(resultadoSQL.getString(CAMPANHA_UF));

			Donor donor = new Donor();
			donor.setDonorName(resultadoSQL.getString(NOME_DOADOR));
			donor.setDonorPersonRegister(resultadoSQL.getString(CPF_CNPJ_DOADOR));

			Revenue revenue = new Revenue();
			revenue.setFinancialTransactionIdentifier(resultadoSQL.getInt(ID));
			revenue.setFinancialTransactionType(resultadoSQL.getString(TIPO_MOVIMENTACAO));
			revenue.setFinancialTransactionPaymentType(resultadoSQL.getString(FORMA_PAGAMENTO));
			revenue.setFinancialTransactionCampaign(campaign);
			revenue.setRevenueDonor(donor);
			revenue.setRevenueElectoralReceipt(resultadoSQL.getString(RECIBO_ELEITORAL));
			revenue.setFinancialTransactionDocumentNumber(resultadoSQL.getString(NUMERO_DOCUMENTO));
			revenue.setFinancialTransactionDate(resultadoSQL.getString(DATA));
			revenue.setFinancialTransactionPrice(resultadoSQL.getFloat(VALOR));
			revenue.setFinancialTransactionDescription(resultadoSQL.getString(DESCRICAO));
			lista.add(revenue);
		}
	}
	
	/*
	 * This method retrieves a receipt through the year or number or position or UF
	 * @param an instance of Class Campaign
	 * @return an instance of Class Revenue
	 */
	public ArrayList<Revenue> getPorAnoNumeroCargoUf(Campaign campaign) throws Exception {
		String comandoSQL = SQL_SELECT + " WHERE "
				  + CAMPANHA_ANO + " = " + campaign.getCampaignYear() + " AND "
				  + CAMPANHA_NUMERO + " = " + campaign.getCampaignCandidateNumber()
				  + " AND " + CAMPANHA_UF + " = '" + campaign.getCampaignCountryState()
				  + "' AND " + CAMPANHA_CARGO + " LIKE '%" 
				  + campaign.getCampaignPosition().getPositionDescription() 
				  + "%'";
		return buscaBD(comandoSQL);
	}
	
	/*
	 * This method retrieves a receipt through the ID
	 * @param an Integer with the ID
	 * @return an instance of Class Revenue
	 */
	public Revenue getPeloId(int id) throws Exception {
		String comandoSQL = SQL_SELECT + " WHERE "
				  + ID + " = " + id;
		return buscaBD(comandoSQL).get(0);
	}
	
	/*
	 * This method retrieves a complete list of revenue stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Revenue>
	 */
	public ArrayList<Revenue> buscaBD(String SQL) throws Exception {

		ArrayList<Revenue> listaReceita = new ArrayList<>();

		try {
			this.conexao = new DatabaseConnection().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Revenue revenue = new Revenue();
				
				Position position = new Position();
				position.setPositionDescription(resultadoSQL.getString(CAMPANHA_CARGO));

				Campaign campaign = new Campaign();
				campaign.setCampaignYear(resultadoSQL.getInt(CAMPANHA_ANO));
				campaign.setCampaignCandidateNumber(resultadoSQL.getInt(CAMPANHA_NUMERO));
				campaign.setCampaignPosition(position);
				revenue.setFinancialTransactionCampaign(campaign);
				
				Donor donor = new Donor();
				donor.setDonorName(resultadoSQL.getString(NOME_DOADOR));
				donor.setDonorPersonRegister(resultadoSQL.getString(CPF_CNPJ_DOADOR));
				revenue.setRevenueDonor(donor);

				revenue.setFinancialTransactionDate(resultadoSQL.getString(DATA));
				revenue.setFinancialTransactionDescription(resultadoSQL.getString(DESCRICAO));
				revenue.setFinancialTransactionPaymentType(resultadoSQL.getString(FORMA_PAGAMENTO));
				revenue.setFinancialTransactionIdentifier(resultadoSQL.getInt(ID));
				revenue.setFinancialTransactionDocumentNumber(resultadoSQL.getString(NUMERO_DOCUMENTO));
				revenue.setRevenueElectoralReceipt(resultadoSQL.getString(RECIBO_ELEITORAL));
				revenue.setFinancialTransactionType(resultadoSQL.getString(TIPO_MOVIMENTACAO));
				revenue.setFinancialTransactionPrice(resultadoSQL.getFloat(VALOR));
				
				if(revenue != null) {
					listaReceita.add(revenue);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("ReceitaDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaReceita;
	}
}