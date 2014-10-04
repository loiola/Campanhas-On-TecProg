package test.parse.control;

import model.beans.Expense;
import model.dao.ExpenseDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlExpense;
import parse.index.ExpenseParseIndex;
import test.TemplateTest;

public class ExpenseParseControlTest extends TemplateTest {
	
	public static final int NAME = 0;
	public static final int PERSON_REGISTER = 1;
	public static final int DOCUMENT_TYPE = 2;
	public static final String YEAR = "2010";
	

	private String fields[];
	private ExpenseDAO expenseDAO;
	private ExpenseParseIndex expenseParseIndex;
	private ParseControlExpense parseControlExpense;

	@Override
	public void beforeTest() throws Exception {
		this.fields = new String[3];
		this.expenseDAO = new ExpenseDAO();
		this.expenseParseIndex = new ExpenseParseIndex(YEAR);
		this.parseControlExpense = new ParseControlExpense(this.expenseParseIndex);

		fieldsStart();
		indexStart();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void registeringExpense() throws Exception {

		this.parseControlExpense.addInstance(fields);
		this.parseControlExpense.registeringInstances();
		this.parseControlExpense.clear();

		Expense expenseRegistered = this.expenseDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.fields[DOCUMENT_TYPE], expenseRegistered.getExpenseDocumentType()
				.toString());
		Assert.assertEquals(this.fields[NAME],
				expenseRegistered.getExpenseSupplier().getSupplierName());
		Assert.assertEquals(this.fields[PERSON_REGISTER], expenseRegistered.getExpenseSupplier().getSupplierPersonRegister());
	}

	private void indexStart() {

		this.expenseParseIndex.setIndexNameSupplier(NAME);
		this.expenseParseIndex.setIndexCpfCnpjSupplier(PERSON_REGISTER);
		this.expenseParseIndex.setIndexDocumentType(DOCUMENT_TYPE);
	}

	private void fieldsStart() {

		this.fields[NAME] = "NAME TESTE";
		this.fields[PERSON_REGISTER] = "123456";
		this.fields[DOCUMENT_TYPE] = "BOLETO";
	}

}
