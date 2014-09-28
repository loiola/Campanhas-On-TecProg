package test.model.bean;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Donor;
import model.beans.Expense;
import model.beans.FinancialTransaction;
import model.beans.Party;
import model.beans.Position;
import model.beans.Result;
import model.beans.Revenue;
import model.beans.Supplier;

public class BeanTest {
	
	BeanTest() {}
	public void usarBeanTeste() {}
	
	public static final Integer INT_TEST = 50;
	public static final Integer INT_TEST_2 = 60;
	public static final float FLOAT_TEST = 1000;
	public static final float FLOAT_TEST_2 = 2000;
	public static final String STRING_TEST = "String Teste";
	public static final String STRING_TEST_2 = "String Teste Dois";
	
	public static Campaign instantiateCampaign() {
		
		Campaign campaign = new Campaign();
		campaign.setCampaignYear(INT_TEST);
		campaign.setCampaignCandidate(instantiateCandidate());
		campaign.setCampaignPosition(instantiatePosition());
		campaign.setCampaignMaximumExpenseDeclared(FLOAT_TEST);
		campaign.setCampaignTotalExpenseCalculated(FLOAT_TEST);
		campaign.setCampaignIdentifier(INT_TEST);
		campaign.setCampaignNameOfUrn(STRING_TEST); 
		campaign.setCampaignCandidateNumber(INT_TEST);
		campaign.setCampaignParty(instantiateParty());
		campaign.setCampaignTotalRevenueCalculated(FLOAT_TEST);
		campaign.setCampaignResult(instantiateResult());
		campaign.setCampaignCountryState(STRING_TEST);
		
		return campaign;
	}
	
	public static Candidate instantiateCandidate() {
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName(STRING_TEST);
		candidate.setCandidateElectoralTitle(STRING_TEST);
		
		return candidate;
	}
	
	public static Position instantiatePosition() {
		
		Position position = new Position();
		position.setPositionCode(INT_TEST);
		position.setPositionDescription(STRING_TEST);
		
		return position;
	}
	
	public static Expense instantiateExpense() {
		
		Expense expense = new Expense();
		expense.setFinancialTransactionCampaign(instantiateCampaign());
		expense.setFinancialTransactionDate(STRING_TEST);
		expense.setFinancialTransactionDescription(STRING_TEST);
		expense.setFinancialTransactionPaymentType(STRING_TEST);
		expense.setExpenseSupplier(instantiateSupplier());
		expense.setFinancialTransactionIdentifier(INT_TEST);
		expense.setFinancialTransactionDocumentNumber(STRING_TEST);
		expense.setExpenseDocumentType(STRING_TEST);
		expense.setFinancialTransactionType(STRING_TEST);
		expense.setFinancialTransactionPrice(FLOAT_TEST);
		
		return expense;
	}
	
	public static Donor instantiateDonor() {
		
		Donor donor = new Donor();
		donor.setDonorPersonRegister(STRING_TEST);
		donor.setDonorName(STRING_TEST);
		donor.setDonorRegisterSituation(STRING_TEST);
		donor.setDonorCountryState(STRING_TEST);
		
		return donor;		
	}
	
	public static Supplier instantiateSupplier() {
		
		Supplier supplier = new Supplier();
		supplier.setSupplierPersonRegister(STRING_TEST);
		supplier.setSupplierName(STRING_TEST);
		supplier.setSupplierRegisterSituation(STRING_TEST);
		supplier.setSupplierCountryState(STRING_TEST);
		
		return supplier;
	}
	
	public static FinancialTransaction instantiateFinancialTransaction() {
		
		FinancialTransaction financialTransaction = new FinancialTransaction();
		financialTransaction.setFinancialTransactionCampaign(instantiateCampaign());
		financialTransaction.setFinancialTransactionDate(STRING_TEST);
		financialTransaction.setFinancialTransactionDescription(STRING_TEST);
		financialTransaction.setFinancialTransactionPaymentType(STRING_TEST);
		financialTransaction.setFinancialTransactionIdentifier(INT_TEST);
		financialTransaction.setFinancialTransactionDocumentNumber(STRING_TEST);
		financialTransaction.setFinancialTransactionType(STRING_TEST);
		financialTransaction.setFinancialTransactionPrice(FLOAT_TEST);
		
		return financialTransaction;
	}
	
	public static Party instantiateParty() {
		
		Party party = new Party();
		party.setPartyConcession(STRING_TEST);
		party.setPartyName(STRING_TEST);
		party.setPartyNumber(INT_TEST);
		party.setPartyAcronym(STRING_TEST);
		
		return party;
	}
	
	public static Revenue instantiateRevenue() {
		
		Revenue revenue = new Revenue();
		revenue.setFinancialTransactionCampaign(instantiateCampaign());
		revenue.setFinancialTransactionDate(STRING_TEST);
		revenue.setFinancialTransactionDescription(STRING_TEST);
		revenue.setRevenueDonor(instantiateDonor());
		revenue.setFinancialTransactionPaymentType(STRING_TEST);
		revenue.setFinancialTransactionIdentifier(INT_TEST);
		revenue.setFinancialTransactionDocumentNumber(STRING_TEST);
		revenue.setRevenueElectoralReceipt(STRING_TEST);
		revenue.setFinancialTransactionType(STRING_TEST);
		revenue.setFinancialTransactionPrice(FLOAT_TEST);

		return revenue;
	}
	
	public static Result instantiateResult() {
		
		Result result = new Result();
		result.setResultType(INT_TEST);
		result.setResultDescription(STRING_TEST);
		
		return result;
	}
	
}