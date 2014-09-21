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

public class DespesaDAO extends BasicoDAO<Expense> implements ParseDAO<Expense> {
	
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
	public DespesaDAO() {
		super(NOME_TABELA, null);
	}

	// Other methods
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
	 * This method prepares a list of expenses to be registered
	 * @param an ArrayList<Expense>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Expense> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Expense expense : lista) {
			instrucaoSQL.setInt(1, expense.getId());	
			instrucaoSQL.setInt(2, expense.getCampanha().getCampaignYear());
			instrucaoSQL.setInt(3, expense.getCampanha().getCampaignCandidateNumber());
			instrucaoSQL.setFloat(4, expense.getValor());	
			instrucaoSQL.setString(5, expense.getFormaPagamento());
			instrucaoSQL.setString(6, expense.getDescricao());
			instrucaoSQL.setString(7, expense.getData());	
			instrucaoSQL.setString(8, expense.getTipoMovimentacao());
			instrucaoSQL.setString(9, expense.getExpenseDocumentType());
			instrucaoSQL.setString(10, expense.getNumeroDocumento());
			instrucaoSQL.setString(11, expense.getExpenseSupplier().getNome());
			instrucaoSQL.setString(12, expense.getExpenseSupplier().getCpf_cnpj());
			instrucaoSQL.setString(13, expense.getCampanha().getCampaignPosition().getPositionDescription());
			instrucaoSQL.setString(14, expense.getCampanha().getCampaignCountryState());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Expense>
	 * @param an ArrayList<Expense>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Expense> lista,
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
			supplier.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_FORNECEDOR));
			supplier.setNome(resultadoSQL.getString(NOME_FORNECEDOR));

			Expense expense = new Expense();
			expense.setId(resultadoSQL.getInt(ID));			
			expense.setCampanha(campaign);
			expense.setData(resultadoSQL.getString(DATA));
			expense.setDescricao(resultadoSQL.getString(DESCRICAO));
			expense.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
			expense.setExpenseSupplier(supplier);
			expense.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
			expense.setExpenseDocumentType(resultadoSQL.getString(TIPO_DOCUMENTO));
			expense.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
			expense.setValor(resultadoSQL.getFloat(VALOR));
			
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
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Expense expense = new Expense();
				Position position = new Position();
				position.setPositionDescription(resultadoSQL.getString(CAMPANHA_CARGO));

				Campaign campaign = new Campaign();
				campaign.setCampaignYear(resultadoSQL.getInt(CAMPANHA_ANO));
				campaign.setCampaignCandidateNumber(resultadoSQL.getInt(CAMPANHA_NUMERO));
				campaign.setCampaignPosition(position);
				expense.setCampanha(campaign);
				
				Supplier supplier = new Supplier();
				supplier.setNome(resultadoSQL.getString(NOME_FORNECEDOR));
				supplier.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_FORNECEDOR));
				expense.setExpenseSupplier(supplier);

				expense.setData(resultadoSQL.getString(DATA));
				expense.setDescricao(resultadoSQL.getString(DESCRICAO));
				expense.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
				expense.setId(resultadoSQL.getInt(ID));
				expense.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
				expense.setExpenseDocumentType(resultadoSQL.getString(TIPO_DOCUMENTO));
				expense.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
				expense.setValor(resultadoSQL.getFloat(VALOR));
				
				if(expense != null) {
					listaDespesa.add(expense);
				}
			}

		}  catch(SQLException e) {
			throw new SQLException("DespesaDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaDespesa;
	}
}