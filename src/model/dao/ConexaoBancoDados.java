package model.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBancoDados {
	
	/*
	 * This class contains methods for managing connection to the database
	 */
	
	// Attributes
	private Connection conexao;
	private Statement afirmacao;
	
	// Constants
	private static String localBanco = "jdbc:mysql://";	
	private static String nomeSevidor = "localhost";
	private static String nomeBanco = "c_on";
	private static String usuario = "root";
	private static String senha = "root";
	
	// Constructors
	public ConexaoBancoDados() {
		
	}
	
	// Other methods
	/*
	 * This method returns a connection to the database
	 * @return an instance of Class Connection
	 */
	public Connection getConexao() throws SQLException {
		Connection conexao = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(localBanco + nomeSevidor + "/" + nomeBanco, usuario, senha);
			return conexao;
		} catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	/*
	 * This method creates a database
	 * @param a String with the name of database
	 */
	public void criarBanco(String nomeNovoBanco) throws SQLException {
		try {
			this.conexao = new ConexaoBancoDados().getConexao();
			this.afirmacao = this.conexao.createStatement();

			String comandoSQL = "create database if not exists " + nomeNovoBanco;
			
			this.afirmacao.executeUpdate(comandoSQL);
		} catch(Exception e) {
			throw new SQLException("ConexaoBancoDados - " + e.getMessage());
		} finally {
			fecharConexao();
		}
	}
	
	/*
	 * This method changes the database name
	 * @param a String with the new name
	 */
	public void alterarBanco(String nomeBancoAtual) {
		nomeBanco = nomeBancoAtual;
	}
	
	/*
	 * This method performs an import of SQL commands contained in a file
	 * @param a String with the description of SQL file
	 */
	public void importarSQL(String arquivoSQL) throws SQLException {
		try {
			this.conexao = new ConexaoBancoDados().getConexao();
			this.afirmacao = this.conexao.createStatement();
			
			String comando[] = getLinhasArquivo(arquivoSQL);
			for(String linha : comando) {
				this.afirmacao.execute(linha);
			}	
		} catch(Exception e) {
			throw new SQLException("ConexaoBancoDados - " + e.getMessage());
		} finally {
			fecharConexao();
		}
	}
	
	/*
	 * This method performs the deletion of a database
	 */
	public void deletarBanco() throws SQLException {
		try {
			this.conexao = new ConexaoBancoDados().getConexao();
			this.afirmacao = this.conexao.createStatement();
			
			String comandoSQL = "drop database if exists " + nomeBanco;
			
			this.afirmacao.executeUpdate(comandoSQL);
		} catch(Exception e) {
			throw new SQLException("ConexaoBancoDados - " + e.getMessage());
		} finally {
			fecharConexao();
		}
	}

	/*
	 * This method modifies the location of the database
	 * @param a String with the new place for database
	 */
	public void setLocalBanco(String novoLocalBanco) {
		localBanco = novoLocalBanco;
	}
	
	/*
	 * This method retrieves the current location of the database
	 * @return a String with the place of database
	 */
	public String getLocalBanco() {
		return localBanco;
	}
	
	/*
	 * This method closes the connection to the database
	 */
	private void fecharConexao() throws SQLException {
		if(this.conexao != null) {
			this.conexao.close();
		}
	}
	
	/*
	 * This method performs the reading of a file and stores the command
	 * @param an array of strings with the command
	 */
	private String[] getLinhasArquivo(String arquivo) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(arquivo)));
		String linhaComando = "";
		String comando[];
		String linhaLida;
		while((linhaLida = bufferedReader.readLine()) != null) {
			if(!linhaLida.isEmpty()) {
				linhaComando += linhaLida;
			}
		}
		comando = linhaComando.split(";");
		bufferedReader.close();
		return comando;
	}
}