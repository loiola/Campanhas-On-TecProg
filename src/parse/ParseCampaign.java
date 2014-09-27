package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.campaign.RegisterToParseCampaign;
import parse.register.campaign.RegisterToParseCandidate;
import parse.register.campaign.RegisterToParsePosition;
import parse.register.campaign.RegisterToParseResult;
import parse.register.party.RegisterToParseParty;

public class ParseCampaign extends Parse {

	public ParseCampaign(String fileType, String year) throws ParseException {
		super(fileType, year);
	}

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
