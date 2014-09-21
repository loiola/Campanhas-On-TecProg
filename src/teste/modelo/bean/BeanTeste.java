package teste.modelo.bean;

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

public class BeanTeste {
	
	BeanTeste() {}
	public void usarBeanTeste() {}
	
	public static final Integer INT_TESTE = 50;
	public static final Integer INT_TESTE_2 = 60;
	public static final float FLOAT_TESTE = 1000;
	public static final float FLOAT_TESTE_2 = 2000;
	public static final String STRING_TESTE = "String Teste";
	public static final String STRING_TESTE_2 = "String Teste Dois";
	
	public static Campaign instanciarCampanha() {
		
		Campaign campaign = new Campaign();
		campaign.setCampaignYear(INT_TESTE);
		campaign.setCampaignCandidate(instanciarCandidato());
		campaign.setCampaignPosition(instanciarCargo());
		campaign.setCampaignMaximumExpenseDeclared(FLOAT_TESTE);
		campaign.setCampaignTotalExpenseCalculated(FLOAT_TESTE);
		campaign.setCampaignIdentifier(INT_TESTE);
		campaign.setCampaignNameOfUrn(STRING_TESTE); 
		campaign.setCampaignCandidateNumber(INT_TESTE);
		campaign.setCampaignParty(instanciarPartido());
		campaign.setCampaignTotalRevenueCalculated(FLOAT_TESTE);
		campaign.setCampaignResult(instanciarResultado());
		campaign.setCampaignCountryState(STRING_TESTE);
		
		return campaign;
	}
	
	public static Candidate instanciarCandidato() {
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName(STRING_TESTE);
		candidate.setCandidateElectoralTitle(STRING_TESTE);
		
		return candidate;
	}
	
	public static Position instanciarCargo() {
		
		Position position = new Position();
		position.setPositionCode(INT_TESTE);
		position.setPositionDescription(STRING_TESTE);
		
		return position;
	}
	
	public static Expense instanciarDespesa() {
		
		Expense expense = new Expense();
		expense.setFinancialTransactionCampaign(instanciarCampanha());
		expense.setFinancialTransactionDate(STRING_TESTE);
		expense.setFinancialTransactionDescription(STRING_TESTE);
		expense.setFinancialTransactionPaymentType(STRING_TESTE);
		expense.setExpenseSupplier(instanciarFornecedor());
		expense.setFinancialTransactionIdentifier(INT_TESTE);
		expense.setFinancialTransactionDocumentNumber(STRING_TESTE);
		expense.setExpenseDocumentType(STRING_TESTE);
		expense.setFinancialTransactionType(STRING_TESTE);
		expense.setFinancialTransactionPrice(FLOAT_TESTE);
		
		return expense;
	}
	
	public static Donor instanciarDoador() {
		
		Donor donor = new Donor();
		donor.setDonorPersonRegister(STRING_TESTE);
		donor.setDonorName(STRING_TESTE);
		donor.setDonorRegisterSituation(STRING_TESTE);
		donor.setDonorCountryState(STRING_TESTE);
		
		return donor;		
	}
	
	public static Supplier instanciarFornecedor() {
		
		Supplier supplier = new Supplier();
		supplier.setSupplierPersonRegister(STRING_TESTE);
		supplier.setSupplierName(STRING_TESTE);
		supplier.setSupplierRegisterSituation(STRING_TESTE);
		supplier.setSupplierCountryState(STRING_TESTE);
		
		return supplier;
	}
	
	public static FinancialTransaction instanciarMovimentacaoFinanceira() {
		
		FinancialTransaction financialTransaction = new FinancialTransaction();
		financialTransaction.setFinancialTransactionCampaign(instanciarCampanha());
		financialTransaction.setFinancialTransactionDate(STRING_TESTE);
		financialTransaction.setFinancialTransactionDescription(STRING_TESTE);
		financialTransaction.setFinancialTransactionPaymentType(STRING_TESTE);
		financialTransaction.setFinancialTransactionIdentifier(INT_TESTE);
		financialTransaction.setFinancialTransactionDocumentNumber(STRING_TESTE);
		financialTransaction.setFinancialTransactionType(STRING_TESTE);
		financialTransaction.setFinancialTransactionPrice(FLOAT_TESTE);
		
		return financialTransaction;
	}
	
	public static Party instanciarPartido() {
		
		Party party = new Party();
		party.setPartyConcession(STRING_TESTE);
		party.setPartyName(STRING_TESTE);
		party.setPartyNumber(INT_TESTE);
		party.setPartyAcronym(STRING_TESTE);
		
		return party;
	}
	
	public static Revenue instanciarReceita() {
		
		Revenue revenue = new Revenue();
		revenue.setFinancialTransactionCampaign(instanciarCampanha());
		revenue.setFinancialTransactionDate(STRING_TESTE);
		revenue.setFinancialTransactionDescription(STRING_TESTE);
		revenue.setRevenueDonor(instanciarDoador());
		revenue.setFinancialTransactionPaymentType(STRING_TESTE);
		revenue.setFinancialTransactionIdentifier(INT_TESTE);
		revenue.setFinancialTransactionDocumentNumber(STRING_TESTE);
		revenue.setRevenueElectoralReceipt(STRING_TESTE);
		revenue.setFinancialTransactionType(STRING_TESTE);
		revenue.setFinancialTransactionPrice(FLOAT_TESTE);

		return revenue;
	}
	
	public static Result instanciarResultado() {
		
		Result result = new Result();
		result.setCodigo(INT_TESTE);
		result.setDescricao(STRING_TESTE);
		
		return result;
	}
	
}
