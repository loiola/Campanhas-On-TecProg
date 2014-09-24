package parse.control;

import model.beans.Revenue;
import model.dao.RevenueDAO;
import parse.index.ParseIndex;

public class ParseControlRevenue extends ParseControl<Revenue> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Revenue
	 */

	// Constructors
	public ParseControlRevenue(ParseIndex<Revenue> indexParse) {
		super(indexParse, new RevenueDAO());
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
	public boolean equalObjects(Revenue objectOne, Revenue objectTwo) {
		return objectOne.equals(objectTwo);
	}

}