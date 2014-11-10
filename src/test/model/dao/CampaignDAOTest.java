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
	
	private static final String DATABASE_CAMPAIGN_TABLE_NAME = "campanha";
	private final String DATABASE_CAMPAIGN_YEAR = "ano";
	private final String DATABASE_CAMPAIGN_CANDIDATE_NUMBER = "numero_candidato";
	private final String DATABASE_CAMPAIGN_POSITION_CODE = "cargo_cod_cargo";
	private final String DATABASE_CAMPAIGN_PARTY_NUMBER = "partido_numero";
	private final String DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE = "candidato_titulo_eleitoral";
	private final String DATABASE_CAMPAIGN_NAME_OF_URN = "nome_de_urna";
	private final String DATABASE_CAMPAIGN_COUNTRY_STATE = "uf";
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_CAMPAIGN_TABLE_NAME;
	private final String DATABASE_CAMPAIGN_CANDIDATE_INDEX = "campanha_fk_3";
	private final String DATABASE_CAMPAIGN_PARTY_INDEX = "campanha_fk_4";
	private final String DATABASE_CAMPAIGN_YEAR_INDEX = "campanha_sk_1";

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

		registerCampaigns();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	private void registerCampaigns() throws SQLException {

		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		Campaign campaignOne = new Campaign();
		this.resultOne.setResultType(2);
		this.position.setPositionCode(1);
		this.partyOne.setPartyNumber(13);
		this.partyOne.setPartyAcronym("PT");
		this.candidateOne.setCandidateElectoralTitle("55325424149");
		campaignOne.setCampaignIdentifier(1);
		campaignOne.setCampaignYear(2006);
		campaignOne.setCampaignCandidateNumber(13555);
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
		this.partyTwo.setPartyAcronym("PT");
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
	public void shouldRetrieveSQLSelectNameOfUrnCommand() {
		String nameOfUrn = "LULA";
		String sqlCommand = "SELECT " + DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE + " FROM " + DATABASE_CAMPAIGN_TABLE_NAME
				+ " WHERE " + DATABASE_CAMPAIGN_NAME_OF_URN + " LIKE '%" + nameOfUrn + "%'";
		
		Assert.assertEquals(sqlCommand, this.campaignDAO.getSQLSelectNameOfUrnCommand(nameOfUrn));
	}
	
	@Test
	public void shouldRetrieveSQLConsultationForCandidate() {
		Candidate candidate = new Candidate();
		candidate.setCandidateElectoralTitle("123456");
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " USE INDEX ("
				+ DATABASE_CAMPAIGN_CANDIDATE_INDEX + ")" + " WHERE "
				+ DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE + " = '"
				+ candidate.getCandidateElectoralTitle() + "' ";
		
		Assert.assertEquals(sqlCommand, this.campaignDAO.mountingSQLConsultationForCandidate(candidate));
	}
	
	@Test
	public void shouldRetrieveCampaignArrayDataByCandidateElectoralTitle() throws SQLException {
		ArrayList<Campaign> campaignListRecovered = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateElectoralTitle("55325424149");
		
		registerCampaigns();
		
		campaignListRecovered = this.campaignDAO.getCampaignArrayDataByCandidateElectoralTitle(candidate);
		
		Assert.assertEquals(1, campaignListRecovered.size());
	}
	
	@Test
	public void shouldRetrieveSQLConsultationForPartyAcronymAndElectionYear() throws SQLException {
		String partyAcronym = "PT";
		String electionYear = "2002";
		
		Party party = new Party();
		party.setPartyAcronym(partyAcronym);
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " USE INDEX ("
				+ DATABASE_CAMPAIGN_PARTY_INDEX + ", "	+ DATABASE_CAMPAIGN_YEAR_INDEX
				+ ")" + " WHERE " + DATABASE_CAMPAIGN_PARTY_NUMBER + " = '"
				+ party.getPartyNumber() + "' AND " + DATABASE_CAMPAIGN_YEAR + " = '"
				+ electionYear + "' ";
		
		Assert.assertEquals(sqlCommand, this.campaignDAO.mountingSQLConsultationForPartyAcronymAndElectionYear(partyAcronym, electionYear));
	}
	
	@Test
	public void shouldRetrieveCampaignArrayDataByPartyAcronymAndElectionYear() throws SQLException {
		ArrayList<Campaign> campaignListRecovered = new ArrayList<>();
		
		Party party = new Party();
		party.setPartyAcronym("PT");
		String partyAcronym = party.getPartyAcronym();
		
		String electionYear = "2006";
		
		registerCampaigns();
		
		campaignListRecovered = this.campaignDAO.getCampaignArrayDataByPartyAcronymAndElectionYear(partyAcronym, electionYear);
		
		Assert.assertEquals(2, campaignListRecovered.size());
	}
	
	@Test
	public void shouldRetrieveSQLConsultationForElectionYearAndCandidateNumberAndPositionCodeAndCountryState() throws SQLException {
		Position position = new Position();
		position.setPositionCode(4);
		
		Campaign campaign = new Campaign();
		campaign.setCampaignYear(2002);
		campaign.setCampaignCountryState("DF");
		campaign.setCampaignCandidateNumber(13222);
		campaign.setCampaignPosition(position);
		
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE " + DATABASE_CAMPAIGN_YEAR + " = "
				+ campaign.getCampaignYear() + " AND " + DATABASE_CAMPAIGN_COUNTRY_STATE + " = '" + campaign.getCampaignCountryState()
				+ "' AND " + DATABASE_CAMPAIGN_CANDIDATE_NUMBER + " = '"
				+ campaign.getCampaignCandidateNumber() + "' AND " + DATABASE_CAMPAIGN_POSITION_CODE + " = "
				+ campaign.getCampaignPosition().getPositionCode();
		
		Assert.assertEquals(sqlCommand, this.campaignDAO.mountingSQLConsultationForElectionYearAndCandidateNumberAndPositionCodeAndCountryState(campaign));
	}
	
	@Test
	public void shouldRetrieveCampaignDataByElectionYearAndCandidateNumberAndPositionCodeAndCountryState() throws SQLException {
		Campaign campaign = new Campaign();
		Position position = new Position();
		
		position.setPositionCode(4);
		campaign.setCampaignYear(2006);
		campaign.setCampaignCandidateNumber(13122);
		campaign.setCampaignPosition(position);
		campaign.setCampaignCountryState("DF");
		
		Assert.assertEquals(null, this.campaignDAO.getCampaignDataByElectionYearAndCandidateNumberAndPositionCodeAndCountryState(campaign));
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
