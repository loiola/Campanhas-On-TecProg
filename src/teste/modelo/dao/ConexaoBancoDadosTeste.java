package teste.modelo.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import model.dao.ConexaoBancoDados;

import org.junit.Before;
import org.junit.Test;

public class ConexaoBancoDadosTeste {
	
	private static final String NOME_BANCO_OFICIAL = "c_on";
	private static final String LOCAL_BANCO_OFICIAL = "jdbc:mysql://";
	
	private static final String NOME_BANCO_TESTES = "banco_de_testes";
	
	private ConexaoBancoDados conexaoBancoDados;
	
	@Before
	public void setUp() {
		
		this.conexaoBancoDados = new ConexaoBancoDados();
		this.conexaoBancoDados.alterarBanco(NOME_BANCO_OFICIAL);
		this.conexaoBancoDados.setLocalBanco(LOCAL_BANCO_OFICIAL);
	}

	@Test
	public void naoDeveLancarExcecaoAoCriarEDeletarOBancoDeTestes() throws IOException, SQLException {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL1 = diretorioSQL + "/mer_campanha.sql";
		String arquivoSQL2 = diretorioSQL + "/mer_movimentacoes.sql";			
		this.conexaoBancoDados = new ConexaoBancoDados();
		this.conexaoBancoDados.criarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.alterarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.importarSQL(arquivoSQL1);
		this.conexaoBancoDados.importarSQL(arquivoSQL2);
		this.conexaoBancoDados.deletarBanco();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoCriarUmBancoDeTestesComLocalInvalido() throws IOException, SQLException {
		
		String localInvalido = "Local Invalido";
		this.conexaoBancoDados = new ConexaoBancoDados();
		this.conexaoBancoDados.setLocalBanco(localInvalido);
		this.conexaoBancoDados.criarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.deletarBanco();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoCriarUmBancoDeTestesComArquivoInvalido() throws IOException, SQLException {
		
		String diretorioSQL = new File("./lib/").getCanonicalPath();
		String arquivoSQL = diretorioSQL + "/Arquivo_Invalido.sql";			
		this.conexaoBancoDados = new ConexaoBancoDados();
		this.conexaoBancoDados.criarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.alterarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.importarSQL(arquivoSQL);
		this.conexaoBancoDados.deletarBanco();
	}
	
	@Test(expected = SQLException.class)
	public void deveLancarExcecaoAoDeletarUmBancoDeTestesComLocalInvalido() throws IOException, SQLException {
		
		String localInvalido = "Local Invalido";
		this.conexaoBancoDados = new ConexaoBancoDados();
		this.conexaoBancoDados.criarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.setLocalBanco(localInvalido);
		this.conexaoBancoDados.alterarBanco(NOME_BANCO_TESTES);
		this.conexaoBancoDados.deletarBanco();
	}

}
