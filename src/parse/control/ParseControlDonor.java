package parse.control;

import model.beans.Donor;
import model.dao.DonorDAO;
import parse.index.IndicesParse;

public class ParseControlDonor extends ParseControl<Donor> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Donor
	 */

	// Constructors
	public ParseControlDonor(IndicesParse<Donor> indicesParse) {
		super(indicesParse, new DonorDAO());
	}

	/*
	 * This method instantiates an object of Class Donor
	 * @return an instance of Class Donor
	 */
	@Override
	public Donor newInstance() {
		Donor donor = new Donor();
		return donor;
	}

	/*
	 * This method checks if two instances are equal Class Donor
	 * @param two instances of Class Donor
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Donor objectOne, Donor objectTwo) {
		return objectOne.equals(objectTwo);
	}	
	
}