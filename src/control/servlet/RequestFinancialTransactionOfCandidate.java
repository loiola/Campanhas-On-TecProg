package control.servlet;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Campaign;
import model.beans.Expense;
import model.beans.Position;
import model.beans.Revenue;
import control.CampaignControl;
import control.TransactionControl;
import control.servlet.basic.Logic;
import control.servlet.listpage.ListPaginationLogic;

public class RequestFinancialTransactionOfCandidate implements Logic {

	/*
	 * Servlet requests to control display search result list of financial
	 * transactions a candidate
	 */

	// Attributes

	// Attribute that characterizes an instance of campaign's control
	private CampaignControl campaignControl;

	// Attribute that characterizes a search of candidate's campaign
	private Campaign searchCampaign;

	// Attribute that characterizes a candidate's campaign
	private Campaign campaign;

	// Attribute that characterizes a financial transaction of candidate's
	// campaign
	private TransactionControl transactionControl;

	// Attribute that characterizes total expense of candidate's campaign
	private String totalExpense;

	// Attribute that characterizes maximum expense calculated of candidate's
	// campaign
	private float totalExpenseCalculatedValue;

	// Attribute that characterizes maximum revenue calculated of candidate's
	// campaign
	private float totalRevenueCalculatedValue;

	// Attribute that characterizes list of expense of candidate's campaign
	private List<Expense> expenseList;

	// Attribute that characterizes list of revenue of candidate's campaign
	private List<Revenue> revenueList;

	// Attribute that characterizes a request of servlet
	private HttpServletRequest servletRequest;

	// Other methods
	/*
	 * Concretizing method that implements requests for display the result of
	 * the search list of financial transactions
	 * 
	 * @param an HTTP request and HTTP response
	 * 
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest requestServlet,
			HttpServletResponse responseServlet) throws Exception {

		this.servletRequest = requestServlet;

		receiveParameters();

		if (this.campaign == null) {

			// Returns an error page if the list is empty
			return "/error_nonexistent_candidate.jsp";

		} else {

			// Otherwise, call refactored methods
			setParameters();
			prepareParametersTransmission();

			// And returns the page with the result of requests
			return "/visualize_transaction.jsp";
		}
	}

	/*
	 * Receive methods for the parameters of the request
	 */
	private void receiveParameters() throws SQLException {
		this.searchCampaign = buildCampaign(this.servletRequest);
		this.totalRevenueCalculatedValue = Float.parseFloat(this.servletRequest
				.getParameter("totalRevenueCalculatedValue"));
		this.totalExpenseCalculatedValue = Float.parseFloat(this.servletRequest
				.getParameter("totalExpenseCalculatedValue"));
		this.campaignControl = new CampaignControl();
		this.campaign = this.campaignControl
				.getByYearNumberCodePositionAndUF(this.searchCampaign);
		this.transactionControl = new TransactionControl();
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws Exception {
		this.totalExpense = formatOfExpenses(this.campaign
				.getCampaignMaximumExpenseDeclared());
		this.revenueList = this.transactionControl
				.getListRevenue(this.campaign);
		this.expenseList = this.transactionControl
				.getListExpense(this.campaign);

		final String REVENUE_PAGINATION_NAME = "revenue_"
				+ ListPaginationLogic.STANDARD_EXPECTED_PAGINATION_NAME;
		this.servletRequest = ListPaginationLogic.updatePaginationList(
				servletRequest, revenueList.size(), REVENUE_PAGINATION_NAME);
		final String EXPENSE_PAGINATION_NAME = "expense_"
				+ ListPaginationLogic.STANDARD_EXPECTED_PAGINATION_NAME;
		this.servletRequest = ListPaginationLogic.updatePaginationList(
				servletRequest, expenseList.size(), EXPENSE_PAGINATION_NAME);
	}

	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.servletRequest.setAttribute("revenueList", this.revenueList);
		this.servletRequest.setAttribute("expenseList", this.expenseList);
		this.servletRequest.setAttribute("campaign", this.campaign);
		this.servletRequest.setAttribute("totalExpense", this.totalExpense);
		this.servletRequest.setAttribute("totalExpenseCalculatedValue",
				totalExpenseCalculatedValue);
		this.servletRequest.setAttribute("totalRevenueCalculatedValue",
				totalRevenueCalculatedValue);
	}

	/*
	 * Logic to implement the format of expenses
	 * 
	 * @param an expenses
	 * 
	 * @return a String with the correct format
	 */
	private String formatOfExpenses(Float expense) {
		DecimalFormatSymbols brazilianStandard = new DecimalFormatSymbols(
				Locale.GERMAN);

		DecimalFormat df = new DecimalFormat("###,###,##0.00",
				brazilianStandard);
		String totalExpense = df.format(expense);

		totalExpense = "R$ " + totalExpense;

		return totalExpense;
	}

	/*
	 * Set a political campaign according to the parameters requested by
	 * 
	 * @param an HTTP request
	 * 
	 * @return a political campaign
	 */
	private Campaign buildCampaign(HttpServletRequest requestServlet) {

		int electionYear = Integer.parseInt(requestServlet
				.getParameter("electionYear"));
		int candidateNumber = Integer.parseInt(requestServlet
				.getParameter("candidateNumber"));
		int codeOfPosition = Integer.parseInt(requestServlet
				.getParameter("codeOfPosition"));
		String countryState = requestServlet.getParameter("countryState");

		Position position = new Position();
		position.setPositionCode(codeOfPosition);

		Campaign campaign = new Campaign();
		campaign.setCampaignCandidateNumber(candidateNumber);
		campaign.setCampaignYear(electionYear);
		campaign.setCampaignPosition(position);
		campaign.setCampaignCountryState(countryState);

		return campaign;
	}
}
