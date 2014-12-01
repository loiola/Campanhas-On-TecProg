package control.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.general.ControlLogger;
import model.beans.Party;
import control.PartyControl;
import control.servlet.basic.Logic;
import control.servlet.listpage.ListPaginationLogic;

public class RequestPoliticalParty implements Logic {

	/*
	 * Servlet to request control of political parties
	 */

	// Attributes

	// Attribute that characterizes an instance of political party's control
	private PartyControl control;

	// Attribute that characterizes list of political parties of Brazil
	private List<Party> politicalPartyList;

	// Attribute that characterizes a request of servlet
	private HttpServletRequest servletRequest;

	// Other methods
	/*
	 * Concretizing method that implements requests for requests the political
	 * parties
	 * 
	 * @param an HTTP request and HTTP response
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
		
		String forwardPageLink = "/list_political_party.jsp";
		ControlLogger.info(ControlLogger.SERVLET_LOG_STRING,
				ControlLogger.INFORM_END_CALLED_METHOD
						+ "\nThe returned parameter has the type ["
						+ forwardPageLink.getClass() + "] with value ["
						+ forwardPageLink + "].");
		// Returns the page with the list of parties
		return forwardPageLink;
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws SQLException {
		this.control = new PartyControl();
		this.politicalPartyList = control.getListAllParties();

		this.servletRequest = ListPaginationLogic.updatePaginationList(
				servletRequest, politicalPartyList.size(),
				ListPaginationLogic.STANDARD_EXPECTED_PAGINATION_NAME);
	}

	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.servletRequest.setAttribute("politicalPartyList",
				this.politicalPartyList);
	}

}