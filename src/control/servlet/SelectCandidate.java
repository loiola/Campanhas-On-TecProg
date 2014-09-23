package control.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Expense;
import model.beans.Revenue;
import control.CampaignControl;
import control.CandidateControl;
import control.TransactionControl;

public class SelectCandidate implements Logic {
	
	/*
	 * Servlet requests to control the selection of political candidates
	 */

	// Attributes
	private CandidateControl candidateControl;
	private Candidate candidate;
	private CampaignControl campaignControl;
	private List<Campaign> campaignList;

	private String electoralTitle;
	
	private TransactionControl transactionControl;
	private List<Revenue> revenueList;
	private List<Expense> expenseList;
	
	private float maximumExpenseIn2002;
	private float maximumExpenseIn2006;
	private float maximumExpenseIn2010;
	
	private float expenseCalculatedValueIn2002;
	private float revenueCalculatedValueIn2002;
	
	private float expenseCalculatedValueIn2006;
	private float revenueCalculatedValueIn2006;
	
	private float expenseCalculatedValueIn2010;
	private float revenueCalculatedValueIn2010;

	
	// Other methods
	/*
	 * Concretizing method that implements requests for selection of a
	 * candidate in all their years of election campaign 
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		this.maximumExpenseIn2002 = 0;
		this.maximumExpenseIn2006 = 0;
		this.maximumExpenseIn2010 = 0;
		
		this.electoralTitle = req.getParameter("electoralTitle");

		this.candidateControl = new CandidateControl();
		this.campaignControl = new CampaignControl();
		this.transactionControl = new TransactionControl();
		
		this.candidate = this.candidateControl.getACandidate(this.electoralTitle);

		this.campaignList = this.campaignControl.getListCampaign(this.candidate);
		
		// Loop to receive all the necessary information about their candidate campaigns
		for(Campaign campaign : campaignList) {
			
			// Condition for receiving data from the 2002 campaign if there
			if(campaign.getCampaignYear() == 2002) {
				this.revenueList = 
						this.transactionControl.getListRevenue(campaign);
				this.expenseList = 
						this.transactionControl.getListExpense(campaign);
				this.maximumExpenseIn2002 = campaign.getCampaignMaximumExpenseDeclared();
				for(Revenue revenue : revenueList)
					this.revenueCalculatedValueIn2002 += revenue.getFinancialTransactionPrice();
				for(Expense expense : expenseList)
					this.expenseCalculatedValueIn2002 += expense.getFinancialTransactionPrice();
				
				campaign.setCampaignTotalExpenseCalculated(this.expenseCalculatedValueIn2002);
				campaign.setCampaignTotalRevenueCalculated(this.revenueCalculatedValueIn2002);
			}
			
			// Condition for receiving data from the 2006 campaign if there
			else if(campaign.getCampaignYear() == 2006) {
				this.revenueList = 
						this.transactionControl.getListRevenue(campaign);
				this.expenseList = 
						this.transactionControl.getListExpense(campaign);
				this.maximumExpenseIn2006 = campaign.getCampaignMaximumExpenseDeclared();
				for(Revenue revenue : revenueList)
					this.revenueCalculatedValueIn2006 += revenue.getFinancialTransactionPrice();
				for(Expense expense : expenseList)
					this.expenseCalculatedValueIn2006 += expense.getFinancialTransactionPrice();

				campaign.setCampaignTotalExpenseCalculated(this.expenseCalculatedValueIn2006);
				campaign.setCampaignTotalRevenueCalculated(this.revenueCalculatedValueIn2006);
			}
			
			// Condition for receiving data from the 2010 campaign if there
			else if(campaign.getCampaignYear() == 2010) {
				this.revenueList = 
						this.transactionControl.getListRevenue(campaign);
				this.expenseList = 
						this.transactionControl.getListExpense(campaign);
				this.maximumExpenseIn2010 = campaign.getCampaignMaximumExpenseDeclared();
				for(Revenue revenue : revenueList)
					this.revenueCalculatedValueIn2010 += revenue.getFinancialTransactionPrice();
				for(Expense expense : expenseList)
					this.expenseCalculatedValueIn2010 += expense.getFinancialTransactionPrice();
			
				campaign.setCampaignTotalExpenseCalculated(this.expenseCalculatedValueIn2010);
				campaign.setCampaignTotalRevenueCalculated(this.revenueCalculatedValueIn2010);
			}
		}
		
		// Set of answers to requests made concerning the applicant requested
		req.setAttribute("candidate", this.candidate);
		req.setAttribute("campaigns", this.campaignList);
		req.setAttribute("candidate", this.candidate);
		req.setAttribute("campaigns", this.campaignList);
		req.setAttribute("expense1", this.maximumExpenseIn2002);
		req.setAttribute("expense2", this.maximumExpenseIn2006);
		req.setAttribute("expense3", this.maximumExpenseIn2010);
		req.setAttribute("expense01", this.expenseCalculatedValueIn2002);
		req.setAttribute("revenue1", this.revenueCalculatedValueIn2002);
		req.setAttribute("expense02", this.expenseCalculatedValueIn2006);
		req.setAttribute("revenue2", this.revenueCalculatedValueIn2006);
		req.setAttribute("expense03", this.expenseCalculatedValueIn2010);
		req.setAttribute("revenue3", this.revenueCalculatedValueIn2010);
		
		// Return the HTML page with the requested information
		return "/visualize_candidate.jsp";
	}
}
