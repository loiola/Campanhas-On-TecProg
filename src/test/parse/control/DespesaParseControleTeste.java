package test.parse.control;

import model.beans.Expense;
import model.dao.ExpenseDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlExpense;
import parse.index.ExpenseParseIndex;
import test.TemplateTest;

public class DespesaParseControleTeste extends TemplateTest {
	
	public static final int NOME = 0;
	public static final int CPF_CNPJ = 1;
	public static final int TIPODOCUMENTO = 2;
	public static final String ANO = "2010";
	

	private String campo[];
	private ExpenseDAO expenseDAO;
	private ExpenseParseIndex expenseParseIndex;
	private ParseControlExpense parseControlExpense;

	@Override
	public void beforeTest() throws Exception {
		this.campo = new String[3];
		this.expenseDAO = new ExpenseDAO();
		this.expenseParseIndex = new ExpenseParseIndex(ANO);
		this.parseControlExpense = new ParseControlExpense(this.expenseParseIndex);

		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void cadastrarDespesa() throws Exception {

		this.parseControlExpense.addInstance(campo);
		this.parseControlExpense.registeringInstances();
		this.parseControlExpense.clear();

		Expense despesaCadastrado = this.expenseDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.campo[TIPODOCUMENTO], despesaCadastrado.getExpenseDocumentType()
				.toString());
		Assert.assertEquals(this.campo[NOME],
				despesaCadastrado.getExpenseSupplier().getSupplierName());
		Assert.assertEquals(this.campo[CPF_CNPJ], despesaCadastrado.getExpenseSupplier().getSupplierPersonRegister());
	}

	private void iniciarIndices() {

		this.expenseParseIndex.setIndexNameSupplier(NOME);
		this.expenseParseIndex.setIndexCpfCnpjSupplier(CPF_CNPJ);
		this.expenseParseIndex.setIndexDocumentType(TIPODOCUMENTO);
	}

	private void iniciarCampos() {

		this.campo[NOME] = "NOME TESTE";
		this.campo[CPF_CNPJ] = "123456";
		this.campo[TIPODOCUMENTO] = "BOLETO";
	}

}
