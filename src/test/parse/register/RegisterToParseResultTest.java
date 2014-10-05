package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParseResult;
import test.TemplateTest;

public class RegisterToParseResultTest extends TemplateTest {
	
	private RegisterToParseResult register;
	private ResultDAO dao;
	String  fileType = "campaign";
	String  year         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register = new RegisterToParseResult(this.fileType, this.year);	
		this.dao = new ResultDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void shouldReturnResultRegistered() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[40] = "25";
		field[41] = "Result 25";
		register.runFileLine(field);
		register.registerInstances();
		
		Result result = dao.getResultByCode(25);
		assertEquals(result.getResultDescription(), "Result 25");
	}

}
