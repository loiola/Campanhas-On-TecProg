package teste;

import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Expense;
import modelo.beans.Donor;
import modelo.beans.Supplier;
import modelo.beans.Revenue;
import modelo.dao.DespesaDAO;
import modelo.dao.ReceitaDAO;

import org.junit.Assert;
import org.junit.Test;

import controle.MovimentacaoControle;

public class MovimentacaoControleTeste extends TemplateTeste {

	private DespesaDAO despesaDAO;
	private ReceitaDAO receitaDAO;
	private MovimentacaoControle movimentacaoControle;
	private Candidate candidate;
	private Campaign campaign;
	private Position position;
	private Revenue revenue;
	private Expense expense;
	private Donor donor;
	private Supplier supplier;
	private Integer ano;
	private String uf;
	private Integer numeroCandidato;

	@Override
	public void beforeTest() throws Exception {
		
		this.despesaDAO = new DespesaDAO();
		this.receitaDAO = new ReceitaDAO();
		this.movimentacaoControle = new MovimentacaoControle();
		this.candidate = new Candidate();
		this.campaign = new Campaign();
		this.revenue = new Revenue();
		this.expense = new Expense();
		this.donor = new Donor();
		this.supplier = new Supplier();
		this.ano = 2014;
		this.uf = "DF";
		this.position = new Position();
		this.numeroCandidato = 1234;
		
		ArrayList<Expense> listaDespesa = new ArrayList<>();
		ArrayList<Revenue> listaReceita = new ArrayList<>();

		candidate.setCandidateName("FULANO");
		candidate.setCandidateElectoralTitle("12345");
		
		position.setDescricao("Presidente");
		
		campaign.setCampaignPosition(position);
		campaign.setCampaignCandidate(candidate);
		campaign.setCampaignYear(this.ano);
		campaign.setCampaignCountryState(this.uf);
		campaign.setCampaignCandidateNumber(this.numeroCandidato);
		
		supplier.setCpf_cnpj("555555555555");
		donor.setCpf_cnpj("333333333333");
		
		revenue.setValor((float) 55.0);
		revenue.setCampanha(campaign);
		revenue.setId(3);
		revenue.setDoador(donor);
		listaReceita.add(revenue);
		
		expense.setValor((float) 90.0);
		expense.setCampanha(campaign);
		expense.setId(5);
		expense.setFornecedor(supplier);
		listaDespesa.add(expense);
		
		this.despesaDAO.cadastrarLista(listaDespesa);
		this.receitaDAO.cadastrarLista(listaReceita);
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmaMovimentacaoPeloId() throws Exception {
		
		Assert.assertEquals(this.receitaDAO.getPeloId(3).getValor(), this.movimentacaoControle.getReceitaPeloId(3).getValor());
		Assert.assertEquals(this.despesaDAO.getPeloId(5).getValor(), this.movimentacaoControle.getDespesaPeloId(5).getValor());
	}
	
	
	@Test
	public void deveRecuperarMovimentacoesDeUmaCampanha() throws Exception {
		
		Campaign campanhaTeste = new Campaign();
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));

		campanhaTeste.setCampaignPosition(this.position);
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));
		
		campanhaTeste.setCampaignYear(this.ano);
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));
	
		campanhaTeste.setCampaignCandidateNumber(this.numeroCandidato);
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));
		
		campanhaTeste.setCampaignCountryState(this.uf);
		Assert.assertEquals(this.despesaDAO.getPorAnoNumeroCargoUf(campanhaTeste).size(),this.movimentacaoControle.getListaDespesas(campanhaTeste).size());
		Assert.assertEquals(this.receitaDAO.getPorAnoNumeroCargoUf(campanhaTeste).size(),this.movimentacaoControle.getListaReceitas(campanhaTeste).size());
	}

}
