package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import parse.ParseDAO;
import parse.ParseException;

public abstract class BasicDAO<O> implements ParseDAO<O> {
	
	/*
	 * Abstract class with generic methods for data management by the subclasses in DAO package
	 */

	// Attributes
	protected Connection conexao;
	protected PreparedStatement instrucaoSQL;
	private Comparator<O> comparador;
	private String nomeTabela;
	
	// Constructors
	public BasicDAO(String nomeTabela, Comparator<O> comparador) {
		this.nomeTabela = nomeTabela;
		this.comparador = comparador;
	}
	
	/*
	 * This method registers a list of parse, getting a list of objects of any class
	 * @param an ArrayList<O>
	 */
	@Override
	public void cadastrarListaParse(ArrayList<O> lista) throws ParseException {
		try {
			cadastrarLista(lista);
		} catch(Exception e) {
			throw new ParseException(e.getMessage());
		}
	}
	
	/*
	 * This method retrieves a list registered in the parse
	 * @return an ArrayList<O>
	 */
	@Override
	public ArrayList<O> getListaParse() throws ParseException {
		ArrayList<O> lista = new ArrayList<>();
		
		try {
			lista = getLista();
		} catch (Exception e) {
			throw new ParseException(e.getMessage());
		}
		return lista;
	}
	
	/*
	 * This method is responsible for monitoring objects not registered in the parse
	 * and register them
	 * @param an ArrayList<O>
	 */
	public void cadastrarLista(ArrayList<O> lista) throws SQLException {
		try {
			ArrayList<O> listaNaoCadastrados = getListaNaoCadastrados(lista);

			this.conexao = new DatabaseConnection().getConexao();
			this.instrucaoSQL = getInstrucaoSQL(getSqlInsert());
			this.conexao.setAutoCommit(false);
	
			adicionarListaNoBatch(listaNaoCadastrados, instrucaoSQL);
	
			this.instrucaoSQL.executeBatch();
			this.conexao.commit();			
		} catch (Exception e) {
			throw new SQLException(nomeTabela + " - " + e.getMessage());
		} finally {
			fecharConexao();
		}
	}
	
	/*
	 * This method retrieves a list of registered objects
	 * @param an ArrayList<O>
	 */
	public ArrayList<O> getLista() throws SQLException {
		ArrayList<O> lista = new ArrayList<>();
		
		try {
			this.conexao = new DatabaseConnection().getConexao();
			
			String comandoSQL = getSqlSelect();
			
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultadoSQL = (ResultSet) this.instrucaoSQL.executeQuery();
			
			adicionarResultSetNaLista(lista, resultadoSQL);
		} catch(Exception e) {
			throw new SQLException(nomeTabela + " - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return lista;
	}
	
	/*
	 * This method retrieves a list of objects not registered
	 * @param an ArrayList<O>
	 * @return an ArrayList<O>
	 */
	protected ArrayList<O> getListaNaoCadastrados(ArrayList<O> lista) throws SQLException {
		ArrayList<O> listaNaoCadastrados = new ArrayList<>();
		ArrayList<O> listaCadastrados = getLista();
		
		if(this.comparador != null) {
			Collections.sort(listaCadastrados, this.comparador);
			for(O objeto : lista) {
				if(Collections.binarySearch(listaCadastrados, objeto, this.comparador) < 0) {
					listaNaoCadastrados.add(objeto);
				}
			}
		} else {
			for(O objeto : lista) {
				if(!listaCadastrados.contains(objeto)) {
					listaNaoCadastrados.add(objeto);
				}
			}
		}
		return listaNaoCadastrados;
	}
	
	// Signature of the method to recover the insert SQL command
	protected abstract String getSqlInsert();
	
	// Signature of the method to recover the select SQL command
	protected abstract String getSqlSelect();
	
	// Method signature for formalization joined a list of instances in the database
	protected abstract void adicionarListaNoBatch(ArrayList<O> lista, PreparedStatement instrucaoSQL) throws SQLException;
	
	// Signature of the method to populates the ArrayList<O>
	protected abstract void adicionarResultSetNaLista(ArrayList<O> lista, ResultSet resultadoSQL) throws SQLException ;
	
	/*
	 * This method closes the connection with the database
	 */
	protected void fecharConexao() throws SQLException {
		if(this.instrucaoSQL != null) {
			this.instrucaoSQL.close();
		}
		if(this.conexao != null) {
			this.conexao.close();
		}
	}
	
	/*
	 * This method prepares statement for transactions in the database
	 */
	protected PreparedStatement getInstrucaoSQL(String comandoSQL) throws SQLException {		
		return this.conexao.prepareStatement(comandoSQL);
	}
}