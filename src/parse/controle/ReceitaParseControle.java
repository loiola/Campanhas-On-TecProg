package parse.controle;

import modelo.beans.Receita;
import modelo.dao.ReceitaDAO;
import parse.indices.IndicesParse;

public class ReceitaParseControle extends ParseControle<Receita> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Revenue
	 */

	// Constructors
	public ReceitaParseControle(IndicesParse<Receita> indicesParse) {
		super(indicesParse, new ReceitaDAO());
	}

	/*
	 * This method instantiates an object of Class Revenue
	 * @return an instance of Class Revenue
	 */
	@Override
	public Receita novaInstancia() {
		Receita receita = new Receita();
		return receita;
	}

	/*
	 * This method checks if two instances are equal Class Revenue
	 * @param two instances of Class Revenue
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Receita objetoUm, Receita objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}