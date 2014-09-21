package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import modelo.beans.Donor;
import modelo.beans.Supplier;
import parse.ParseDAO;

public class DoadorDAO extends BasicoDAO<Donor> implements ParseDAO<Donor> {

	/*
	 * Class for manipulating the data about donor
	 */
	
	// Constants
	private static final String NOME_TABELA = "doador";
	private static final String CPF_CNPJ = "cpf_cnpj";
	private static final String NOME = "nome";
	private static final String UF = "uf";
	private static final String SITUACAO_CADASTRAL = "situacao_cadastral";
	private static final String SQL_INSERCAO = "INSERT INTO " + NOME_TABELA
			+ " (" + CPF_CNPJ + ", " + NOME + ", " + UF + ", " + SITUACAO_CADASTRAL + ") "
			+ "values (?, ?, ?, ?)";
	private static final String SQL_SELECAO = "SELECT * FROM " + NOME_TABELA;
	
	// Constructors
	public DoadorDAO() {
		super(NOME_TABELA, Comparacao.NOME);
	}

	// Other methods
	/*
	 * Comparator to check if two instances are equal donor through CNPJ
	 */
	public enum Comparacao implements Comparator<Donor> {
		NOME {
			public int compare(Donor d1, Donor d2) {
				return d1.getCpf_cnpj().compareToIgnoreCase(d2.getCpf_cnpj());
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
	 * This method prepares a list of donor to be registered
	 * @param an ArrayList<Donor>
	 * @param a SQLinstruction
	 */
	@Override
	protected void adicionarListaNoBatch(ArrayList<Donor> lista,
			PreparedStatement instrucaoSQL) throws SQLException {
		for(Donor donor : lista) {
			instrucaoSQL.setString(1, donor.getCpf_cnpj());
			instrucaoSQL.setString(2, donor.getNome());
			instrucaoSQL.setString(3, donor.getUf());
			instrucaoSQL.setString(4, donor.getSituacaoCadastral());
			instrucaoSQL.addBatch();
		}
	}

	/*
	 * This method populates the ArrayList<Donor>
	 * @param an ArrayList<Donor>
	 * @param a SQLresult
	 */
	@Override
	protected void adicionarResultSetNaLista(ArrayList<Donor> lista,
			ResultSet resultadoSQL) throws SQLException {
		while(resultadoSQL.next()) {
			Donor donor = new Donor();
			donor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ));
			donor.setNome(resultadoSQL.getString(NOME));
			donor.setUf(resultadoSQL.getString(UF));
			donor.setSituacaoCadastral(resultadoSQL.getString(SITUACAO_CADASTRAL));
			
			lista.add(donor);
		}
	}

	/*
	 * This method retrieves a complete list of donor stored in the database
	 * @param a String with the SQL command
	 * @return an ArrayList<Donor>
	 */
	public ArrayList<Donor> buscaBD(String SQL) throws SQLException {

		ArrayList<Donor> listaDoador = new ArrayList<>();
		
		try {
			this.conexao = new ConexaoBancoDados().getConexao();

			String comandoSQL = SQL;
			this.instrucaoSQL = this.conexao.prepareStatement(comandoSQL);
			ResultSet resultadoSQL = (ResultSet) instrucaoSQL.executeQuery();

			while(resultadoSQL.next()) {
				Donor donor = new Donor();
				donor.setNome(resultadoSQL.getString(NOME));
				donor.setCpf_cnpj(resultadoSQL.getString(CPF_CNPJ));
				donor.setSituacaoCadastral(resultadoSQL.getString(SITUACAO_CADASTRAL));
				donor.setUf(resultadoSQL.getString(UF));

				if(donor != null) {
					listaDoador.add(donor);
				}
			}
		} catch(SQLException e) {
			throw new SQLException("DoadorDAO - " + e.getMessage());
		} finally {
			fecharConexao();
		}
		return listaDoador;
	}
	
	/*
	 * This method retrieves a donor through the name or CNPJ
	 * @param an instance of Class Donor
	 * @return an instance of Class Donor
	 */
	public Donor getPeloNomeOuCpfCnpj(Donor donor) throws Exception {
		String comandoSQL = SQL_SELECAO + " WHERE ";
		if(!donor.getNome().equals(Supplier.STRING_VAZIO)) {
			comandoSQL = comandoSQL + NOME + " = " 
		  + donor.getNome();
		}
		else if(!donor.getCpf_cnpj().equals(Supplier.STRING_VAZIO)) {
			comandoSQL = comandoSQL + CPF_CNPJ + " = " 
		  + donor.getCpf_cnpj();
		} else {
			throw new Exception();
		}
		return buscaBD(comandoSQL).get(0);
	}
}