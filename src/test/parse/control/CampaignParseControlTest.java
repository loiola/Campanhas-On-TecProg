package test.parse.control;

import model.beans.Campaign;
import model.dao.CampaignDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlCampaign;
import parse.index.CampaignParseIndex;
import test.TemplateTest;

public class CampaignParseControlTest extends TemplateTest {

	public static final int RESULT = 0;
	public static final int POSITION = 1;
	public static final int PARTY = 2;
	public static final int CANDIDATE_TITLE = 3;
	public static final int YEAR = 4;
	public static final int CANDIDATE_NUMBER = 5;
	public static final int NAME_URN = 6;
	public static final int UF = 7;
	public static final int MAXIMUM_EXPENSE = 8;
	
	private String field[];
	private CampaignDAO campaignDAO;
	private CampaignParseIndex campaignParseIndex;
	private ParseControlCampaign parseControlCampaign;

	@Override
	public void beforeTest() throws Exception {
		
		this.field = new String[11];
		this.campaignDAO = new CampaignDAO();
		this.campaignParseIndex = new CampaignParseIndex();
		this.parseControlCampaign = new ParseControlCampaign(this.campaignParseIndex);
		
		fieldsStart();
		indexStart();
	}
	
	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void registeringCampaign() throws Exception {
		this.parseControlCampaign.registeringInstances();
		this.parseControlCampaign.addInstance(field);
		this.parseControlCampaign.registeringInstances();
		this.parseControlCampaign.clear();
		
		Campaign campaignRegistered = this.campaignDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.field[RESULT], campaignRegistered.getCampaignResult().getResultType().toString());
		Assert.assertEquals(this.field[POSITION], campaignRegistered.getCampaignPosition().getPositionCode().toString());
		Assert.assertEquals(this.field[PARTY], campaignRegistered.getCampaignParty().getPartyNumber().toString());
		Assert.assertEquals(this.field[CANDIDATE_TITLE], campaignRegistered.getCampaignCandidate().getCandidateElectoralTitle());
		Assert.assertEquals(this.field[YEAR], campaignRegistered.getCampaignYear().toString());
		Assert.assertEquals(this.field[CANDIDATE_NUMBER], campaignRegistered.getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.field[NAME_URN], campaignRegistered.getCampaignNameOfUrn());
		Assert.assertEquals(this.field[UF], campaignRegistered.getCampaignCountryState());
		Assert.assertEquals(this.field[MAXIMUM_EXPENSE], campaignRegistered.getCampaignMaximumExpenseDeclared().toString());
	}
	
	@Test
	public void notToSignTwoEqualCampaigns() throws Exception {
		
		this.parseControlCampaign.addInstance(field);
		this.parseControlCampaign.addInstance(field);
		this.parseControlCampaign.registeringInstances();
		this.parseControlCampaign.clear();
		
		int campaignNumberDatabase = this.campaignDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, campaignNumberDatabase);
	}
	
	@Test
	public void testAddedInstance() throws Exception {
		this.parseControlCampaign.addEqualInstance(field);
	}
	
	private void indexStart() {
		
		this.campaignParseIndex.setIndexCodeResult(RESULT);
		this.campaignParseIndex.setIndexCodePosition(POSITION);
		this.campaignParseIndex.setIndexNumberParty(PARTY);
		this.campaignParseIndex.setIndexTitleCandidate(CANDIDATE_TITLE);
		this.campaignParseIndex.setIndexYear(YEAR);
		this.campaignParseIndex.setIndexNumberCandidate(CANDIDATE_NUMBER);
		this.campaignParseIndex.setIndexUrnName(NAME_URN);
		this.campaignParseIndex.setIndexUnitFederation(UF);
		this.campaignParseIndex.setIndexMaximumExpenseDeclared(MAXIMUM_EXPENSE);
	}
	
	private void fieldsStart() {
		
		this.field[RESULT] = "1";
		this.field[POSITION] = "6";
		this.field[PARTY] = "13";
		this.field[CANDIDATE_TITLE] = "55896321447";
		this.field[YEAR] = "2010";
		this.field[CANDIDATE_NUMBER] = "13222";
		this.field[NAME_URN] = "SOARES";
		this.field[UF] = "DF";
		this.field[MAXIMUM_EXPENSE] = "450000.0";
	}

}
