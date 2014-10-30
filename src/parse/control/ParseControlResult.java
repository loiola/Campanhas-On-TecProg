package parse.control;

import model.beans.Result;
import model.dao.ResultDAO;
import parse.index.ParseIndex;

public class ParseControlResult extends ParseControl<Result> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Result
	 */

	// Constructors
	public ParseControlResult(ParseIndex<Result> indexParse) {
		super(indexParse, new ResultDAO());
	}

	/*
	 * This method instantiates an object of Class Result
	 * @return an instance of Class Result
	 */
	@Override
	public Result newInstance() {
		Result result = new Result();
		return result;
	}

	/*
	 * This method checks if two instances are equal Class Result
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Result objectOne, Result objectTwo) {
		return objectOne.equals(objectTwo);
	}

}