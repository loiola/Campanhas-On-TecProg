package control;

import java.util.ArrayList;
import java.util.List;

import model.beans.Campaign;
import model.beans.Expense;
import model.beans.Party;
import model.beans.Revenue;
import model.dao.ExpenseDAO;
import model.dao.RevenueDAO;

public class TransactionControl {

	/*
	 * Control class handling financial, which mediates the application layer
	 * with the model
	 */

	// Attributes
	ExpenseDAO expenseDAO;
	RevenueDAO revenueDAO;

	// Constructors
	public TransactionControl() {
		this.expenseDAO = new ExpenseDAO();
		this.revenueDAO = new RevenueDAO();
	}

	// Other methods
	/*
	 * Method that makes the application of a revenue requirement of a campaign
	 * 
	 * @param a campaign
	 * 
	 * @return a List with revenues campaign informed
	 */
	public List<Revenue> getListRevenue(Campaign campaign) throws Exception {

		ArrayList<Revenue> listRevenue = new ArrayList<>();

		// Check equality of a list of revenue
		// Access some information from list
		String positionDescriptionRevenueCampaign = campaign
				.getCampaignPosition().getPositionDescription();
		String countryStateRevenueCampaign = campaign.getCampaignCountryState();
		Integer numberRevenueCampaign = campaign.getCampaignCandidateNumber();
		Integer yearRevenueCampaign = campaign.getCampaignYear();

		// Compares the information accessed
		boolean comparisonResult = ((positionDescriptionRevenueCampaign
				.equals(Campaign.EMPTY_TYPE_STRING))
				|| (yearRevenueCampaign.equals(Campaign.EMPTY_TYPE_INTEGER))
				|| (numberRevenueCampaign.equals(Campaign.EMPTY_TYPE_INTEGER)) || (countryStateRevenueCampaign
				.equals(Campaign.EMPTY_TYPE_STRING)));

		if (comparisonResult) {
			listRevenue = null;

		} else {
			listRevenue = this.revenueDAO
					.getRevenueByCampaignPositionAndCampaignCountryStateAndCampaignYear(campaign);

			if (campaign.getCampaignYear() == 2002) {
				for (Revenue revenue : listRevenue)
					revenue.setFinancialTransactionType("Revenue");
			}
		}
		return listRevenue;
	}

	/*
	 * Method that makes the request for an application of a campaign expense
	 * 
	 * @param a campaign
	 * 
	 * @return a List with expenses campaign informed
	 */
	public List<Expense> getListExpense(Campaign campaign) throws Exception {

		ArrayList<Expense> listExpense = new ArrayList<>();

		// Check equality of a list of expense
		// Access some information from list
		String positionDescriptioneExpenseCampaign = campaign
				.getCampaignPosition().getPositionDescription();
		String countryStateExpenseCampaign = campaign.getCampaignCountryState();
		Integer numberExpenseCampaign = campaign.getCampaignCandidateNumber();
		Integer yearExpenseCampaign = campaign.getCampaignYear();

		// Compares the information accessed
		boolean comparisonResult = ((positionDescriptioneExpenseCampaign
				.equals(Campaign.EMPTY_TYPE_STRING))
				|| (yearExpenseCampaign.equals(Campaign.EMPTY_TYPE_INTEGER))
				|| (numberExpenseCampaign.equals(Campaign.EMPTY_TYPE_INTEGER)) || (countryStateExpenseCampaign
				.equals(Campaign.EMPTY_TYPE_STRING)));

		if (comparisonResult) {
			listExpense = null;
		} else {
			listExpense = this.expenseDAO
					.getExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition(campaign);
		}
		return listExpense;
	}

	/*
	 * Method that makes a request of request for a recipe campaign by ID
	 * 
	 * @param a ID
	 * 
	 * @return the reported income
	 */
	public Revenue getRevenueById(int id) throws Exception {

		// Auxiliary variable that returns a Revenue by ID
		Revenue auxiliaryReturn = this.revenueDAO.getRevenueByIdentifier(id);

		return auxiliaryReturn;

	}

	/*
	 * Method that makes a request of request for an expenditure campaign by ID
	 * 
	 * @param a ID
	 * 
	 * @return the reported expense
	 */
	public Expense getExpenseById(int id) throws Exception {
		
		// Auxiliary variable that returns a Expense by ID
		Expense auxiliaryReturn = this.expenseDAO.getExpenseByIdentifier(id);
		
		return auxiliaryReturn;
	}
}
