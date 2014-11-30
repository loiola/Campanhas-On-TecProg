package model.beans;

public class Position {

	/*
	 * Model Class Position.java This class is responsible for getting the
	 * Position's informations
	 */

	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final int EMPTY_TYPE_INTEGER = 0;

	// Attributes
	
	// Attribute that characterizes code of position 
	private Integer positionCode;
	
	// Attribute that characterizes description of position
	private String positionDescription;

	// Empty Constructor
	public Position() {
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

	/*
	 * This method verified if an object provided is an instance of position
	 * @param an object for comparison 
	 * @return boolean with the result of comparison
	 */
	@Override
	public boolean equals(Object object) {
		
		// Variable that is not instance of a Position
		boolean notInstanceOfObject = (!(object instanceof Position));
		
		if(notInstanceOfObject) {
			return false;
		} else {

			Position otherPosition = (Position) object;

			// Variable that stores the logic state of the comparison between
			// two positions
			boolean auxiliaryReturn = this.positionDescription
					.equalsIgnoreCase(otherPosition.getPositionDescription());

			return auxiliaryReturn;
		}
	}
}