package test.parse.index;

import model.beans.FinancialTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.FinancialTransactionParseIndex;

public class FinancialTransactionParseIndexTest {

	public final static Integer YEAR_2010 = 2010;
	
	private String field[];
	private FinancialTransactionParseIndex<Object> financialTransactionParseIndex;
	private String year = "2006";
	private String testYear = "2010";

	@Before
	public void setUp() throws Exception {
		
		this.financialTransactionParseIndex = new FinancialTransactionParseIndex<>(year);
		this.field = new String[10];
		
		fieldsStart();
		indexStart();
	}

	@Test
	public void startFinancialTransactionWithValidIndices() throws Exception {
		
		FinancialTransaction financialTransaction = new FinancialTransaction();
		this.financialTransactionParseIndex.startInstance(financialTransaction, field);
		Assert.assertEquals(this.field[0], financialTransaction.getFinancialTransactionCampaign().getCampaignYear().toString());
		Assert.assertEquals(this.field[1], financialTransaction.getFinancialTransactionCampaign().getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.field[2], financialTransaction.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
		Assert.assertEquals(this.field[3], financialTransaction.getFinancialTransactionDocumentNumber());
		Assert.assertEquals(this.field[4], financialTransaction.getFinancialTransactionDate());
		Assert.assertEquals(this.field[5], financialTransaction.getFinancialTransactionPrice().toString());
		Assert.assertEquals(this.field[6], financialTransaction.getFinancialTransactionType());
		Assert.assertEquals(this.field[7], financialTransaction.getFinancialTransactionPaymentType());
		Assert.assertEquals(this.field[8], financialTransaction.getFinancialTransactionDescription());
		Assert.assertEquals(this.field[9], financialTransaction.getFinancialTransactionCampaign().getCampaignCountryState());
	}
	
	@Test
	public void startFinancialTransactionWithInvalidIndices() throws Exception {
		
		this.financialTransactionParseIndex = new FinancialTransactionParseIndex<>(testYear);
		FinancialTransaction financialTransaction = new FinancialTransaction();
		this.financialTransactionParseIndex.startInstance(financialTransaction, field);
		Assert.assertNotEquals(this.field[0], financialTransaction.getFinancialTransactionCampaign().getCampaignYear().toString());
		Assert.assertNotEquals(this.field[1], financialTransaction.getFinancialTransactionCampaign().getCampaignCandidateNumber().toString());
		Assert.assertNotEquals(this.field[2], financialTransaction.getFinancialTransactionCampaign().getCampaignPosition().getPositionDescription());
		Assert.assertNotEquals(this.field[3], financialTransaction.getFinancialTransactionDocumentNumber());
		Assert.assertNotEquals(this.field[4], financialTransaction.getFinancialTransactionDate());
		Assert.assertNotEquals(this.field[5], financialTransaction.getFinancialTransactionPrice().toString());
		Assert.assertNotEquals(this.field[6], financialTransaction.getFinancialTransactionType());
		Assert.assertNotEquals(this.field[7], financialTransaction.getFinancialTransactionPaymentType());
		Assert.assertNotEquals(this.field[8], financialTransaction.getFinancialTransactionDescription());
		Assert.assertNotEquals(this.field[9], financialTransaction.getFinancialTransactionCampaign().getCampaignCountryState());
	}
	
	private void indexStart() {
		
		this.financialTransactionParseIndex.setIndexCampaignYear(0);
		this.financialTransactionParseIndex.setIndexNumberCampaign(1);
		this.financialTransactionParseIndex.setIndexPositionCampaign(2);
		this.financialTransactionParseIndex.setIndexDocumentNumber(3);
		this.financialTransactionParseIndex.setIndexDate(4);
		this.financialTransactionParseIndex.setIndexValue(5);
		this.financialTransactionParseIndex.setIndexTypeOfFinancialTransaction(6);
		this.financialTransactionParseIndex.setIndexFormOfPayment(7);
		this.financialTransactionParseIndex.setIndexDescription(8);
		this.financialTransactionParseIndex.setIndexUnitFederationCampaign(9);
		this.financialTransactionParseIndex.setYear(YEAR_2010);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "2006";
		this.field[1] = "45555";
		this.field[2] = "POSITION";
		this.field[3] = "55896321447";
		this.field[4] = "12/10/2006";
		this.field[5] = "450.0";
		this.field[6] = "TIPO MOVIMENTACAO";
		this.field[7] = "FORMA PAGAMENTO";
		this.field[8] = "DESCRIPTION";
		this.field[9] = "UNIT FEDERATION";
	}

}
