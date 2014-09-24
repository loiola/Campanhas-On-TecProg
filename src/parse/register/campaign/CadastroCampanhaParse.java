package parse.register.campaign;

import model.beans.Campaign;
import parse.ParseException;
import parse.control.ParseControlCampaign;
import parse.control.ParseControl;
import parse.index.CampaignParseIndex;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public class CadastroCampanhaParse extends RegisterParse<Campaign> {
	
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
	public CadastroCampanhaParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
		
		setlinesToRegister(100000);
	}

	// Methods
	
	/*
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseCampaignControl to be used by constructor
	 * @return a ParseCampaignControl
	 */
	@Override
	public ParseControl<Campaign> newIntance(
			ParseIndex<Campaign> indicesParse) {
		ParseControlCampaign parseControlCampaign = new ParseControlCampaign(indicesParse);	
		return parseControlCampaign;
	}

	/*
	 * @see parse.register.RegisterParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseCampaignIndex, setting the index number for each attribute
	 * @return a ParseCampaignIndex
	 */
	@Override
	protected ParseIndex<Campaign> getParseIndex(String tipoArquivo,
			String ano) throws ParseException {
		
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
	 */
	@Override
	public void runFileLine(String[] campo) throws ParseException {
		this.parseControl.addEqualInstance(campo);
		this.linesRead++;
		if(this.linesRead >= this.linesToRegister) {
			registerInstances();
		}
	}

}
