package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Cargo;
import modelo.beans.Despesa;
import modelo.beans.Fornecedor;
import parse.ParseDAO;

public class DespesaDAO extends BasicoDAO<Despesa> implements ParseDAO<Despesa> {
	
	/*
	 * Class for manipulating the data about expenses
	 */
	
	// Constants
	private static final String NOME_TABELA = "despesa";
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
	protected void adicionarListaNoBatch(ArrayList<Despesa> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Despesa despesa : lista) {
			instrucaoSQL.setInt(1, despesa.getId());	
			instrucaoSQL.setInt(2, despesa.getCampanha().getAno());
			instrucaoSQL.setInt(3, despesa.getCampanha().getNumeroCandidato());
			instrucaoSQL.setFloat(4, despesa.getValor());	
			instrucaoSQL.setString(5, despesa.getFormaPagamento());
			instrucaoSQL.setString(6, despesa.getDescricao());
			instrucaoSQL.setString(7, despesa.getData());	
			instrucaoSQL.setString(8, despesa.getTipoMovimentacao());
			instrucaoSQL.setString(9, despesa.getTipoDocumento());
			instrucaoSQL.setString(10, despesa.getNumeroDocumento());
			instrucaoSQL.setString(11, despesa.getFornecedor().getNome());
			instrucaoSQL.setString(12, despesa.getFornecedor().getCpf_cnpj());
			instrucaoSQL.setString(13, despesa.getCampanha().getCargo().getDescricao());
			instrucaoSQL.setString(14, despesa.getCampanha().getUf());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Expense>
	 * @param an ArrayList<Expense>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Despesa> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Campaign campaign = new Campaign();
			Cargo cargo = new Cargo();
			cargo.setDescricao(resultadoSQL.getString(CAMPANHA_CARGO));
			campaign.setAno(resultadoSQL.getInt(CAMPANHA_ANO));
			campaign.setNumeroCandidato(resultadoSQL.getInt(CAMPANHA_NUMERO));
			campaign.setUf(resultadoSQL.getString(CAMPANHA_UF));
			campaign.setCargo(cargo);

			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_FORNECEDOR));
			fornecedor.setNome(resultadoSQL.getString(NOME_FORNECEDOR));

			Despesa despesa = new Despesa();
			despesa.setId(resultadoSQL.getInt(ID));			
			despesa.setCampanha(campaign);
			despesa.setData(resultadoSQL.getString(DATA));
			despesa.setDescricao(resultadoSQL.getString(DESCRICAO));
			despesa.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
			despesa.setFornecedor(fornecedor);
			despesa.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
			despesa.setTipoDocumento(resultadoSQL.getString(TIPO_DOCUMENTO));
			despesa.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
			despesa.setValor(resultadoSQL.getFloat(VALOR));
			
			lista.add(despesa);
		}
	}

	/*
	 * This method retrieves a list of expenses through the year, candidate number,
	 * position and unit federation
	 * @param an instance of Class Campaign
	 * @return an ArrayList<Expense>
	 */
	public ArrayList<Despesa> getPorAnoNumeroCargoUf(Campaign campaign) throws Exception {
		String comandoSQL = SQL_SELECT + " WHERE "
				  + CAMPANHA_ANO + " = " + campaign.getAno() + " AND "
				  + CAMPANHA_NUMERO + " = " + campaign.getNumeroCandidato()
				  + " AND " + CAMPANHA_UF + " = '" + campaign.getUf()
				  + "' AND " + CAMPANHA_CARGO 
				  + " LIKE '%" + campaign.getCargo().getDescricao()
				  + "%'";
		return buscaBD(comandoSQL);
	}
	
	/*
	 * This method retrieves an instance of Class Expense through ID
	 * @param an int value with the ID
	 * @return an instance of Class Expense
	 */
	public Despesa getPeloId(int id) throws Exception {
			String comandoSQL = SQL_SELECT + " WHERE "
					  + ID + " = " + id;
			return buscaBD(comandoSQL).get(0);
	}
	
	/*
	 * This method retrieves a complete list of Expenses stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Expense>
	 */
	public ArrayList<Despesa> buscaBD(String SQL) throws Exception {
		ArrayList<Despesa> listaDespesa = new ArrayList<>();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Despesa despesa = new Despesa();
				Cargo cargo = new Cargo();
				cargo.setDescricao(resultadoSQL.getString(CAMPANHA_CARGO));

				Campaign campaign = new Campaign();
				campaign.setAno(resultadoSQL.getInt(CAMPANHA_ANO));
				campaign.setNumeroCandidato(resultadoSQL.getInt(CAMPANHA_NUMERO));
				campaign.setCargo(cargo);
				despesa.setCampanha(campaign);
				
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setNome(resultadoSQL.getString(NOME_FORNECEDOR));
				fornecedor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_FORNECEDOR));
				despesa.setFornecedor(fornecedor);

				despesa.setData(resultadoSQL.getString(DATA));
				despesa.setDescricao(resultadoSQL.getString(DESCRICAO));
				despesa.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
				despesa.setId(resultadoSQL.getInt(ID));
				despesa.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
				despesa.setTipoDocumento(resultadoSQL.getString(TIPO_DOCUMENTO));
				despesa.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
				despesa.setValor(resultadoSQL.getFloat(VALOR));
				
				if(despesa != null) {
					listaDespesa.add(despesa);
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