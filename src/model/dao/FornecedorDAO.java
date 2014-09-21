package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import model.beans.Supplier;
import parse.ParseDAO;

public class FornecedorDAO extends BasicoDAO<Supplier> implements ParseDAO<Supplier> {
	
	/*
	 * Class for manipulating the data about supplier
	 */
	
	// Constants	
	private static final String NOME_TABELA = "fornecedor";
	private static final String CPF_CNPJ = "cpf_cnpj";
	private static final String NOME = "nome";
	private static final String UF = "uf";
	private static final String SITUACAO_CADASTRAL = "situacao_cadastral";
	
	private static final String SQL_INSERCAO = "INSERT INTO " + NOME_TABELA
			+ " (" + CPF_CNPJ + ", " + NOME + ", " + UF + ", " + SITUACAO_CADASTRAL + ") "
			+ "VALUES(?, ?, ?, ?)";
	private static final String SQL_SELECAO = "SELECT * FROM " + NOME_TABELA;
	
	// Constructors
	public FornecedorDAO() {
		super(NOME_TABELA, Comparacao.NOME);
	}
	
	// Other methods
	/*
	 * Comparator to check if two instances are equal supplier through name
	 */
	public enum Comparacao implements Comparator<Supplier> {
		NOME {
			public int compare(Supplier f1, Supplier f2) {
				return f1.getSupplierName().compareToIgnoreCase(f2.getSupplierName());
			}
		}	
	}

	/*
	 * This method retrieves the SQL command to insert data
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlInsert() {
		return SQL_INSERCAO;
	}

	/*
	 * This method retrieves the string that has the SQL command for selecting data in a database table
	 * @return a String with the SQL command
	 */
	@Override
	protected String getSqlSelect() {
		return SQL_SELECAO;
	}

	/*
	 * This method prepares a list of supplier to be registered
	 * @param an ArrayList<Supplier>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Supplier> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Supplier supplier : lista) {
			instrucaoSQL.setString(1, supplier.getSupplierPersonRegister());
			instrucaoSQL.setString(2, supplier.getSupplierName());
			instrucaoSQL.setString(3, supplier.getSupplierCountryState());
			instrucaoSQL.setString(4, supplier.getSupplierRegisterSituation());
			instrucaoSQL.addBatch();
		}	
	}

	/*
	 * This method populates the ArrayList<supplier>
	 * @param an ArrayList<Supplier>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Supplier> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Supplier supplier = new Supplier();
			supplier.setSupplierPersonRegister(resultadoSQL.getString(CPF_CNPJ));
			supplier.setSupplierName(resultadoSQL.getString(NOME));
			supplier.setSupplierCountryState(resultadoSQL.getString(UF));
			supplier.setSupplierRegisterSituation(resultadoSQL.getString(SITUACAO_CADASTRAL));
			lista.add(supplier);
		}
	}

	/*
	 * This method retrieves a supplier through the name or CNPJ
	 * @param an instance of Class Supplier
	 * @return an instance of Class Supplier
	 */
	public Supplier getPeloNomeOuCpfCnpj(Supplier supplier) throws Exception {
		String comandoSQL = SQL_SELECAO + " WHERE ";
		if(!supplier.getSupplierName().equals(Supplier.EMPTY_TYPE_STRING)) {
			comandoSQL = comandoSQL + NOME + " = " 
		  + supplier.getSupplierName();
		}
		else if(!supplier.getSupplierPersonRegister().equals(Supplier.EMPTY_TYPE_STRING)) {
			comandoSQL = comandoSQL + CPF_CNPJ + " = " 
		  + supplier.getSupplierPersonRegister();
		} else {
			throw new Exception();
		}
		return buscaBD(comandoSQL).get(0);
	}

	/*
	 * This method retrieves a complete list of suppliers stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Supplier>
	 */
	public ArrayList<Supplier> buscaBD(String SQL) throws SQLException {

		ArrayList<Supplier> listaFornecedor = new ArrayList<>();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Supplier supplier = new Supplier();
				
				supplier.setSupplierName(resultadoSQL.getString(NOME));
				supplier.setSupplierPersonRegister(resultadoSQL.getString(CPF_CNPJ));
				supplier.setSupplierRegisterSituation(resultadoSQL.getString(SITUACAO_CADASTRAL));
				supplier.setSupplierCountryState(resultadoSQL.getString(UF));

				if(supplier != null) {
					listaFornecedor.add(supplier);
				}
			}
		}  catch(SQLException e) {
			throw new SQLException("FornecedorDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaFornecedor;
	}
}