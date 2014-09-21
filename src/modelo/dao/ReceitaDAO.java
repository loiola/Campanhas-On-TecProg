package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Cargo;
import modelo.beans.Doador;
import modelo.beans.Receita;
import parse.ParseDAO;

public class ReceitaDAO extends BasicoDAO<Receita> implements ParseDAO<Receita> {

	/*
	 * Class for manipulating the data about revenue
	 */
	
	// Constants
	private static final String NOME_TABELA = "receita";
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
	protected void adicionarListaNoBatch(ArrayList<Receita> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Receita receita : lista) {
			instrucaoSQL.setInt(1, receita.getId());
			instrucaoSQL.setInt(2, receita.getCampanha().getAno());
			instrucaoSQL.setInt(3, receita.getCampanha().getNumeroCandidato());
			instrucaoSQL.setFloat(4, receita.getValor());
			instrucaoSQL.setString(5, receita.getFormaPagamento());
			instrucaoSQL.setString(6, receita.getDescricao());
			instrucaoSQL.setString(7, receita.getData());
			instrucaoSQL.setString(8, receita.getTipoMovimentacao());
			instrucaoSQL.setString(9, receita.getReciboEleitoral());
			instrucaoSQL.setString(10, receita.getNumeroDocumento());
			instrucaoSQL.setString(11, receita.getDoador().getNome());
			instrucaoSQL.setString(12, receita.getDoador().getCpf_cnpj());
			instrucaoSQL.setString(13, receita.getCampanha().getCargo().getDescricao());
			instrucaoSQL.setString(14, receita.getCampanha().getUf());
			instrucaoSQL.addBatch();
		}	
	}

	/*
	 * This method populates the ArrayList<Revenue>
	 * @param an ArrayList<Revenue>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Receita> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Campaign campaign = new Campaign();
			Cargo cargo = new Cargo();
			cargo.setDescricao(resultadoSQL.getString(CAMPANHA_CARGO));
			campaign.setAno(resultadoSQL.getInt(CAMPANHA_ANO));	
			campaign.setNumeroCandidato(resultadoSQL.getInt(CAMPANHA_NUMERO));	
			campaign.setCargo(cargo);
			campaign.setUf(resultadoSQL.getString(CAMPANHA_UF));

			Doador doador = new Doador();
			doador.setNome(resultadoSQL.getString(NOME_DOADOR));
			doador.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_DOADOR));

			Receita receita = new Receita();
			receita.setId(resultadoSQL.getInt(ID));
			receita.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
			receita.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
			receita.setCampanha(campaign);
			receita.setDoador(doador);
			receita.setReciboEleitoral(resultadoSQL.getString(RECIBO_ELEITORAL));
			receita.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
			receita.setData(resultadoSQL.getString(DATA));
			receita.setValor(resultadoSQL.getFloat(VALOR));
			receita.setDescricao(resultadoSQL.getString(DESCRICAO));
			lista.add(receita);
		}
	}
	
	/*
	 * This method retrieves a receipt through the year or number or position or UF
	 * @param an instance of Class Campaign
	 * @return an instance of Class Revenue
	 */
	public ArrayList<Receita> getPorAnoNumeroCargoUf(Campaign campaign) throws Exception {
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
	public Receita getPeloId(int id) throws Exception {
		String comandoSQL = SQL_SELECT + " WHERE "
				  + ID + " = " + id;
		return buscaBD(comandoSQL).get(0);
	}
	
	/*
	 * This method retrieves a complete list of revenue stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Revenue>
	 */
	public ArrayList<Receita> buscaBD(String SQL) throws Exception {

		ArrayList<Receita> listaReceita = new ArrayList<>();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Receita receita = new Receita();
				
				Cargo cargo = new Cargo();
				cargo.setDescricao(resultadoSQL.getString(CAMPANHA_CARGO));

				Campaign campaign = new Campaign();
				campaign.setAno(resultadoSQL.getInt(CAMPANHA_ANO));
				campaign.setNumeroCandidato(resultadoSQL.getInt(CAMPANHA_NUMERO));
				campaign.setCargo(cargo);
				receita.setCampanha(campaign);
				
				Doador doador = new Doador();
				doador.setNome(resultadoSQL.getString(NOME_DOADOR));
				doador.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ_DOADOR));
				receita.setDoador(doador);

				receita.setData(resultadoSQL.getString(DATA));
				receita.setDescricao(resultadoSQL.getString(DESCRICAO));
				receita.setFormaPagamento(resultadoSQL.getString(FORMA_PAGAMENTO));
				receita.setId(resultadoSQL.getInt(ID));
				receita.setNumeroDocumento(resultadoSQL.getString(NUMERO_DOCUMENTO));
				receita.setReciboEleitoral(resultadoSQL.getString(RECIBO_ELEITORAL));
				receita.setTipoMovimentacao(resultadoSQL.getString(TIPO_MOVIMENTACAO));
				receita.setValor(resultadoSQL.getFloat(VALOR));
				
				if(receita != null) {
					listaReceita.add(receita);
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