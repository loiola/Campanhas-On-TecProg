package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import modelo.beans.Fornecedor;
import parse.ParseDAO;

public class FornecedorDAO extends BasicoDAO<Fornecedor> implements ParseDAO<Fornecedor> {
	
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
	public enum Comparacao implements Comparator<Fornecedor> {
		NOME {
			public int compare(Fornecedor f1, Fornecedor f2) {
				return f1.getNome().compareToIgnoreCase(f2.getNome());
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
	protected void adicionarListaNoBatch(ArrayList<Fornecedor> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Fornecedor fornecedor : lista) {
			instrucaoSQL.setString(1, fornecedor.getCpf_cnpj());
			instrucaoSQL.setString(2, fornecedor.getNome());
			instrucaoSQL.setString(3, fornecedor.getUf());
			instrucaoSQL.setString(4, fornecedor.getSituacaoCadastral());
			instrucaoSQL.addBatch();
		}	
	}

	/*
	 * This method populates the ArrayList<supplier>
	 * @param an ArrayList<Supplier>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Fornecedor> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ));
			fornecedor.setNome(resultadoSQL.getString(NOME));
			fornecedor.setUf(resultadoSQL.getString(UF));
			fornecedor.setSituacaoCadastral(resultadoSQL.getString(SITUACAO_CADASTRAL));
			lista.add(fornecedor);
		}
	}

	/*
	 * This method retrieves a supplier through the name or CNPJ
	 * @param an instance of Class Supplier
	 * @return an instance of Class Supplier
	 */
	public Fornecedor getPeloNomeOuCpfCnpj(Fornecedor fornecedor) throws Exception {
		String comandoSQL = SQL_SELECAO + " WHERE ";
		if(!fornecedor.getNome().equals(Fornecedor.STRING_VAZIO)) {
			comandoSQL = comandoSQL + NOME + " = " 
		  + fornecedor.getNome();
		}
		else if(!fornecedor.getCpf_cnpj().equals(Fornecedor.STRING_VAZIO)) {
			comandoSQL = comandoSQL + CPF_CNPJ + " = " 
		  + fornecedor.getCpf_cnpj();
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
	public ArrayList<Fornecedor> buscaBD(String SQL) throws SQLException {

		ArrayList<Fornecedor> listaFornecedor = new ArrayList<>();

		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;

			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);

			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Fornecedor fornecedor = new Fornecedor();
				
				fornecedor.setNome(resultadoSQL.getString(NOME));
				fornecedor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ));
				fornecedor.setSituacaoCadastral(resultadoSQL.getString(SITUACAO_CADASTRAL));
				fornecedor.setUf(resultadoSQL.getString(UF));

				if(fornecedor != null) {
					listaFornecedor.add(fornecedor);
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