package model.beans;

public class Result {

	/*
	 * Class Result.java This class is responsible for getting the Result's
	 * informations
	 */

	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final int EMPTY_TYPE_INTEGER = 0;

	// Attributes
	
	// Attribute that characterizes type of result. 
	// Domain: 1 = candidate elected; 4 = candidate not elected; 5 = second round runoff election
	private Integer resultType;
	
	// Attribute that characterizes description of result
	private String resultDescription;

	// Empty constructor
	public Result() {
		this.resultType = EMPTY_TYPE_INTEGER;
		this.resultDescription = EMPTY_TYPE_STRING;
	}

	// Getters and Setters
	public Integer getResultType() {
		return resultType;
	}

	public void setResultType(int resultType) {
		this.resultType = resultType;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}

	/*
	 * This method verified if an object provided is an instance of result
	 * @param an object for comparison 
	 * @return boolean with the result of comparison
	 */
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Result)) {
			return false;
		} else {

			Result otherResult = (Result) object;

			// Variable that stores the logic state of the comparison between
			// two results
			boolean auxiliaryReturn = this.resultDescription
					.equalsIgnoreCase(otherResult.getResultDescription());

			return auxiliaryReturn;
		}
	}
}
