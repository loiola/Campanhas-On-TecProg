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
	 * Control class handling financial, which mediates the application layer
	 * with the model
	 */

	// Attributes
	ExpenseDAO expenseDAO;
	RevenueDAO revenueDAO;

	// Constants
	public static final int YEAR_2002 = 2002;
		
	// Constructors
	public TransactionControl() {
		this.expenseDAO = new ExpenseDAO();
		this.revenueDAO = new RevenueDAO();
	}
	
	// Other methods
	/*
	 * Method that checks if fields of revenue or expense is empty
	 * @param a campaign
	 * @return a boolean resulting from verification
	 */
	private boolean checkerTypeEmpty(final Campaign campaign){
		
		// Check equality of a list of revenue or expense 
		String positionDescriptionCampaign = campaign
				.getCampaignPosition().getPositionDescription();
		String countryStateCampaign = campaign.getCampaignCountryState();
		Integer numberCampaign = campaign.getCampaignCandidateNumber();
		Integer yearCampaign = campaign.getCampaignYear();
	
		// Compares the information accessed
		boolean comparisonResult = ((positionDescriptionCampaign.equals(Campaign.EMPTY_TYPE_STRING))
				|| (yearCampaign.equals(Campaign.EMPTY_TYPE_INTEGER))
				|| (numberCampaign.equals(Campaign.EMPTY_TYPE_INTEGER))
				|| (countryStateCampaign.equals(Campaign.EMPTY_TYPE_STRING)));		
		
		return comparisonResult;
	}

	/*
	 * Method that makes the application of a revenue requirement of a campaign
	 * @param a campaign
	 * @return a List with revenues campaign informed
	 */
	public List<Revenue> getListRevenue(Campaign campaign) throws Exception {

		// Checking if attributes are empty campaign
		ArrayList<Revenue> listRevenue = new ArrayList<>();
		boolean comparisonResult = checkerTypeEmpty(campaign);

		// In the case of empty being generated list is empty
		if(comparisonResult) {
			listRevenue = null;
		
		// Otherwise requested to list of revenue the attributes
		// Position, CountryState and Year of campaign
		} else {
			listRevenue = this.revenueDAO
					.getRevenueByCampaignPositionAndCampaignCountryStateAndCampaignYear(campaign);

			// If the revenue for 2002 changes to the list
			if(campaign.getCampaignYear() == YEAR_2002) {
				for(Revenue revenue : listRevenue) {
					revenue.setFinancialTransactionType("Revenue");
				}
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

		// Checking if attributes are empty campaign
		ArrayList<Expense> listExpense = new ArrayList<>();
		boolean comparisonResult = checkerTypeEmpty(campaign);

		// In the case of empty being generated list is empty
		if (comparisonResult) {
			listExpense = null;
		
		// Otherwise requested to list of expense the attributes
		// Position, CountryState, Number and Year of campaign
		} else {
			listExpense = this.expenseDAO
					.getExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition(campaign);
		}
		return listExpense;
	}

	/*
	 * Method that makes a request of request for a recipe campaign by ID
	 * @param a ID
	 * @return the reported income
	 */
	public Revenue getRevenueById(int id) throws Exception {

		// Auxiliary variable that returns a Revenue by ID
		Revenue auxiliaryReturn = this.revenueDAO.getRevenueByIdentifier(id);

		return auxiliaryReturn;
	}

	/*
	 * Method that makes a request of request for an expenditure campaign by ID
	 * @param a ID
	 * @return the reported expense
	 */
	public Expense getExpenseById(int id) throws Exception {
		
		// Auxiliary variable that returns a Expense by ID
		Expense auxiliaryReturn = this.expenseDAO.getExpenseByIdentifier(id);
		
		return auxiliaryReturn;
	}
}
