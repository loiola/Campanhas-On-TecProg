package control.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Candidate;
import control.CandidateControl;
import control.servlet.basic.Logic;
import control.servlet.listpage.ListPaginationLogic;

public class VisualizeResultOfSearchCandidateList implements Logic {

	/*
	 * Servlet requests to control display search result list of candidates
	 */

	// Attributes

	// Attribute that characterizes an instance of candidate's control
	private CandidateControl control;

	// Attribute that characterizes list of candidates
	private List<Candidate> listCandidates;

	// Attribute that characterizes passed for search candidate for your name
	private String nameOfCandidateSearch;

	// Attribute that characterizes a servletRequest of servlet
	private HttpServletRequest servletRequest;

	// Other methods
	/*
	 * Concretizing method that implements requests for display the result of
	 * the search list of candidates
	 * 
	 * @param an HTTP servletRequest and HTTP response
	 * 
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.servletRequest = request;

		// Call refactored methods
		receiveParameters();

		String passForwardPage = checkCandidatesList();

		return passForwardPage;
	}

	private String checkCandidatesList() {

		String pageToBeReturned = "/error_candidate_nonexistent.jsp";
		if (!this.listCandidates.isEmpty()) {
			prepareParametersTransmission();

			pageToBeReturned = "/visualize_list_candidates.jsp";
		}

		return pageToBeReturned;
	}

	/*
	 * Receive methods for the parameters of the servletRequest
	 */
	private void receiveParameters() throws SQLException {
		this.nameOfCandidateSearch = this.servletRequest
				.getParameter("nameOfCandidateSearch");
		this.control = new CandidateControl();
		this.listCandidates = this.control
				.getListCandidate(this.nameOfCandidateSearch);
	}

	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.servletRequest.setAttribute("listCandidates", this.listCandidates);
		this.servletRequest.setAttribute("nameOfCandidateSearch",
				this.nameOfCandidateSearch);
		this.servletRequest = ListPaginationLogic.updatePaginationList(
				this.servletRequest, this.listCandidates.size(),
				ListPaginationLogic.STANDARD_EXPECTED_PAGINATION_NAME);
	}
}
