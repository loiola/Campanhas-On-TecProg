package parse.control;

import java.util.ArrayList;

import model.beans.Campaign;
import model.dao.CampaignDAO;
import parse.ParseException;
import parse.index.ParseIndex;

public class ParseControlCampaign extends ParseControl<Campaign> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Campaign
	 */

	// Constants
	public static final int CANDIDATE_ELECTED = 1;
	public static final int CANDIDATE_NOT_ELECTED = 4;
	public static final int SECOND_ROUND_RUNOFF = 5; // In portuguese segundo turno;
	
	// Constructors
	public ParseControlCampaign(ParseIndex<Campaign> indicesParse) {
		super(indicesParse, new CampaignDAO());
	}

	/*
	 * This method instantiates an object of Class Campaign
	 * @return an instance of Class Campaign
	 */
	@Override
	public Campaign newInstance() {
		Campaign campaign = new Campaign();
		return campaign;
	}

	/*
	 * This method checks if two instances are equal Class Campaign
	 * @param an instances of Class for first argument to comparison 
	 * @param an instances of Class for second argument to comparison
	 * @return a boolean resulting from the comparison
	 */
	@Override
	public boolean equalObjects(Campaign objectOne, Campaign objectTwo) {
		// Variable to store the logical value of the check
		boolean comparisonResult;
		
		comparisonResult = objectOne.equals(objectTwo);
		return comparisonResult;
	}
	
	/*
	 * This method performs the processing and registration of instances of Class Campaign
	 */
	@Override
	public void registeringInstances() throws ParseException {
		ArrayList<Campaign> listCampaign = new ArrayList<>();
		
		// Variable represents if a candidate was elected or not
		boolean wasElected;
		
		// Variable store the size of list instances
		int sizeOfListInstance = this.listInstance.size();
		
		for(int i = 0; i < sizeOfListInstance; i++) {
			
			wasElected = false;
			
			// Condition for increment the counter 
			if(listCampaign.contains(this.listInstance.get(i))) {
				continue;
			}
			
			for(int j = i; j < sizeOfListInstance; j++) {
				
				// Variable for test name of urn ignoring uppercase and downcase 
				boolean verifiedNameOfUrnIgnoreCase = this.listInstance.get(i).getCampaignNameOfUrn().
						equalsIgnoreCase(this.listInstance.get(j).getCampaignNameOfUrn());
				
				if(verifiedNameOfUrnIgnoreCase) {
					
					// Variable for test in file if candidate was elected or to second round runoff  
					boolean verifiedResultWasElected = this.listInstance.get(j).getCampaignResult().getResultType() == CANDIDATE_ELECTED ||
							this.listInstance.get(j).getCampaignResult().getResultType() == SECOND_ROUND_RUNOFF; 
					
					// Case the result of candidate's campaign was elected register in list of campaign
					if(verifiedResultWasElected) {
						wasElected = true;
						listCampaign.add(this.listInstance.get(j));
						break;
					}
				}
			}
			
			if(!wasElected) {
				
				// If a candidate wasn't elected set the result campaign as 'not elected'
				this.listInstance.get(i).getCampaignResult().setResultType(CANDIDATE_NOT_ELECTED);
				listCampaign.add(this.listInstance.get(i));
			}

		}
		
		// Register the list of campaign
		this.listInstance = new ArrayList<>(listCampaign);
		super.registeringInstances();
	}

}