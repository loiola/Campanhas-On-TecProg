package parse;

import java.util.ArrayList;

public interface ParseDAO<O> {

	public void registerObjectArrayListOnParse(ArrayList<O> lista) throws ParseException;
	public ArrayList<O> getObjectArrayListFromParse() throws ParseException;
	
}
