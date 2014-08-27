package controle.servlet;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.beans.Campanha;
import modelo.beans.Cargo;
import modelo.beans.Despesa;
import modelo.beans.Receita;
import controle.CampanhaControle;
import controle.MovimentacaoControle;

public class RequisitarMovimentacoesDeCandidato implements Logica {
	
	/*
	 * Servlet requests to control display search result list of financial transactions a candidate
	 */

	// Attributes
	private CampanhaControle campanhaControle;
	private Campanha campanhaBusca;
	private Campanha campanha;
	private MovimentacaoControle movimentacaoControle;

	private String despesaTot;

	private float despesaTC;
	private float receitaTC;

	private List<Receita> listaReceita;
	private List<Despesa> listaDespesa;

	private HttpServletRequest req;

	private int inicioR;
	private int inicioD;
	private int qtdPorPaginaR;
	private int qtdPorPaginaD;
	private boolean verTodosR;
	private boolean verTodosD;
	private int indiceR;
	private int indiceD;
	private int qtdDePPR;
	private int qtdDePPD;
	private int centroR;
	private int minRaioR;
	private int maxRaioR;
	private int centroD;
	private int minRaioD;
	private int maxRaioD;

	// Other methods
	/*
	 * Concretizing method that implements requests for display
	 * the result of the search list of financial transactions
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		this.req = req;
		
		// Call refactored methods
		recebeParametros();

		if(this.campanha == null) {
			
			// Returns an error page if the list is empty
			return "/erro_candidato_inexistente.jsp";
		} else {
			
			// Otherwise, call refactored methods
			estabeleceParametros();
			estabeleceRaioDePaginacaoR();
			estabeleceRaioDePaginacaoD();
			preparaEnvioDeParametros();

			// And returns the page with the result of requests
			return "/visualizar_movimentacoes.jsp";
		}
	}

	/*
	 * Rebecer methods for the parameters of the request
	 */
	private void recebeParametros() throws SQLException {
		this.campanhaBusca = constroiCampanha(this.req);
		this.receitaTC = Float.parseFloat(this.req.getParameter("receitaTC"));
		this.despesaTC = Float.parseFloat(this.req.getParameter("despesaTC"));
		this.inicioR = Integer.parseInt(this.req.getParameter("inicioR"));
		this.qtdPorPaginaR = Integer.parseInt(this.req
				.getParameter("qtdPorPaginaR"));
		this.verTodosR = Boolean.parseBoolean(this.req
				.getParameter("verTodosR"));
		this.centroR = Integer.parseInt(this.req.getParameter("centroR"));
		this.inicioD = Integer.parseInt(this.req.getParameter("inicioD"));
		this.qtdPorPaginaD = Integer.parseInt(this.req
				.getParameter("qtdPorPaginaD"));
		this.verTodosD = Boolean.parseBoolean(this.req
				.getParameter("verTodosD"));
		this.centroD = Integer.parseInt(this.req.getParameter("centroD"));
		this.campanhaControle = new CampanhaControle();
		this.campanha = this.campanhaControle
				.getPeloAnoNumeroCodCargoEUf(this.campanhaBusca);
		this.movimentacaoControle = new MovimentacaoControle();
	}

	/*
	 * Establishes what each parameter will receive
	 */
	private void estabeleceParametros() throws Exception {
		this.despesaTot = formataDespesa(this.campanha.getDespesaMaxDeclarada());
		this.listaReceita = this.movimentacaoControle
				.getListaReceitas(this.campanha);
		this.listaDespesa = this.movimentacaoControle
				.getListaDespesas(this.campanha);
		this.indiceR = geraIndiceDaListaR(this.listaReceita, this.qtdPorPaginaR);
		this.qtdDePPR = geraIndiceDePaginacaoR(this.listaReceita);
		this.indiceD = geraIndiceDaListaD(this.listaDespesa, this.qtdPorPaginaD);
		this.qtdDePPD = geraIndiceDePaginacaoD(this.listaDespesa);
	}
	
	/*
	 * Prepare responses of forwarded requests
	 */
	private void preparaEnvioDeParametros() {
		this.req.setAttribute("listaReceitas", this.listaReceita);
		this.req.setAttribute("listaDespesas", this.listaDespesa);
		this.req.setAttribute("campanha", this.campanha);
		this.req.setAttribute("depesaTot", this.despesaTot);
		this.req.setAttribute("despesaTC", despesaTC);
		this.req.setAttribute("receitaTC", receitaTC);
		this.req.setAttribute("inicioR", this.inicioR);
		
		// Defines the number of pages is equal to the size of the candidate list
		if (this.verTodosR) {
			this.qtdPorPaginaR = this.listaReceita.size();
		}
		this.req.setAttribute("qtdPorPaginaR", this.qtdPorPaginaR);
		this.req.setAttribute("indiceR", this.indiceR);
		this.req.setAttribute("qtdDePPR", this.qtdDePPR);
		this.req.setAttribute("inicioD", this.inicioD);
		
		// Defines the number of pages is equal to the size of the candidate list
		if (this.verTodosD) {
			this.qtdPorPaginaD = this.listaDespesa.size();
		}
		this.req.setAttribute("qtdPorPaginaD", this.qtdPorPaginaD);
		this.req.setAttribute("indiceD", this.indiceD);
		this.req.setAttribute("qtdDePPD", this.qtdDePPD);
		this.req.setAttribute("centroR", this.centroR);
		this.req.setAttribute("minRaioR", this.minRaioR);
		this.req.setAttribute("maxRaioR", this.maxRaioR);
		this.req.setAttribute("centroD", this.centroD);
		this.req.setAttribute("minRaioD", this.minRaioD);
		this.req.setAttribute("maxRaioD", this.maxRaioD);
	}
	
	/*
	 * Generate indexes for list of receipt
	 * @param a list of receipt and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int geraIndiceDaListaR(List<Receita> lista, int divisor) {
		if(divisor != 0) {
			int indice = (int) Math.ceil((double) lista.size()
					/ (double) divisor);
			return indice;
		} else {
			return 1;
		}
	}

	/*
	 * Generate index list for paging of receipt
	 * @param a list of receipt
	 * @return a number representing the index of paging
	 */
	private int geraIndiceDePaginacaoR(List<Receita> lista) {
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
	 * Generate indexes for list of expenses
	 * @param a list of expenses and a number that is a divisor
	 * @return a number representing the index of list
	 */
	private int geraIndiceDaListaD(List<Despesa> lista, int divisor) {
		if(divisor != 0) {
			int indice = (int) Math.ceil((double) lista.size()
					/ (double) divisor);
			return indice;
		} else {
			return 1;
		}
	}

	/*
	 * Generate index list for paging of expenses
	 * @param a list of expenses
	 * @return a number representing the index of paging
	 */
	private int geraIndiceDePaginacaoD(List<Despesa> lista) {
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
	 * Logic for implementing paging of receipt
	 */
	private void estabeleceRaioDePaginacaoR() {
		int cont = 0;
		if(this.indiceR > 9) {
			cont = 9;
		} else {
			cont = this.indiceR - 1;
		}
		
		int raioMin = this.centroR;
		int raioMax = this.centroR;
		this.minRaioR = 0;
		this.maxRaioR = 0;
		while(cont != 0) {
			if(raioMin == 1)
				this.maxRaioR++;
			else if(this.minRaioR < 5) {
				this.minRaioR++;
				raioMin--;
			} else if(raioMax == this.indiceR)
				this.minRaioR++;
			else {
				this.maxRaioR++;
				raioMax++;
			}
			cont--;
		}
		this.maxRaioR += this.centroR;
		this.minRaioR = this.centroR - this.minRaioR;
	}

	/*
	 * Logic for implementing paging of expenses
	 */
	private void estabeleceRaioDePaginacaoD() {
		int cont = 0;
		if(this.indiceD > 9) {
			cont = 9;
		} else {
			cont = this.indiceD - 1;
		}
		
		int raioMin = this.centroD;
		int raioMax = this.centroD;
		this.minRaioD = 0;
		this.maxRaioD = 0;
		while(cont != 0) {
			if(raioMin == 1)
				this.maxRaioD++;
			else if(this.minRaioD < 5) {
				this.minRaioD++;
				raioMin--;
			} else if(raioMax == this.indiceD)
				this.minRaioD++;
			else {
				this.maxRaioD++;
				raioMax++;
			}
			cont--;
		}
		this.maxRaioD += this.centroD;
		this.minRaioD = this.centroD - this.minRaioD;
	}
	
	/*
	 * Logic to implement the format of expenses
	 * @param an expenses
	 * @return a String with the correct format
	 */
	private String formataDespesa(Float despesa) {
		DecimalFormatSymbols padraoBrasileiro = new DecimalFormatSymbols(
				Locale.GERMAN);

		DecimalFormat df = new DecimalFormat("###,###,##0.00", padraoBrasileiro);
		String despesaTot = df.format(despesa);

		despesaTot = "R$ " + despesaTot;

		return despesaTot;
	}

	/*
	 * Set a political campaign according to the parameters requested by
	 * @param  an HTTP request
	 * @return a political campaign
	 */
	private Campanha constroiCampanha(HttpServletRequest req) {

		int ano = Integer.parseInt(req.getParameter("ano"));
		int numero = Integer.parseInt(req.getParameter("numero_cand"));
		int cargo_cod = Integer.parseInt(req.getParameter("cargo_cod"));
		String uf = req.getParameter("uf");

		Cargo cargo = new Cargo();
		cargo.setCodigo(cargo_cod);

		Campanha campanha = new Campanha();
		campanha.setNumeroCandidato(numero);
		campanha.setAno(ano);
		campanha.setCargo(cargo);
		campanha.setUf(uf);

		return campanha;
	}
}
