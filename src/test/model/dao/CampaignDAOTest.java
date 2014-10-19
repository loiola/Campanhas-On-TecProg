package test.model.dao;

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

import test.TemplateTest;

public class CampaignDAOTest extends TemplateTest {
	
	private CampaignDAO campaignDAO;
	private CandidateDAO candidateDAO;
	private Result resultOne;
	private Position position;
	private Party partyOne;
	private Candidate candidateOne;
	private Result resultTwo;
	private Party partyTwo;
	private Candidate candidateTwo;
	private ArrayList<Campaign> campaignList;

	@Override
	public void beforeTest() throws Exception {
		
		this.campaignDAO = new CampaignDAO();
		this.candidateDAO = new CandidateDAO();
		this.resultOne = new Result();
		this.position = new Position();
		this.partyOne = new Party();
		this.candidateOne = new Candidate();
		this.resultTwo = new Result();
		this.partyTwo = new Party();
		this.candidateTwo = new Candidate();
		this.campaignList = new ArrayList<>();

		registerInDatabase();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	private void registerInDatabase() throws SQLException {

		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		Campaign campaignOne = new Campaign();
		this.resultOne.setResultType(2);
		this.position.setPositionCode(1);
		this.partyOne.setPartyNumber(45);
		this.candidateOne.setCandidateElectoralTitle("55325424149");
		campaignOne.setCampaignIdentifier(1);
		campaignOne.setCampaignYear(2006);
		campaignOne.setCampaignCandidateNumber(45555);
		campaignOne.setCampaignResult(resultOne);
		campaignOne.setCampaignPosition(position);
		campaignOne.setCampaignParty(partyOne);
		campaignOne.setCampaignCandidate(candidateOne);
		campaignOne.setCampaignNameOfUrn("NAME DE URNA UM");
		campaignOne.setCampaignCountryState("DF");
		campaignOne.setCampaignMaximumExpenseDeclared((float) 450000.);
		campaignOne.setCampaignTotalExpenseCalculated((float) 450000.0);
		campaignOne.setCampaignTotalRevenueCalculated((float) 450000.0);
		campaignList.add(campaignOne);
		candidateList.add(candidateOne);
		
		Campaign campaignTwo = new Campaign();
		this.resultTwo.setResultType(3);
		this.partyTwo.setPartyNumber(13);
		this.candidateTwo.setCandidateElectoralTitle("04725698130");
		campaignTwo.setCampaignIdentifier(2);
		campaignTwo.setCampaignYear(2006);
		campaignTwo.setCampaignCandidateNumber(13122);
		campaignTwo.setCampaignResult(resultTwo);
		campaignTwo.setCampaignPosition(position);
		campaignTwo.setCampaignParty(partyTwo);
		campaignTwo.setCampaignCandidate(candidateTwo);
		campaignTwo.setCampaignNameOfUrn("NAME DE URNA DOIS");
		campaignTwo.setCampaignCountryState("DF");
		campaignTwo.setCampaignMaximumExpenseDeclared((float) 500000.);
		campaignTwo.setCampaignTotalExpenseCalculated((float) 500000.0);
		campaignTwo.setCampaignTotalRevenueCalculated((float) 500000.0);
		campaignList.add(campaignTwo);
		candidateList.add(candidateTwo);
		
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);				
		this.campaignDAO.registerUnregisteredObjectArrayListOnDatabase(campaignList);
	}
	
	@Test
	public void shouldCheckOutTheTopFiveIsAppointed() throws SQLException {	
		
		Assert.assertEquals(campaignList, this.campaignDAO.getObjectArrayListFromDatabase());
	}
	
	@Test
	public void shouldRetrieveCandidatesOrderByMaximumExpenseDeclared() throws Exception {
		
		ArrayList<Campaign> listTopOne = this.campaignDAO.generateTopFiveCampaignListAboutMaximumExpenseDeclared("presidente",2006);
		Assert.assertTrue(listTopOne.get(0).getCampaignMaximumExpenseDeclared() >= listTopOne.get(1).getCampaignMaximumExpenseDeclared());
	}

}
