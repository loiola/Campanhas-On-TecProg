package test.parse.index;

import model.beans.FinancialTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.FinancialTransactionParseIndex;

public class MovimentacaoFinanceiraIndicesParseTeste {

	private String campo[];
	private FinancialTransactionParseIndex financialTransactionParseIndex;
	private String ano = "2006";
	private String anoTeste = "2010";

	@Before
	public void setUp() throws Exception {
		
		this.financialTransactionParseIndex = new FinancialTransactionParseIndex<>(ano);
		this.campo = new String[9];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmaMovimentacaoFinanceiraComIndicesValidos() throws Exception {
		
		FinancialTransaction financialTransaction = new FinancialTransaction();
		this.financialTransactionParseIndex.startInstance(financialTransaction, campo);
		Assert.assertEquals(this.campo[0], financialTransaction.getFinancialTransactionCampaign().getCampaignYear().toString());
		Assert.assertEquals(this.campo[1], financialTransaction.getFinancialTransactionCampaign().getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.campo[2], financialTransaction.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
		Assert.assertEquals(this.campo[3], financialTransaction.getFinancialTransactionDocumentNumber());
		Assert.assertEquals(this.campo[4], financialTransaction.getFinancialTransactionDate());
		Assert.assertEquals(this.campo[5], financialTransaction.getFinancialTransactionPrice().toString());
		Assert.assertEquals(this.campo[6], financialTransaction.getFinancialTransactionType());
		Assert.assertEquals(this.campo[7], financialTransaction.getFinancialTransactionPaymentType());
		Assert.assertEquals(this.campo[8], financialTransaction.getFinancialTransactionDescription());
	}
	
	@Test
	public void iniciarUmaMovimentacaoFinanceiraComIndicesInvalidos() throws Exception {
		
		this.financialTransactionParseIndex = new FinancialTransactionParseIndex<>(anoTeste);
		FinancialTransaction financialTransaction = new FinancialTransaction();
		this.financialTransactionParseIndex.startInstance(financialTransaction, campo);
		Assert.assertNotEquals(this.campo[0], financialTransaction.getFinancialTransactionCampaign().getCampaignYear().toString());
		Assert.assertNotEquals(this.campo[1], financialTransaction.getFinancialTransactionCampaign().getCampaignCandidateNumber().toString());
		Assert.assertNotEquals(this.campo[2], financialTransaction.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
		Assert.assertNotEquals(this.campo[3], financialTransaction.getFinancialTransactionDocumentNumber());
		Assert.assertNotEquals(this.campo[4], financialTransaction.getFinancialTransactionDate());
		Assert.assertNotEquals(this.campo[5], financialTransaction.getFinancialTransactionPrice().toString());
		Assert.assertNotEquals(this.campo[6], financialTransaction.getFinancialTransactionType());
		Assert.assertNotEquals(this.campo[7], financialTransaction.getFinancialTransactionPaymentType());
		Assert.assertNotEquals(this.campo[8], financialTransaction.getFinancialTransactionDescription());
	}
	
	private void iniciarIndices() {
		
		this.financialTransactionParseIndex.setIndexCampaignYear(0);
		this.financialTransactionParseIndex.setIndexNumberCampaign(1);
		this.financialTransactionParseIndex.setIndexPositionCampaign(2);
		this.financialTransactionParseIndex.setIndexDocumentNumber(3);
		this.financialTransactionParseIndex.setIndexDate(4);
		this.financialTransactionParseIndex.setIndexValue(5);
		this.financialTransactionParseIndex.setIndexTypeOfFinancialTransaction(6);
		this.financialTransactionParseIndex.setIndexFormOfPayment(7);
		this.financialTransactionParseIndex.setIndexDescription(8);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "2006";
		this.campo[1] = "45555";
		this.campo[2] = "POSITION";
		this.campo[3] = "55896321447";
		this.campo[4] = "12/10/2006";
		this.campo[5] = "450.0";
		this.campo[6] = "TIPO MOVIMENTACAO";
		this.campo[7] = "FORMA PAGAMENTO";
		this.campo[8] = "DESCRICAO";
	}

}
