package parse.index;

import model.beans.Result;

public class ResultParseIndex extends ParseIndex<Result> {
	
	/*
	 * Class to control the contents of information inherent to the results
	 */

	// Attributes
	private int indexCodeResult;
	private int indexDescriptionResult;
	
	// Constructors
	public ResultParseIndex() {
		this.indexCodeResult = INVALID_INDEX;
		this.indexDescriptionResult = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the results in the file
	 * @param an instance of the Class Result
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Result result, String[] field) {
		
		//Variable to store the result of index validation
		boolean validationResult;
				
		validationResult = validIndex(this.indexCodeResult);
		if(validationResult) {
			result.setResultType(Integer.parseInt(field[this.indexCodeResult]));
		}
		
		validationResult = validIndex(this.indexDescriptionResult);
		if(validationResult) {
			result.setResultDescription(field[this.indexDescriptionResult]);
		}	
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Result
	 */
	@Override
	protected void setEmptyInAllSetters(Result result) {
		result.setResultType(Result.EMPTY_TYPE_INTEGER);
		result.setResultDescription(Result.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndexCodeResult(int indexCodeResult) {
		this.indexCodeResult = indexCodeResult;
	}

	public void setIndexDescriptionResult(int indexDescriptionResult) {
		this.indexDescriptionResult = indexDescriptionResult;
	}
	
}