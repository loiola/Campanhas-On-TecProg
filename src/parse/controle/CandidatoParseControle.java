package parse.controle;

import modelo.beans.Candidate;
import modelo.dao.CandidatoDAO;
import parse.indices.IndicesParse;

public class CandidatoParseControle extends ParseControle<Candidate> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Candidate
	 */

	// Constructors
	public CandidatoParseControle(IndicesParse<Candidate> indicesParse) {
		super(indicesParse, new CandidatoDAO());
	}
	
	/*
	 * This method instantiates an object of Class Candidate
	 * @return an instance of Class Candidate
	 */
	@Override
	public Candidate novaInstancia() {
		Candidate candidate = new Candidate();
		return candidate;
	}

	/*
	 * This method checks if two instances are equal Class Candidate
	 * @param two instances of Class Candidate
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Candidate objetoUm, Candidate objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}