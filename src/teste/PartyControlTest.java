package teste;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Test;

import parse.ParseException;
import control.PartyControl;

public class PartyControlTest extends TemplateTest {
	
	private PartyDAO partyDAO;
	private PartyControl partyControl;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.partyControl = new PartyControl();
		this.partyDAO = new PartyDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void mustRetrieveAListOfPoliticalParties() throws SQLException, ParseException {
		
		ArrayList<Party> listPartiesToRegister = new ArrayList<>();
		ArrayList<Party> listPartiesRecovered = new ArrayList<>();
		
		Party partyOne = new Party();
		partyOne.setPartyName("PARTIDO EXISTENTE 1");
		partyOne.setPartyAcronym("PE1");
		partyOne.setPartyNumber(46);
		partyOne.setPartyConcession("15.8.1996");
		listPartiesToRegister.add(partyOne);
		
		Party partyTwo = new Party();
		partyTwo.setPartyName("PARTIDO EXISTENTE 2");
		partyTwo.setPartyAcronym("PE2");
		partyOne.setPartyNumber(78);
		partyOne.setPartyConcession("15.4.1995");
		listPartiesToRegister.add(partyTwo);
		
		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(listPartiesToRegister);
		listPartiesRecovered = this.partyDAO.getObjectArrayListFromDatabase();
		
		assertEquals(listPartiesRecovered, this.partyControl.getListAllParties());
	}
	
	@Test
	public void mustRetrieveAPartyByAcronym() throws SQLException {
		
		ArrayList<Party> partyList = new ArrayList<>();
		Party partidoRecuperado = new Party();
		
		Party partyOne = new Party();
		partyOne.setPartyName("PARTIDO EXISTENTE 1");
		partyOne.setPartyAcronym("PE1");
		partyOne.setPartyNumber(46);
		partyOne.setPartyConcession("15.8.1996");
		partyList.add(partyOne);
		
		Party partyTwo = new Party();
		partyTwo.setPartyName("PARTIDO EXISTENTE 2");
		partyTwo.setPartyAcronym("PE2");
		partyOne.setPartyNumber(78);
		partyOne.setPartyConcession("15.4.1995");
		partyList.add(partyTwo);
		
		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(partyList);
		partidoRecuperado = this.partyDAO.getPartyByAcronym("PE1");
		
		assertEquals(partidoRecuperado, this.partyControl.getBySigla("PE1"));
	}
	
	@Test
	public void mustRetrieveAPartyByNumber() throws SQLException {
		
		ArrayList<Party> partyList = new ArrayList<>();
		Party partyRecovered = new Party();
		
		Party partyOne = new Party();
		partyOne.setPartyName("PARTIDO EXISTENTE 3");
		partyOne.setPartyAcronym("PE3");
		partyOne.setPartyNumber(47);
		partyOne.setPartyConcession("15.8.1985");
		partyList.add(partyOne);
		
		Party partyTwo = new Party();
		partyTwo.setPartyName("PARTIDO EXISTENTE 4");
		partyTwo.setPartyAcronym("PE4");
		partyOne.setPartyNumber(78);
		partyOne.setPartyConcession("15.5.1996");
		partyList.add(partyTwo);
		
		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(partyList);
		partyRecovered = this.partyDAO.getPartyByNumber("47");
		
		assertEquals(partyRecovered, this.partyControl.getByNumber("47"));
	}
	
}