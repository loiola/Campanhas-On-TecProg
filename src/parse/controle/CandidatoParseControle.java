package parse.controle;

import modelo.beans.Candidato;
import modelo.dao.CandidatoDAO;
import parse.indices.IndicesParse;

public class CandidatoParseControle extends ParseControle<Candidato> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Candidate
	 */

	// Constructors
	public CandidatoParseControle(IndicesParse<Candidato> indicesParse) {
		super(indicesParse, new CandidatoDAO());
	}
	
	/*
	 * This method instantiates an object of Class Candidate
	 * @return an instance of Class Candidate
	 */
	@Override
	public Candidato novaInstancia() {
		Candidato candidato = new Candidato();
		return candidato;
	}

	/*
	 * This method checks if two instances are equal Class Candidate
	 * @param two instances of Class Candidate
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Candidato objetoUm, Candidato objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}