package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Position;
import model.dao.PositionDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParsePosition;
import test.TemplateTest;

public class CadastroCargoParseTeste extends TemplateTest {

	private RegisterToParsePosition cadastro;
	private PositionDAO positionDAO;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new RegisterToParsePosition(this.tipoArquivo, this.ano);	
		this.positionDAO = new PositionDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRetornarUmCandidatoCadastrado() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[8] = "1";
		campo[9] = "CARGO TESTE";
		cadastro.runFileLine(campo);
		cadastro.registerInstances();
		
		Position position = this.positionDAO.getPositionByCode(1);
		assertEquals(position.getPositionCode().toString(), "1");
	}

}
