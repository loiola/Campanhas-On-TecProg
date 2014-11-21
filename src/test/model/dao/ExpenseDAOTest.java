package test.model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import model.beans.Campaign;
import model.beans.Expense;
import model.beans.Position;
import model.beans.Supplier;
import model.dao.ExpenseDAO;
import test.TemplateTest;

public class ExpenseDAOTest extends TemplateTest {
	
	private ExpenseDAO expenseDAO;
	private Expense expenseOne;
	private Expense expenseTwo;
	private Campaign campaignOne;
	private Supplier supplierOne;
	private Position positionOne;
	private Campaign campaignTwo;
	private Supplier supplierTwo;
	private Position positionTwo;
	
	private static final String DATABASE_EXPENSE_TABLE_NAME = "despesa";
	private final String DATABASE_EXPENSE_CAMPAIGN_YEAR = "campanha_ano";
	private final String DATABASE_EXPENSE_CAMPAIGN_CANDIDATE_NUMBER = "campanha_numero_candidato";
	private final String DATABASE_EXPENSE_CAMPAIGN_POSITION = "cargo";
	private final String DATABASE_EXPENSE_CAMPAIGN_COUNTRY_STATE = "campanha_uf";
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_EXPENSE_TABLE_NAME;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.expenseDAO = new ExpenseDAO();
		this.expenseOne = new Expense();
		this.expenseTwo = new Expense();
		this.campaignOne = new Campaign();
		this.campaignTwo = new Campaign();
		this.supplierOne = new Supplier();
		this.supplierTwo = new Supplier();
		this.positionOne = new Position();
		this.positionTwo = new Position();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	private void shouldRegisterAnArrayOfExpenses() throws Exception {
		
		ArrayList<Expense> expenseList = new ArrayList<>();
		
		this.positionOne.setPositionDescription("POSITION UM");
		this.campaignOne.setCampaignIdentifier(1);
		this.campaignOne.setCampaignYear(2006);
		this.campaignOne.setCampaignCandidateNumber(45555);
		this.campaignOne.setCampaignPosition(positionOne);
		this.supplierOne.setSupplierName("DOADOR UM");
		this.supplierOne.setSupplierPersonRegister("55325424149");
		expenseOne.setFinancialTransactionCampaign(campaignOne);
		expenseOne.setFinancialTransactionPrice((float) 450000.0);
		expenseOne.setFinancialTransactionPaymentType("FORMA PAGAMENTO UM");
		expenseOne.setFinancialTransactionIdentifier(12);
		expenseOne.setFinancialTransactionDescription("DESCRIPTION UM");
		expenseOne.setFinancialTransactionDate("12/10/2006");
		expenseOne.setFinancialTransactionType("TIPO MOVIMENTACAO UM");
		expenseOne.setFinancialTransactionDocumentNumber("NUMBER DOCUMENTO UM");
		expenseOne.setExpenseSupplier(supplierOne);
		expenseList.add(expenseOne);
		
		this.positionTwo.setPositionDescription("POSITION DOIS");
		this.campaignTwo.setCampaignIdentifier(2);
		this.campaignTwo.setCampaignYear(2006);
		this.campaignTwo.setCampaignCandidateNumber(131222);
		this.campaignTwo.setCampaignPosition(positionTwo);
		this.supplierTwo.setSupplierName("DOADOR DOIS");
		this.supplierTwo.setSupplierPersonRegister("55325424149");
		expenseTwo.setFinancialTransactionCampaign(campaignTwo);
		expenseTwo.setFinancialTransactionPrice((float) 500000.0);
		expenseTwo.setFinancialTransactionPaymentType("FORMA PAGAMENTO DOIS");
		expenseOne.setFinancialTransactionIdentifier(11);
		expenseTwo.setFinancialTransactionDescription("DESCRIPTION DOIS");
		expenseTwo.setFinancialTransactionDate("12/10/2006");
		expenseTwo.setFinancialTransactionType("TIPO MOVIMENTACAO DOIS");
		expenseTwo.setFinancialTransactionDocumentNumber("NUMBER DOCUMENTO DOIS");
		expenseTwo.setExpenseSupplier(supplierTwo);
		expenseList.add(expenseTwo);
		
		this.expenseDAO.registerUnregisteredObjectArrayListOnDatabase(expenseList);
		this.expenseDAO.getExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition(campaignOne);
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
				  + DATABASE_EXPENSE_CAMPAIGN_YEAR + " = " + campaign.getCampaignYear()
				  + " AND " + DATABASE_EXPENSE_CAMPAIGN_CANDIDATE_NUMBER + " = "
				  + campaign.getCampaignCandidateNumber() + " AND "
				  + DATABASE_EXPENSE_CAMPAIGN_COUNTRY_STATE + " = '" 
				  + campaign.getCampaignCountryState() + "' AND "
				  + DATABASE_EXPENSE_CAMPAIGN_POSITION + " LIKE '%"
				  + campaign.getCampaignPosition().getPositionDescription() + "%'";
		
		assertEquals(sqlCommand, this.expenseDAO.mountingSQLConsultationForAttributes(campaign));
	}
	
	@Test
	public void shouldRetrieveExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition() throws Exception {
		
		shouldRegisterAnArrayOfExpenses();
		
		int expensesArraySize = 0;
		expensesArraySize = this.expenseDAO.getExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition(campaignOne).size();
		
		assertEquals(1, expensesArraySize);
	}

}
