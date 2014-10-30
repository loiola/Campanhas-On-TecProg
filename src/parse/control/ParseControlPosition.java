package parse.control;

import model.beans.Position;
import model.dao.PositionDAO;
import parse.index.ParseIndex;

public class ParseControlPosition extends ParseControl<Position> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Position
	 */

	// Constructors
	public ParseControlPosition(ParseIndex<Position> indicesParse) {
		super(indicesParse, new PositionDAO());
	}

	/*
	 * This method instantiates an object of Class Position
	 * @return an instance of Class Position
	 */
	@Override
	public Position newInstance() {
		Position position = new Position();
		return position;
	}

	/*
	 * This method checks if two instances are equal Class Position
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Position objectOne, Position objectTwo) {
		// Variable to store the logical value of the check
		boolean comparisonResult;
				
		comparisonResult = objectOne.equals(objectTwo);
		return comparisonResult;
	}

}