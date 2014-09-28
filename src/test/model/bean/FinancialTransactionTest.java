package test.model.bean;

import static org.junit.Assert.assertFalse;
import static test.model.bean.BeanTest.instantiateCampaign;
import static test.model.bean.BeanTest.instantiateExpense;
import static test.model.bean.BeanTest.instantiateDonor;
import static test.model.bean.BeanTest.instantiateSupplier;
import static test.model.bean.BeanTest.instantiateFinancialTransaction;
import static test.model.bean.BeanTest.instantiateRevenue;
import model.beans.Campaign;
import model.beans.Donor;
import model.beans.Expense;
import model.beans.FinancialTransaction;
import model.beans.Revenue;
import model.beans.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FinancialTransactionTest {

	Expense expense;
	Revenue revenue;
	Expense expense2;
	Revenue revenue2;
	
	@Before
	public void SetUp() {
		
		this.expense = instantiateExpense();
		this.revenue = instantiateRevenue();
		this.expense2 = instantiateExpense();
		this.revenue2 = instantiateRevenue();
	}

	@Test
	public void equalsShouldReturnFalseForAnyCondition() {

		Assert.assertFalse(expense.equals(revenue));
		Assert.assertFalse(revenue.equals(expense));
		Assert.assertEquals(BeanTest.STRING_TEST, expense.getExpenseDocumentType());
		Assert.assertEquals(instantiateSupplier(), expense.getExpenseSupplier());
		Assert.assertEquals(BeanTest.STRING_TEST, revenue.getRevenueElectoralReceipt());
		Assert.assertEquals(instantiateDonor(), revenue.getRevenueDonor());
	}
	
	@Test
	public void equalsShouldReturnTrue() {

		Expense otherExpense = expense;
		Revenue otherRevenue = revenue;
		
		Assert.assertFalse(expense.equals(otherExpense));
		Assert.assertFalse(revenue.equals(otherRevenue));
	}
	
	@Test
	public void equalsShouldReturnFalseForOtherCases() {
		Donor donorTwo = instantiateDonor();
		donorTwo.setDonorPersonRegister(BeanTest.STRING_TEST_2);
		
		Supplier supplierTwo = instantiateSupplier();
		supplierTwo.setSupplierPersonRegister(BeanTest.STRING_TEST_2);	
		
		revenue2.setRevenueDonor(donorTwo);
		expense2.setExpenseSupplier(supplierTwo);
		
		assertFalse(revenue.equals(revenue2));
		assertFalse(expense.equals(expense2));
		
	}
	
	@Test
	public void equalsShouldReturnTrueIfFinancialTransactionsAreTheSame() {
		
		FinancialTransaction financialTransactionOne = instantiateFinancialTransaction();
		FinancialTransaction financialTransactionTwo = instantiateFinancialTransaction();
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
	}
	
	@Test
	public void equalsShouldReturnFalseIfFianancialTransactionsAreDifferent() {
		
		FinancialTransaction financialTransactionOne = instantiateFinancialTransaction();
		FinancialTransaction financialTransactionTwo = instantiateFinancialTransaction();
		Campaign campaign = instantiateCampaign();
		campaign.setCampaignNameOfUrn(BeanTest.STRING_TEST_2);
		financialTransactionTwo.setFinancialTransactionCampaign(campaign);
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
		campaign.setCampaignNameOfUrn(BeanTest.STRING_TEST);
		financialTransactionTwo.setFinancialTransactionCampaign(campaign);
		financialTransactionTwo.setFinancialTransactionDescription(BeanTest.STRING_TEST_2);
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
		financialTransactionTwo.setFinancialTransactionDescription(BeanTest.STRING_TEST);
		financialTransactionTwo.setFinancialTransactionPaymentType(BeanTest.STRING_TEST_2);
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
		financialTransactionTwo.setFinancialTransactionPaymentType(BeanTest.STRING_TEST);
		financialTransactionTwo.setFinancialTransactionDocumentNumber(BeanTest.STRING_TEST_2);
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
		financialTransactionTwo.setFinancialTransactionDocumentNumber(BeanTest.STRING_TEST);
		financialTransactionTwo.setFinancialTransactionType(BeanTest.STRING_TEST_2);
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
		financialTransactionTwo.setFinancialTransactionType(BeanTest.STRING_TEST);
		financialTransactionTwo.setFinancialTransactionPrice(BeanTest.FLOAT_TEST_2);
		Assert.assertFalse(financialTransactionOne.equals(financialTransactionTwo));
		
		Assert.assertEquals(BeanTest.INT_TEST, financialTransactionOne.getFinancialTransactionIdentifier());
		Assert.assertEquals(BeanTest.STRING_TEST, financialTransactionOne.getFinancialTransactionDate());
		
		BeanTest bt = new BeanTest();
		bt.usarBeanTeste();
		
		Assert.assertEquals((float) 1000, BeanTest.FLOAT_TEST,0);
		Assert.assertEquals((float) 2000, BeanTest.FLOAT_TEST_2,0);
		Assert.assertEquals("String Teste", BeanTest.STRING_TEST);
	}

}