package controle.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Despesa;
import modelo.beans.Receita;

import controle.CampanhaControle;
import controle.CandidatoControle;
import controle.MovimentacaoControle;

public class SelecionarCandidato implements Logica {
	
	/*
	 * Servlet requests to control the selection of political candidates
	 */

	// Attributes
	private CandidatoControle candidatoControle;
	private Candidate candidate;
	private CampanhaControle campanhaControle;
	private List<Campaign> listaCampanha;

	private String tituloEleitoral;
	
	private MovimentacaoControle movimentacaoControle;
	private List<Receita> listaReceita;
	private List<Despesa> listaDespesa;
	
	private float despesaMax2002;
	private float despesaMax2006;
	private float despesaMax2010;
	
	private float despesaCalc2002;
	private float receitaCalc2002;
	
	private float despesaCalc2006;
	private float receitaCalc2006;
	
	private float despesaCalc2010;
	private float receitaCalc2010;

	
	// Other methods
	/*
	 * Concretizing method that implements requests for selection of a
	 * candidate in all their years of election campaign 
	 * @param an HTTP request and HTTP response
	 * @return the next HTML page and their responses to requests
	 */
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		this.despesaMax2002 = 0;
		this.despesaMax2006 = 0;
		this.despesaMax2010 = 0;
		
		this.tituloEleitoral = req.getParameter("tituloEleitoral");

		this.candidatoControle = new CandidatoControle();
		this.campanhaControle = new CampanhaControle();
		this.movimentacaoControle = new MovimentacaoControle();
		
		this.candidate = this.candidatoControle.getUmCandidato(this.tituloEleitoral);

		this.listaCampanha = this.campanhaControle.getListaCampanhas(this.candidate);
		
		// Loop to receive all the necessary information about their candidate campaigns
		for(Campaign campaign : listaCampanha) {
			
			// Condition for receiving data from the 2002 campaign if there
			if(campaign.getAno() == 2002) {
				this.listaReceita = 
						this.movimentacaoControle.getListaReceitas(campaign);
				this.listaDespesa = 
						this.movimentacaoControle.getListaDespesas(campaign);
				this.despesaMax2002 = campaign.getDespesaMaxDeclarada();
				for(Receita receita : listaReceita)
					this.receitaCalc2002 += receita.getValor();
				for(Despesa despesa : listaDespesa)
					this.despesaCalc2002 += despesa.getValor();
				
				campaign.setDespesaTotalCalculada(this.despesaCalc2002);
				campaign.setReceitaTotalCalculada(this.receitaCalc2002);
			}
			
			// Condition for receiving data from the 2006 campaign if there
			else if(campaign.getAno() == 2006) {
				this.listaReceita = 
						this.movimentacaoControle.getListaReceitas(campaign);
				this.listaDespesa = 
						this.movimentacaoControle.getListaDespesas(campaign);
				this.despesaMax2006 = campaign.getDespesaMaxDeclarada();
				for(Receita receita : listaReceita)
					this.receitaCalc2006 += receita.getValor();
				for(Despesa despesa : listaDespesa)
					this.despesaCalc2006 += despesa.getValor();

				campaign.setDespesaTotalCalculada(this.despesaCalc2006);
				campaign.setReceitaTotalCalculada(this.receitaCalc2006);
			}
			
			// Condition for receiving data from the 2010 campaign if there
			else if(campaign.getAno() == 2010) {
				this.listaReceita = 
						this.movimentacaoControle.getListaReceitas(campaign);
				this.listaDespesa = 
						this.movimentacaoControle.getListaDespesas(campaign);
				this.despesaMax2010 = campaign.getDespesaMaxDeclarada();
				for(Receita receita : listaReceita)
					this.receitaCalc2010 += receita.getValor();
				for(Despesa despesa : listaDespesa)
					this.despesaCalc2010 += despesa.getValor();
			
				campaign.setDespesaTotalCalculada(this.despesaCalc2010);
				campaign.setReceitaTotalCalculada(this.receitaCalc2010);
			}
		}
		
		// Set of answers to requests made concerning the applicant requested
		req.setAttribute("candidate", this.candidate);
		req.setAttribute("campanhas", this.listaCampanha);
		req.setAttribute("candidate", this.candidate);
		req.setAttribute("campanhas", this.listaCampanha);
		req.setAttribute("despesa1", this.despesaMax2002);
		req.setAttribute("despesa2", this.despesaMax2006);
		req.setAttribute("despesa3", this.despesaMax2010);
		req.setAttribute("despesa01", this.despesaCalc2002);
		req.setAttribute("receita1", this.receitaCalc2002);
		req.setAttribute("despesa02", this.despesaCalc2006);
		req.setAttribute("receita2", this.receitaCalc2006);
		req.setAttribute("despesa03", this.despesaCalc2010);
		req.setAttribute("receita3", this.receitaCalc2010);
		
		// Return the HTML page with the requested information
		return "/visualizar_candidato.jsp";
	}
}
