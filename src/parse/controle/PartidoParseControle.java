package parse.controle;

import modelo.beans.Partido;
import modelo.dao.PartidoDAO;
import parse.indices.IndicesParse;

public class PartidoParseControle extends ParseControle<Partido> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Party
	 */

	// Constructors
	public PartidoParseControle(IndicesParse<Partido> indicesParse) {
		super(indicesParse, new PartidoDAO());
	}

	/*
	 * This method instantiates an object of Class Party
	 * @return an instance of Class Party
	 */
	@Override
	public Partido novaInstancia() {
		Partido partido = new Partido();
		return partido;
	}

	/*
	 * This method checks if two instances are equal Class Party
	 * @param two instances of Class Party
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Partido objetoUm, Partido objetoDois) {
		return objetoUm.equals(objetoDois);
	}
	
}