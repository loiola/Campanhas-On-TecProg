package parse.register.campaign;

import model.beans.Campaign;
import parse.ParseException;
import parse.control.ParseControlCampaign;
import parse.control.ParseControl;
import parse.index.CampaignParseIndex;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public class RegisterToParseCampaign extends RegisterParse<Campaign> {
	
	/* 
	 * Class used to extract Campaign attributes and forward the register to the Database
	 */

	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register informations from a Campaign
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterToParseCampaign(String fileType, String year) throws ParseException {
		super(fileType, year);
		
		setlinesToRegister(100000); // MAGIC NUMBER!!! 
	}

	// Methods
	
	/*
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseCampaignControl to be used by constructor
	 * @param a ParseIndex who'll be used by the ParseControl constructor
	 * @return a ParseCampaignControl
	 */
	@Override
	public ParseControl<Campaign> newIntance(ParseIndex<Campaign> parseIndex) {
		ParseControlCampaign parseControlCampaign = new ParseControlCampaign(parseIndex);	
		return parseControlCampaign;
	}

	/*
	 * @see parse.register.RegisterParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseCampaignIndex, setting the index number for each attribute
	 * @param String who define the type of the list file to be used to get the ParseIndex 
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 * @return a ParseCampaignIndex
	 */
	@Override
	protected ParseIndex<Campaign> getParseIndex(String fileType, String year) throws ParseException {
		CampaignParseIndex campaignParseIndex;
		campaignParseIndex = new CampaignParseIndex();
		
		campaignParseIndex.setIndexYear(2);
		campaignParseIndex.setIndexNumberCandidate(12);
		campaignParseIndex.setIndexUrnName(13);
		campaignParseIndex.setIndexUnitFederation(5);
		campaignParseIndex.setIndexCodePosition(8);
		campaignParseIndex.setIndexTitleCandidate(26);
		campaignParseIndex.setIndexNumberParty(16);
		campaignParseIndex.setIndexCodeResult(40);
		campaignParseIndex.setIndexMaximumExpenseDeclared(39);
		return campaignParseIndex;
	}
	
	/*
	 * @see parse.register.RegisterParse#executarLinhaDoArquivo(java.lang.String[])
	 * This method read a line of the file based on fields
	 * @param vector of Strings
	 */
	@Override
	public void runFileLine (String[] field) throws ParseException {
		this.parseControl.addEqualInstance(field);
		this.linesRead++;
		if(this.linesRead >= this.linesToRegister) {
			registerInstances();
		}
	}
}