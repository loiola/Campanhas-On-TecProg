package parse.controle;

import modelo.beans.Fornecedor;
import modelo.dao.FornecedorDAO;
import parse.indices.IndicesParse;

public class FornecedorParseControle extends ParseControle<Fornecedor> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Supplier
	 */

	// Constructors
	public FornecedorParseControle(IndicesParse<Fornecedor> indicesParse) {
		super(indicesParse, new FornecedorDAO());
	}

	/*
	 * This method instantiates an object of Class Supplier
	 * @return an instance of Class Supplier
	 */
	@Override
	public Fornecedor novaInstancia() {
		Fornecedor fornecedor = new Fornecedor();
		return fornecedor;
	}

	/*
	 * This method checks if two instances are equal Class Supplier
	 * @param two instances of Class Supplier
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Fornecedor objetoUm, Fornecedor objetoDois) {
		return objetoUm.equals(objetoDois);
	}	
	
}