package control.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loadCampaignParse")
public class LoadCampaignParse extends BasicParseServlet {

	/*
	 * Servlet asked by parseView.jsp to extract a Data File of Campaigns and
	 * Send to Campaign Parse Control Classes to populate the information
	 * extracted in the database
	 */

	// Attribute
	private static final long serialVersionUID = 5625867877274809499L;
	
	/**
	 * 
	 * Method that makes the call load and parse controls its execution
	 * 
	 * @param request
	 * @param response
	 */

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		readDataFile(request, response, CAMPAIGN_FILE_TYPE);
	}
}
