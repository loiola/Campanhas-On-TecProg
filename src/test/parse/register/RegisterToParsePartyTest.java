package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.party.RegisterToParseParty;
import test.TemplateTest;

public class RegisterToParsePartyTest extends TemplateTest {

	String  fileTypeA = "party";
	String  fileTypeB = "campaign";
	String  fileTypeC = "error";
	String  year1         = "2002";
	String  year2         = "2006";
	String  year3         = "2010";
	PartyDAO partyDAO;
	private RegisterToParseParty register1;
	private RegisterToParseParty register2;
	private RegisterToParseParty register3;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register3 = new RegisterToParseParty(this.fileTypeC, this.year1);
		this.partyDAO = new PartyDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void shouldReturnPartyRegisteredFor2002PerParty() throws ParseException, SQLException {
		
		this.register1 = new RegisterToParseParty(this.fileTypeA, this.year1);
		
		String field[] = new String[50];
		field[2] = "Nome";
		field[1] = "Sigla";
		field[5] = "123";
		field[3] = "1234";
		register1.runFileLine(field);
		register1.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
		
	}
	
	@Test
	public void shouldReturnPartyRegisteredFor2002PerCampaign() throws ParseException, SQLException {
		
		this.register2 = new RegisterToParseParty(this.fileTypeB, this.year1);
		
		String field[] = new String[50];
		field[18] = "Nome";
		field[17] = "Sigla";
		field[16] = "123";
		register2.runFileLine(field);
		register2.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void shouldReturnPartyRegisteredFor2006PerParty() throws ParseException, SQLException {
		
		this.register1 = new RegisterToParseParty(this.fileTypeA, this.year2);
		
		String field[] = new String[50];
		field[2] = "Nome";
		field[1] = "Sigla";
		field[5] = "123";
		field[3] = "1234";
		register1.runFileLine(field);
		register1.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void shouldReturnPartyRegisteredFor2006PerCampaign() throws ParseException, SQLException {
		
		this.register2 = new RegisterToParseParty(this.fileTypeB, this.year2);
		
		String field[] = new String[50];
		field[18] = "Nome";
		field[17] = "Sigla";
		field[16] = "123";
		register2.runFileLine(field);
		register2.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void shouldReturnPartyRegisteredFor2010PerParty() throws ParseException, SQLException {
		
		this.register1 = new RegisterToParseParty(this.fileTypeA, this.year3);
		
		String field[] = new String[50];
		field[2] = "Nome";
		field[1] = "Sigla";
		field[5] = "123";
		field[3] = "1234";
		register1.runFileLine(field);
		register1.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void shouldReturnPartyRegisteredFor2010PerCampaign() throws ParseException, SQLException {
		
		this.register2 = new RegisterToParseParty(this.fileTypeB, this.year3);
		
		String field[] = new String[50];
		field[18] = "Nome";
		field[17] = "Sigla";
		field[16] = "123";
		register2.runFileLine(field);
		register2.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void shouldReturnPartyEmptyIndexWhenPassAFileWithInvalidName() throws ParseException, SQLException {
		
		String field[] = new String[50];
		register3.runFileLine(field);
		register3.registerInstances();
	}

}
