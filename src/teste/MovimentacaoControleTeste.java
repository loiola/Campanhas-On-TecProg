package teste;

import java.util.ArrayList;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Donor;
import model.beans.Expense;
import model.beans.Position;
import model.beans.Revenue;
import model.beans.Supplier;
import model.dao.DespesaDAO;
import model.dao.ReceitaDAO;

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
		
		position.setPositionDescription("Presidente");
		
		campaign.setCampaignPosition(position);
		campaign.setCampaignCandidate(candidate);
		campaign.setCampaignYear(this.ano);
		campaign.setCampaignCountryState(this.uf);
		campaign.setCampaignCandidateNumber(this.numeroCandidato);
		
		supplier.setSupplierPersonRegister("555555555555");
		donor.setDonorPersonRegister("333333333333");
		
		revenue.setFinancialTransactionPrice((float) 55.0);
		revenue.setFinancialTransactionCampaign(campaign);
		revenue.setFinancialTransactionIdentifier(3);
		revenue.setRevenueDonor(donor);
		listaReceita.add(revenue);
		
		expense.setFinancialTransactionPrice((float) 90.0);
		expense.setFinancialTransactionCampaign(campaign);
		expense.setFinancialTransactionIdentifier(5);
		expense.setExpenseSupplier(supplier);
		listaDespesa.add(expense);
		
		this.despesaDAO.cadastrarLista(listaDespesa);
		this.receitaDAO.cadastrarLista(listaReceita);
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmaMovimentacaoPeloId() throws Exception {
		
		Assert.assertEquals(this.receitaDAO.getPeloId(3).getFinancialTransactionPrice(), this.movimentacaoControle.getReceitaPeloId(3).getFinancialTransactionPrice());
		Assert.assertEquals(this.despesaDAO.getPeloId(5).getFinancialTransactionPrice(), this.movimentacaoControle.getDespesaPeloId(5).getFinancialTransactionPrice());
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
