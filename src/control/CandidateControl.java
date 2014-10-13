package control;

import java.util.LinkedList;

import model.beans.Candidate;
import model.dao.CandidateDAO;

public class CandidateControl {
	
	/*
	 * Control class political party, which mediates the application layer with the model
	 */

	// Attributes
	private CandidateDAO candidateDAO;

	// Constructors	
	public CandidateControl() {
		this.candidateDAO = new CandidateDAO();
	}
	
	// Other methods
	/*
	 * Method that requests a search of all candidate with the name informed
	 * @param the name of candidate
	 * @return an LinkedList a list with all candidate with name informed
	 */
	public LinkedList<Candidate> getListCandidate(String name) {
		//Variable to store the retrieved candidates
		LinkedList<Candidate> candidateList = new LinkedList<>();
		
		candidateList = this.candidateDAO.getCandidateListByName(name);
		return candidateList;
	}

	/*
	 * Method that requests a search of all candidate with the voter informed 
	 * @param the voter of the candidate
	 * @return a candidate with voter informed
	 */
	public Candidate getACandidate(String ElectoralTitle) {
		//Variable to store the retrieved candidate
		Candidate candidateRecovered = new Candidate();
		
		candidateRecovered = this.candidateDAO.getCandidateByElectoralTitle(ElectoralTitle);
		return candidateRecovered;
	}
}
