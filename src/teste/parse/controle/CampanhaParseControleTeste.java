package teste.parse.controle;

import model.beans.Campaign;
import model.dao.CampaignDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlCampaign;
import parse.index.CampanhaIndicesParse;
import teste.TemplateTeste;

public class CampanhaParseControleTeste extends TemplateTeste {

	public static final int RESULTADO = 0;
	public static final int CARGO = 1;
	public static final int PARTIDO = 2;
	public static final int CANDIDATO_TITULO = 3;
	public static final int ANO = 4;
	public static final int CANDIDATO_NUMERO = 5;
	public static final int NOME_URNA = 6;
	public static final int UF = 7;
	public static final int DESPESA_MAX = 8;
	
	private String campo[];
	private CampaignDAO campaignDAO;
	private CampanhaIndicesParse campanhaIndicesParse;
	private ParseControlCampaign parseControlCampaign;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[11];
		this.campaignDAO = new CampaignDAO();
		this.campanhaIndicesParse = new CampanhaIndicesParse();
		this.parseControlCampaign = new ParseControlCampaign(this.campanhaIndicesParse);
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void cadastrarCampanha() throws Exception {
		
		this.parseControlCampaign.addInstancia(campo);
		this.parseControlCampaign.registeringInstances();
		this.parseControlCampaign.resetar();
		
		Campaign campanhaCadastrada = this.campaignDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.campo[RESULTADO], campanhaCadastrada.getCampaignResult().getResultType().toString());
		Assert.assertEquals(this.campo[CARGO], campanhaCadastrada.getCampaignPosition().getPositionCode().toString());
		Assert.assertEquals(this.campo[PARTIDO], campanhaCadastrada.getCampaignParty().getPartyNumber().toString());
		Assert.assertEquals(this.campo[CANDIDATO_TITULO], campanhaCadastrada.getCampaignCandidate().getCandidateElectoralTitle());
		Assert.assertEquals(this.campo[ANO], campanhaCadastrada.getCampaignYear().toString());
		Assert.assertEquals(this.campo[CANDIDATO_NUMERO], campanhaCadastrada.getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.campo[NOME_URNA], campanhaCadastrada.getCampaignNameOfUrn());
		Assert.assertEquals(this.campo[UF], campanhaCadastrada.getCampaignCountryState());
		Assert.assertEquals(this.campo[DESPESA_MAX], campanhaCadastrada.getCampaignMaximumExpenseDeclared().toString());
	}
	
	@Test
	public void naoDeveCadastrarDuasCampanhasIguais() throws Exception {
		
		this.parseControlCampaign.addInstancia(campo);
		this.parseControlCampaign.addInstancia(campo);
		this.parseControlCampaign.registeringInstances();
		this.parseControlCampaign.resetar();
		
		int numeroCampanhasCadastradas = this.campaignDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, numeroCampanhasCadastradas);
	}
	
	private void iniciarIndices() {
		
		this.campanhaIndicesParse.setIndiceResultadoCod(RESULTADO);
		this.campanhaIndicesParse.setIndiceCargoCod(CARGO);
		this.campanhaIndicesParse.setIndicePartidoNumero(PARTIDO);
		this.campanhaIndicesParse.setIndiceCandidatoTitulo(CANDIDATO_TITULO);
		this.campanhaIndicesParse.setIndiceAno(ANO);
		this.campanhaIndicesParse.setIndiceNumeroCandidato(CANDIDATO_NUMERO);
		this.campanhaIndicesParse.setIndiceNomeDeUrna(NOME_URNA);
		this.campanhaIndicesParse.setIndiceUf(UF);
		this.campanhaIndicesParse.setIndiceDespesaMaxDeclarada(DESPESA_MAX);
	}
	
	private void iniciarCampos() {
		
		this.campo[RESULTADO] = "1";
		this.campo[CARGO] = "6";
		this.campo[PARTIDO] = "13";
		this.campo[CANDIDATO_TITULO] = "55896321447";
		this.campo[ANO] = "2010";
		this.campo[CANDIDATO_NUMERO] = "13222";
		this.campo[NOME_URNA] = "SOARES";
		this.campo[UF] = "DF";
		this.campo[DESPESA_MAX] = "450000.0";
	}

}
