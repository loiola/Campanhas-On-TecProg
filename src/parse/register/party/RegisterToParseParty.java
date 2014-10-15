package parse.register.party;

import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlParty;
import parse.index.ParseIndex;
import parse.index.PartyParseIndex;
import parse.register.RegisterParse;
import model.beans.Party;

public class RegisterToParseParty extends RegisterParse<Party> {
	
	/*
	 * Class responsible for identifying the contents of the file to read and get information
	 */

	// Constructors
	public RegisterToParseParty(String fileType, String year) throws ParseException {
		super(fileType, year);
	}

	/*
	 * This method formalizes the instance index to be used
	 * @param an instance of class ParseIndex<Party>
	 * @return an instance of class PartyParseControl
	 */
	@Override
	public ParseControl<Party> newIntance(ParseIndex<Party> parseIndex) {
		ParseControlParty parseControlParty;
		parseControlParty = new ParseControlParty(parseIndex);
		return parseControlParty;
	}

	/*
	 * This method checks the file type to correctly identify the indexes that have the information
	 * to be read and stored
	 * @param an String fileType
	 * @param an String year
	 * @return an instance of class PartyIndicesParse
	 */
	@Override
	protected ParseIndex<Party> getParseIndex(String fileType, String year) throws ParseException {
		PartyParseIndex partyParseIndex;
		partyParseIndex = new PartyParseIndex();
		
		//Variables to store result of validation of file type
		boolean validationFileParty = fileType.equals("party");
		boolean validationFileCampaign = fileType.equals("campaign");
		
		if (validationFileParty) {
			partyParseIndex.setIndexPartyName(2);
			partyParseIndex.setIndexAcronym(1);
			partyParseIndex.setIndexNumberParty(5);
			partyParseIndex.setIndexDeferral(3);
		} else if (validationFileCampaign) {
			partyParseIndex.setIndexPartyName(18);
			partyParseIndex.setIndexAcronym(17);
			partyParseIndex.setIndexNumberParty(16);
		}
		return partyParseIndex;
	}
}