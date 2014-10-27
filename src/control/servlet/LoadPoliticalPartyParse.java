package control.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loadPoliticalPartyParse")
public class LoadPoliticalPartyParse extends BasicParseServlet {

	/*
	 * Servlet asked by parseView.jsp to extract a Data File of Political
	 * Parties and Send to Party Parse Control Classes to populate the
	 * information extracted in the database
	 */

	private static final long serialVersionUID = -5159983967292605199L;
	
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
		readDataFile(request,response,PARTY_PARSE_NAME);
	}
}
