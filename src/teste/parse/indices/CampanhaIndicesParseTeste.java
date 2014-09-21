package teste.parse.indices;

import modelo.beans.Campaign;

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
		Assert.assertEquals(this.campo[0], campaign.getResultado().getCodigo().toString());
		Assert.assertEquals(this.campo[1], campaign.getCargo().getCodigo().toString());
		Assert.assertEquals(this.campo[2], campaign.getPartido().getNumero().toString());
		Assert.assertEquals(this.campo[3], campaign.getCandidato().getTituloEleitoral());
		Assert.assertEquals(this.campo[4], campaign.getAno().toString());
		Assert.assertEquals(this.campo[5], campaign.getNumeroCandidato().toString());
		Assert.assertEquals(this.campo[6], campaign.getNomeDeUrna());
		Assert.assertEquals(this.campo[7], campaign.getUf());
		Assert.assertEquals(this.campo[8], campaign.getDespesaMaxDeclarada().toString());
	}
	
	@Test
	public void iniciarUmaCampanhaComIndicesInvalidos() throws Exception {
		
		this.campanhaIndicesParse = new CampanhaIndicesParse();
		Campaign campaign = new Campaign();
		this.campanhaIndicesParse.iniciarInstancia(campaign, campo);
		Assert.assertNotEquals(this.campo[0], campaign.getResultado().getCodigo().toString());
		Assert.assertNotEquals(this.campo[1], campaign.getCargo().getCodigo().toString());
		Assert.assertNotEquals(this.campo[2], campaign.getPartido().getNumero().toString());
		Assert.assertNotEquals(this.campo[3], campaign.getCandidato().getTituloEleitoral());
		Assert.assertNotEquals(this.campo[4], campaign.getAno().toString());
		Assert.assertNotEquals(this.campo[5], campaign.getNumeroCandidato().toString());
		Assert.assertNotEquals(this.campo[6], campaign.getNomeDeUrna());
		Assert.assertNotEquals(this.campo[7], campaign.getUf());
		Assert.assertNotEquals(this.campo[8], campaign.getDespesaMaxDeclarada().toString());
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
