package parse.controle;

import model.beans.Position;
import model.dao.PositionDAO;
import parse.indices.IndicesParse;

public class CargoParseControle extends ParseControle<Position> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Position
	 */

	// Constructors
	public CargoParseControle(IndicesParse<Position> indicesParse) {
		super(indicesParse, new PositionDAO());
	}

	/*
	 * This method instantiates an object of Class Position
	 * @return an instance of Class Position
	 */
	@Override
	public Position novaInstancia() {
		Position position = new Position();
		return position;
	}

	/*
	 * This method checks if two instances are equal Class Position
	 * @param two instances of Class Position
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Position objetoUm, Position objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}