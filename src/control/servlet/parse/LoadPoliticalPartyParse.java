package control.servlet.parse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.servlet.basic.BasicParseServlet;

// Called in: parseView.jsp
@WebServlet("/loadPoliticalPartyParse")
public class LoadPoliticalPartyParse extends BasicParseServlet {

	/*
	 * Servlet asked by the View to extract a Data File of Political Parties and
	 * Send to Party Parse Control Classes to populate the information extracted
	 * in the database
	 */

	private static final long serialVersionUID = -5159983967292605199L;

	/**
	 * This service method pass the request and response forward and the
	 * Political File Type to be used by readDataFile method (super) to populate
	 * the file informations in database
	 * 
	 * @param request
	 *            values received from the View
	 * @param response
	 *            values received from the View
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		readDataFile(request, response, PARTY_FILE_TYPE);
	}
}
