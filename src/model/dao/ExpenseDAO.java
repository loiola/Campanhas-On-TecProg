package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Campaign;
import model.beans.Expense;
import model.beans.Position;
import model.beans.Supplier;
import parse.ParseDAO;

public class ExpenseDAO extends BasicDAO<Expense> implements ParseDAO<Expense> {
	
	/*
	 * Class for manipulating the data about expenses
	 */
	
	// Constants
	private static final String NOME_TABELA = "expense";
	private final String ID = "id_despesa";
	private final String CAMPANHA_ANO = "campanha_ano";
	private final String CAMPANHA_NUMERO = "campanha_numero_candidato";
	private final String VALOR = "valor";
	private final String FORMA_PAGAMENTO = "forma_pagamento";
	private final String DESCRICAO = "descricao";
	private final String DATA = "data_despesa";	
	private final String TIPO_MOVIMENTACAO = "tipo_movimentacao";
	private final String TIPO_DOCUMENTO = "tipo_documento";
	private final String NUMERO_DOCUMENTO = "numero_documento";
	private final String NOME_FORNECEDOR = "fornecedor_nome";
	private final String CPF_CNPJ_FORNECEDOR = "fornecedor_cpf_cnpj";
	private final String CAMPANHA_CARGO = "cargo";
	private final String CAMPANHA_UF = "campanha_uf";
	
	private final String SQL_SELECT = "SELECT * FROM " + NOME_TABELA;
	private final String SQL_INSERT = "INSERT INTO "
					   + NOME_TABELA + " (" + ID + ", " + CAMPANHA_ANO + ", "
					   + CAMPANHA_NUMERO + ", " + VALOR + ", " 
					   + FORMA_PAGAMENTO + ", " + DESCRICAO + ", " + DATA
					   + ", " + TIPO_MOVIMENTACAO + ", " + TIPO_DOCUMENTO 
					   + ", " + NUMERO_DOCUMENTO + ", " 
					   + NOME_FORNECEDOR + ", " + CPF_CNPJ_FORNECEDOR + ", " 
					   + CAMPANHA_CARGO + ", " + CAMPANHA_UF
					   + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	

	// Constructors
	public ExpenseDAO() {
		super(NOME_TABELA, null);
	}

	// Other methods
	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSQLInsertCommand() {
		return SQL_INSERT;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSQLSelectCommand() {
		return SQL_SELECT;
	}

	/*
	 * This method prepares a list of expenses to be registered
	 * @param an ArrayList<Expense>
	 * @param a SQLinstruction
	 */
	@Override
	protected void registerObjectArrayListOnBatch(ArrayList<Expense> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Expense expense : lista) {
			instrucaoSQL.setInt(1, expense.getFinancialTransactionIdentifier());	
			instrucaoSQL.setInt(2, expense.getFinancialTransactionCampaign().getCampaignYear());
			instrucaoSQL.setInt(3, expense.getFinancialTransactionCampaign().getCampaignCandidateNumber());
			instrucaoSQL.setFloat(4, expense.getFinancialTransactionPrice());	
			instrucaoSQL.setString(5, expense.getFinancialTransactionPaymentType());
			instrucaoSQL.setString(6, expense.getFinancialTransactionDescription());
			instrucaoSQL.setString(7, expense.getFinancialTransactionDate());	
			instrucaoSQL.setString(8, expense.getFinancialTransactionType());
			instrucaoSQL.setString(9, expense.getExpenseDocumentType());
			instrucaoSQL.setString(10, expense.getFinancialTransactionDocumentNumber());
			instrucaoSQL.setString(11, expense.getExpenseSupplier().getSupplierName());
			instrucaoSQL.setString(12, expense.getExpenseSupplier().getSupplierPersonRegister());
			instrucaoSQL.setString(13, expense.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
			instrucaoSQL.setString(14, expense.getFinancialTransactionCampaign().getCampaignCountryState());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Expense>
	 * @param an ArrayList<Expense>
	 * @param a SQLresult
	 */
	@Override
	protected void registerResultSetOnObjectArrayList(ArrayList<Expense> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Campaign campaign = new Campaign();
			Position position = new Position();
			position.setPositionDescription(resultadoSQL.getString(CAMPANHA_CARGO));
			campaign.setCampaignYear(resultadoSQL.getInt(CAMPANHA_ANO));
			campaign.setCampaignCandidateNumber(resultadoSQL.getInt(CAMPANHA_NUMERO));
			campaign.setCampaignCountryState(resultadoSQL.getString(CAMPANHA_UF));
			campaign.setCampaignPosition(position);

			Supplier supplier = new Supplier();
			supplier.setSupplierPersonRegister(resultadoSQL.getString(CPF_CNPJ_FORNECEDOR));
			supplier.setSupplierName(resultadoSQL.getString(NOME_FORNECEDOR));

			Expense expense = new Expense();
			expense.setFinancialTransactionIdentifier(resultadoSQL.getInt(ID));			
			expense.setFinancialTransactionCampaign(campaign);
			expense.setFinancialTransactionDate(resultadoSQL.getString(DATA));
			expense.setFinancialTransactionDescription(resultadoSQL.getString(DESCRICAO));
			expense.setFinancialTransactionPaymentType(resultadoSQL.getString(FORMA_PAGAMENTO));
			expense.setExpenseSupplier(supplier);
			expense.setFinancialTransactionDocumentNumber(resultadoSQL.getString(NUMERO_DOCUMENTO));
			expense.setExpenseDocumentType(resultadoSQL.getString(TIPO_DOCUMENTO));
			expense.setFinancialTransactionType(resultadoSQL.getString(TIPO_MOVIMENTACAO));
			expense.setFinancialTransactionPrice(resultadoSQL.getFloat(VALOR));
			
			lista.add(expense);
		}
	}

	/*
	 * This method retrieves a list of expenses through the year, candidate number,
	 * position and unit federation
	 * @param an instance of Class Campaign
	 * @return an ArrayList<Expense>
	 */
	public ArrayList<Expense> getPorAnoNumeroCargoUf(Campaign campaign) throws Exception {
		String comandoSQL = SQL_SELECT + " WHERE "
				  + CAMPANHA_ANO + " = " + campaign.getCampaignYear() + " AND "
				  + CAMPANHA_NUMERO + " = " + campaign.getCampaignCandidateNumber()
				  + " AND " + CAMPANHA_UF + " = '" + campaign.getCampaignCountryState()
				  + "' AND " + CAMPANHA_CARGO 
				  + " LIKE '%" + campaign.getCampaignPosition().getPositionDescription()
				  + "%'";
		return buscaBD(comandoSQL);
	}
	
	/*
	 * This method retrieves an instance of Class Expense through ID
	 * @param an int value with the ID
	 * @return an instance of Class Expense
	 */
	public Expense getPeloId(int id) throws Exception {
			String comandoSQL = SQL_SELECT + " WHERE "
					  + ID + " = " + id;
			return buscaBD(comandoSQL).get(0);
	}
	
	/*
	 * This method retrieves a complete list of Expenses stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Expense>
	 */
	public ArrayList<Expense> buscaBD(String SQL) throws Exception {
		ArrayList<Expense> listaDespesa = new ArrayList<>();

		try {
			this.connection = new DatabaseConnection().getConexao();

			String comandoSQL = SQL;

			this.daoSQLInstruction = this.connection.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) daoSQLInstruction.executeQuery();

			while(resultadoSQL.next()) {
				Expense expense = new Expense();
				Position position = new Position();
				position.setPositionDescription(resultadoSQL.getString(CAMPANHA_CARGO));

				Campaign campaign = new Campaign();
				campaign.setCampaignYear(resultadoSQL.getInt(CAMPANHA_ANO));
				campaign.setCampaignCandidateNumber(resultadoSQL.getInt(CAMPANHA_NUMERO));
				campaign.setCampaignPosition(position);
				expense.setFinancialTransactionCampaign(campaign);
				
				Supplier supplier = new Supplier();
				supplier.setSupplierName(resultadoSQL.getString(NOME_FORNECEDOR));
				supplier.setSupplierPersonRegister(resultadoSQL.getString(CPF_CNPJ_FORNECEDOR));
				expense.setExpenseSupplier(supplier);

				expense.setFinancialTransactionDate(resultadoSQL.getString(DATA));
				expense.setFinancialTransactionDescription(resultadoSQL.getString(DESCRICAO));
				expense.setFinancialTransactionPaymentType(resultadoSQL.getString(FORMA_PAGAMENTO));
				expense.setFinancialTransactionIdentifier(resultadoSQL.getInt(ID));
				expense.setFinancialTransactionDocumentNumber(resultadoSQL.getString(NUMERO_DOCUMENTO));
				expense.setExpenseDocumentType(resultadoSQL.getString(TIPO_DOCUMENTO));
				expense.setFinancialTransactionType(resultadoSQL.getString(TIPO_MOVIMENTACAO));
				expense.setFinancialTransactionPrice(resultadoSQL.getFloat(VALOR));
				
				if(expense != null) {
					listaDespesa.add(expense);
				}
			}

		}  catch(SQLException e) {
			throw new SQLException("ExpenseDAO - " + e.getMessage());
		} finally {
			closeDatabaseConnection();
		}
		return listaDespesa;
	}
}