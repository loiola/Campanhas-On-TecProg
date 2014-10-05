package model.beans;

public class Result {

	/*
	 * Class Result.java
	 * This class is responsable for getting the Result's informations
	 */
	
	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final int EMPTY_TYPE_INTEGER = 0;
	
	// Attributes
	private Integer resultType;
	private String  resultDescription;
	
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
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Result))
			return false;
		
		Result otherResult = (Result) object;
		
		//Variable that stores the logic state of the comparison between two results
		boolean auxiliaryReturn = this.resultDescription.equalsIgnoreCase(
				otherResult.getResultDescription());
		
		return auxiliaryReturn;
	}
}
