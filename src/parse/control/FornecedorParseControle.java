package parse.control;

import model.beans.Supplier;
import model.dao.SupplierDAO;
import parse.index.IndicesParse;

public class FornecedorParseControle extends ParseControle<Supplier> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Supplier
	 */

	// Constructors
	public FornecedorParseControle(IndicesParse<Supplier> indicesParse) {
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
	public boolean equalObjects(Supplier objetoUm, Supplier objetoDois) {
		return objetoUm.equals(objetoDois);
	}	
	
}