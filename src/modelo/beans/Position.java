package modelo.beans;


public class Position {
	
	/*
	 * Model Class Position.java
	 * This class is responsable for getting the Position's informations
	 */
	
	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final int EMPTY_TYPE_INTEGER = 0;
	
	// Attributes
	private Integer positionCode;
	private String positionDescription;
	
	// Empty Constructor
	public Position(){
		this.positionCode = EMPTY_TYPE_INTEGER;
		this.positionDescription = EMPTY_TYPE_STRING;
	}
	
	// Getters and Setters
	public Integer getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(int positionCode) {
		this.positionCode = positionCode;
	}
	public String getPositionDescription() {
		return positionDescription;
	}
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Position))
			return false;
		
		Position otherPosition = (Position) object;
		return this.positionDescription.equalsIgnoreCase(otherPosition.getPositionDescription());
	}
}
