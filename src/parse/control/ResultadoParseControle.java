package parse.control;

import model.beans.Result;
import model.dao.ResultDAO;
import parse.index.IndicesParse;

public class ResultadoParseControle extends ParseControle<Result> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Result
	 */

	// Constructors
	public ResultadoParseControle(IndicesParse<Result> indicesParse) {
		super(indicesParse, new ResultDAO());
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
	 * @param two instances of Class Result
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Result objetoUm, Result objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}