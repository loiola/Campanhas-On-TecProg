package teste;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Party;
import model.beans.Position;
import model.beans.Result;
import model.dao.CampaignDAO;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import controle.CampanhaControle;

public class CampanhaControleTeste extends TemplateTeste {
	
	private CampaignDAO campaignDAO;
	private CandidateDAO candidateDAO;
	private CampanhaControle campanhaControle;
	private Candidate candidate;
	private Party partido1;
	private Campaign campanha1;
	private Result resultado1;
	private Position cargo1;
	private Party partido2;
	private Campaign campanha2;
	private Result resultado2;
	private Position cargo2;

	@Override
	public void beforeTest() throws Exception {
		
		this.campaignDAO = new CampaignDAO();
		this.candidateDAO = new CandidateDAO();
		this.campanhaControle = new CampanhaControle();
		this.campanha1 = new Campaign();
		this.partido1 = new Party();
		this.candidate = new Candidate();
		this.resultado1 = new Result();
		this.cargo1 = new Position();
		this.resultado2 = new Result();
		this.cargo2 = new Position();
		this.campanha2 = new Campaign();
		this.partido2 = new Party();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmaListaDeCampanhasDeUmDeterminadoCandidato() throws SQLException {
		
		ArrayList<Campaign> listaCampanhas = new ArrayList<>();
		ArrayList<Candidate> listaCandidato = new ArrayList<>();

		
		this.candidate.setCandidateName("CANDIDATO");
		this.candidate.setCandidateElectoralTitle("5532424149");
		listaCandidato.add(candidate);
		
		this.resultado1.setResultType(1);
		this.resultado1.setResultDescription("NAO ELEITO");
		this.cargo1.setPositionCode(2);
		this.cargo1.setPositionDescription("DEPUTADO DISTRITAL");
		this.partido1.setPartyAcronym("SGL");
		this.partido1.setPartyNumber(45);
		this.campanha1.setCampaignIdentifier(1);
		this.campanha1.setCampaignYear(2002);
		this.campanha1.setCampaignCandidateNumber(45555);
		this.campanha1.setCampaignResult(resultado1);
		this.campanha1.setCampaignPosition(cargo1);
		this.campanha1.setCampaignParty(partido1);
		this.campanha1.setCampaignCandidate(candidate);
		this.campanha1.setCampaignNameOfUrn("CAND");
		this.campanha1.setCampaignCountryState("DF");
		this.campanha1.setCampaignMaximumExpenseDeclared((float) 450000.0);
		this.campanha1.setCampaignTotalExpenseCalculated((float) 450000.0);
		this.campanha1.setCampaignTotalRevenueCalculated((float) 450000.0);
		listaCampanhas.add(campanha1);
		
		this.resultado2.setResultType(2);
		this.resultado2.setResultDescription("ELEITO");
		this.cargo2.setPositionCode(1);
		this.cargo2.setPositionDescription("DEPUTADO FEDERAL");
		this.partido2.setPartyAcronym("SGLL");
		this.partido2.setPartyNumber(13);
		this.campanha2.setCampaignIdentifier(2);
		this.campanha2.setCampaignYear(2006);
		this.campanha2.setCampaignCandidateNumber(1313);
		this.campanha2.setCampaignResult(resultado2);
		this.campanha2.setCampaignPosition(cargo2);
		this.campanha2.setCampaignParty(partido2);
		this.campanha2.setCampaignCandidate(candidate);
		this.campanha2.setCampaignNameOfUrn("CAND");
		this.campanha2.setCampaignCountryState("DF");
		this.campanha2.setCampaignMaximumExpenseDeclared((float) 450000.0);
		this.campanha2.setCampaignTotalExpenseCalculated((float) 450000.0);
		this.campanha2.setCampaignTotalRevenueCalculated((float) 450000.0);
		listaCampanhas.add(campanha2);
		
		this.campaignDAO.registerUnregisteredObjectArrayListOnDatabase(listaCampanhas);
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidato);
		
		Assert.assertEquals(this.campaignDAO.getCampanhasPeloTituloEleitoral(candidate), this.campanhaControle.getListaCampanhas(candidate));
		Assert.assertEquals(this.campaignDAO.getCampanhasPorSiglaEAno("SGLL", "2006"), this.campanhaControle.getListaCampanhasPorSiglaPartidoEAno("SGLL", "2006"));
		Assert.assertEquals(this.campaignDAO.getPeloAnoNumeroCodCargoEUf(campanha1), this.campanhaControle.getPeloAnoNumeroCodCargoEUf(campanha1));
		Assert.assertNotEquals(this.campaignDAO.getPeloAnoNumeroCodCargoEUf(campanha1), this.campanhaControle.getPeloAnoNumeroCodCargoEUf(campanha2));
	}

}
