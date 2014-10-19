package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

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
	
	@Test
	public void shouldRetrieveAnArrayOfRevenues() throws SQLException {
		
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

}
