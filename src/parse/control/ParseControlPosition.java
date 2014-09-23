package parse.control;

import model.beans.Position;
import model.dao.PositionDAO;
import parse.index.IndicesParse;

public class ParseControlPosition extends ParseControle<Position> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Position
	 */

	// Constructors
	public ParseControlPosition(IndicesParse<Position> indicesParse) {
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
	 * @param two instances of Class Position
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Position objectOne, Position objectTwo) {
		return objectOne.equals(objectTwo);
	}

}