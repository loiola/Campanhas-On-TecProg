package control.servlet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.general.ControlLogger;
import model.beans.Campaign;
import model.beans.Party;
import control.CampaignControl;
import control.PartyControl;
import control.servlet.basic.Logic;
import control.servlet.listpage.ListPaginationLogic;

public class VisualizeCandidateOfPoliticalParty implements Logic {

	/*
	 * Servlet servletRequest to control display political candidate
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

	// Attribute that characterizes a servletRequest of servlet
	private HttpServletRequest servletRequest;

	// Other methods
	/*
	 * Concretizing method that implements requests for display of a candidate
	 * in all their years of election campaign
	 * 
	 * @param an HTTP servletRequest and HTTP response
	 * 
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ControlLogger.info(ControlLogger.SERVLET_LOG_STRING,
				ControlLogger.INFORM_BEGIN_CALLED_METHOD);

		this.servletRequest = request;

		setParameters();
		prepareParametersTransmission();

		// Returns the page with the list of candidates
		String forwardPageLink = "/visualize_candidate_party.jsp"; 
		ControlLogger.info(
				ControlLogger.SERVLET_LOG_STRING,
				ControlLogger.INFORM_END_CALLED_METHOD
						+ ControlLogger.returnInformations(forwardPageLink));
		return forwardPageLink;
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws SQLException {
		this.partyAcronym = servletRequest.getParameter("partyAcronym");
		this.year = servletRequest.getParameter("year");
		this.campaignControl = new CampaignControl();
		this.partyControl = new PartyControl();
		this.party = this.partyControl.getBySigla(this.partyAcronym);
		this.listCampaign = new ArrayList<>();
		this.listCampaign = this.campaignControl
				.getListCampaignByPartyAcronymAndYear(this.partyAcronym,
						this.year);

		this.servletRequest = ListPaginationLogic.updatePaginationList(
				servletRequest, listCampaign.size(),
				ListPaginationLogic.STANDARD_EXPECTED_PAGINATION_NAME);
	}

	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.servletRequest.setAttribute("year", this.year);
		this.servletRequest.setAttribute("listCampaign", this.listCampaign);
		this.servletRequest.setAttribute("party", this.party);
		this.servletRequest.setAttribute("partyAcronym", this.partyAcronym);
	}
}
