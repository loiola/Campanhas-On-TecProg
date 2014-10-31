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

public class RequestFinancialTransactionOfCandidate implements Logic {
	
	/*
	 * Servlet requests to control display search result list of financial transactions a candidate
	 */

	// Attributes
	
	// Attribute that characterizes an instance of campaign's control
	private CampaignControl campaignControl;
	
	// Attribute that characterizes a search of candidate's campaign
	private Campaign searchCampaign;
	
	// Attribute that characterizes a candidate's campaign
	private Campaign campaign;
	
	// Attribute that characterizes a financial transaction of candidate's campaign
	private TransactionControl transactionControl;

	// Attribute that characterizes total expense of candidate's campaign
	private String totalExpense;

	// Attribute that characterizes maximum expense calculated of candidate's campaign
	private float totalExpenseCalculatedValue;
	
	// Attribute that characterizes maximum revenue calculated of candidate's campaign
	private float totalRevenueCalculatedValue;
	
	// Attribute that characterizes list of expense of candidate's campaign
	private List<Expense> expenseList;
		
	// Attribute that characterizes list of revenue of candidate's campaign
	private List<Revenue> revenueList;

	// Attribute that characterizes a request of servlet 
	private HttpServletRequest requestServlet;

	//Variables for paging in HTML
	
	// Variable that characterizes the first expense shows in page
	private int firstExpense;
	
	// Variable that characterizes the first revenue shows in page
	private int firstRevenue;
	
	// Variable that characterizes the quantity of expense shows per page
	private int quantityExpensePerPage;
	
	// Variable that characterizes that quantity of revenue shows per page
	private int quantityRevenuePerPage;
	
	// Variable that characterizes all expenses shows per page 
	private boolean seeAllExpenses;
	
	// Variable that characterizes all revenues shows per page
	private boolean seeAllRevenues;
	
	// Variable that characterizes expense index
	private int expenseIndex;
	
	// Variable that characterizes revenue index
	private int revenueIndex;

	// Variable that characterizes numbers of links for shows the quantity of expenses per page 
	private int quantityOfPPD;
	
	// Variable that characterizes numbers of links for shows the quantity of revenues per page
	private int quantityOfPPR;
	
	// Variable that characterizes median of number present in the variable quantityOfPPD 
	private int centerOfExpense;

	// Variable that characterizes median of number present in the variable quantityOfPPR
	private int centerOfRevenue;
	
	// Variable that characterizes number minimum displayed variable quantityOfPPD  
	private int minimumRadiusExpense;
	
	// Variable that characterizes number minimum displayed variable quantityOfPPR
	private int minimumRadiusRevenue;
	
	// Variable that characterizes number maximum displayed variable quantityOfPPD
	private int maximumRadiusExpense;
	
	// Variable that characterizes number maximum displayed variable quantityOfPPR
	private int maximumRadiusRevenue;
	

	// Other methods
	/*
	 * Concretizing method that implements requests for display
	 * the result of the search list of financial transactions
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest requestServlet, HttpServletResponse responseServlet)
			throws Exception {

		this.requestServlet = requestServlet;
		
		
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
	 * Receive methods for the parameters of the request
	 */
	private void receiveParameters() throws SQLException {
		this.searchCampaign = buildCampaign(this.requestServlet);
		this.totalRevenueCalculatedValue = Float.parseFloat(this.requestServlet.getParameter("totalRevenueCalculatedValue"));
		this.totalExpenseCalculatedValue = Float.parseFloat(this.requestServlet.getParameter("totalExpenseCalculatedValue"));
		this.firstRevenue = Integer.parseInt(this.requestServlet.getParameter("firstRevenue"));
		this.quantityRevenuePerPage = Integer.parseInt(this.requestServlet
				.getParameter("quantityRevenuePerPage"));
		this.seeAllRevenues = Boolean.parseBoolean(this.requestServlet
				.getParameter("seeAllRevenues"));
		this.centerOfRevenue = Integer.parseInt(this.requestServlet.getParameter("centerOfRevenue"));
		this.firstExpense = Integer.parseInt(this.requestServlet.getParameter("firstExpense"));
		this.quantityExpensePerPage = Integer.parseInt(this.requestServlet
				.getParameter("quantityExpensePerPage"));
		this.seeAllExpenses = Boolean.parseBoolean(this.requestServlet
				.getParameter("seeAllExpenses"));
		this.centerOfExpense = Integer.parseInt(this.requestServlet.getParameter("centerOfExpense"));
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
		this.requestServlet.setAttribute("revenueList", this.revenueList);
		this.requestServlet.setAttribute("expenseList", this.expenseList);
		this.requestServlet.setAttribute("campaign", this.campaign);
		this.requestServlet.setAttribute("totalExpense", this.totalExpense);
		this.requestServlet.setAttribute("totalExpenseCalculatedValue", totalExpenseCalculatedValue);
		this.requestServlet.setAttribute("totalRevenueCalculatedValue", totalRevenueCalculatedValue);
		this.requestServlet.setAttribute("firstRevenue", this.firstRevenue);
		
		// Defines the number of pages is equal to the size of the candidate list
		if (this.seeAllRevenues) {
			this.quantityRevenuePerPage = this.revenueList.size();
		}
		this.requestServlet.setAttribute("quantityRevenuePerPage", this.quantityRevenuePerPage);
		this.requestServlet.setAttribute("revenueIndex", this.revenueIndex);
		this.requestServlet.setAttribute("quantityOfPPR", this.quantityOfPPR);
		this.requestServlet.setAttribute("firstExpense", this.firstExpense);
		
		// Defines the number of pages is equal to the size of the candidate list
		if (this.seeAllExpenses) {
			this.quantityExpensePerPage = this.expenseList.size();
		}
		this.requestServlet.setAttribute("quantityExpensePerPage", this.quantityExpensePerPage);
		this.requestServlet.setAttribute("expenseIndex", this.expenseIndex);
		this.requestServlet.setAttribute("quantityOfPPD", this.quantityOfPPD);
		this.requestServlet.setAttribute("centerOfRevenue", this.centerOfRevenue);
		this.requestServlet.setAttribute("minimumRadiusRevenue", this.minimumRadiusRevenue);
		this.requestServlet.setAttribute("maximumRadiusRevenue", this.maximumRadiusRevenue);
		this.requestServlet.setAttribute("centerOfExpense", this.centerOfExpense);
		this.requestServlet.setAttribute("minimumRadiusExpense", this.minimumRadiusExpense);
		this.requestServlet.setAttribute("maximumRadiusExpense", this.maximumRadiusExpense);
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
		
		if(index >= 4 && index < 10) {
			return 4;
		} else if(index >= 10 && index < 20) {
			return 5;
		} else if(index >= 20 && index < 40) {
			return 6;
		} else if(index >= 40 && index < 80) {
			return 7;
		} else if(index >= 80) {
			return 8;
		}
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
		
		if(index >= 4 && index < 10) {
			return 4;
		} else if(index >= 10 && index < 20) {
			return 5;
		} else if(index >= 20 && index < 40) {
			return 6;
		} else if(index >= 40 && index < 80) {
			return 7;
		} else if(index >= 80) {
			return 8;
		}
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
			if(minimumRadius == 1) {
				this.maximumRadiusRevenue++;
			} else if(this.minimumRadiusRevenue < 5) {
				this.minimumRadiusRevenue++;
				minimumRadius--;
			} else if(maximumRadius == this.revenueIndex) {
				this.minimumRadiusRevenue++;
			} else {
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
			} else if(maximumRadius == this.expenseIndex) {
				this.minimumRadiusExpense++;
			} else {
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
	private Campaign buildCampaign(HttpServletRequest requestServlet) {

		int electionYear = Integer.parseInt(requestServlet.getParameter("electionYear"));
		int candidateNumber = Integer.parseInt(requestServlet.getParameter("candidateNumber"));
		int codeOfPosition = Integer.parseInt(requestServlet.getParameter("codeOfPosition"));
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
