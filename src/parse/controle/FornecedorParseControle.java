package parse.controle;

import model.beans.Supplier;
import model.dao.FornecedorDAO;
import parse.indices.IndicesParse;

public class FornecedorParseControle extends ParseControle<Supplier> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Supplier
	 */

	// Constructors
	public FornecedorParseControle(IndicesParse<Supplier> indicesParse) {
		super(indicesParse, new FornecedorDAO());
	}

	/*
	 * This method instantiates an object of Class Supplier
	 * @return an instance of Class Supplier
	 */
	@Override
	public Supplier novaInstancia() {
		Supplier supplier = new Supplier();
		return supplier;
	}

	/*
	 * This method checks if two instances are equal Class Supplier
	 * @param two instances of Class Supplier
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Supplier objetoUm, Supplier objetoDois) {
		return objetoUm.equals(objetoDois);
	}	
	
}