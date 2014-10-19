package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Assert;
import org.junit.Test;

import test.TemplateTest;

public class PartyDAOTest extends TemplateTest {

	private PartyDAO partyDAO;

	@Override
	public void beforeTest() throws Exception {
		
		this.partyDAO = new PartyDAO();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void comparationValues() throws Exception {

		Party partyOne = new Party();
		Party partyTwo = new Party();
		partyOne.setPartyAcronym("AEIOU");
		partyTwo.setPartyAcronym("AEIOU");
		int result;

		result = PartyDAO.CompareTwoPartiesAcronym.ACRONYM.compare(partyOne, partyTwo);

		Assert.assertEquals(0, result);
	}

	@Test
	public void shouldNotThrowsExceptionToRegisterANewParty() throws Exception {

		ArrayList<Party> partyList = new ArrayList<>();

		Party party = new Party();
		party.setPartyNumber(1);
		party.setPartyAcronym("A");
		party.setPartyConcession("11.2.1982");
		party.setPartyName("AEIOU");
		partyList.add(party);

		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(partyList);
	}

	@Test
	public void shouldNotRegisterAgainAnExistingParty() throws Exception {

		ArrayList<Party> partyList = new ArrayList<>();

		Party party = new Party();
		party.setPartyNumber(1);
		party.setPartyAcronym("A");
		party.setPartyConcession("11.2.1982");
		party.setPartyName("AEIOU");
		partyList.add(party);

		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(partyList);
		int numeroDePartidosNaLista = this.partyDAO.getObjectArrayListFromDatabase().size();

		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(partyList);

		Assert.assertEquals(numeroDePartidosNaLista, this.partyDAO.getObjectArrayListFromDatabase()
				.size());
	}

	@Test
	public void shouldRetrievePartyByAcronym() throws SQLException {
		
		ArrayList<Party> partyList = new ArrayList<>();
		Party partyRecovered = new Party();
		
		Party partyOne = new Party();
		partyOne.setPartyNumber(45);
		partyOne.setPartyAcronym("PI1");
		partyOne.setPartyName("PARTY INEXISTENTE 1");
		partyOne.setPartyConcession("11.8.1996");
		partyList.add(partyOne);
		
		Party partyTwo = new Party();
		partyTwo.setPartyNumber(23);
		partyTwo.setPartyAcronym("PI2");
		partyTwo.setPartyName("PARTY INEXISTENTE 2");
		partyTwo.setPartyConcession("11.8.1994");
		partyList.add(partyTwo);
		
		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(partyList);
		partyRecovered = this.partyDAO.getPartyByAcronym("PI1");
		
		Assert.assertEquals(partyOne, partyRecovered);
	}

	@Test
	public void shouldRetrievePartyByNumber() throws SQLException {
		
		partyDAO.getPartyByNumber(null);
	}
	
}
