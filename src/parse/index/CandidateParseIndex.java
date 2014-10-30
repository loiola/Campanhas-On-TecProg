package parse.index;

import model.beans.Candidate;

public class CandidateParseIndex extends ParseIndex<Candidate> {
	
	/*
	 * Class to control the contents of information inherent to the candidates
	 */

	// Attributes
	
	// Variable to the index of the candidate's name
	private int indexName;
	
	// Variable to the index of the candidate's election title
	private int indexElectionTitle;

	// Constructors
	public CandidateParseIndex() {
		this.indexName = INVALID_INDEX;
		this.indexElectionTitle = INVALID_INDEX;
	}

	/*
	 * This method formalizes the indices for reading the information about the candidates in the file
	 * @param an instance of Class Candidate
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Candidate candidate, String[] field) {
		//Variable to store the result of index validation
		boolean validationResult;
		
		validationResult = validIndex(this.indexName);
		if(validationResult) {
			candidate.setCandidateName(field[this.indexName]);
		}
		
		validationResult = validIndex(this.indexElectionTitle);
		if(validationResult) {
			candidate.setCandidateElectoralTitle(field[this.indexElectionTitle]);

		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Candidate
	 */
	@Override
	protected void setEmptyInAllSetters(Candidate candidate) {
		candidate.setCandidateName(Candidate.EMPTY_TYPE_STRING);
		candidate.setCandidateElectoralTitle(Candidate.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndexElectionTitle(int indexElectionTitle) {
		this.indexElectionTitle = indexElectionTitle;
	}

	public void setIndexName(int indexName) {
		this.indexName = indexName;
	}

}