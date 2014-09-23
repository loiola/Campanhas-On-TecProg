package control.servlet;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Campaign;
import control.CampaignControl;

@WebServlet("/VisualizarTopFive")
public class VisualizarTopFive implements Logic {
	
	/*
	 * Servlet requests to control display TOP Five
	 */

	// Attributes
	private CampaignControl campaignControl;
	private ArrayList<Campaign> listaCampanha;
	
	private String cargo;
	private Integer ano;
	
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
		recebeParametros();
		estabeleceParametros();
		preparaEnvioDeParametros();
		
		// Returns the page TOP Five
		return "/top_five.jsp";
	}
	
	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void recebeParametros() {
		this.ano =  Integer.parseInt(this.req.getParameter("ano"));
		this.cargo = this.req.getParameter("cargo");
	}
	
	/*
	 * Establishes what each parameter will receive
	 */
	private void estabeleceParametros() throws SQLException {
		this.campaignControl = new CampaignControl();
		this.listaCampanha = new ArrayList<>();
		this.listaCampanha = this.campaignControl.topFiveByPositionAndYear(this.cargo, this.ano);
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void preparaEnvioDeParametros() {
		this.req.setAttribute("ano", this.ano);
		this.req.setAttribute("cargo", this.cargo);
		this.req.setAttribute("candidato1", this.listaCampanha.get(0));
		this.req.setAttribute("candidato2", this.listaCampanha.get(1));
		this.req.setAttribute("candidato3", this.listaCampanha.get(2));
		this.req.setAttribute("candidato4", this.listaCampanha.get(3));
		this.req.setAttribute("candidato5", this.listaCampanha.get(4));
		
	}
}
