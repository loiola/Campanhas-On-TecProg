package parse.control;

import model.beans.Supplier;
import model.dao.SupplierDAO;
import parse.index.ParseIndex;

public class ParseControlSupplier extends ParseControl<Supplier> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Supplier
	 */

	// Constructors
	public ParseControlSupplier(ParseIndex<Supplier> indicesParse) {
		super(indicesParse, new SupplierDAO());
	}

	/*
	 * This method instantiates an object of Class Supplier
	 * @return an instance of Class Supplier
	 */
	@Override
	public Supplier newInstance() {
		Supplier supplier = new Supplier();
		return supplier;
	}

	/*
	 * This method checks if two instances are equal Class Supplier
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Supplier objectOne, Supplier objectTwo) {
		return objectOne.equals(objectTwo);
	}	
	
}