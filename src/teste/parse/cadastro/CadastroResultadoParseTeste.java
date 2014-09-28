package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParseResult;
import teste.TemplateTest;

public class CadastroResultadoParseTeste extends TemplateTest {
	
	private RegisterToParseResult cadastro;
	private ResultDAO dao;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new RegisterToParseResult(this.tipoArquivo, this.ano);	
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
		cadastro.runFileLine(campo);
		cadastro.registerInstances();
		
		Result result = dao.getResultByCode(25);
		assertEquals(result.getResultDescription(), "Result 25");
	}

}
