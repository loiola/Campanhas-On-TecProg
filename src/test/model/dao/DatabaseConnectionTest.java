package test.model.dao;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import model.dao.DatabaseConnection;

import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {
	
	private static final String DATABASE_OFFICIAL_NAME = "c_on";
	private static final String DATABASE_OFFICIAL_PATH = "jdbc:mysql://";
	
	private static final String DATABASE_TESTS = "banco_de_testes";
	
	private DatabaseConnection databaseConnection;
	
	@Before
	public void setUp() {
		
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.adjustDatabaseSchemaName(DATABASE_OFFICIAL_NAME);
		this.databaseConnection.setDatabasePath(DATABASE_OFFICIAL_PATH);
	}

	@Test
	public void naoDeveLancarExcecaoAoCriarEDeletarOBancoDeTestes() throws IOException, SQLException {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL1 = diretorioSQL + "/mer_campanha.sql";
		String arquivoSQL2 = diretorioSQL + "/mer_movimentacoes.sql";			
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.createDatabaseSchema(DATABASE_TESTS);
		this.databaseConnection.adjustDatabaseSchemaName(DATABASE_TESTS);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL1);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL2);
		this.databaseConnection.dropDatabaseName();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoCriarUmBancoDeTestesComLocalInvalido() throws IOException, SQLException {
		
		String localInvalido = "Local Invalido";
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.setDatabasePath(localInvalido);
		this.databaseConnection.createDatabaseSchema(DATABASE_TESTS);
		this.databaseConnection.dropDatabaseName();
		assertTrue(true);
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoCriarUmBancoDeTestesComArquivoInvalido() throws IOException, SQLException {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL = diretorioSQL + "/Arquivo_Invalido.sql";			
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.createDatabaseSchema(DATABASE_TESTS);
		this.databaseConnection.adjustDatabaseSchemaName(DATABASE_TESTS);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL);
		this.databaseConnection.dropDatabaseName();
		assertTrue(true);
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoDeletarUmBancoDeTestesComLocalInvalido() throws IOException, SQLException {
		
		String localInvalido = "Local Invalido";
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.createDatabaseSchema(DATABASE_TESTS);
		this.databaseConnection.setDatabasePath(localInvalido);
		this.databaseConnection.adjustDatabaseSchemaName(DATABASE_TESTS);
		this.databaseConnection.dropDatabaseName();
		assertTrue(true);
	}

}
