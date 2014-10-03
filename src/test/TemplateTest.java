package test;

import java.io.File;

import model.dao.DatabaseConnection;

import org.junit.After;
import org.junit.Before;

public abstract class TemplateTest {
	
	public static final String OFICIAL_DATABASE_NAME = "c_on";
	public static final String OFICIAL_DATABASE_PATH = "jdbc:mysql://";
	
	public static final String TEST_DATABASE_NAME = "banco_de_testes";
	public static final String ERROR_CONNECTION_DATABASE = "Erro na Conexao";
	
	protected DatabaseConnection databaseConnection;
	
	@Before
	public void setUp() throws Exception {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL1 = diretorioSQL + "/mer_campanha.sql";	
		String arquivoSQL2 = diretorioSQL + "/mer_movimentacoes.sql";		
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.adjustDatabaseSchemaName(OFICIAL_DATABASE_NAME);
		this.databaseConnection.setDatabasePath(OFICIAL_DATABASE_PATH);
		this.databaseConnection.createDatabaseSchema(TEST_DATABASE_NAME);
		this.databaseConnection.adjustDatabaseSchemaName(TEST_DATABASE_NAME);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL1);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL2);

		beforeTest();
	}

	@After
	public void tearDown() throws Exception {
		afterTest();
		
		if(!this.databaseConnection.getDatabasePath().equals(ERROR_CONNECTION_DATABASE)) {
			this.databaseConnection.dropDatabaseName();
		}	
	}
	
	public abstract void beforeTest() throws Exception;
	public abstract void afterTest() throws Exception;

}