package control.servlet;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Candidate;
import control.CandidateControl;

public class VisualizarResultadoListaBuscaCandidato implements Logica {
	
	/*
	 * Servlet requests to control display search result list of candidates
	 */

	// Attributes
	private CandidateControl controle;
	private List<Candidate> listaCandidatos;

	private String nome;

	private HttpServletRequest req;

	private int inicio;
	private int qtdPorPagina;
	private boolean verTodos;
	private int indice;
	private int qtdDePP;
	
	private int centro;
	private int minRaio;
	private int maxRaio;
	
	// Other methods
	/*
	 * Concretizing method that implements requests for display
	 * the result of the search list of candidates
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.req = req;
		
		// Call refactored methods
		recebeParametros();

		if(this.listaCandidatos.isEmpty()) {
			
			// Returns an error page if the list is empty
			return "/erro_candidato_inexistente.jsp";
		} else {
			
			// Otherwise, call refactored methods
			estabeleceParametros();
			estabeleceRaioDePaginacao();
			preparaEnvioDeParametros();

			// And returns the page with the result of requests
			return "/visualizar_lista_candidatos.jsp";
		}
	}

	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void recebeParametros() throws SQLException {
		this.nome = this.req.getParameter("nome");
		this.inicio = Integer.parseInt(this.req.getParameter("inicio"));
		this.qtdPorPagina = Integer.parseInt(this.req.getParameter("qtdPorPagina"));
		this.verTodos = Boolean.parseBoolean(this.req.getParameter("verTodos"));
		this.centro = Integer.parseInt(this.req.getParameter("centro"));
		this.controle = new CandidateControl();
		this.listaCandidatos = this.controle.getListCandidate(this.nome);
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void estabeleceParametros() {
		this.indice = geraIndiceDaLista(this.listaCandidatos,this.qtdPorPagina);
		this.qtdDePP = geraIndiceDePaginacao(this.listaCandidatos);
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void preparaEnvioDeParametros() {
		this.req.setAttribute("listaCandidatos", this.listaCandidatos);
		this.req.setAttribute("nome", this.nome);
		this.req.setAttribute("inicio", this.inicio);
		
		// Defines the number of pages is equal to the size of the candidate list
		if(this.verTodos) {
			this.qtdPorPagina = this.listaCandidatos.size();
		}
		this.req.setAttribute("qtdPorPagina", this.qtdPorPagina);
		this.req.setAttribute("indice", this.indice);
		this.req.setAttribute("qtdDePP", this.qtdDePP);	
		this.req.setAttribute("centro", this.centro);
		this.req.setAttribute("minRaio", this.minRaio);
		this.req.setAttribute("maxRaio", this.maxRaio);
	}	
	
	/*
	 * Generate indexes for list of candidates
	 * @param a list of candidates and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int geraIndiceDaLista(List<Candidate> lista, int divisor) {
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
	private int geraIndiceDePaginacao(List<Candidate> lista) {
		int indice = (int) Math.floor((double) lista.size() / (double) 25);
		if(indice >= 4 && indice < 10)
			return 4;
		else if(indice >= 10 && indice < 20)
			return 5;
		else if(indice >= 20 && indice < 40)
			return 6;
		else if(indice >= 40 && indice < 80)
			return 7;
		else if(indice >= 80)
			return 8;
		return indice;
	}
	
	/*
	 * Logic for implementing paging
	 */
	private void estabeleceRaioDePaginacao() {
		int cont = 0;
		if(this.indice > 9) {
			cont = 9;
		} else {
			cont = this.indice - 1;
		}
		
		int raioMin = this.centro;
		int raioMax = this.centro;
		this.minRaio = 0;
		this.maxRaio = 0;
		while(cont != 0) {
			if(raioMin == 1)
				this.maxRaio++;
			else if(this.minRaio < 5) {
				this.minRaio++;
				raioMin--;
			} else if(raioMax == this.indice)
				this.minRaio++;
			else {
				this.maxRaio++;
				raioMax++;
			}
			cont--;
		}
		this.maxRaio += this.centro;
		this.minRaio = this.centro - this.minRaio;
	}
}
