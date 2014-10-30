package parse.index;

import model.beans.Position;

public class PositionParseIndex extends ParseIndex<Position> {
	
	/*
	 * Class to control the contents of information inherent to the positions
	 */
	
	// Attributes
	
	// Variable to the index of the position code
	private int indexCode;
	
	// Variable to the index of the position description
	private int indexDescription;

	// Constructors
	public PositionParseIndex() {
		super();
		this.indexCode = INVALID_INDEX;
		this.indexDescription = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the positions in the file
	 * @param an instance of Class Position
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Position position, String field[]) {
		//Variable to store the result of index validation
		boolean validationResult;
		
		validationResult = validIndex(this.indexCode);
		if(validationResult) {
			position.setPositionCode(Integer.parseInt(field[this.indexCode]));
		}
		
		validationResult = validIndex(this.indexDescription);
		if(validationResult) {
			position.setPositionDescription(field[this.indexDescription]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Position
	 */
	@Override
	protected void setEmptyInAllSetters(Position position) {
		position.setPositionCode(Position.EMPTY_TYPE_INTEGER);
		position.setPositionDescription(Position.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndexCode(int indexCode) {
		this.indexCode = indexCode;
	}

	public void setIndexDescription(int indexDescription) {
		this.indexDescription = indexDescription;
	}

}