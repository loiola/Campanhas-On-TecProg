package teste;

import java.io.File;

import model.dao.DatabaseConnection;

import org.junit.After;
import org.junit.Before;

public abstract class TemplateTeste {
	
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
		this.databaseConnection.alterarBanco(NOME_BANCO_OFICIAL);
		this.databaseConnection.setLocalBanco(LOCAL_BANCO_OFICIAL);
		this.databaseConnection.criarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.alterarBanco(NOME_BANCO_TESTES);
		this.databaseConnection.importarSQL(arquivoSQL1);
		this.databaseConnection.importarSQL(arquivoSQL2);

		beforeTest();
	}

	@After
	public void tearDown() throws Exception {
		afterTest();
		
		if(!this.databaseConnection.getLocalBanco().equals(LOCAL_BANCO_ERROR)) {
			this.databaseConnection.deletarBanco();
		}	
	}
	
	public abstract void beforeTest() throws Exception;
	public abstract void afterTest() throws Exception;

}
