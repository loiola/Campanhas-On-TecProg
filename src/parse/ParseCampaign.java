package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.campaign.RegisterToParseCampaign;
import parse.register.campaign.RegisterToParseCandidate;
import parse.register.campaign.RegisterToParsePosition;
import parse.register.campaign.RegisterToParseResult;
import parse.register.party.RegisterToParseParty;

public class ParseCampaign extends Parse {
	
	/*
	 * Class responsible for registering the parse of a given campaign
	 */

	// Constructor
	
	/*
	 * This constructor use the Parse inherited constructor to
	 * register informations from an Campaign
	 * @param String who define the type of the list file to be used 
	 * @param String who define the year of the campaign to be used 
	 */
	public ParseCampaign(String fileType, String year) throws ParseException {
		super(fileType, year);
	}

	/*
	 * Method that records a linking campaign a candidate for party,
	 * result, position and his campaign
	 * @param an ArrayList of register in parse of the Object O
	 * @param String who define the type of the list file to be used 
	 * @param String who define the year of the campaign to be used 
	 */
	@Override
	protected void addRegisterParseOnList(
			ArrayList<RegisterParse<?>> listRegisterParse,
			String fileType, String year) throws ParseException {

		listRegisterParse.add(new RegisterToParseCandidate(fileType, year));
		listRegisterParse.add(new RegisterToParseResult(fileType, year));
		listRegisterParse.add(new RegisterToParsePosition(fileType, year));
		listRegisterParse.add(new RegisterToParseParty(fileType, year));
		listRegisterParse.add(new RegisterToParseCampaign(fileType, year));
	}
	
}
