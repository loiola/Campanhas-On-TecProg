package model.beans;

public class Party {
	
	/*
	 * Model class Party.java
	 * This class is responsable for getting the Political Party's informations
	 */ 
	
	// Constants 
	public static final String EMPTY_TYPE_STRING = "";
	public static final Integer EMPTY_TYPE_INTEGER = 0;

	// Attributes
	private Integer partyNumber;
	private String partyAcronym;
	private String partyConcession;
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
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Party))
			return false;
		
		Party otherParty = (Party) object;
		
		//Variable that stores the logic state of the comparison between two parties
		boolean auxiliaryReturn = this.partyAcronym.equals(otherParty.getPartyAcronym());
		
		return auxiliaryReturn;
	}
}
