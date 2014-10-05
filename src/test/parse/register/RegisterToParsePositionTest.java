package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Position;
import model.dao.PositionDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParsePosition;
import test.TemplateTest;

public class RegisterToParsePositionTest extends TemplateTest {

	private RegisterToParsePosition register;
	private PositionDAO positionDAO;
	String  fileType = "campaign";
	String  year         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register = new RegisterToParsePosition(this.fileType, this.year);	
		this.positionDAO = new PositionDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void shouldReturnCandidateRegistered() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[8] = "1";
		field[9] = "POSITION TESTE";
		register.runFileLine(field);
		register.registerInstances();
		
		Position position = this.positionDAO.getPositionByCode(1);
		assertEquals(position.getPositionCode().toString(), "1");
	}

}
