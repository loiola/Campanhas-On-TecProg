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

	// Objects to be used during tests
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

	// Constants to columns in the database
	private final String DATABASE_CAMPAIGN_TABLE_NAME = "campanha";
	private final String DATABASE_CAMPAIGN_YEAR = "ano";
	private final String DATABASE_CAMPAIGN_CANDIDATE_NUMBER = "numero_candidato";
	private final String DATABASE_CAMPAIGN_POSITION_CODE = "cargo_cod_cargo";
	private final String DATABASE_CAMPAIGN_PARTY_NUMBER = "partido_numero";
	private final String DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE = "candidato_titulo_eleitoral";
	private final String DATABASE_CAMPAIGN_NAME_OF_URN = "nome_de_urna";
	private final String DATABASE_CAMPAIGN_COUNTRY_STATE = "uf";
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM "
			+ DATABASE_CAMPAIGN_TABLE_NAME;
	private final String DATABASE_CAMPAIGN_CANDIDATE_INDEX = "campanha_fk_3";
	private final String DATABASE_CAMPAIGN_PARTY_INDEX = "campanha_fk_4";
	private final String DATABASE_CAMPAIGN_YEAR_INDEX = "campanha_sk_1";

	// Constants to set informations
	private final String COUNTRY_STATE = "DF";
	private final String PARTY_ACRONYM = "PT";
	private final Integer PARTY_NUMBER = 13;
	private final Integer RESULT_TYPE_TWO = 2;
	private final Integer RESULT_TYPE_THREE = 3;
	private final Integer POSITION_CODE = 1;
	private final Integer POSITION_CODE_TWO = 4;
	private final String ELECTORAL_TITLE_ONE = "55325424149";
	private final String ELECTORAL_TITLE_TWO = "04725698130";
	private final Integer CAMPAIGN_IDENTIFIER_ONE = 1;
	private final Integer CAMPAIGN_IDENTIFIER_TWO = 2;
	private final Integer CAMPAIGN_YEAR = 2006;
	private final Integer NUMBER_CANDIDATE_ONE = 13555;
	private final Integer NUMBER_CANDIDATE_TWO = 13122;
	private final float VALUE_TRANSACTION_ONE = (float) 450000.0;
	private final float VALUE_TRANSACTION_TWO = (float) 500000.0;
	private final String NAME_OF_URN_ONE = "NOME DE URNA UM";
	private final String NAME_OF_URN_TWO = "NOME DE URNA DOIS";

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
		this.resultOne.setResultType(RESULT_TYPE_TWO);
		this.position.setPositionCode(POSITION_CODE);
		this.partyOne.setPartyNumber(PARTY_NUMBER);
		this.partyOne.setPartyAcronym(PARTY_ACRONYM);
		this.candidateOne.setCandidateElectoralTitle(ELECTORAL_TITLE_ONE);
		campaignOne.setCampaignIdentifier(CAMPAIGN_IDENTIFIER_ONE);
		campaignOne.setCampaignYear(CAMPAIGN_YEAR);
		campaignOne.setCampaignCandidateNumber(NUMBER_CANDIDATE_ONE);
		campaignOne.setCampaignResult(resultOne);
		campaignOne.setCampaignPosition(position);
		campaignOne.setCampaignParty(partyOne);
		campaignOne.setCampaignCandidate(candidateOne);
		campaignOne.setCampaignNameOfUrn(NAME_OF_URN_ONE);
		campaignOne.setCampaignCountryState(COUNTRY_STATE);
		campaignOne.setCampaignMaximumExpenseDeclared(VALUE_TRANSACTION_ONE);
		campaignOne.setCampaignTotalExpenseCalculated(VALUE_TRANSACTION_ONE);
		campaignOne.setCampaignTotalRevenueCalculated(VALUE_TRANSACTION_ONE);
		campaignList.add(campaignOne);
		candidateList.add(candidateOne);

		Campaign campaignTwo = new Campaign();
		this.resultTwo.setResultType(RESULT_TYPE_THREE);
		this.partyTwo.setPartyNumber(PARTY_NUMBER);
		this.partyTwo.setPartyAcronym(PARTY_ACRONYM);
		this.candidateTwo.setCandidateElectoralTitle(ELECTORAL_TITLE_TWO);
		campaignTwo.setCampaignIdentifier(CAMPAIGN_IDENTIFIER_TWO);
		campaignTwo.setCampaignYear(CAMPAIGN_YEAR);
		campaignTwo.setCampaignCandidateNumber(NUMBER_CANDIDATE_TWO);
		campaignTwo.setCampaignResult(resultTwo);
		campaignTwo.setCampaignPosition(position);
		campaignTwo.setCampaignParty(partyTwo);
		campaignTwo.setCampaignCandidate(candidateTwo);
		campaignTwo.setCampaignNameOfUrn(NAME_OF_URN_TWO);
		campaignTwo.setCampaignCountryState(COUNTRY_STATE);
		campaignTwo.setCampaignMaximumExpenseDeclared(VALUE_TRANSACTION_TWO);
		campaignTwo.setCampaignTotalExpenseCalculated(VALUE_TRANSACTION_TWO);
		campaignTwo.setCampaignTotalRevenueCalculated(VALUE_TRANSACTION_TWO);
		campaignList.add(campaignTwo);
		candidateList.add(candidateTwo);

		this.candidateDAO
				.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		this.campaignDAO
				.registerUnregisteredObjectArrayListOnDatabase(campaignList);
	}

	@Test
	public void shouldRetrieveSQLSelectNameOfUrnCommand() {
		String nameOfUrn = "LULA";
		String sqlCommand = "SELECT "
				+ DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE + " FROM "
				+ DATABASE_CAMPAIGN_TABLE_NAME + " WHERE "
				+ DATABASE_CAMPAIGN_NAME_OF_URN + " LIKE '%" + nameOfUrn + "%'";

		Assert.assertEquals(sqlCommand,
				this.campaignDAO.getSQLSelectNameOfUrnCommand(nameOfUrn));
	}

	@Test
	public void shouldRetrieveSQLConsultationForCandidate() {
		Candidate candidate = new Candidate();
		candidate.setCandidateElectoralTitle("123456");
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " USE INDEX ("
				+ DATABASE_CAMPAIGN_CANDIDATE_INDEX + ")" + " WHERE "
				+ DATABASE_CAMPAIGN_CANDIDATE_ELECTORAL_TITLE + " = '"
				+ candidate.getCandidateElectoralTitle() + "' ";

		Assert.assertEquals(sqlCommand,
				this.campaignDAO.mountingSQLConsultationForCandidate(candidate));
	}

	@Test
	public void shouldRetrieveCampaignArrayDataByCandidateElectoralTitle()
			throws SQLException {
		ArrayList<Campaign> campaignListRecovered = new ArrayList<>();

		Candidate candidate = new Candidate();
		candidate.setCandidateElectoralTitle(ELECTORAL_TITLE_ONE);

		registerCampaigns();

		campaignListRecovered = this.campaignDAO
				.getCampaignArrayDataByCandidateElectoralTitle(candidate);

		Assert.assertEquals(1, campaignListRecovered.size());
	}

	@Test
	public void shouldRetrieveSQLConsultationForPartyAcronymAndElectionYear()
			throws SQLException {
		String partyAcronym = "PT";
		String electionYear = "2002";

		Party party = new Party();
		party.setPartyAcronym(partyAcronym);

		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " USE INDEX ("
				+ DATABASE_CAMPAIGN_PARTY_INDEX + ", "
				+ DATABASE_CAMPAIGN_YEAR_INDEX + ")" + " WHERE "
				+ DATABASE_CAMPAIGN_PARTY_NUMBER + " = '"
				+ party.getPartyNumber() + "' AND " + DATABASE_CAMPAIGN_YEAR
				+ " = '" + electionYear + "' ";

		Assert.assertEquals(sqlCommand, this.campaignDAO
				.mountingSQLConsultationForPartyAcronymAndElectionYear(
						partyAcronym, electionYear));
	}

	@Test
	public void shouldRetrieveCampaignArrayDataByPartyAcronymAndElectionYear()
			throws SQLException {
		ArrayList<Campaign> campaignListRecovered = new ArrayList<>();

		Party party = new Party();
		party.setPartyAcronym("PT");
		String partyAcronym = party.getPartyAcronym();

		String electionYear = "2006";

		registerCampaigns();

		campaignListRecovered = this.campaignDAO
				.getCampaignArrayDataByPartyAcronymAndElectionYear(
						partyAcronym, electionYear);

		Assert.assertEquals(2, campaignListRecovered.size());
	}

	@Test
	public void shouldRetrieveSQLConsultationForElectionYearAndCandidateNumberAndPositionCodeAndCountryState()
			throws SQLException {
		Position position = new Position();
		position.setPositionCode(POSITION_CODE);

		Campaign campaign = new Campaign();
		campaign.setCampaignYear(CAMPAIGN_YEAR);
		campaign.setCampaignCountryState(COUNTRY_STATE);
		campaign.setCampaignCandidateNumber(NUMBER_CANDIDATE_ONE);
		campaign.setCampaignPosition(position);

		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				+ DATABASE_CAMPAIGN_YEAR + " = " + campaign.getCampaignYear()
				+ " AND " + DATABASE_CAMPAIGN_COUNTRY_STATE + " = '"
				+ campaign.getCampaignCountryState() + "' AND "
				+ DATABASE_CAMPAIGN_CANDIDATE_NUMBER + " = '"
				+ campaign.getCampaignCandidateNumber() + "' AND "
				+ DATABASE_CAMPAIGN_POSITION_CODE + " = "
				+ campaign.getCampaignPosition().getPositionCode();

		Assert.assertEquals(
				sqlCommand,
				this.campaignDAO
						.mountingSQLConsultationForElectionYearAndCandidateNumberAndPositionCodeAndCountryState(campaign));
	}

	@Test
	public void shouldRetrieveCampaignDataByElectionYearAndCandidateNumberAndPositionCodeAndCountryState()
			throws SQLException {
		Campaign campaign = new Campaign();
		Position position = new Position();

		position.setPositionCode(POSITION_CODE_TWO);
		campaign.setCampaignYear(CAMPAIGN_YEAR);
		campaign.setCampaignCandidateNumber(NUMBER_CANDIDATE_TWO);
		campaign.setCampaignPosition(position);
		campaign.setCampaignCountryState(COUNTRY_STATE);
		
		Assert.assertEquals(
				null,
				this.campaignDAO
						.getCampaignDataByElectionYearAndCandidateNumberAndPositionCodeAndCountryState(campaign));
	}

	@Test
	public void shouldCheckOutTheTopFiveIsAppointed() throws SQLException {

		Assert.assertEquals(campaignList,
				this.campaignDAO.getObjectArrayListFromDatabase());
	}

	@Test
	public void shouldRetrieveCandidatesOrderByMaximumExpenseDeclared()
			throws Exception {

		ArrayList<Campaign> listTopOne = this.campaignDAO
				.generateTopFiveCampaignListAboutMaximumExpenseDeclared(
						"presidente", 2006);
		Assert.assertTrue(listTopOne.get(0).getCampaignMaximumExpenseDeclared() >= listTopOne
				.get(1).getCampaignMaximumExpenseDeclared());
	}

}
