package model.beans;

public class Party {

	/*
	 * Model class Party.java This class is responsible for getting the
	 * Political Party's informations
	 */

	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final Integer EMPTY_TYPE_INTEGER = 0;

	// Attributes
	
	// Attribute that characterizes political party number. Domain: double numbers
	private Integer partyNumber;
	
	// Attribute that characterizes political party sigla 
	private String partyAcronym;
	
	// Attribute that characterizes political party concession
	private String partyConcession;
	
	// Attribute that characterizes complete name of political party
	private String partyName;

	// Empty constructors
	public Party() {
		this.partyName = EMPTY_TYPE_STRING;
		this.partyAcronym = EMPTY_TYPE_STRING;
		this.partyNumber = EMPTY_TYPE_INTEGER;
		this.partyConcession = EMPTY_TYPE_STRING;
	}

	// Getters and Setters
	public Integer getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(Integer partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getPartyAcronym() {
		return partyAcronym;
	}

	public void setPartyAcronym(String partyAcronym) {
		this.partyAcronym = partyAcronym;
	}

	public String getPartyConcession() {
		return partyConcession;
	}

	public void setPartyConcession(String partyConcession) {
		this.partyConcession = partyConcession;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/*
	 * This method verified if an object provided is an instance of political party
	 * @param an object for comparison 
	 * @return boolean with the result of comparison
	 */
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Party)) {
			return false;
		} else {

			Party otherParty = (Party) object;

			// Variable that stores the logic state of the comparison between
			// two parties
			boolean auxiliaryReturn = this.partyAcronym.equals(otherParty
					.getPartyAcronym());

			return auxiliaryReturn;
		}
	}
}
