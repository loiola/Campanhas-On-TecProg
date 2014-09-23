package parse.control;

import model.beans.Candidate;
import model.dao.CandidateDAO;
import parse.index.IndicesParse;

public class ParseControlCandidate extends ParseControl<Candidate> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Candidate
	 */

	// Constructors
	public ParseControlCandidate(IndicesParse<Candidate> indicesParse) {
		super(indicesParse, new CandidateDAO());
	}
	
	/*
	 * This method instantiates an object of Class Candidate
	 * @return an instance of Class Candidate
	 */
	@Override
	public Candidate newInstance() {
		Candidate candidate = new Candidate();
		return candidate;
	}

	/*
	 * This method checks if two instances are equal Class Candidate
	 * @param two instances of Class Candidate
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Candidate objectOne, Candidate objectTwo) {
		return objectOne.equals(objectTwo);
	}

}