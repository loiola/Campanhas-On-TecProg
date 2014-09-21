package controle;

import java.util.LinkedList;
import modelo.beans.Candidate;
import modelo.dao.CandidatoDAO;

public class CandidatoControle {
	
	/*
	 * Control class political party, which mediates the application layer with the model
	 */

	// Attributes
	private CandidatoDAO candidatoDAO;

	// Constructors	
	public CandidatoControle() {
		this.candidatoDAO = new CandidatoDAO();
	}
	
	// Other methods
	/*
	 * Method that requests a search of all candidate with the name informed
	 * @param the name of candidate
	 * @return an LinkedList a list with all candidate with name informed
	 */
	public LinkedList<Candidate> getListaCandidatos(String nome) {
		return this.candidatoDAO.getListaPeloNome(nome);
	}

	/*
	 * Method that requests a search of all candidate with the voter informed 
	 * @param the voter of the candidate
	 * @return a candidate with voter informed
	 */
	public Candidate getUmCandidato(String tituloEleitoral) {
		return this.candidatoDAO.getCandidatoPeloTitulo(tituloEleitoral);
	}
}
