package control.servlet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Campaign;
import control.CampaignControl;

@WebServlet("/VisualizeTopFive")
public class VisualizeTopFive implements Logic {
	
	/*
	 * Servlet requests to control display TOP Five
	 */

	// Attributes
	private CampaignControl campaignControl;
	private ArrayList<Campaign> campaignList;
	
	private String position;
	private Integer electionYear;
	
	HttpServletRequest req;
	
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
		
		this.req = req;
		
		// Call refactored methods
		receiveParameters();
		setParameters();
		prepareParametersTransmission();
		
		// Returns the page TOP Five
		return "/top_five.jsp";
	}
	
	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void receiveParameters() {
		this.electionYear =  Integer.parseInt(this.req.getParameter("electionYear"));
		this.position = this.req.getParameter("position");
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
		this.req.setAttribute("electionYear", this.electionYear);
		this.req.setAttribute("position", this.position);
		this.req.setAttribute("candidate1", this.campaignList.get(0));
		this.req.setAttribute("candidate2", this.campaignList.get(1));
		this.req.setAttribute("candidate3", this.campaignList.get(2));
		this.req.setAttribute("candidate4", this.campaignList.get(3));
		this.req.setAttribute("candidate5", this.campaignList.get(4));
		
	}
}
