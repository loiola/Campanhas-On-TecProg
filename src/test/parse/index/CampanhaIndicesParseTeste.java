package test.parse.index;

import model.beans.Campaign;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.CampaignParseIndex;

public class CampanhaIndicesParseTeste {

	private String campo[];
	private CampaignParseIndex campaignParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.campaignParseIndex = new CampaignParseIndex();
		this.campo = new String[11];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmaCampanhaComIndicesValidos() throws Exception {
		
		Campaign campaign = new Campaign();
		this.campaignParseIndex.startInstance(campaign, campo);
		Assert.assertEquals(this.campo[0], campaign.getCampaignResult().getResultType().toString());
		Assert.assertEquals(this.campo[1], campaign.getCampaignPosition().getPositionCode().toString());
		Assert.assertEquals(this.campo[2], campaign.getCampaignParty().getPartyNumber().toString());
		Assert.assertEquals(this.campo[3], campaign.getCampaignCandidate().getCandidateElectoralTitle());
		Assert.assertEquals(this.campo[4], campaign.getCampaignYear().toString());
		Assert.assertEquals(this.campo[5], campaign.getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.campo[6], campaign.getCampaignNameOfUrn());
		Assert.assertEquals(this.campo[7], campaign.getCampaignCountryState());
		Assert.assertEquals(this.campo[8], campaign.getCampaignMaximumExpenseDeclared().toString());
	}
	
	@Test
	public void iniciarUmaCampanhaComIndicesInvalidos() throws Exception {
		
		this.campaignParseIndex = new CampaignParseIndex();
		Campaign campaign = new Campaign();
		this.campaignParseIndex.startInstance(campaign, campo);
		Assert.assertNotEquals(this.campo[0], campaign.getCampaignResult().getResultType().toString());
		Assert.assertNotEquals(this.campo[1], campaign.getCampaignPosition().getPositionCode().toString());
		Assert.assertNotEquals(this.campo[2], campaign.getCampaignParty().getPartyNumber().toString());
		Assert.assertNotEquals(this.campo[3], campaign.getCampaignCandidate().getCandidateElectoralTitle());
		Assert.assertNotEquals(this.campo[4], campaign.getCampaignYear().toString());
		Assert.assertNotEquals(this.campo[5], campaign.getCampaignCandidateNumber().toString());
		Assert.assertNotEquals(this.campo[6], campaign.getCampaignNameOfUrn());
		Assert.assertNotEquals(this.campo[7], campaign.getCampaignCountryState());
		Assert.assertNotEquals(this.campo[8], campaign.getCampaignMaximumExpenseDeclared().toString());
	}
	
	private void iniciarIndices() {
		
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
	
	private void iniciarCampos() {
		
		this.campo[0] = "1";
		this.campo[1] = "6";
		this.campo[2] = "13";
		this.campo[3] = "55896321447";
		this.campo[4] = "2010";
		this.campo[5] = "13222";
		this.campo[6] = "SOARES";
		this.campo[7] = "DF";
		this.campo[8] = "450000.0";
	}

}
