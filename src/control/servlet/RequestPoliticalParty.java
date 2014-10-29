package control.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Party;
import control.PartyControl;
import control.servlet.basic.Logic;

public class RequestPoliticalParty implements Logic {
	
	/*
	 * Servlet to request control of political parties
	 */

	// Attributes
	private PartyControl control;
	private List<Party> politicalPartyList;

	private HttpServletRequest req;

	private int firstPoliticalParty;
	private int quantityPoliticalPartyPerPage;
	private boolean seeAllPoliticalParties;
	private int currentPoliticalParty;

	private int index;
	private int quantityOfPP;

	// Other methods
	/*
	 * Concretizing method that implements requests for requests the political parties
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.req = req;
		
		// Call refactored methods
		receiveListParameters();
		setParameters();
		prepareParametersTransmission();
		
		// Returns the page with the list of candidates
		return "/list_political_party.jsp";	
	}

	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void receiveListParameters() {
		this.firstPoliticalParty = Integer.parseInt(this.req.getParameter("firstPoliticalParty"));
		this.quantityPoliticalPartyPerPage = Integer.parseInt(this.req.getParameter("quantityPoliticalPartyPerPage"));
		this.seeAllPoliticalParties = Boolean.parseBoolean(this.req.getParameter("seeAllPoliticalParties"));
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws SQLException {
		this.control = new PartyControl();
		this.politicalPartyList = control.getListAllParties();
		this.index = generateListIndex(this.politicalPartyList,this.quantityPoliticalPartyPerPage);
		this.quantityOfPP = generatePageIndex(this.politicalPartyList);
		this.currentPoliticalParty = (int) Math.round((float) this.firstPoliticalParty / (float) this.quantityPoliticalPartyPerPage)+1;
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.req.setAttribute("politicalPartyList", this.politicalPartyList);
		this.req.setAttribute("firstPoliticalParty", this.firstPoliticalParty);
		
		// Defines the number of pages is equal to the size of the candidate list
		if(this.seeAllPoliticalParties) {
			this.quantityPoliticalPartyPerPage = this.politicalPartyList.size();
		}
		this.req.setAttribute("quantityPoliticalPartyPerPage", this.quantityPoliticalPartyPerPage);
		this.req.setAttribute("index", this.index);
		this.req.setAttribute("quantityOfPP", this.quantityOfPP);
		this.req.setAttribute("currentPoliticalParty", this.currentPoliticalParty);
	}

	/*
	 * Generate indexes for list of candidates
	 * @param a list of candidates and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int generateListIndex(List<Party> list, int divider) {
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
	private int generatePageIndex(List<Party> list) {
		int index = (int) Math.floor((double)list.size()/(double)25);
		return index;
	}
}
