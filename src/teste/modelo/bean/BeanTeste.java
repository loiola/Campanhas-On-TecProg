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
		expense.setCampanha(instanciarCampanha());
		expense.setData(STRING_TESTE);
		expense.setDescricao(STRING_TESTE);
		expense.setFormaPagamento(STRING_TESTE);
		expense.setExpenseSupplier(instanciarFornecedor());
		expense.setId(INT_TESTE);
		expense.setNumeroDocumento(STRING_TESTE);
		expense.setExpenseDocumentType(STRING_TESTE);
		expense.setTipoMovimentacao(STRING_TESTE);
		expense.setValor(FLOAT_TESTE);
		
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
		supplier.setCpf_cnpj(STRING_TESTE);
		supplier.setNome(STRING_TESTE);
		supplier.setSituacaoCadastral(STRING_TESTE);
		supplier.setUf(STRING_TESTE);
		
		return supplier;
	}
	
	public static FinancialTransaction instanciarMovimentacaoFinanceira() {
		
		FinancialTransaction financialTransaction = new FinancialTransaction();
		financialTransaction.setCampanha(instanciarCampanha());
		financialTransaction.setData(STRING_TESTE);
		financialTransaction.setDescricao(STRING_TESTE);
		financialTransaction.setFormaPagamento(STRING_TESTE);
		financialTransaction.setId(INT_TESTE);
		financialTransaction.setNumeroDocumento(STRING_TESTE);
		financialTransaction.setTipoMovimentacao(STRING_TESTE);
		financialTransaction.setValor(FLOAT_TESTE);
		
		return financialTransaction;
	}
	
	public static Party instanciarPartido() {
		
		Party party = new Party();
		party.setDeferimento(STRING_TESTE);
		party.setNome(STRING_TESTE);
		party.setNumero(INT_TESTE);
		party.setSigla(STRING_TESTE);
		
		return party;
	}
	
	public static Revenue instanciarReceita() {
		
		Revenue revenue = new Revenue();
		revenue.setCampanha(instanciarCampanha());
		revenue.setData(STRING_TESTE);
		revenue.setDescricao(STRING_TESTE);
		revenue.setDoador(instanciarDoador());
		revenue.setFormaPagamento(STRING_TESTE);
		revenue.setId(INT_TESTE);
		revenue.setNumeroDocumento(STRING_TESTE);
		revenue.setReciboEleitoral(STRING_TESTE);
		revenue.setTipoMovimentacao(STRING_TESTE);
		revenue.setValor(FLOAT_TESTE);

		return revenue;
	}
	
	public static Result instanciarResultado() {
		
		Result result = new Result();
		result.setCodigo(INT_TESTE);
		result.setDescricao(STRING_TESTE);
		
		return result;
	}
	
}
