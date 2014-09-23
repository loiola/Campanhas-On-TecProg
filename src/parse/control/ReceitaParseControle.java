package parse.control;

import model.beans.Revenue;
import model.dao.RevenueDAO;
import parse.index.IndicesParse;

public class ReceitaParseControle extends ParseControle<Revenue> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Revenue
	 */

	// Constructors
	public ReceitaParseControle(IndicesParse<Revenue> indicesParse) {
		super(indicesParse, new RevenueDAO());
	}

	/*
	 * This method instantiates an object of Class Revenue
	 * @return an instance of Class Revenue
	 */
	@Override
	public Revenue newInstance() {
		Revenue revenue = new Revenue();
		return revenue;
	}

	/*
	 * This method checks if two instances are equal Class Revenue
	 * @param two instances of Class Revenue
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Revenue objetoUm, Revenue objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}