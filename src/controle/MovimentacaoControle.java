package controle;

import java.util.ArrayList;
import java.util.List;

import model.beans.Campaign;
import model.beans.Expense;
import model.beans.Revenue;
import model.dao.ExpenseDAO;
import model.dao.RevenueDAO;

public class MovimentacaoControle {
	
	/*
	 * Control class handling financial, which mediates the application layer with the model
	 */

	// Attributes
	ExpenseDAO expenseDAO;
	RevenueDAO revenueDAO;
	
	// Constructors
	public MovimentacaoControle(){
		this.expenseDAO = new ExpenseDAO();
		this.revenueDAO = new RevenueDAO();
	}

	// Other methods
	/*
	 * Method that makes the application of a revenue requirement of a campaign 
	 * @param a campaign
	 * @return a List with revenues campaign informed
	 */
	public List<Revenue> getListaReceitas(Campaign campaign) throws Exception {

		ArrayList<Revenue> listaReceita = new ArrayList<>();
		
		if((campaign.getCampaignPosition().getPositionDescription().equals(Campaign.EMPTY_TYPE_STRING)) 
				|| (campaign.getCampaignYear().equals(Campaign.EMPTY_TYPE_INTEGER)) 
				|| (campaign.getCampaignCandidateNumber()).equals(Campaign.EMPTY_TYPE_INTEGER)
				|| (campaign.getCampaignCountryState()).equals(Campaign.EMPTY_TYPE_STRING)) {
			listaReceita =  null;
			
		} else {
			listaReceita = this.revenueDAO.getPorAnoNumeroCargoUf(campaign);
			
			if(campaign.getCampaignYear() == 2002) {
				for(Revenue revenue : listaReceita)
					revenue.setFinancialTransactionType("Revenue");
			}
		}
		return listaReceita;
	}

	/*
	 * Method that makes the request for an application of a campaign expense 
	 * @param a campaign
	 * @return a List with expenses campaign informed
	 */
	public List<Expense> getListaDespesas(Campaign campaign) throws Exception {
		
		ArrayList<Expense> listaDespesa = new ArrayList<>();
		
		if((campaign.getCampaignPosition().getPositionDescription().equals(Campaign.EMPTY_TYPE_STRING)) 
				|| (campaign.getCampaignYear().equals(Campaign.EMPTY_TYPE_INTEGER)) 
				|| (campaign.getCampaignCandidateNumber()).equals(Campaign.EMPTY_TYPE_INTEGER)
				|| (campaign.getCampaignCountryState()).equals(Campaign.EMPTY_TYPE_STRING)) {
			listaDespesa =  null;
		} else {
			listaDespesa =  this.expenseDAO.getExpenseByCampaignYearAndCandidateNumberAndCampaignCountryStateAndCampaignPosition(campaign);
		}
		return listaDespesa;
	}

	/*
	 * Method that makes a request of request for a recipe campaign by ID
	 * @param a ID
	 * @return the reported income
	 */
	public Revenue getReceitaPeloId(int id) throws Exception {
		return this.revenueDAO.getPeloId(id);
	}

	/*
	 * Method that makes a request of request for an expenditure campaign by ID 
	 * @param a ID
	 * @return the reported expense
	 */
	public Expense getDespesaPeloId(int id) throws Exception {
		return this.expenseDAO.getExpenseByIdentifier(id);
	}
}
