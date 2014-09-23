package parse.control;

import java.util.ArrayList;

import model.beans.Campaign;
import model.dao.CampaignDAO;
import parse.ParseException;
import parse.index.IndicesParse;

public class ParseControlCampaign extends ParseControl<Campaign> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Campaign
	 */

	// Constructors
	public ParseControlCampaign(IndicesParse<Campaign> indicesParse) {
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
	 * @param two instances of Class Campaign
	 * @return a boolean value
	 */
	@Override
	public boolean equalObjects(Campaign objectOne, Campaign objectTwo) {
		return objectOne.equals(objectTwo);
	}
	
	/*
	 * This method performs the processing and registration of instances of Class Campaign
	 */
	@Override
	public void registeringInstances() throws ParseException {
		ArrayList<Campaign> listCampaign = new ArrayList<>();
		
		boolean wasElected;
		for(int i = 0; i < this.listInstance.size(); i++) {
			
			wasElected = false;
			
			if(listCampaign.contains(this.listInstance.get(i))) {
				continue;
			}
			
			for(int j = i; j < this.listInstance.size(); j++) {
				if(this.listInstance.get(i).getCampaignNameOfUrn().equalsIgnoreCase(this.listInstance.get(j).getCampaignNameOfUrn())) {
					if(this.listInstance.get(j).getCampaignResult().getResultType() == 1 ||
					   this.listInstance.get(j).getCampaignResult().getResultType() == 5) {
						wasElected = true;
						listCampaign.add(this.listInstance.get(j));
						break;
					}
				}
			}
			
			if(!wasElected) {
				this.listInstance.get(i).getCampaignResult().setResultType(4);
				listCampaign.add(this.listInstance.get(i));
			}

		}
		
		this.listInstance = new ArrayList<>(listCampaign);
		super.registeringInstances();
	}

}