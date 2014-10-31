package control.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Candidate;
import control.CandidateControl;
import control.servlet.basic.Logic;

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

	// Attribute that characterizes a request of servlet 
	private HttpServletRequest request;

	// Variable that characterizes the first candidate shows in page
	private int firstCandidate;
	
	// Variable that characterizes the quantity of candidate shows per page
	private int quantityCandidatePerPage;
	
	// Variable that characterizes all candidates shows per page
	private boolean seeAllCandidates;
	
	// Variable that characterizes candidate index
	private int index;
	
	// Variable that characterizes numbers of links for shows the quantity of candidate per page
	private int quantityOfPP;
	
	// Variable that characterizes median of number present in the variable quantityOfPP
	private int centerCandidate;
	
	// Variable that characterizes number minimum displayed variable quantityOfPP
	private int minimumRadius;
	
	// Variable that characterizes number maximum displayed variable quantityOfPP
	private int maximumRadius;
	
	// Other methods
	/*
	 * Concretizing method that implements requests for display
	 * the result of the search list of candidates
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.request = req;
		
		// Call refactored methods
		receiveParameters();

		if(this.listCandidates.isEmpty()) {
			
			// Returns an error page if the list is empty
			return "/error_candidate_nonexistent.jsp";
		} else {
			
			// Otherwise, call refactored methods
			setParameters();
			setPageRadiusPaging();
			prepareParametersTransmission();

			// And returns the page with the result of requests
			return "/visualize_list_candidates.jsp";
		}
	}

	/*
	 * Receive methods for the parameters of the request
	 */
	private void receiveParameters() throws SQLException {
		this.nameOfCandidateSearch = this.request.getParameter("nameOfCandidateSearch");
		this.firstCandidate = Integer.parseInt(this.request.getParameter("firstCandidate"));
		this.quantityCandidatePerPage = Integer.parseInt(this.request.getParameter("quantityCandidatePerPage"));
		this.seeAllCandidates = Boolean.parseBoolean(this.request.getParameter("seeAllCandidates"));
		this.centerCandidate = Integer.parseInt(this.request.getParameter("centerCandidate"));
		this.control = new CandidateControl();
		this.listCandidates = this.control.getListCandidate(this.nameOfCandidateSearch);
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() {
		this.index = generateListIndex(this.listCandidates,this.quantityCandidatePerPage);
		this.quantityOfPP = generatePageIndex(this.listCandidates);
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.request.setAttribute("listCandidates", this.listCandidates);
		this.request.setAttribute("nameOfCandidateSearch", this.nameOfCandidateSearch);
		this.request.setAttribute("firstCandidate", this.firstCandidate);
		
		// Defines the number of pages is equal to the size of the candidate list
		if(this.seeAllCandidates) {
			this.quantityCandidatePerPage = this.listCandidates.size();
		}
		this.request.setAttribute("quantityCandidatePerPage", this.quantityCandidatePerPage);
		this.request.setAttribute("index", this.index);
		this.request.setAttribute("quantityOfPP", this.quantityOfPP);	
		this.request.setAttribute("centerCandidate", this.centerCandidate);
		this.request.setAttribute("minimumRadius", this.minimumRadius);
		this.request.setAttribute("maximumRadius", this.maximumRadius);
	}	
	
	/*
	 * Generate indexes for list of candidates
	 * @param a list of candidates and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int generateListIndex(List<Candidate> list, int divider) {
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
	private int generatePageIndex(List<Candidate> list) {
		int index = (int) Math.floor((double) list.size() / (double) 25);
		if(index >= 4 && index < 10) {
			return 4;
		} else if(index >= 10 && index < 20) {
			return 5;
		} else if(index >= 20 && index < 40) {
			return 6;
		} else if(index >= 40 && index < 80) {
			return 7;
		} else if(index >= 80) {
			return 8;
		}
		return index;
	}
	
	/*
	 * Logic for implementing paging
	 */
	private void setPageRadiusPaging() {
		int counter = 0;
		if(this.index > 9) {
			counter = 9;
		} else {
			counter = this.index - 1;
		}
		
		int minimumRadius = this.centerCandidate;
		int maximumRadius = this.centerCandidate;
		this.minimumRadius = 0;
		this.maximumRadius = 0;
		while(counter != 0) {
			if(minimumRadius == 1) {
				this.maximumRadius++;
			} else if(this.minimumRadius < 5) {
				this.minimumRadius++;
				minimumRadius--;
			} else if(maximumRadius == this.index) {
				this.minimumRadius++;
			} else {
				this.maximumRadius++;
				maximumRadius++;
			}
			counter--;
		}
		this.maximumRadius += this.centerCandidate;
		this.minimumRadius = this.centerCandidate - this.minimumRadius;
	}
}
