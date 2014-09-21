package parse.controle;

import modelo.beans.Party;
import modelo.dao.PartidoDAO;
import parse.indices.IndicesParse;

public class PartidoParseControle extends ParseControle<Party> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Party
	 */

	// Constructors
	public PartidoParseControle(IndicesParse<Party> indicesParse) {
		super(indicesParse, new PartidoDAO());
	}

	/*
	 * This method instantiates an object of Class Party
	 * @return an instance of Class Party
	 */
	@Override
	public Party novaInstancia() {
		Party party = new Party();
		return party;
	}

	/*
	 * This method checks if two instances are equal Class Party
	 * @param two instances of Class Party
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Party objetoUm, Party objetoDois) {
		return objetoUm.equals(objetoDois);
	}
	
}