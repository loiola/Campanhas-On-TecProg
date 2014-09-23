package control.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Party;
import control.PartyControl;

public class SelectPoliticalParty implements Logic {
	
	/*
	 * Servlet requests to control the selection of political parties
	 */

	// Attributes
	private final int[] YEARS = {2010, 2006, 2002};
	private PartyControl partyControl;
	private Party party;

	private String partyAcronym;
	private String siglaWithUnder;
	private String linkTSE;

	// Other methods
	/*
	 * Concretizing  method that implements requests for the selection of one of the
	 * political parties in accordance with its logo and other information
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.party = new Party();
		this.partyControl = new PartyControl();

		this.partyAcronym = req.getParameter("partyAcronym");
		this.siglaWithUnder = this.partyAcronym.replaceAll(" ", "_");
		this.siglaWithUnder = this.siglaWithUnder.toLowerCase();

		this.party = this.partyControl.getBySigla(this.partyAcronym);
		this.linkTSE = changeSpecialCharacters(this.party);

		// Set of answers to requests made concerning the applicant requested
		req.setAttribute("party", this.party);
		req.setAttribute("years", this.YEARS);
		req.setAttribute("linktse", this.linkTSE);
		req.setAttribute("siglaUnder", this.siglaWithUnder);

		// Return the HTML page with the requested information
		return "/visualize_political_party.jsp";
	}
	
	/*
	 * Method for exchanging special characters emanating from the database, if any
	 * @param a political parties
	 * @return a String with exchanges
	 */
	private String changeSpecialCharacters(Party party) {
		String local = party.getPartyName().toLowerCase();
		local = local.replaceAll(" ", "-");
		local = local.replaceAll("á", "a");
		local = local.replaceAll("ã", "a");
		local = local.replaceAll("ó", "o");
		local = local.replaceAll("ú", "u");
		local = local.replaceAll("ç", "c");
		return local;
	}
}
