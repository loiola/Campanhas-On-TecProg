package test.parse.register;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParseCampaign;
import test.TemplateTest;

public class RegisterToParseCampaignTest extends TemplateTest {

	public final static int NUMBER_LINES_TO_REGISTER = 100000;
	
	private RegisterToParseCampaign register;
	String  fileType = "campaign";
	String  year         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register = new RegisterToParseCampaign(this.fileType, this.year);	
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void shouldReturnCampaignRegistered() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[2] = "2006";
		field[12] = "45555";
		field[13] = "NAME URNA";
		field[5] = "DF";
		field[8] = "1";
		field[26] = "55325424149";
		field[16] = "45";
		field[40] = "2";
		field[39] = "450000.0";
		register.runFileLine(field);
		register.registerInstances();
	}
	
	@Test
	public void shouldReturnGetLinesToRegister() throws ParseException, SQLException {
		int linesToRegister = register.getlinesToRegister();
		Assert.assertEquals(NUMBER_LINES_TO_REGISTER, linesToRegister);
	}
	
	

}
