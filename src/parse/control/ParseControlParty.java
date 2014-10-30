package parse.control;

import model.beans.Party;
import model.dao.PartyDAO;
import parse.index.ParseIndex;

public class ParseControlParty extends ParseControl<Party> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Party
	 */

	// Constructors
	public ParseControlParty(ParseIndex<Party> indexParse) {
		super(indexParse, new PartyDAO());
	}

	/*
	 * This method instantiates an object of Class Party
	 * @return an instance of Class Party
	 */
	@Override
	public Party newInstance() {
		Party party = new Party();
		return party;
	}

	/*
	 * This method checks if two instances are equal Class Party
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Party objectOne, Party objectTwo) {
		// Variable to store the logical value of the check
		boolean comparisonResult;
				
		comparisonResult = objectOne.equals(objectTwo);
		return comparisonResult;
	}
	
}