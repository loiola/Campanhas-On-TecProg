package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.party.RegisterToParseParty;

public class ParseParty extends Parse {
	
	/*
	 * Class responsible for performing the parse of political parties
	 */

	// Constructors
	public ParseParty(String fileType, String year) throws ParseException {
		super(fileType, year);

	}

	/*
	 * This method adds an instance of class PartyRegisterParse to parse the list of registration
	 * @param an ArrayList<RegisterParse<?>>
	 * @param an String fileType
	 * @param an String year
	 */
	@Override
	protected void addRegisterParseOnList(
			ArrayList<RegisterParse<?>> listRegisterParse,
			String fileType, String year) throws ParseException {

		listRegisterParse.add(new RegisterToParseParty(fileType, year));
	}

}