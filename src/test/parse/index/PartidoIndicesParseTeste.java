package test.parse.index;

import model.beans.Party;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.PartyParseIndex;

public class PartidoIndicesParseTeste {
		
	private String campo[];
	private PartyParseIndex partyParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.partyParseIndex = new PartyParseIndex();
		this.campo = new String[4];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmPartidoComIndicesValidos() throws Exception {
		
		Party party = new Party();
		this.partyParseIndex.startInstance(party, campo);
		Assert.assertEquals(this.campo[0], party.getPartyAcronym());
		Assert.assertEquals(this.campo[1], party.getPartyNumber().toString());
		Assert.assertEquals(this.campo[2], party.getPartyConcession());
		Assert.assertEquals(this.campo[3], party.getPartyName());
	}
	
	@Test
	public void iniciarUmPartidoComIndicesInvalidos() throws Exception {
		
		this.partyParseIndex = new PartyParseIndex();
		Party party = new Party();
		this.partyParseIndex.startInstance(party, campo);
		Assert.assertNotEquals(this.campo[0], party.getPartyAcronym());
		Assert.assertNotEquals(this.campo[1], party.getPartyNumber().toString());
		Assert.assertNotEquals(this.campo[2], party.getPartyConcession());
		Assert.assertNotEquals(this.campo[3], party.getPartyName());
	}
	
	private void iniciarIndices() {
		
		this.partyParseIndex.setIndexAcronym(0);
		this.partyParseIndex.setIndexNumberParty(1);
		this.partyParseIndex.setIndexDeferral(2);
		this.partyParseIndex.setIndexPartyName(3);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "DEM";
		this.campo[1] = "25";
		this.campo[2] = "11.9.1986";
		this.campo[3] = "DEMOCRATAS";
	}
	
}
