package control.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Party;
import control.PartyControl;

public class RequisitarPartido implements Logica {
	
	/*
	 * Servlet to request control of political parties
	 */

	// Attributes
	private PartyControl controle;
	private List<Party> listaPartidos;

	private HttpServletRequest req;

	private int inicio;
	private int qtdPorPagina;
	private boolean verTodos;
	private int atual;

	private int indice;
	private int qtdDePP;

	// Other methods
	/*
	 * Concretizing method that implements requests for requests the political parties
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.req = req;
		
		// Call refactored methods
		recebeParametrosDeListagem();
		estabeleceParametros();
		preparaEnvioDeParametros();
		
		// Returns the page with the list of candidates
		return "/listar_partidos.jsp";	
	}

	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void recebeParametrosDeListagem() {
		this.inicio = Integer.parseInt(this.req.getParameter("inicio"));
		this.qtdPorPagina = Integer.parseInt(this.req.getParameter("qtdPorPagina"));
		this.verTodos = Boolean.parseBoolean(this.req.getParameter("verTodos"));
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void estabeleceParametros() throws SQLException {
		this.controle = new PartyControl();
		this.listaPartidos = controle.getListaTodosPartidos();
		this.indice = geraIndiceDaLista(this.listaPartidos,this.qtdPorPagina);
		this.qtdDePP = geraIndiceDePaginacao(this.listaPartidos);
		this.atual = (int) Math.round((float) this.inicio / (float) this.qtdPorPagina)+1;
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void preparaEnvioDeParametros() {
		this.req.setAttribute("listaPartidos", this.listaPartidos);
		this.req.setAttribute("inicio", this.inicio);
		
		// Defines the number of pages is equal to the size of the candidate list
		if(this.verTodos) {
			this.qtdPorPagina = this.listaPartidos.size();
		}
		this.req.setAttribute("qtdPorPagina", this.qtdPorPagina);
		this.req.setAttribute("indice", this.indice);
		this.req.setAttribute("qtdDePP", this.qtdDePP);
		this.req.setAttribute("atual", this.atual);
	}

	/*
	 * Generate indexes for list of candidates
	 * @param a list of candidates and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int geraIndiceDaLista(List<Party> lista, int divisor) {
		if(divisor!=0) {
			int indice = (int) Math.ceil((double)lista.size()/(double)divisor);
			return indice;
		} else {
			return 1;
		}
	}
	
	/*
	 * Generate index list for paging
	 * @param a list of candidates
	 * @return a number representing the index of paging
	 */
	private int geraIndiceDePaginacao(List<Party> lista) {
		int indice = (int) Math.floor((double)lista.size()/(double)25);
		return indice;
	}
}
