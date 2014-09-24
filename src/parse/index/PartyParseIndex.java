package parse.index;

import model.beans.Party;

public class PartyParseIndex extends ParseIndex<Party> {
	
	/*
	 * Class to control the contents of information inherent to the political parties
	 */

	// Attributes
	private int indexAcronym;
	private int indexNumberParty;
	private int indexDeferral;
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
		if(validIndex(this.indexAcronym)) {
			party.setPartyAcronym(field[this.indexAcronym]);
		}
		if(validIndex(this.indexNumberParty)) {
			party.setPartyNumber(Integer.parseInt(field[this.indexNumberParty]));
		}
		if(validIndex(this.indexDeferral)){
			party.setPartyConcession(field[this.indexDeferral]);
		}
		if(validIndex(this.indexPartyName)){
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