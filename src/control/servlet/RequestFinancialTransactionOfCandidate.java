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

public class RequestFinancialTransactionOfCandidate implements Logic {
	
	/*
	 * Servlet requests to control display search result list of financial transactions a candidate
	 */

	// Attributes
	private CampaignControl campaignControl;
	private Campaign searchCampaign;
	private Campaign campaign;
	private TransactionControl transactionControl;

	private String totalExpense;

	private float totalExpenseCalculatedValue;
	private float totalRevenueCalculatedValue;

	private List<Revenue> revenueList;
	private List<Expense> expenseList;

	private HttpServletRequest req;

	//Variables for paging in HTML
	private int firstRevenue;
	private int firstExpense;
	private int quantityRevenuePerPage;
	private int quantityExpensePerPage;
	private boolean seeAllRevenues;
	private boolean seeAllExpenses;
	private int revenueIndex;
	private int expenseIndex;
	private int quantityOfPPR;
	private int quantityOfPPD;
	private int centerOfRevenue;
	private int minimumRadiusRevenue;
	private int maximumRadiusRevenue;
	private int centerOfExpense;
	private int minimumRadiusExpense;
	private int maximumRadiusExpense;

	// Other methods
	/*
	 * Concretizing method that implements requests for display
	 * the result of the search list of financial transactions
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.req = req;
		
		// Call refactored methods
		receiveParameters();

		if(this.campaign == null) {
			
			// Returns an error page if the list is empty
			return "/error_nonexistent_candidate.jsp";
			
		} else {
			
			// Otherwise, call refactored methods
			setParameters();
			setPageRadiusRevenue();
			setPageRadiusExpense();
			prepareParametersTransmission();

			// And returns the page with the result of requests
			return "/visualize_transaction.jsp";
		}
	}

	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void receiveParameters() throws SQLException {
		this.searchCampaign = buildCampaign(this.req);
		this.totalRevenueCalculatedValue = Float.parseFloat(this.req.getParameter("totalRevenueCalculatedValue"));
		this.totalExpenseCalculatedValue = Float.parseFloat(this.req.getParameter("totalExpenseCalculatedValue"));
		this.firstRevenue = Integer.parseInt(this.req.getParameter("firstRevenue"));
		this.quantityRevenuePerPage = Integer.parseInt(this.req
				.getParameter("quantityRevenuePerPage"));
		this.seeAllRevenues = Boolean.parseBoolean(this.req
				.getParameter("seeAllRevenues"));
		this.centerOfRevenue = Integer.parseInt(this.req.getParameter("centerOfRevenue"));
		this.firstExpense = Integer.parseInt(this.req.getParameter("firstExpense"));
		this.quantityExpensePerPage = Integer.parseInt(this.req
				.getParameter("quantityExpensePerPage"));
		this.seeAllExpenses = Boolean.parseBoolean(this.req
				.getParameter("seeAllExpenses"));
		this.centerOfExpense = Integer.parseInt(this.req.getParameter("centerOfExpense"));
		this.campaignControl = new CampaignControl();
		this.campaign = this.campaignControl
				.getByYearNumberCodePositionAndUF(this.searchCampaign);
		this.transactionControl = new TransactionControl();
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws Exception {
		this.totalExpense = formatOfExpenses(this.campaign.getCampaignMaximumExpenseDeclared());
		this.revenueList = this.transactionControl
				.getListRevenue(this.campaign);
		this.expenseList = this.transactionControl
				.getListExpense(this.campaign);
		this.revenueIndex = generateIndexListOfRevenue(this.revenueList, this.quantityRevenuePerPage);
		this.quantityOfPPR = generateIndexPageOfRevenue(this.revenueList);
		this.expenseIndex = generateIndexListOfExpense(this.expenseList, this.quantityExpensePerPage);
		this.quantityOfPPD = generateIndexPageOfExpense(this.expenseList);
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.req.setAttribute("revenueList", this.revenueList);
		this.req.setAttribute("expenseList", this.expenseList);
		this.req.setAttribute("campaign", this.campaign);
		this.req.setAttribute("depesaTot", this.totalExpense);
		this.req.setAttribute("totalExpenseCalculatedValue", totalExpenseCalculatedValue);
		this.req.setAttribute("totalRevenueCalculatedValue", totalRevenueCalculatedValue);
		this.req.setAttribute("firstRevenue", this.firstRevenue);
		
		// Defines the number of pages is equal to the size of the candidate list
		if (this.seeAllRevenues) {
			this.quantityRevenuePerPage = this.revenueList.size();
		}
		this.req.setAttribute("quantityRevenuePerPage", this.quantityRevenuePerPage);
		this.req.setAttribute("revenueIndex", this.revenueIndex);
		this.req.setAttribute("quantityOfPPR", this.quantityOfPPR);
		this.req.setAttribute("firstExpense", this.firstExpense);
		
		// Defines the number of pages is equal to the size of the candidate list
		if (this.seeAllExpenses) {
			this.quantityExpensePerPage = this.expenseList.size();
		}
		this.req.setAttribute("quantityExpensePerPage", this.quantityExpensePerPage);
		this.req.setAttribute("expenseIndex", this.expenseIndex);
		this.req.setAttribute("quantityOfPPD", this.quantityOfPPD);
		this.req.setAttribute("centerOfRevenue", this.centerOfRevenue);
		this.req.setAttribute("minimumRadiusRevenue", this.minimumRadiusRevenue);
		this.req.setAttribute("maximumRadiusRevenue", this.maximumRadiusRevenue);
		this.req.setAttribute("centerOfExpense", this.centerOfExpense);
		this.req.setAttribute("minimumRadiusExpense", this.minimumRadiusExpense);
		this.req.setAttribute("maximumRadiusExpense", this.maximumRadiusExpense);
	}
	
	/*
	 * Generate indexes for list of receipt
	 * @param a list of receipt and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int generateIndexListOfRevenue(List<Revenue> list, int divider) {
		if(divider != 0) {
			int index = (int) Math.ceil((double) list.size()
					/ (double) divider);
			return index;
		} else {
			return 1;
		}
	}

	/*
	 * Generate index list for paging of receipt
	 * @param a list of receipt
	 * @return a number representing the index of paging
	 */
	private int generateIndexPageOfRevenue(List<Revenue> list) {
		int index = (int) Math.floor((double) list.size() / (double) 25);
		if(index >= 4 && index < 10)
			return 4;
		else if(index >= 10 && index < 20)
			return 5;
		else if(index >= 20 && index < 40)
			return 6;
		else if(index >= 40 && index < 80)
			return 7;
		else if(index >= 80)
			return 8;
		return index;
	}

	/*
	 * Generate indexes for list of expenses
	 * @param a list of expenses and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int generateIndexListOfExpense(List<Expense> list, int divider) {
		if(divider != 0) {
			int index = (int) Math.ceil((double) list.size()
					/ (double) divider);
			return index;
		} else {
			return 1;
		}
	}

	/*
	 * Generate index list for paging of expenses
	 * @param a list of expenses
	 * @return a number representing the index of paging
	 */
	private int generateIndexPageOfExpense(List<Expense> list) {
		int index = (int) Math.floor((double) list.size() / (double) 25);
		if(index >= 4 && index < 10)
			return 4;
		else if(index >= 10 && index < 20)
			return 5;
		else if(index >= 20 && index < 40)
			return 6;
		else if(index >= 40 && index < 80)
			return 7;
		else if(index >= 80)
			return 8;
		return index;
	}
	
	/*
	 * Logic for implementing paging of receipt
	 */
	private void setPageRadiusRevenue() {
		int counter = 0;
		if(this.revenueIndex > 9) {
			counter = 9;
		} else {
			counter = this.revenueIndex - 1;
		}
		
		int minimumRadius = this.centerOfRevenue;
		int maximumRadius = this.centerOfRevenue;
		this.minimumRadiusRevenue = 0;
		this.maximumRadiusRevenue = 0;
		while(counter != 0) {
			if(minimumRadius == 1)
				this.maximumRadiusRevenue++;
			else if(this.minimumRadiusRevenue < 5) {
				this.minimumRadiusRevenue++;
				minimumRadius--;
			} else if(maximumRadius == this.revenueIndex)
				this.minimumRadiusRevenue++;
			else {
				this.maximumRadiusRevenue++;
				maximumRadius++;
			}
			counter--;
		}
		this.maximumRadiusRevenue += this.centerOfRevenue;
		this.minimumRadiusRevenue = this.centerOfRevenue - this.minimumRadiusRevenue;
	}

	/*
	 * Logic for implementing paging of expenses
	 */
	private void setPageRadiusExpense() {
		int counter = 0;
		if(this.expenseIndex > 9) {
			counter = 9;
		} else {
			counter = this.expenseIndex - 1;
		}
		
		int minimumRadius = this.centerOfExpense;
		int maximumRadius = this.centerOfExpense;
		this.minimumRadiusExpense = 0;
		this.maximumRadiusExpense = 0;
		while(counter != 0) {
			if(minimumRadius == 1)
				this.maximumRadiusExpense++;
			else if(this.minimumRadiusExpense < 5) {
				this.minimumRadiusExpense++;
				minimumRadius--;
			} else if(maximumRadius == this.expenseIndex)
				this.minimumRadiusExpense++;
			else {
				this.maximumRadiusExpense++;
				maximumRadius++;
			}
			counter--;
		}
		this.maximumRadiusExpense += this.centerOfExpense;
		this.minimumRadiusExpense = this.centerOfExpense - this.minimumRadiusExpense;
	}
	
	/*
	 * Logic to implement the format of expenses
	 * @param an expenses
	 * @return a String with the correct format
	 */
	private String formatOfExpenses(Float expense) {
		DecimalFormatSymbols brazilianStandard = new DecimalFormatSymbols(
				Locale.GERMAN);

		DecimalFormat df = new DecimalFormat("###,###,##0.00", brazilianStandard);
		String totalExpense = df.format(expense);

		totalExpense = "R$ " + totalExpense;

		return totalExpense;
	}

	/*
	 * Set a political campaign according to the parameters requested by
	 * @param  an HTTP request
	 * @return a political campaign
	 */
	private Campaign buildCampaign(HttpServletRequest req) {

		int year = Integer.parseInt(req.getParameter("year"));
		int candidateNumber = Integer.parseInt(req.getParameter("candidateNumber"));
		int codeOfPosition = Integer.parseInt(req.getParameter("codeOfPosition"));
		String uf = req.getParameter("uf");

		Position position = new Position();
		position.setPositionCode(codeOfPosition);

		Campaign campaign = new Campaign();
		campaign.setCampaignCandidateNumber(candidateNumber);
		campaign.setCampaignYear(year);
		campaign.setCampaignPosition(position);
		campaign.setCampaignCountryState(uf);

		return campaign;
	}
}
