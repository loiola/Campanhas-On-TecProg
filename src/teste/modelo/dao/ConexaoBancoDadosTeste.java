package teste.modelo.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import model.dao.DatabaseConnection;

import org.junit.Before;
import org.junit.Test;

public class ConexaoBancoDadosTeste {
	
	private static final String NOME_BANCO_OFICIAL = "c_on";
	private static final String LOCAL_BANCO_OFICIAL = "jdbc:mysql://";
	
	private static final String NOME_BANCO_TESTES = "banco_de_testes";
	
	private DatabaseConnection databaseConnection;
	
	@Before
	public void setUp() {
		
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.alterarBanco(NOME_BANCO_OFICIAL);
		this.databaseConnection.setLocalBanco(LOCAL_BANCO_OFICIAL);
	}

	@Test
	public void naoDeveLancarExcecaoAoCriarEDeletarOBancoDeTestes() throws IOException, SQLException {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL1 = diretorioSQL + "/mer_campanha.sql";
		String arquivoSQL2 = diretorioSQL + "/mer_movimentacoes.sql";			
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.criarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.alterarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.importarSQL(arquivoSQL1);
		this.databaseConnection.importarSQL(arquivoSQL2);
		this.databaseConnection.deletarBanco();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoCriarUmBancoDeTestesComLocalInvalido() throws IOException, SQLException {
		
		String localInvalido = "Local Invalido";
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.setLocalBanco(localInvalido);
		this.databaseConnection.criarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.deletarBanco();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoCriarUmBancoDeTestesComArquivoInvalido() throws IOException, SQLException {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL = diretorioSQL + "/Arquivo_Invalido.sql";			
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.criarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.alterarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.importarSQL(arquivoSQL);
		this.databaseConnection.deletarBanco();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoDeletarUmBancoDeTestesComLocalInvalido() throws IOException, SQLException {
		
		String localInvalido = "Local Invalido";
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.criarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.setLocalBanco(localInvalido);
		this.databaseConnection.alterarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.deletarBanco();
	}

}
