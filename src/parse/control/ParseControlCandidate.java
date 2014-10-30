package parse.control;

import model.beans.Candidate;
import model.dao.CandidateDAO;
import parse.index.ParseIndex;

public class ParseControlCandidate extends ParseControl<Candidate> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Candidate
	 */

	// Constructors
	public ParseControlCandidate(ParseIndex<Candidate> indicesParse) {
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
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Candidate objectOne, Candidate objectTwo) {
		// Variable to store the logical value of the check
		boolean comparisonResult;
				
		comparisonResult = objectOne.equals(objectTwo);
		return comparisonResult;
	}

}