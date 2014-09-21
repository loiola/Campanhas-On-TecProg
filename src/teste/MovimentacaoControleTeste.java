package teste;

import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Expense;
import modelo.beans.Donor;
import modelo.beans.Supplier;
import modelo.beans.Receita;
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
	private Receita receita;
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
		this.receita = new Receita();
		this.expense = new Expense();
		this.donor = new Donor();
		this.supplier = new Supplier();
		this.ano = 2014;
		this.uf = "DF";
		this.position = new Position();
		this.numeroCandidato = 1234;
		
		ArrayList<Expense> listaDespesa = new ArrayList<>();
		ArrayList<Receita> listaReceita = new ArrayList<>();

		candidate.setNome("FULANO");
		candidate.setTituloEleitoral("12345");
		
		position.setDescricao("Presidente");
		
		campaign.setCargo(position);
		campaign.setCandidato(candidate);
		campaign.setAno(this.ano);
		campaign.setUf(this.uf);
		campaign.setNumeroCandidato(this.numeroCandidato);
		
		supplier.setCpf_cnpj("555555555555");
		donor.setCpf_cnpj("333333333333");
		
		receita.setValor((float) 55.0);
		receita.setCampanha(campaign);
		receita.setId(3);
		receita.setDoador(donor);
		listaReceita.add(receita);
		
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

		campanhaTeste.setCargo(this.position);
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));
		
		campanhaTeste.setAno(this.ano);
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));
	
		campanhaTeste.setNumeroCandidato(this.numeroCandidato);
		Assert.assertNull(this.movimentacaoControle.getListaDespesas(campanhaTeste));
		Assert.assertNull(this.movimentacaoControle.getListaReceitas(campanhaTeste));
		
		campanhaTeste.setUf(this.uf);
		Assert.assertEquals(this.despesaDAO.getPorAnoNumeroCargoUf(campanhaTeste).size(),this.movimentacaoControle.getListaDespesas(campanhaTeste).size());
		Assert.assertEquals(this.receitaDAO.getPorAnoNumeroCargoUf(campanhaTeste).size(),this.movimentacaoControle.getListaReceitas(campanhaTeste).size());
	}

}
