package controle;

import java.util.ArrayList;
import java.util.List;
import modelo.beans.Campaign;
import modelo.beans.Expense;
import modelo.beans.Revenue;
import modelo.dao.DespesaDAO;
import modelo.dao.ReceitaDAO;

public class MovimentacaoControle {
	
	/*
	 * Control class handling financial, which mediates the application layer with the model
	 */

	// Attributes
	DespesaDAO despesaDAO;
	ReceitaDAO receitaDAO;
	
	// Constructors
	public MovimentacaoControle(){
		this.despesaDAO = new DespesaDAO();
		this.receitaDAO = new ReceitaDAO();
	}

	// Other methods
	/*
	 * Method that makes the application of a revenue requirement of a campaign 
	 * @param a campaign
	 * @return a List with revenues campaign informed
	 */
	public List<Revenue> getListaReceitas(Campaign campaign) throws Exception {

		ArrayList<Revenue> listaReceita = new ArrayList<>();
		
		if((campaign.getCampaignPosition().getDescricao().equals(Campaign.EMPTY_TYPE_STRING)) 
				|| (campaign.getCampaignYear().equals(Campaign.EMPTY_TYPE_INTEGER)) 
				|| (campaign.getCampaignCandidateNumber()).equals(Campaign.EMPTY_TYPE_INTEGER)
				|| (campaign.getCampaignCountryState()).equals(Campaign.EMPTY_TYPE_STRING)) {
			listaReceita =  null;
			
		} else {
			listaReceita = this.receitaDAO.getPorAnoNumeroCargoUf(campaign);
			
			if(campaign.getCampaignYear() == 2002) {
				for(Revenue revenue : listaReceita)
					revenue.setTipoMovimentacao("Revenue");
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
		
		if((campaign.getCampaignPosition().getDescricao().equals(Campaign.EMPTY_TYPE_STRING)) 
				|| (campaign.getCampaignYear().equals(Campaign.EMPTY_TYPE_INTEGER)) 
				|| (campaign.getCampaignCandidateNumber()).equals(Campaign.EMPTY_TYPE_INTEGER)
				|| (campaign.getCampaignCountryState()).equals(Campaign.EMPTY_TYPE_STRING)) {
			listaDespesa =  null;
		} else {
			listaDespesa =  this.despesaDAO.getPorAnoNumeroCargoUf(campaign);
		}
		return listaDespesa;
	}

	/*
	 * Method that makes a request of request for a recipe campaign by ID
	 * @param a ID
	 * @return the reported income
	 */
	public Revenue getReceitaPeloId(int id) throws Exception {
		return this.receitaDAO.getPeloId(id);
	}

	/*
	 * Method that makes a request of request for an expenditure campaign by ID 
	 * @param a ID
	 * @return the reported expense
	 */
	public Expense getDespesaPeloId(int id) throws Exception {
		return this.despesaDAO.getPeloId(id);
	}
}
