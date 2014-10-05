package test.parse.index;

import model.beans.Campaign;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.CampaignParseIndex;

public class CampaignParseIndexTest {

	private String field[];
	private CampaignParseIndex campaignParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.campaignParseIndex = new CampaignParseIndex();
		this.field = new String[11];
		
		fieldsStart();
		indexStart();
	}

	@Test
	public void startCampaignWithValidIndices() throws Exception {
		
		Campaign campaign = new Campaign();
		this.campaignParseIndex.startInstance(campaign, field);
		Assert.assertEquals(this.field[0], campaign.getCampaignResult().getResultType().toString());
		Assert.assertEquals(this.field[1], campaign.getCampaignPosition().getPositionCode().toString());
		Assert.assertEquals(this.field[2], campaign.getCampaignParty().getPartyNumber().toString());
		Assert.assertEquals(this.field[3], campaign.getCampaignCandidate().getCandidateElectoralTitle());
		Assert.assertEquals(this.field[4], campaign.getCampaignYear().toString());
		Assert.assertEquals(this.field[5], campaign.getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.field[6], campaign.getCampaignNameOfUrn());
		Assert.assertEquals(this.field[7], campaign.getCampaignCountryState());
		Assert.assertEquals(this.field[8], campaign.getCampaignMaximumExpenseDeclared().toString());
	}
	
	@Test
	public void startCampaignWithInvalidIndices() throws Exception {
		
		this.campaignParseIndex = new CampaignParseIndex();
		Campaign campaign = new Campaign();
		this.campaignParseIndex.startInstance(campaign, field);
		Assert.assertNotEquals(this.field[0], campaign.getCampaignResult().getResultType().toString());
		Assert.assertNotEquals(this.field[1], campaign.getCampaignPosition().getPositionCode().toString());
		Assert.assertNotEquals(this.field[2], campaign.getCampaignParty().getPartyNumber().toString());
		Assert.assertNotEquals(this.field[3], campaign.getCampaignCandidate().getCandidateElectoralTitle());
		Assert.assertNotEquals(this.field[4], campaign.getCampaignYear().toString());
		Assert.assertNotEquals(this.field[5], campaign.getCampaignCandidateNumber().toString());
		Assert.assertNotEquals(this.field[6], campaign.getCampaignNameOfUrn());
		Assert.assertNotEquals(this.field[7], campaign.getCampaignCountryState());
		Assert.assertNotEquals(this.field[8], campaign.getCampaignMaximumExpenseDeclared().toString());
	}
	
	private void indexStart() {
		
		this.campaignParseIndex.setIndexCodeResult(0);
		this.campaignParseIndex.setIndexCodePosition(1);
		this.campaignParseIndex.setIndexNumberParty(2);
		this.campaignParseIndex.setIndexTitleCandidate(3);
		this.campaignParseIndex.setIndexYear(4);
		this.campaignParseIndex.setIndexNumberCandidate(5);
		this.campaignParseIndex.setIndexUrnName(6);
		this.campaignParseIndex.setIndexUnitFederation(7);
		this.campaignParseIndex.setIndexMaximumExpenseDeclared(8);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "1";
		this.field[1] = "6";
		this.field[2] = "13";
		this.field[3] = "55896321447";
		this.field[4] = "2010";
		this.field[5] = "13222";
		this.field[6] = "SOARES";
		this.field[7] = "DF";
		this.field[8] = "450000.0";
	}

}
