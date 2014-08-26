package parse.controle;

import modelo.beans.Doador;
import modelo.dao.DoadorDAO;
import parse.indices.IndicesParse;

public class DoadorParseControle extends ParseControle<Doador> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Donor
	 */

	// Constructors
	public DoadorParseControle(IndicesParse<Doador> indicesParse) {
		super(indicesParse, new DoadorDAO());
	}

	/*
	 * This method instantiates an object of Class Donor
	 * @return an instance of Class Donor
	 */
	@Override
	public Doador novaInstancia() {
		Doador doador = new Doador();
		return doador;
	}

	/*
	 * This method checks if two instances are equal Class Donor
	 * @param two instances of Class Donor
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Doador objetoUm, Doador objetoDois) {
		return objetoUm.equals(objetoDois);
	}	
	
}