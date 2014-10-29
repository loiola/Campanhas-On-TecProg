package control.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.servlet.basic.BasicParseServlet;

// Called in: parseView.jsp
@WebServlet("/loadCampaignParse")
public class LoadCampaignParse extends BasicParseServlet {

	/*
	 * Servlet asked by the View to extract a Data File of Campaigns and
	 * Send to Campaign Parse Control Classes to populate the information
	 * extracted in the database
	 */

	// UID for LoadCampaignParse
	private static final long serialVersionUID = 5625867877274809499L;

	
	/**
	 * This service method pass the request and response forward and the Campaign File Type
	 * to be used by readDataFile method (super) to populate the file informations in database
	 * 
	 * @param request values received from the View
	 * @param response values received from the View
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		readDataFile(request, response, CAMPAIGN_FILE_TYPE);
	}
}
