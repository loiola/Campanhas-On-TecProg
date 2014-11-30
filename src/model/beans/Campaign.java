package model.beans;

public class Campaign {

	/*
	 * Class Campaign.java This class is responsible for getting the Campaign's
	 * informations
	 */

	// Constants
	public static final String EMPTY_TYPE_STRING = ""; 
	public static final Integer EMPTY_TYPE_INTEGER = 0;
	public static final Result EMPTY_CLASS_RESULT = new Result();
	public static final Party EMPTY_CLASS_PARTY = new Party();
	public static final Position EMPTY_CLASS_POSITION = new Position();
	public static final Candidate EMPTY_CLASS_CANDIDATE = new Candidate();
	public static final float EMPTY_TYPE_FLOAT = (float) 0.0;

	// Attributes
	
	// Unique attribute of candidate's campaign
	private Integer campaignIdentifier; 
	
	// Attribute that characterizes election result of candidate in campaign
	private Result campaignResult; 
	
	// Attribute that characterizes position of candidate in campaign
	private Position campaignPosition;
	
	// Attribute that characterizes political party of candidate in campaign
	private Party campaignParty; 
	
	// Attribute that characterizes candidate of campaign
	private Candidate campaignCandidate;
	
	// Attribute that characterizes a election year. Limit {2002, 2006, 2010}
	private Integer campaignYear; 
	
	// Attribute that characterizes urn number of candidate in campaign
	private Integer campaignCandidateNumber; 
	
	// Attribute that characterizes urn name of candidate in campaign
	private String campaignNameOfUrn; 
	
	// Attribute that characterizes country state of candidate in campaign
	private String campaignCountryState; 
	
	// Attribute that characterizes maximum expense declared of candidate in campaign
	private Float campaignMaximumExpenseDeclared; 
	
	// Attribute that characterizes total expense calculated of candidate in campaign
	private Float campaignTotalExpenseCalculated; 
	
	// Attribute that characterizes total revenue calculated of candidate in campaign
	private Float campaignTotalRevenueCalculated; 

	// Empty constructor
	public Campaign() {
		this.campaignIdentifier = EMPTY_TYPE_INTEGER;
		this.campaignResult = EMPTY_CLASS_RESULT;
		this.campaignPosition = EMPTY_CLASS_POSITION;
		this.campaignParty = EMPTY_CLASS_PARTY;
		this.campaignCandidate = EMPTY_CLASS_CANDIDATE;
		this.campaignYear = EMPTY_TYPE_INTEGER;
		this.campaignCandidateNumber = EMPTY_TYPE_INTEGER;
		this.campaignNameOfUrn = EMPTY_TYPE_STRING;
		this.campaignCountryState = EMPTY_TYPE_STRING;
		this.campaignMaximumExpenseDeclared = EMPTY_TYPE_FLOAT;
		this.campaignTotalExpenseCalculated = EMPTY_TYPE_FLOAT;
		this.campaignTotalRevenueCalculated = EMPTY_TYPE_FLOAT;
	}

	// Getters and Setters
	public Integer getCampaignIdentifier() {
		return campaignIdentifier;
	}

	public void setCampaignIdentifier(Integer campaignIdentifier) {
		this.campaignIdentifier = campaignIdentifier;
	}

	public Result getCampaignResult() {
		return campaignResult;
	}

	public void setCampaignResult(Result campaignResult) {
		this.campaignResult = campaignResult;
	}

	public Position getCampaignPosition() {
		return campaignPosition;
	}

	public void setCampaignPosition(Position campaignPosition) {
		this.campaignPosition = campaignPosition;
	}

	public Party getCampaignParty() {
		return campaignParty;
	}

	public void setCampaignParty(Party campaignParty) {
		this.campaignParty = campaignParty;
	}

	public Candidate getCampaignCandidate() {
		return campaignCandidate;
	}

	public void setCampaignCandidate(Candidate campaignCandidate) {
		this.campaignCandidate = campaignCandidate;
	}

	public Integer getCampaignYear() {
		return campaignYear;
	}

	public void setCampaignYear(Integer campaignYear) {
		this.campaignYear = campaignYear;
	}

	public Integer getCampaignCandidateNumber() {
		return campaignCandidateNumber;
	}

	public void setCampaignCandidateNumber(Integer candidateYear) {
		this.campaignCandidateNumber = candidateYear;
	}

	public String getCampaignNameOfUrn() {
		return campaignNameOfUrn;
	}

	public void setCampaignNameOfUrn(String campaignNameOfUrn) {
		this.campaignNameOfUrn = campaignNameOfUrn;
	}

	public String getCampaignCountryState() {
		return campaignCountryState;
	}

	public void setCampaignCountryState(String campaignCountryState) {
		this.campaignCountryState = campaignCountryState;
	}

	public Float getCampaignMaximumExpenseDeclared() {
		return campaignMaximumExpenseDeclared;
	}

	public void setCampaignMaximumExpenseDeclared(
			Float campaignMaximumExpenseDeclared) {
		this.campaignMaximumExpenseDeclared = campaignMaximumExpenseDeclared;
	}

	public Float getCampaignTotalExpenseCalculated() {
		return campaignTotalExpenseCalculated;
	}

	public void setCampaignTotalExpenseCalculated(
			Float campaignTotalExpenseCalculated) {
		this.campaignTotalExpenseCalculated = campaignTotalExpenseCalculated;
	}

	public Float getCampaignTotalRevenueCalculated() {
		return campaignTotalRevenueCalculated;
	}

	public void setCampaignTotalRevenueCalculated(
			Float campaignTotalRevenueCalculated) {
		this.campaignTotalRevenueCalculated = campaignTotalRevenueCalculated;
	}

	/*
	 * This method verified if an object provided is an instance of campaign
	 * @param an object for comparison 
	 * @return boolean with the result of comparison
	 */
	@Override
	public boolean equals(Object object) {
		
		// Variable that is not instance of a campaign
		boolean notInstanceOfObject = (!(object instanceof Campaign));
		
		if(notInstanceOfObject) {
			return false;
		} else {

			Campaign otherCampaign = (Campaign) object;

			// Variable that stores the logic state of the comparison between 
			// two campaigns
			boolean auxiliaryReturn = this.campaignYear.equals(otherCampaign
					.getCampaignYear())
					&& this.campaignCandidateNumber.equals(otherCampaign
							.getCampaignCandidateNumber())
					&& this.campaignPosition.equals(otherCampaign
							.getCampaignPosition())
					&& this.campaignNameOfUrn.equals(otherCampaign
							.getCampaignNameOfUrn());

			return auxiliaryReturn;
		}
	}
}
