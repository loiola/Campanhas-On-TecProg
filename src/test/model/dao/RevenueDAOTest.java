package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;

import model.beans.Campaign;
import model.beans.Donor;
import model.beans.Position;
import model.beans.Revenue;
import model.dao.RevenueDAO;

import org.junit.Test;

import test.TemplateTest;

public class RevenueDAOTest extends TemplateTest {
	
	private RevenueDAO revenueDAO;
	private Revenue revenueOne;
	private Revenue revenueTwo;
	private Campaign campaignOne;
	private Donor donorOne;
	private Position positionOne;
	private Campaign campaignTwo;
	private Donor donorTwo;
	private Position positionTwo;
	
	private static final String DATABASE_REVENUE_TABLE_NAME = "receita";
	private final String DATABASE_REVENUE_IDENTIFIER = "id_receita";
	private final String DATABASE_REVENUE_CAMPAIGN_YEAR = "campanha_ano";
	private final String DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER = "campanha_numero_candidato";
	private final String DATABASE_REVENUE_CAMPAIGN_POSITION = "cargo";
	private final String DATABASE_REVENUE_CAMPAIGN_COUNTRY_STATE = "campanha_uf";
	
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_REVENUE_TABLE_NAME;

	@Override
	public void beforeTest() throws Exception {
		
		this.revenueDAO = new RevenueDAO();
		this.campaignOne = new Campaign();
		this.donorOne = new Donor();
		this.positionOne = new Position();
		this.revenueOne = new Revenue();
		this.revenueTwo = new Revenue();
		this.campaignTwo = new Campaign();
		this.donorTwo = new Donor();
		this.positionTwo = new Position();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	private void shouldRegisterAnArrayOfRevenues() throws SQLException {
		
		ArrayList<Revenue> revenueList = new ArrayList<>();
		
		this.positionOne.setPositionDescription("POSITION UM");
		this.campaignOne.setCampaignIdentifier(1);
		this.campaignOne.setCampaignYear(2006);
		this.campaignOne.setCampaignCandidateNumber(45555);
		this.campaignOne.setCampaignPosition(positionOne);
		this.donorOne.setDonorName("DOADOR UM");
		this.donorOne.setDonorPersonRegister("55325424149");
		revenueOne.setFinancialTransactionCampaign(campaignOne);
		revenueOne.setFinancialTransactionPrice((float) 450000.0);
		revenueOne.setFinancialTransactionPaymentType("FORMA PAGAMENTO UM");
		revenueOne.setFinancialTransactionIdentifier(12);
		revenueOne.setFinancialTransactionDescription("DESCRIPTION UM");
		revenueOne.setFinancialTransactionDate("12/10/2006");
		revenueOne.setFinancialTransactionType("TIPO MOVIMENTACAO UM");
		revenueOne.setRevenueElectoralReceipt("RECEIPT ELEITORAL UM");
		revenueOne.setFinancialTransactionDocumentNumber("NUMBER DOCUMENTO UM");
		revenueOne.setRevenueDonor(donorOne);
		revenueList.add(revenueOne);
		
		this.positionTwo.setPositionDescription("POSITION DOIS");
		this.campaignTwo.setCampaignIdentifier(2);
		this.campaignTwo.setCampaignYear(2006);
		this.campaignTwo.setCampaignCandidateNumber(131222);
		this.campaignTwo.setCampaignPosition(positionTwo);
		this.donorTwo.setDonorName("DOADOR DOIS");
		this.donorTwo.setDonorPersonRegister("55325424149");
		revenueTwo.setFinancialTransactionCampaign(campaignTwo);
		revenueTwo.setFinancialTransactionPrice((float) 500000.0);
		revenueTwo.setFinancialTransactionPaymentType("FORMA PAGAMENTO DOIS");
		revenueOne.setFinancialTransactionIdentifier(11);
		revenueTwo.setFinancialTransactionDescription("DESCRIPTION DOIS");
		revenueTwo.setFinancialTransactionDate("12/10/2006");
		revenueTwo.setFinancialTransactionType("TIPO MOVIMENTACAO DOIS");
		revenueTwo.setRevenueElectoralReceipt("RECEIPT ELEITORAL DOIS");
		revenueTwo.setFinancialTransactionDocumentNumber("NUMBER DOCUMENTO DOIS");
		revenueTwo.setRevenueDonor(donorTwo);
		revenueList.add(revenueTwo);
		
		this.revenueDAO.registerUnregisteredObjectArrayListOnDatabase(revenueList);
		this.revenueDAO.getObjectArrayListFromDatabase();
	}
	
	@Test
	public void shouldRetrieveSQLConsultationForAttributes() throws SQLException {
		
		Position position = new Position();
		position.setPositionDescription("PRESIDENTE");
		
		Campaign campaign = new Campaign();
		campaign.setCampaignYear(2002);
		campaign.setCampaignCandidateNumber(13222);
		campaign.setCampaignCountryState("DF");
		
		
		String sqlCommand;
		
		sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				  + DATABASE_REVENUE_CAMPAIGN_YEAR + " = " + campaign.getCampaignYear()
				  + " AND " + DATABASE_REVENUE_CAMPAIGN_CANDIDATE_NUMBER + " = "
				  + campaign.getCampaignCandidateNumber() + " AND "
				  + DATABASE_REVENUE_CAMPAIGN_COUNTRY_STATE + " = '"
				  + campaign.getCampaignCountryState() + "' AND "
				  + DATABASE_REVENUE_CAMPAIGN_POSITION + " LIKE '%"
				  + campaign.getCampaignPosition().getPositionDescription() + "%'";
		
		Assert.assertEquals(sqlCommand, this.revenueDAO.mountingSQLConsultationForAttributes(campaign));
	}
	
	@Test
	public void shouldRetrieveRevenueByCampaignPositionAndCampaignCountryStateAndCampaignYear() throws Exception {
		
		shouldRegisterAnArrayOfRevenues();
		
		int revenuesArraySize = 0;
		revenuesArraySize = this.revenueDAO.getRevenueByCampaignPositionAndCampaignCountryStateAndCampaignYear(campaignOne).size();
		Assert.assertEquals(1, revenuesArraySize);
	}
	
	@Test
	public void shouldRetrieveSQLConsultationForIdentifier() throws SQLException {
		
		Revenue revenue = new Revenue();
		revenue.setFinancialTransactionIdentifier(12);
		
		Integer revenueIdentifier = revenue.getFinancialTransactionIdentifier();
		
		String sqlCommand;
		
		sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				  + DATABASE_REVENUE_IDENTIFIER + " = " + revenueIdentifier;
		
		Assert.assertEquals(sqlCommand, this.revenueDAO.mountingSQLConsultationForIdentifier(revenueIdentifier));
	}
	
}
