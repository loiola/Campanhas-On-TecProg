package model.beans;

public class Candidate {

	/*
	 * Model Class Candidate.java This class is responsable for getting the
	 * Candidate's informations
	 */

	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final Integer EMPTY_TYPE_INTEGER = 0;

	// Attributes
	private String candidateName;
	private String candidateElectoralTitle;

	// Empty Constructor
	public Candidate() {
		this.candidateName = EMPTY_TYPE_STRING;
		this.candidateElectoralTitle = EMPTY_TYPE_STRING;
	}

	// Getters and Setters
	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateElectoralTitle() {
		return candidateElectoralTitle;
	}

	public void setCandidateElectoralTitle(String candidateElectoralTitle) {
		this.candidateElectoralTitle = candidateElectoralTitle;
	}

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Candidate)) {
			return false;
		} else {

			Candidate otherCandidate = (Candidate) object;

			// Variable that stores the logic state of the comparison between
			// two candidates
			boolean auxiliaryReturn = this.candidateElectoralTitle
					.equals(otherCandidate.getCandidateElectoralTitle());

			return auxiliaryReturn;
		}
	}
}
