package teste;

import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidato;
import modelo.beans.Cargo;
import modelo.beans.Despesa;
import modelo.beans.Doador;
import modelo.beans.Fornecedor;
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
	private Candidato candidato;
	private Campaign campaign;
	private Cargo cargo;
	private Receita receita;
	private Despesa despesa;
	private Doador doador;
	private Fornecedor fornecedor;
	private Integer ano;
	private String uf;
	private Integer numeroCandidato;

	@Override
	public void beforeTest() throws Exception {
		
		this.despesaDAO = new DespesaDAO();
		this.receitaDAO = new ReceitaDAO();
		this.movimentacaoControle = new MovimentacaoControle();
		this.candidato = new Candidato();
		this.campaign = new Campaign();
		this.receita = new Receita();
		this.despesa = new Despesa();
		this.doador = new Doador();
		this.fornecedor = new Fornecedor();
		this.ano = 2014;
		this.uf = "DF";
		this.cargo = new Cargo();
		this.numeroCandidato = 1234;
		
		ArrayList<Despesa> listaDespesa = new ArrayList<>();
		ArrayList<Receita> listaReceita = new ArrayList<>();

		candidato.setNome("FULANO");
		candidato.setTituloEleitoral("12345");
		
		cargo.setDescricao("Presidente");
		
		campaign.setCargo(cargo);
		campaign.setCandidato(candidato);
		campaign.setAno(this.ano);
		campaign.setUf(this.uf);
		campaign.setNumeroCandidato(this.numeroCandidato);
		
		fornecedor.setCpf_cnpj("555555555555");
		doador.setCpf_cnpj("333333333333");
		
		receita.setValor((float) 55.0);
		receita.setCampanha(campaign);
		receita.setId(3);
		receita.setDoador(doador);
		listaReceita.add(receita);
		
		despesa.setValor((float) 90.0);
		despesa.setCampanha(campaign);
		despesa.setId(5);
		despesa.setFornecedor(fornecedor);
		listaDespesa.add(despesa);
		
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

		campanhaTeste.setCargo(this.cargo);
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
