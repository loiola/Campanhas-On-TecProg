package control;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Party;
import model.dao.PartyDAO;

public class PartyControl {
	
	/*
	 * Control class political party, which mediates the application layer with the model
	 */
	
	// Attributes
	private PartyDAO partyDAO;
	
	// Constructors	
	public PartyControl() {
		this.partyDAO = new PartyDAO();
	}
	
	// Other methods
	/*
	 * Method that requests a search of all political parties 
	 * @return an ArrayList with all political parties
	 */
	public ArrayList<Party> getListAllParties() throws SQLException {
		//Variable to store the retrieved political parties
		ArrayList<Party> partyList = new ArrayList<>();
		
		partyList = this.partyDAO.getObjectArrayListFromDatabase();
		return partyList;
	}
	
	/*
	 * Method that requests a search of all political parties with the abbreviation informed
	 * @param the abbreviation of the political party
	 * @return the political party with the abbreviation informed
	 */
	public Party getBySigla(String sigla) throws SQLException {
		//Variable to store the retrieved political party
		Party partyRecovered = new Party();
		
		partyRecovered = this.partyDAO.getPartyByAcronym(sigla);
		return partyRecovered;
	}
	
	/*
	 * Method that requests a search of all political parties with the political number informed
	 * @param the political number of the political party
	 * @return the political party with the political number informed
	 */
	public Party getByNumber(String number) throws SQLException {
		//Variable to store the retrieved political party
		Party partyRecovered = new Party();
		
		partyRecovered = this.partyDAO.getPartyByNumber(number);
		return partyRecovered;
	}
}
