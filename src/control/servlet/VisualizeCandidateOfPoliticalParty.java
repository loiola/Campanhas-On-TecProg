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

public class VisualizeCandidateOfPoliticalParty implements Logic {
	
	/*
	 * Servlet request to control display political candidate
	 */

	// Attributes
	private CampaignControl campaignControl;
	private ArrayList<Campaign> listCampaign;
	private PartyControl partyControl;
	private Party party;

	private String sigla;
	private String year;

	private HttpServletRequest req;

	private int first;
	private int quantityPerPage;
	private boolean seeAllPoliticalParties;
	private int actual;

	private int index;
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

		this.req = req;
		
		//Call refactored methods
		receiveParameters();
		setParameters();
		prepareParametersTransmission();

		return "/visualize_candidate_party.jsp";
	}

	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void receiveParameters() {
		this.sigla = req.getParameter("sigla");
		this.year =  req.getParameter("year");
		this.first = Integer.parseInt(req.getParameter("first"));
		this.quantityPerPage = Integer.parseInt(req.getParameter("quantityPerPage"));
		this.seeAllPoliticalParties = Boolean.parseBoolean(req.getParameter("seeAllPoliticalParties"));
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws SQLException {
		this.campaignControl = new CampaignControl();
		this.partyControl = new PartyControl();
		this.party = this.partyControl.getBySigla(this.sigla);
		this.listCampaign = new ArrayList<>();
		this.listCampaign = this.campaignControl
				.getListCampaignBySiglaPartyAndYear(this.sigla,this.year);
		this.index = generateCandidateListIndexes(this.listCampaign,this.quantityPerPage);
		this.quantityOfPP = generatePageIndex(this.listCampaign);
		
		// Sets the amount of per page
		this.actual = (int) Math.round((float) this.first / (float) this.quantityPerPage)+1;
	}

	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.req.setAttribute("year", this.year);
		this.req.setAttribute("listCampaign", this.listCampaign);
		this.req.setAttribute("party", this.party);
		this.req.setAttribute("sigla", this.sigla);
		this.req.setAttribute("first", this.first);
		
		// Defines the number of pages is equal to the size of the candidate list
		if(this.seeAllPoliticalParties) {
			this.quantityPerPage = this.listCampaign.size();
		}
		this.req.setAttribute("quantityPerPage", this.quantityPerPage);
		this.req.setAttribute("index", this.index);
		this.req.setAttribute("quantityOfPP", this.quantityOfPP);
		this.req.setAttribute("actual", this.actual);
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
