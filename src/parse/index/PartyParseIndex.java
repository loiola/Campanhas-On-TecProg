package parse.index;

import model.beans.Party;

public class PartyParseIndex extends ParseIndex<Party> {
	
	/*
	 * Class to control the contents of information inherent to the political parties
	 */

	// Attributes
	
	// Variable to the index of acronym party
	private int indexAcronym;
	
	// Variable to the index number of the party
	private int indexNumberParty;
	
	// Variable to the index of the differing party (date of foundation of the party)
	private int indexDeferral;
	
	// Variable to the index official name of the party
	private int indexPartyName;
	
	// Constructors
	public PartyParseIndex() {
		super();
		this.indexAcronym = INVALID_INDEX;
		this.indexNumberParty = INVALID_INDEX;
		this.indexDeferral = INVALID_INDEX;
		this.indexPartyName = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the political parties in the file
	 * @param an instance of the Class Party
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Party party, String field[]) {
		//Variable to store the result of index validation
		boolean validationResult;
		
		validationResult = validIndex(this.indexAcronym);
		if(validationResult) {
			party.setPartyAcronym(field[this.indexAcronym]);
		}
		
		validationResult = validIndex(this.indexNumberParty);
		if(validationResult) {
			party.setPartyNumber(Integer.parseInt(field[this.indexNumberParty]));
		}
		
		validationResult = validIndex(this.indexDeferral);
		if(validationResult) {
			party.setPartyConcession(field[this.indexDeferral]);
		}
		
		validationResult = validIndex(this.indexPartyName);
		if(validationResult) {
			party.setPartyName(field[this.indexPartyName]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Party
	 */
	@Override
	protected void setEmptyInAllSetters(Party party) {
		party.setPartyAcronym(Party.EMPTY_TYPE_STRING);
		party.setPartyNumber(Party.EMPTY_TYPE_INTEGER);
		party.setPartyName(Party.EMPTY_TYPE_STRING);
		party.setPartyConcession(Party.EMPTY_TYPE_STRING);
	}
	
	// Mutators for indexes of the array of fields
	public void setIndexAcronym(int indexAcronym) {
		this.indexAcronym = indexAcronym;
	}

	public void setIndexNumberParty(int indexNumberParty) {
		this.indexNumberParty = indexNumberParty;
	}

	public void setIndexDeferral(int indexDeferral) {
		this.indexDeferral = indexDeferral;
	}

	public void setIndexPartyName(int indexPartyName) {
		this.indexPartyName = indexPartyName;
	}

}