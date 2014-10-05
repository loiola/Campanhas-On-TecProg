package test.parse.index;

import model.beans.Party;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.PartyParseIndex;

public class PartyParseIndexTest {
		
	private String field[];
	private PartyParseIndex partyParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.partyParseIndex = new PartyParseIndex();
		this.field = new String[4];
		
		fieldsStart();
		indexStart();
	}

	@Test
	public void startPartyWithValidIndices() throws Exception {
		
		Party party = new Party();
		this.partyParseIndex.startInstance(party, field);
		Assert.assertEquals(this.field[0], party.getPartyAcronym());
		Assert.assertEquals(this.field[1], party.getPartyNumber().toString());
		Assert.assertEquals(this.field[2], party.getPartyConcession());
		Assert.assertEquals(this.field[3], party.getPartyName());
	}
	
	@Test
	public void startPartyWithInvalidIndices() throws Exception {
		
		this.partyParseIndex = new PartyParseIndex();
		Party party = new Party();
		this.partyParseIndex.startInstance(party, field);
		Assert.assertNotEquals(this.field[0], party.getPartyAcronym());
		Assert.assertNotEquals(this.field[1], party.getPartyNumber().toString());
		Assert.assertNotEquals(this.field[2], party.getPartyConcession());
		Assert.assertNotEquals(this.field[3], party.getPartyName());
	}
	
	private void indexStart() {
		
		this.partyParseIndex.setIndexAcronym(0);
		this.partyParseIndex.setIndexNumberParty(1);
		this.partyParseIndex.setIndexDeferral(2);
		this.partyParseIndex.setIndexPartyName(3);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "DEM";
		this.field[1] = "25";
		this.field[2] = "11.9.1986";
		this.field[3] = "DEMOCRATAS";
	}
	
}
