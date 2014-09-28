package teste;

import java.io.File;

import model.dao.DatabaseConnection;

import org.junit.After;
import org.junit.Before;

public abstract class TemplateTest {
	
	public static final String NOME_BANCO_OFICIAL = "c_on";
	public static final String LOCAL_BANCO_OFICIAL = "jdbc:mysql://";
	
	public static final String NOME_BANCO_TESTES = "banco_de_testes";
	public static final String LOCAL_BANCO_ERROR = "Erro na Conexao";
	
	protected DatabaseConnection databaseConnection;
	
	@Before
	public void setUp() throws Exception {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL1 = diretorioSQL + "/mer_campanha.sql";	
		String arquivoSQL2 = diretorioSQL + "/mer_movimentacoes.sql";		
		this.databaseConnection = new DatabaseConnection();
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_OFICIAL);
		this.databaseConnection.setDatabasePath(LOCAL_BANCO_OFICIAL);
		this.databaseConnection.createDatabaseSchema(NOME_BANCO_TESTES);
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_TESTES);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL1);
		this.databaseConnection.readSQLCommandFromFile(arquivoSQL2);

		beforeTest();
	}

	@After
	public void tearDown() throws Exception {
		afterTest();
		
		if(!this.databaseConnection.getDatabasePath().equals(LOCAL_BANCO_ERROR)) {
			this.databaseConnection.dropDatabaseName();
		}	
	}
	
	public abstract void beforeTest() throws Exception;
	public abstract void afterTest() throws Exception;

}