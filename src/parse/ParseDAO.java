package parse;

import java.util.ArrayList;

public interface ParseDAO<O> {
	
	/*
	 * Communication interface between Parse and Database
	 */

	/*
	 * Register a list of objects O coming of the parse
	 */
	public void registerObjectArrayListOnParse(ArrayList<O> lista) throws ParseException;
	
	/*
	 * Shows a list of objects O coming of the parse
	 */
	public ArrayList<O> getObjectArrayListFromParse() throws ParseException;
	
}
