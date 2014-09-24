package parse.index;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Party;
import model.beans.Position;
import model.beans.Result;

public class CampaignParseIndex extends ParseIndex<Campaign> {

	/*
	 * Class to control the indices of the information inherent in electoral campaigns
	 */
	
	// Attributes
	private int indexCodeResult;
	private int indexCodePosition;
	private int indexNumberParty;
	private int indexTitleCandidate;
	private int indexYear;
	private int indexNumberCandidate;
	private int indexUrnName;
	private int indexUnitFederation;
	private int indexMaximumExpenseDeclared;

	// Constructors
	public CampaignParseIndex() {
		this.indexCodeResult = INVALID_INDEX;	
		this.indexCodePosition = INVALID_INDEX;	
		this.indexNumberParty = INVALID_INDEX;	
		this.indexTitleCandidate = INVALID_INDEX;	
		this.indexYear = INVALID_INDEX;	
		this.indexNumberCandidate = INVALID_INDEX;	
		this.indexUrnName = INVALID_INDEX;	
		this.indexUnitFederation = INVALID_INDEX;	
		this.indexMaximumExpenseDeclared = INVALID_INDEX;	
	}
	
	// Other methods
	/*
	 * This method formalizes the indices for reading the information about the campaigns in the file
	 * @param an instance of Class Campaign
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Campaign campaign, String[] field) {
		if (validIndex(this.indexCodeResult)) {
			Result result = new Result();
			result.setResultType(Integer.parseInt(field[this.indexCodeResult]));
			campaign.setCampaignResult(result);
		}
		if (validIndex(this.indexCodePosition)) {
			Position position = new Position();
			position.setPositionCode(Integer.parseInt(field[this.indexCodePosition]));
			campaign.setCampaignPosition(position);
		}	
		if (validIndex(this.indexNumberParty)) {
			Party party = new Party();
			party.setPartyNumber(Integer.parseInt(field[this.indexNumberParty]));
			campaign.setCampaignParty(party);
		}	
		if (validIndex(this.indexTitleCandidate)) {
			Candidate candidate = new Candidate();
			candidate.setCandidateElectoralTitle(field[this.indexTitleCandidate]);
			campaign.setCampaignCandidate(candidate);
		}	
		if (validIndex(this.indexYear)) {
			campaign.setCampaignYear(Integer.parseInt(field[this.indexYear]));
		}	
		if (validIndex(this.indexNumberCandidate)) {
			campaign.setCampaignCandidateNumber(Integer.parseInt(field[this.indexNumberCandidate]));
		}	
		if (validIndex(this.indexUrnName)) {
			campaign.setCampaignNameOfUrn(field[this.indexUrnName]);
		}	
		if (validIndex(this.indexUnitFederation)) {
			campaign.setCampaignCountryState(field[this.indexUnitFederation]);
		}	
		if (validIndex(this.indexMaximumExpenseDeclared)) {
			campaign.setCampaignMaximumExpenseDeclared(
					Float.parseFloat(field[this.indexMaximumExpenseDeclared].replace(',', '.')));
		}		
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Campaign
	 */
	@Override
	protected void setEmptyInAllSetters(Campaign campaign) {
		campaign.setCampaignIdentifier(Campaign.EMPTY_TYPE_INTEGER);
		campaign.setCampaignResult(Campaign.EMPTY_CLASS_RESULT);	
		campaign.setCampaignPosition(Campaign.EMPTY_CLASS_POSITION);	
		campaign.setCampaignParty(Campaign.EMPTY_CLASS_PARTY);	
		campaign.setCampaignCandidate(Campaign.EMPTY_CLASS_CANDIDATE);	
		campaign.setCampaignYear(Campaign.EMPTY_TYPE_INTEGER);	
		campaign.setCampaignCandidateNumber(Campaign.EMPTY_TYPE_INTEGER);	
		campaign.setCampaignNameOfUrn(Campaign.EMPTY_TYPE_STRING);	
		campaign.setCampaignCountryState(Campaign.EMPTY_TYPE_STRING);	
		campaign.setCampaignMaximumExpenseDeclared(Campaign.EMPTY_TYPE_FLOAT);	
		campaign.setCampaignTotalExpenseCalculated(Campaign.EMPTY_TYPE_FLOAT);	
		campaign.setCampaignTotalRevenueCalculated(Campaign.EMPTY_TYPE_FLOAT);	
	}

	// Mutators for indexes of the array of fields
	public void setIndexCodeResult(int indexCodeResult) {
		this.indexCodeResult = indexCodeResult;
	}

	public void setIndexCodePosition(int indexCodePosition) {
		this.indexCodePosition = indexCodePosition;
	}

	public void setIndexNumberParty(int indexNumberParty) {
		this.indexNumberParty = indexNumberParty;
	}

	public void setIndexTitleCandidate(int indexTitleCandidate) {
		this.indexTitleCandidate = indexTitleCandidate;
	}

	public void setIndexYear(int indexYear) {
		this.indexYear = indexYear;
	}

	public void setIndexNumberCandidate(int indexNumberCandidate) {
		this.indexNumberCandidate = indexNumberCandidate;
	}

	public void setIndexUrnName(int indexUrnName) {
		this.indexUrnName = indexUrnName;
	}

	public void setIndexUnitFederation(int indexUnitFederation) {
		this.indexUnitFederation = indexUnitFederation;
	}

	public void setIndexMaximumExpenseDeclared(int indexMaximumExpenseDeclared) {
		this.indexMaximumExpenseDeclared = indexMaximumExpenseDeclared;
	}

}