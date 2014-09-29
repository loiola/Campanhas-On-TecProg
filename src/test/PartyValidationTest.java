package test;

import model.beans.Candidate;
import model.beans.Party;

import org.junit.Test;

import control.exception.PartyException;
import control.validation.PartyValidation;

public class PartyValidationTest extends TemplateTest {
	
	private PartyValidation partyValidation;
	private Party party;
	private Candidate candidate;
	
	@Override
	public void beforeTest() throws Exception {
		this.partyValidation = new PartyValidation();
		this.party = new Party();
		this.candidate = new Candidate();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void dontThrowsExceptionIfAcronymIsNotNullOne() throws PartyException {
		this.party.setPartyAcronym("PT");
		this.partyValidation.siglaNotNull(party);
	}
	
	@Test
	public void dontThrowsExceptionIfAcronymIsNotNullTwo() throws PartyException {
		this.party.setPartyAcronym("PT");
		this.partyValidation.numberNotNull(party);
	}
	
	@Test
	public void dontThrowsExceptionIfNumberIsNotNull() throws PartyException {
		this.party.setPartyNumber(13);
		this.partyValidation.numberNotNull(party);
	}
	
	@Test
	public void testMethodEqualsOne() throws PartyException {
		party.equals(null);
	}

	@Test
	public void testMethodEqualsTwo() throws PartyException {
		party.equals(party);
	}
	
	@Test
	public void testMethodEqualsThree() throws PartyException {
		party.equals(candidate);
	}
	
}