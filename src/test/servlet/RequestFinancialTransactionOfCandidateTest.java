package test.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import test.TemplateTest;
import control.servlet.RequestFinancialTransactionOfCandidate;

public class RequestFinancialTransactionOfCandidateTest extends TemplateTest {

	RequestFinancialTransactionOfCandidate requestFinancialTransactionOfCandidate;
	HttpServletRequest requestServlet;
	HttpServletResponse responseServlet;

	@Test
	public void mockServletWithSeeAllTransactionsEnabledAndQuantityOfTransactionsPerPageDifferentOfZero()
			throws Exception {
		when(requestServlet.getParameter("totalExpenseCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("totalRevenueCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("electionYear")).thenReturn("2006");
		when(requestServlet.getParameter("candidateNumber")).thenReturn("14789");
		when(requestServlet.getParameter("codeOfPosition")).thenReturn("8");
		when(requestServlet.getParameter("countryState")).thenReturn("DF");
		when(requestServlet.getParameter("firstRevenue")).thenReturn("0");
		when(requestServlet.getParameter("quantityRevenuePerPage")).thenReturn("10");
		when(requestServlet.getParameter("seeAllRevenues")).thenReturn("false");
		when(requestServlet.getParameter("centerOfRevenue")).thenReturn("1");
		when(requestServlet.getParameter("firstExpense")).thenReturn("0");
		when(requestServlet.getParameter("quantityExpensePerPage")).thenReturn("10");
		when(requestServlet.getParameter("seeAllExpenses")).thenReturn("false");
		when(requestServlet.getParameter("centerOfExpense")).thenReturn("1");

		this.requestFinancialTransactionOfCandidate.execute(requestServlet, responseServlet);
	}

	@Test
	public void mockServletWithSeeAllTransactionsEnabledAndQuantityOfTransactionsPerPageEqualsToZero()
			throws Exception {
		when(requestServlet.getParameter("totalExpenseCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("totalRevenueCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("electionYear")).thenReturn("2006");
		when(requestServlet.getParameter("candidateNumber")).thenReturn("14789");
		when(requestServlet.getParameter("codeOfPosition")).thenReturn("8");
		when(requestServlet.getParameter("countryState")).thenReturn("DF");
		when(requestServlet.getParameter("firstRevenue")).thenReturn("0");
		when(requestServlet.getParameter("quantityRevenuePerPage")).thenReturn("0");
		when(requestServlet.getParameter("seeAllRevenues")).thenReturn("true");
		when(requestServlet.getParameter("centerOfRevenue")).thenReturn("1");
		when(requestServlet.getParameter("firstExpense")).thenReturn("0");
		when(requestServlet.getParameter("quantityExpensePerPage")).thenReturn("0");
		when(requestServlet.getParameter("seeAllExpenses")).thenReturn("true");
		when(requestServlet.getParameter("centerOfExpense")).thenReturn("1");

		this.requestFinancialTransactionOfCandidate.execute(requestServlet, responseServlet);
	}

	@Test
	public void mockServletWithInexistentCandidate() throws Exception {
		when(requestServlet.getParameter("totalExpenseCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("totalRevenueCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("electionYear")).thenReturn("2006");
		when(requestServlet.getParameter("candidateNumber")).thenReturn("5");
		when(requestServlet.getParameter("codeOfPosition")).thenReturn("8");
		when(requestServlet.getParameter("countryState")).thenReturn("DF");
		when(requestServlet.getParameter("firstRevenue")).thenReturn("0");
		when(requestServlet.getParameter("quantityRevenuePerPage")).thenReturn("0");
		when(requestServlet.getParameter("seeAllRevenues")).thenReturn("true");
		when(requestServlet.getParameter("centerOfRevenue")).thenReturn("1");
		when(requestServlet.getParameter("firstExpense")).thenReturn("0");
		when(requestServlet.getParameter("quantityExpensePerPage")).thenReturn("0");
		when(requestServlet.getParameter("seeAllExpenses")).thenReturn("true");
		when(requestServlet.getParameter("centerOfExpense")).thenReturn("1");

		this.requestFinancialTransactionOfCandidate.execute(requestServlet, responseServlet);
	}

	@Test
	public void mockServletWithHighIndexOfPagination() throws Exception {
		when(requestServlet.getParameter("totalExpenseCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("totalRevenueCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("electionYear")).thenReturn("2010");
		when(requestServlet.getParameter("candidateNumber")).thenReturn("13");
		when(requestServlet.getParameter("codeOfPosition")).thenReturn("1");
		when(requestServlet.getParameter("countryState")).thenReturn("BR");
		when(requestServlet.getParameter("firstRevenue")).thenReturn("130");
		when(requestServlet.getParameter("quantityRevenuePerPage")).thenReturn("0");
		when(requestServlet.getParameter("seeAllRevenues")).thenReturn("true");
		when(requestServlet.getParameter("centerOfRevenue")).thenReturn("65");
		when(requestServlet.getParameter("firstExpense")).thenReturn("0");
		when(requestServlet.getParameter("quantityExpensePerPage")).thenReturn("0");
		when(requestServlet.getParameter("seeAllExpenses")).thenReturn("true");
		when(requestServlet.getParameter("centerOfExpense")).thenReturn("1");

		this.requestFinancialTransactionOfCandidate.execute(requestServlet, responseServlet);
	}

	@Test
	public void mockAnotherServletWithHighIndexOfPagination() throws Exception {
		when(requestServlet.getParameter("totalExpenseCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("totalRevenueCalculatedValue")).thenReturn("10000");
		when(requestServlet.getParameter("electionYear")).thenReturn("2010");
		when(requestServlet.getParameter("candidateNumber")).thenReturn("13");
		when(requestServlet.getParameter("codeOfPosition")).thenReturn("1");
		when(requestServlet.getParameter("countryState")).thenReturn("BR");
		when(requestServlet.getParameter("firstRevenue")).thenReturn("0");
		when(requestServlet.getParameter("quantityRevenuePerPage")).thenReturn("10");
		when(requestServlet.getParameter("seeAllRevenues")).thenReturn("true");
		when(requestServlet.getParameter("centerOfRevenue")).thenReturn("65");
		when(requestServlet.getParameter("firstExpense")).thenReturn("0");
		when(requestServlet.getParameter("quantityExpensePerPage")).thenReturn("10");
		when(requestServlet.getParameter("seeAllExpenses")).thenReturn("true");
		when(requestServlet.getParameter("centerOfExpense")).thenReturn("695");
		
		this.requestFinancialTransactionOfCandidate.execute(requestServlet, responseServlet);
	}

	@Override
	public void beforeTest() throws Exception {
		this.requestFinancialTransactionOfCandidate = new RequestFinancialTransactionOfCandidate();

		this.databaseConnection.adjustDatabaseSchemaName(OFICIAL_DATABASE_NAME);

		this.requestServlet = mock(HttpServletRequest.class);
		this.responseServlet = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
		this.databaseConnection.adjustDatabaseSchemaName(TEST_DATABASE_NAME);
	}

}
