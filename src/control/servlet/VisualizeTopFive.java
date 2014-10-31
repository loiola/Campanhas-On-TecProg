package control.servlet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Campaign;
import control.CampaignControl;
import control.servlet.basic.Logic;

@WebServlet("/VisualizeTopFive")
public class VisualizeTopFive implements Logic {
	
	/*
	 * Servlet requests to control display TOP Five
	 */

	// Attributes
	
	// Attribute that characterizes an instance of campaign's control 
	private CampaignControl campaignControl;
	
	// Attribute that characterizes list of campaign
	private ArrayList<Campaign> campaignList;
	
	// Attribute that characterizes a position
	private String position;
	
	// Attribute that characterizes a electionYear
	private Integer electionYear;
	
	// Attribute that characterizes a request of servlet 
	private HttpServletRequest request;
	
	// Other methods
	/*
	 * Concretizing method that implements requests for display
	 * the search result of the TOP Five
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		this.request = req;
		
		// Call refactored methods
		receiveParameters();
		setParameters();
		prepareParametersTransmission();
		
		// Returns the page TOP Five
		return "/top_five.jsp";
	}
	
	/*
	 * Receive methods for the parameters of the request
	 */
	private void receiveParameters() {
		this.electionYear =  Integer.parseInt(this.request.getParameter("electionYear"));
		this.position = this.request.getParameter("position");
	}
	
	/*
	 * Establishes what each parameter will receive
	 */
	private void setParameters() throws SQLException {
		this.campaignControl = new CampaignControl();
		this.campaignList = new ArrayList<>();
		this.campaignList = this.campaignControl.topFiveByPositionAndYear(this.position, this.electionYear);
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void prepareParametersTransmission() {
		this.request.setAttribute("electionYear", this.electionYear);
		this.request.setAttribute("position", this.position);
		this.request.setAttribute("candidate1", this.campaignList.get(0));
		this.request.setAttribute("candidate2", this.campaignList.get(1));
		this.request.setAttribute("candidate3", this.campaignList.get(2));
		this.request.setAttribute("candidate4", this.campaignList.get(3));
		this.request.setAttribute("candidate5", this.campaignList.get(4));
		
	}
}
