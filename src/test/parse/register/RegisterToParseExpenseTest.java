package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Expense;
import model.dao.ExpenseDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.revenue_expense.RegisterToParseExpense;
import test.TemplateTest;

public class RegisterToParseExpenseTest extends TemplateTest {

	private RegisterToParseExpense register1;
	private RegisterToParseExpense register2;
	private RegisterToParseExpense register3;
	private ExpenseDAO expenseDAO;
	String  fileType = "expense";
	String  year1         = "2006";
	String  year2         = "2002";
	String  year3         = "2010";
	String  wrongFileType = "ArquivoErrado";
	String  invalidYear = "2050";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register1 = new RegisterToParseExpense(this.fileType, this.year1);
		this.register2 = new RegisterToParseExpense(this.fileType, this.year2);
		this.register3 = new RegisterToParseExpense(this.fileType, this.year3);
		this.expenseDAO = new ExpenseDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void shouldReturnExpenseRegisteredFor2006() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[4] = "DF";
		field[3] = "123";
		field[1] = "Position";
		field[11] = "TipoMov";
		field[16] = "TipoDoc";
		field[13] = "FormaPag";
		field[15] = "1234";
		field[10] = "Data";
		field[19] = "12345";
		field[18] = "123456";
		field[9] = "1234567";
		register1.runFileLine(field);
		register1.registerInstances();
		
		ArrayList<Expense> expenseList = expenseDAO.getObjectArrayListFromDatabase();
		assertEquals(expenseList.get(0).getFinancialTransactionType(), "TipoMov");
	}
	
	@Test
	public void shouldReturnExpenseRegisteredFor2002() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[0] = "DF";
		field[4] = "123";
		field[2] = "Position";
		field[10] = "TipoMov";
		field[5] = "Data";
		field[6] = "1234";
		field[8] = "FornecedorNome";
		field[9] = "12345";
		register2.runFileLine(field);
		register2.registerInstances();
		
		ArrayList<Expense> expenseList = expenseDAO.getObjectArrayListFromDatabase();
		assertEquals(expenseList.get(0).getFinancialTransactionType(), "TipoMov");
	}
	
	@Test
	public void shouldReturnExpenseRegisteredFor2010() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[1] = "DF";
		field[3] = "123";
		field[4] = "Position";
		field[14] = "TipoMov";
		field[8] = "TipoDoc";
		field[16] = "FormaPag";
		field[9] = "1234";
		field[12] = "Data";
		field[10] = "12345";
		field[11] = "Nome";
		field[13] = "123456";
		field[17] = "Descricao";
		
		register3.runFileLine(field);
		register3.registerInstances();
		
		ArrayList<Expense> expenseList = expenseDAO.getObjectArrayListFromDatabase();
		assertEquals(expenseList.get(0).getFinancialTransactionType(), "TipoMov");
	}

}
