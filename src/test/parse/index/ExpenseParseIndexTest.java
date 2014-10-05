package test.parse.index;

import model.beans.Expense;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.ExpenseParseIndex;

public class ExpenseParseIndexTest {

	private String field[];
	private ExpenseParseIndex expenseParseIndex;
	private String year = "2006";

	@Before
	public void setUp() throws Exception {
		
		this.expenseParseIndex = new ExpenseParseIndex(year);
		this.field = new String[3];
		
		fieldsStart();
		indexStart();
	}
	
	@Test
	public void startExpenseWithValidIndices() throws Exception {
		
		Expense expense = new Expense();
		this.expenseParseIndex.startInstance(expense, field);
		Assert.assertEquals(this.field[0], expense.getExpenseSupplier().getSupplierName());
		Assert.assertEquals(this.field[1], expense.getExpenseSupplier().getSupplierPersonRegister());
		Assert.assertEquals(this.field[2], expense.getExpenseDocumentType());
	}
	
	@Test
	public void startExpenseWithInvalidIndices() {
		
		this.expenseParseIndex = new ExpenseParseIndex(year);
		Expense expense = new Expense();
		this.expenseParseIndex.startInstance(expense, field);
		Assert.assertNotEquals(this.field[0], expense.getExpenseSupplier().getSupplierName());
		Assert.assertNotEquals(this.field[1], expense.getExpenseSupplier().getSupplierPersonRegister());
		Assert.assertNotEquals(this.field[2], expense.getExpenseDocumentType());
	}
	
	@Test
	public void shouldReturnTheYearRegistered() {
		
		expenseParseIndex.setYear(2010);
	}
	
	private void indexStart() {
		
		this.expenseParseIndex.setIndexNameSupplier(0);
		this.expenseParseIndex.setIndexCpfCnpjSupplier(1);
		this.expenseParseIndex.setIndexDocumentType(2);
	}
	
	private void fieldsStart() {
		this.field[0] = "FORNECEDOR";
		this.field[1] = "55325424149";
		this.field[2] = "BOLETO";
	}

}
