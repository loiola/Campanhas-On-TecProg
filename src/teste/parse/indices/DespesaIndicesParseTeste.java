package teste.parse.indices;

import model.beans.Expense;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.ExpenseParseIndex;

public class DespesaIndicesParseTeste {

	private String campo[];
	private ExpenseParseIndex expenseParseIndex;
	private String ano = "2006";

	@Before
	public void setUp() throws Exception {
		
		this.expenseParseIndex = new ExpenseParseIndex(ano);
		this.campo = new String[3];
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmaDespesaComIndicesValidos() throws Exception {
		
		Expense expense = new Expense();
		this.expenseParseIndex.startInstance(expense, campo);
		Assert.assertEquals(this.campo[0], expense.getExpenseSupplier().getSupplierName());
		Assert.assertEquals(this.campo[1], expense.getExpenseSupplier().getSupplierPersonRegister());
		Assert.assertEquals(this.campo[2], expense.getExpenseDocumentType());
	}
	
	@Test
	public void iniciarUmaDespesaComIndicesInvalidos() {
		
		this.expenseParseIndex = new ExpenseParseIndex(ano);
		Expense expense = new Expense();
		this.expenseParseIndex.startInstance(expense, campo);
		Assert.assertNotEquals(this.campo[0], expense.getExpenseSupplier().getSupplierName());
		Assert.assertNotEquals(this.campo[1], expense.getExpenseSupplier().getSupplierPersonRegister());
		Assert.assertNotEquals(this.campo[2], expense.getExpenseDocumentType());
	}
	
	@Test
	public void deveRetornarOhAnoCadastrado() {
		
		expenseParseIndex.setYear(2010);
	}
	
	private void iniciarIndices() {
		
		this.expenseParseIndex.setIndexNameSupplier(0);
		this.expenseParseIndex.setIndexCpfCnpjSupplier(1);
		this.expenseParseIndex.setIndexDocumentType(2);
	}
	
	private void iniciarCampos() {
		this.campo[0] = "FORNECEDOR";
		this.campo[1] = "55325424149";
		this.campo[2] = "BOLETO";
	}

}
