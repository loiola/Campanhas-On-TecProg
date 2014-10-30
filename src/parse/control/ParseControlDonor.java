package parse.control;

import model.beans.Donor;
import model.dao.DonorDAO;
import parse.index.ParseIndex;

public class ParseControlDonor extends ParseControl<Donor> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Donor
	 */

	// Constructors
	public ParseControlDonor(ParseIndex<Donor> indicesParse) {
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
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Donor objectOne, Donor objectTwo) {
		return objectOne.equals(objectTwo);
	}	
	
}