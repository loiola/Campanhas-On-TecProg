package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Test;

import parse.ParseException;
import parse.cadastro.campanha.CadastroResultadoParse;
import teste.TemplateTeste;

public class CadastroResultadoParseTeste extends TemplateTeste {
	
	private CadastroResultadoParse cadastro;
	private ResultDAO dao;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new CadastroResultadoParse(this.tipoArquivo, this.ano);	
		this.dao = new ResultDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRetornarUmResultadoCadastrado() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[40] = "25";
		campo[41] = "Result 25";
		cadastro.executarLinhaDoArquivo(campo);
		cadastro.cadastrarInstancias();
		
		Result result = dao.getResultByCode(25);
		assertEquals(result.getResultDescription(), "Result 25");
	}

}
