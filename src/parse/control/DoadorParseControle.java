package parse.control;

import model.beans.Donor;
import model.dao.DonorDAO;
import parse.indices.IndicesParse;

public class DoadorParseControle extends ParseControle<Donor> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Donor
	 */

	// Constructors
	public DoadorParseControle(IndicesParse<Donor> indicesParse) {
		super(indicesParse, new DonorDAO());
	}

	/*
	 * This method instantiates an object of Class Donor
	 * @return an instance of Class Donor
	 */
	@Override
	public Donor novaInstancia() {
		Donor donor = new Donor();
		return donor;
	}

	/*
	 * This method checks if two instances are equal Class Donor
	 * @param two instances of Class Donor
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Donor objetoUm, Donor objetoDois) {
		return objetoUm.equals(objetoDois);
	}	
	
}