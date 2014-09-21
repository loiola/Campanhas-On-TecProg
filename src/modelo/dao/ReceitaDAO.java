package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Position;
import modelo.beans.Donor;
import modelo.beans.Revenue;
import parse.ParseDAO;

public class ReceitaDAO extends BasicoDAO<Revenue> implements ParseDAO<Revenue> {

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
			instrucaoSQL.setInt(1, revenue.getId());
			instrucaoSQL.setInt(2, revenue.getCampanha().getAno());
			instrucaoSQL.setInt(3, revenue.getCampanha().getNumeroCandidato());
			instrucaoSQL.setFloat(4, revenue.getValor());
			instrucaoSQL.setString(5, revenue.getFormaPagamento());
			instrucaoSQL.setString(6, revenue.getDescricao());
			instrucaoSQL.setString(7, revenue.getData());
			instrucaoSQL.setString(8, revenue.getTipoMovimentacao());
			instrucaoSQL.setString(9, revenue.getReciboEleitoral());
			instrucaoSQL.setString(10, revenue.getNumeroDocumento());
			instrucaoSQL.setString(11, revenue.getDoador().getNome());
			instrucaoSQL.setString(12, revenue.getDoador().getCpf_cnpj());
			instrucaoSQL.setString(13, revenue.getCampanha().getCargo().getDescricao());
			instrucaoSQL.setString(14, revenue.getCampanha().getUf());
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
			position.setDescricao(resultadoSQL.getString(CAMPANHA_CARGO));
			campaign.setAno(resultadoSQL.getInt(CAMPANHA_ANO));	
			campaign.setNumeroCandidato(resultadoSQL.getInt(CAMPANHA_NUMERO));	
			campaign.setCargo(position);
			campaign.setUf(resultadoSQL.getString(CAMPANHA_UF));

			Donor donor = new Donor();
			donor.setNome(resultadoSQL.getString(NOME_DOADOR));
			donor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_DOADOR));

			Revenue revenue = new Revenue();
			revenue.setId(resultadoSQL.getInt(ID));
			revenue.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
			revenue.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
			revenue.setCampanha(campaign);
			revenue.setDoador(donor);
			revenue.setReciboEleitoral(resultadoSQL.getString(RECIBO_ELEITORAL));
			revenue.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
			revenue.setData(resultadoSQL.getString(DATA));
			revenue.setValor(resultadoSQL.getFloat(VALOR));
			revenue.setDescricao(resultadoSQL.getString(DESCRICAO));
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
				  + CAMPANHA_ANO + " = " + campaign.getAno() + " AND "
				  + CAMPANHA_NUMERO + " = " + campaign.getNumeroCandidato()
				  + " AND " + CAMPANHA_UF + " = '" + campaign.getUf()
				  + "' AND " + CAMPANHA_CARGO + " LIKE '%" 
				  + campaign.getCargo().getDescricao() 
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
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Revenue revenue = new Revenue();
				
				Position position = new Position();
				position.setDescricao(resultadoSQL.getString(CAMPANHA_CARGO));

				Campaign campaign = new Campaign();
				campaign.setAno(resultadoSQL.getInt(CAMPANHA_ANO));
				campaign.setNumeroCandidato(resultadoSQL.getInt(CAMPANHA_NUMERO));
				campaign.setCargo(position);
				revenue.setCampanha(campaign);
				
				Donor donor = new Donor();
				donor.setNome(resultadoSQL.getString(NOME_DOADOR));
				donor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_DOADOR));
				revenue.setDoador(donor);

				revenue.setData(resultadoSQL.getString(DATA));
				revenue.setDescricao(resultadoSQL.getString(DESCRICAO));
				revenue.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
				revenue.setId(resultadoSQL.getInt(ID));
				revenue.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
				revenue.setReciboEleitoral(resultadoSQL.getString(RECIBO_ELEITORAL));
				revenue.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
				revenue.setValor(resultadoSQL.getFloat(VALOR));
				
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