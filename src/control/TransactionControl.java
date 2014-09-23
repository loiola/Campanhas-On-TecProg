package control;

import java.util.ArrayList;
import java.util.List;

import model.beans.Campaign;
import model.beans.Expense;
import model.beans.Revenue;
import model.dao.ExpenseDAO;
import model.dao.RevenueDAO;

public class TransactionControl {
	
	/*
	 * Control class handling financial, which mediates the application layer with the model
	 */

	// Attributes
	ExpenseDAO expenseDAO;
	RevenueDAO revenueDAO;
	
	// Constructors
	public TransactionControl(){
		this.expenseDAO = new ExpenseDAO();
		this.revenueDAO = new RevenueDAO();
	}

	// Other methods
	/*
	 * Method that makes the application of a revenue requirement of a campaign 
	 * @param a campaign
	 * @return a List with revenues campaign informed
	 */
	public List<Revenue> getListRevenue(Campaign campaign) throws Exception {

		ArrayList<Revenue> listRevenue = new ArrayList<>();
		
		if((campaign.getCampaignPosition().getPositionDescription().equals(Campaign.EMPTY_TYPE_STRING)) 
				|| (campaign.getCampaignYear().equals(Campaign.EMPTY_TYPE_INTEGER)) 
				|| (campaign.getCampaignCandidateNumber()).equals(Campaign.EMPTY_TYPE_INTEGER)
				|| (campaign.getCampaignCountryState()).equals(Campaign.EMPTY_TYPE_STRING)) {
			listRevenue =  null;
			
		} else {
			listRevenue = this.revenueDAO.getRevenueByCampaignPositionAndCampaignCountryStateAndCampaignYear(campaign);
			
			if(campaign.getCampaignYear() == 2002) {
				for(Revenue revenue : listRevenue)
					revenue.setFinancialTransactionType("Revenue");
			}
		}
		return listRevenue;
	}

	/*
	 * Method that makes the request for an application of a campaign expense 
	 * @param a campaign
	 * @return a List with expenses campaign informed
	 */
	public List<Expense> getListExpense(Campaign campaign) throws Exception {
		
		ArrayList<Expense> listExpense = new ArrayList<>();
		
		if((campaign.getCampaignPosition().getPositionDescription().equals(Campaign.EMPTY_TYPE_STRING)) 
				|| (campaign.getCampaignYear().equals(Campaign.EMPTY_TYPE_INTEGER)) 
				|| (campaign.getCampaignCandidateNumber()).equals(Campaign.EMPTY_TYPE_INTEGER)
				|| (campaign.getCampaignCountryState()).equals(Campaign.EMPTY_TYPE_STRING)) {
			listExpense =  null;
		} else {
			listExpense =  this.expenseDAO.getExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition(campaign);
		}
		return listExpense;
	}

	/*
	 * Method that makes a request of request for a recipe campaign by ID
	 * @param a ID
	 * @return the reported income
	 */
	public Revenue getRevenueById(int id) throws Exception {
		return this.revenueDAO.getRevenueByIdentifier(id);
	}

	/*
	 * Method that makes a request of request for an expenditure campaign by ID 
	 * @param a ID
	 * @return the reported expense
	 */
	public Expense getExpenseById(int id) throws Exception {
		return this.expenseDAO.getExpenseByIdentifier(id);
	}
}
