package control.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Campaign;
import model.beans.Party;
import control.CampaignControl;
import control.PartyControl;
import control.servlet.basic.Logic;

public class VisualizeCandidateOfPoliticalParty implements Logic {
	
	/*
	 * Servlet request to control display political candidate
	 */

	// Attributes
	
	// Attribute that characterizes an instance of campaign's control 
	private CampaignControl campaignControl;
	
	// Attribute that characterizes list of campaign
	private ArrayList<Campaign> listCampaign;
	
	// Attribute that characterizes an instance of political party's control
	private PartyControl partyControl;
	
	// Attribute that characterizes a political party 
	private Party party;

	// Attribute that characterizes the acronym of a political party
	private String partyAcronym;
	
	// Attribute that characterizes a electionYear
	private String year;

	// Attribute that characterizes a request of servlet 
	private HttpServletRequest request;

	// Variable that characterizes the first candidate of political party shows in page
	private int first;
	
	// Variable that characterizes the quantity of candidate of political party shows per page
	private int quantityPerPage;
	
	// Variable that characterizes all candidate of political party shows per page
	private boolean seeAllPoliticalParties;
	
	
	private int actual;
	
	// Variable that characterizes candidate of political party index
	private int index;
	
	// Variable that characterizes numbers of links for shows the quantity of candidates per page
	private int quantityOfPP;

	// Other methods
	/*
	 * Concretizing method that implements requests for display of a
	 * candidate in all their years of election campaign 
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.request = req;
		
		//Call refactored methods
		receiveParameters();
		setParameters();
		prepareParametersTransmission();

		return "/visualize_candidate_party.jsp";
	}

	/*
	 * Receive methods for the parameters of the request
	 */
	private void receiveParameters() {
		this.partyAcronym = request.getParameter("partyAcronym");
		this.year =  request.getParameter("year");
		this.first = Integer.parseInt(request.getParameter("first"));
		this.quantityPerPage = Integer.parseInt(request.getParameter("quantityPerPage"));
		this.seeAllPoliticalParties = Boolean.parseBoolean(request.getParameter("seeAllPoliticalParties"));
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws SQLException {
		this.campaignControl = new CampaignControl();
		this.partyControl = new PartyControl();
		this.party = this.partyControl.getBySigla(this.partyAcronym);
		this.listCampaign = new ArrayList<>();
		this.listCampaign = this.campaignControl
				.getListCampaignBySiglaPartyAndYear(this.partyAcronym,this.year);
		this.index = generateCandidateListIndexes(this.listCampaign,this.quantityPerPage);
		this.quantityOfPP = generatePageIndex(this.listCampaign);
		
		// Sets the amount of per page
		this.actual = (int) Math.round((float) this.first / (float) this.quantityPerPage)+1;
	}

	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.request.setAttribute("year", this.year);
		this.request.setAttribute("listCampaign", this.listCampaign);
		this.request.setAttribute("party", this.party);
		this.request.setAttribute("partyAcronym", this.partyAcronym);
		this.request.setAttribute("first", this.first);
		
		// Defines the number of pages is equal to the size of the candidate list
		if(this.seeAllPoliticalParties) {
			this.quantityPerPage = this.listCampaign.size();
		}
		this.request.setAttribute("quantityPerPage", this.quantityPerPage);
		this.request.setAttribute("index", this.index);
		this.request.setAttribute("quantityOfPP", this.quantityOfPP);
		this.request.setAttribute("actual", this.actual);
	}

	/*
	 * Generate indexes for list of candidates
	 * @param a list of candidates and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int generateCandidateListIndexes(List<Campaign> list, int divider) {
		if(divider!=0) {
			int index = (int) Math.ceil((double)list.size()/(double)divider);
			return index;
		} else {
			return 1;
		}
	}

	/*
	 * Generate index list for paging
	 * @param a list of candidates
	 * @return a number representing the index of paging
	 */
	private int generatePageIndex(List<Campaign> list) {
		int index = (int) Math.floor((double)list.size()/(double)25);
		return index;
	}
}
