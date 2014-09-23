package parse.control;

import model.beans.Supplier;
import model.dao.SupplierDAO;
import parse.index.IndicesParse;

public class ParseControlSupplier extends ParseControle<Supplier> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Supplier
	 */

	// Constructors
	public ParseControlSupplier(IndicesParse<Supplier> indicesParse) {
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
	 * @param two instances of Class Supplier
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Supplier objectOne, Supplier objectTwo) {
		return objectOne.equals(objectTwo);
	}	
	
}