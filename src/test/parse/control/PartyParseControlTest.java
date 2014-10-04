package test.parse.control;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlParty;
import parse.index.PartyParseIndex;
import test.TemplateTest;

public class PartyParseControlTest extends TemplateTest {
	
	public static final int ACRONYM = 0;
	public static final int NUMBER = 1;
	public static final int CONCESSION = 2;
	public static final int NAME = 3;
	
	private String fields[];
	private PartyDAO partyDAO;
	private PartyParseIndex partyParseIndex;
	private ParseControlParty parseControlParty;

	@Override
	public void beforeTest() throws Exception {
		
		this.fields = new String[4];
		this.partyDAO = new PartyDAO();
		this.partyParseIndex = new PartyParseIndex();
		this.parseControlParty = new ParseControlParty(this.partyParseIndex);
		
		fieldsStart();
		indexStart();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void registeringParty() throws Exception {
		
		this.parseControlParty.addInstance(fields);
		this.parseControlParty.registeringInstances();
		this.parseControlParty.clear();
		
		Party partyRegistered = this.partyDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.fields[ACRONYM], partyRegistered.getPartyAcronym());
		Assert.assertEquals(this.fields[NUMBER], partyRegistered.getPartyNumber().toString());
		Assert.assertEquals(this.fields[CONCESSION], partyRegistered.getPartyConcession());
		Assert.assertEquals(this.fields[NAME], partyRegistered.getPartyName());
	}
	
	@Test
	public void notToSignTwoEqualParty() throws Exception {
		
		this.parseControlParty.addInstance(fields);
		this.parseControlParty.addInstance(fields);
		this.parseControlParty.registeringInstances();
		this.parseControlParty.clear();
		
		int partyNumberDatabase = this.partyDAO.getObjectArrayListFromDatabase().size();
				
		Assert.assertEquals(1, partyNumberDatabase);
	}
	
	private void indexStart() {
		
		this.partyParseIndex.setIndexAcronym(ACRONYM);
		this.partyParseIndex.setIndexNumberParty(NUMBER);
		this.partyParseIndex.setIndexDeferral(CONCESSION);
		this.partyParseIndex.setIndexPartyName(NAME);
	}
	
	private void fieldsStart() {
		this.fields[ACRONYM] = "DEM";
		this.fields[NUMBER] = "25";
		this.fields[CONCESSION] = "11.9.1986";
		this.fields[NAME] = "DEMOCRATAS";
	}
	
}
