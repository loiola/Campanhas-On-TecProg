package teste;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Party;
import modelo.beans.Result;
import modelo.dao.CampanhaDAO;
import modelo.dao.CandidatoDAO;

import org.junit.Assert;
import org.junit.Test;

import controle.CampanhaControle;

public class CampanhaControleTeste extends TemplateTeste {
	
	private CampanhaDAO campanhaDAO;
	private CandidatoDAO candidatoDAO;
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
		
		this.campanhaDAO = new CampanhaDAO();
		this.candidatoDAO = new CandidatoDAO();
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

		
		this.candidate.setNome("CANDIDATO");
		this.candidate.setTituloEleitoral("5532424149");
		listaCandidato.add(candidate);
		
		this.resultado1.setCodigo(1);
		this.resultado1.setDescricao("NAO ELEITO");
		this.cargo1.setCodigo(2);
		this.cargo1.setDescricao("DEPUTADO DISTRITAL");
		this.partido1.setSigla("SGL");
		this.partido1.setNumero(45);
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
		
		this.resultado2.setCodigo(2);
		this.resultado2.setDescricao("ELEITO");
		this.cargo2.setCodigo(1);
		this.cargo2.setDescricao("DEPUTADO FEDERAL");
		this.partido2.setSigla("SGLL");
		this.partido2.setNumero(13);
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
		
		this.campanhaDAO.cadastrarLista(listaCampanhas);
		this.candidatoDAO.cadastrarLista(listaCandidato);
		
		Assert.assertEquals(this.campanhaDAO.getCampanhasPeloTituloEleitoral(candidate), this.campanhaControle.getListaCampanhas(candidate));
		Assert.assertEquals(this.campanhaDAO.getCampanhasPorSiglaEAno("SGLL", "2006"), this.campanhaControle.getListaCampanhasPorSiglaPartidoEAno("SGLL", "2006"));
		Assert.assertEquals(this.campanhaDAO.getPeloAnoNumeroCodCargoEUf(campanha1), this.campanhaControle.getPeloAnoNumeroCodCargoEUf(campanha1));
		Assert.assertNotEquals(this.campanhaDAO.getPeloAnoNumeroCodCargoEUf(campanha1), this.campanhaControle.getPeloAnoNumeroCodCargoEUf(campanha2));
	}

}
