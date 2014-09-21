package teste.parse.indices;

import model.beans.Campaign;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.indices.CampanhaIndicesParse;

public class CampanhaIndicesParseTeste {

	private String campo[];
	private CampanhaIndicesParse campanhaIndicesParse;

	@Before
	public void setUp() throws Exception {
		
		this.campanhaIndicesParse = new CampanhaIndicesParse();
		this.campo = new String[11];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmaCampanhaComIndicesValidos() throws Exception {
		
		Campaign campaign = new Campaign();
		this.campanhaIndicesParse.iniciarInstancia(campaign, campo);
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
		
		this.campanhaIndicesParse = new CampanhaIndicesParse();
		Campaign campaign = new Campaign();
		this.campanhaIndicesParse.iniciarInstancia(campaign, campo);
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
		
		this.campanhaIndicesParse.setIndiceResultadoCod(0);
		this.campanhaIndicesParse.setIndiceCargoCod(1);
		this.campanhaIndicesParse.setIndicePartidoNumero(2);
		this.campanhaIndicesParse.setIndiceCandidatoTitulo(3);
		this.campanhaIndicesParse.setIndiceAno(4);
		this.campanhaIndicesParse.setIndiceNumeroCandidato(5);
		this.campanhaIndicesParse.setIndiceNomeDeUrna(6);
		this.campanhaIndicesParse.setIndiceUf(7);
		this.campanhaIndicesParse.setIndiceDespesaMaxDeclarada(8);
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
