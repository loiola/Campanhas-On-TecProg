package parse.controle;

import modelo.beans.Cargo;
import modelo.dao.CargoDAO;
import parse.indices.IndicesParse;

public class CargoParseControle extends ParseControle<Cargo> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Position
	 */

	// Constructors
	public CargoParseControle(IndicesParse<Cargo> indicesParse) {
		super(indicesParse, new CargoDAO());
	}

	/*
	 * This method instantiates an object of Class Position
	 * @return an instance of Class Position
	 */
	@Override
	public Cargo novaInstancia() {
		Cargo cargo = new Cargo();
		return cargo;
	}

	/*
	 * This method checks if two instances are equal Class Position
	 * @param two instances of Class Position
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Cargo objetoUm, Cargo objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}