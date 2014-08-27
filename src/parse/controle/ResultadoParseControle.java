package parse.controle;

import modelo.beans.Resultado;
import modelo.dao.ResultadoDAO;
import parse.indices.IndicesParse;

public class ResultadoParseControle extends ParseControle<Resultado> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Result
	 */

	// Constructors
	public ResultadoParseControle(IndicesParse<Resultado> indicesParse) {
		super(indicesParse, new ResultadoDAO());
	}

	/*
	 * This method instantiates an object of Class Result
	 * @return an instance of Class Result
	 */
	@Override
	public Resultado novaInstancia() {
		Resultado resultado = new Resultado();
		return resultado;
	}

	/*
	 * This method checks if two instances are equal Class Result
	 * @param two instances of Class Result
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Resultado objetoUm, Resultado objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}