package control.validation;

import control.exception.PartyException;
import model.beans.Party;

public class PartyValidation {
	
	/*
	 * Validation class electoral party, which mediates the application layer with the model
	 */

	// Attributes
	private static final String EMPTY_SIGLA = "Party abbreviation field empty!";
	private static final String EMPTY_NUMBER_PARTY = "Party number field empty!";

	// Constructors
	public PartyValidation() {

	}

	// Other methods
	/*
	 * Method that checks if the abbreviation entered is zero and returns a positive error case 
	 * @param a political party
	 */
	public void siglaNotNull(Party party) throws PartyException {
		if((party.getPartyAcronym() == null)) {
			throw new PartyException(EMPTY_SIGLA);
		}
	}
	
	/*
	 * Method that checks if the entered number is null and returns a positive error if
	 * @param a political party
	 */
	public void numberNotNull(Party party) throws PartyException {
		if((party.getPartyNumber() == null)) {
			throw new PartyException(EMPTY_NUMBER_PARTY);
		}
	}
}
