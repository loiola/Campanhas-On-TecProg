package control;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Campaign;
import model.beans.Candidate;
import model.dao.CampaignDAO;

public class CampaignControl {
	
	/*
	 * Control class political campaign, which mediates the application layer with the model
	 */

	// Attributes
	private CampaignDAO campaignDAO;

	// Constructors	
	public CampaignControl() {
		this.campaignDAO = new CampaignDAO();
	}
	
	// Other methods
	/*
	 * Method making the request of an applicant for voter registration 
	 * @param a candidate
	 * @return an ArrayList of candidates for voter registration
	 */
	public ArrayList<Campaign> getListaCampanhas(Candidate candidate) throws SQLException {
		return this.campaignDAO.getCampaignArrayDataByCandidateElectoralTitle(candidate);
	}
	
	/*
	 * Method making the request of a candidate by the abbreviation of the party and year
	 * @param the abbreviation of the party and year
	 * @return an ArrayLista the political party with the abbreviation of the party and year informed
	 */
	public ArrayList<Campaign> getListaCampanhasPorSiglaPartidoEAno(String sigla, String ano) throws SQLException{
		return this.campaignDAO.getCampaignArrayDataByPartyAcronymAndElectionYear(sigla,ano);
	}
	
	/*
	 * Method that the query requests a set of five candidate with highest value in the data reported
	 * @param the political cargo and year
	 * @return an ArrayLista with the highest values ​​for the position and informed year
	 */
	public ArrayList<Campaign> topFivePorCargoEAno(String cargo, Integer ano) throws SQLException{
		return this.campaignDAO.generateTopFiveCampaignListAboutMaximumExpenseDeclared(cargo, ano);
	}

	/*
	 * Method making the request of a candidate for the post code and UF informed
	 * @param the campaign of the desired candidate
	 * @return a campaign
	 */
	public Campaign getPeloAnoNumeroCodCargoEUf(Campaign campaign) throws SQLException {
		return this.campaignDAO.getCampaignDataByElectionYearAndCandidateNumberAndPositionCodeAndCountryState(campaign);
	}
}
