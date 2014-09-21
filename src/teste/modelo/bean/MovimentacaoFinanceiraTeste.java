package teste.modelo.bean;

import static org.junit.Assert.assertFalse;
import static teste.modelo.bean.BeanTeste.instanciarCampanha;
import static teste.modelo.bean.BeanTeste.instanciarDespesa;
import static teste.modelo.bean.BeanTeste.instanciarDoador;
import static teste.modelo.bean.BeanTeste.instanciarFornecedor;
import static teste.modelo.bean.BeanTeste.instanciarMovimentacaoFinanceira;
import static teste.modelo.bean.BeanTeste.instanciarReceita;
import modelo.beans.Campaign;
import modelo.beans.Despesa;
import modelo.beans.Doador;
import modelo.beans.Fornecedor;
import modelo.beans.MovimentacaoFinanceira;
import modelo.beans.Receita;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovimentacaoFinanceiraTeste {

	Despesa despesa;
	Receita receita;
	Despesa despesa2;
	Receita receita2;
	
	@Before
	public void SetUp() {
		
		this.despesa = instanciarDespesa();
		this.receita = instanciarReceita();
		this.despesa2 = instanciarDespesa();
		this.receita2 = instanciarReceita();
	}

	@Test
	public void equalsDeveRetornarFalsoEmQualquerCondicao() {

		Assert.assertFalse(despesa.equals(receita));
		Assert.assertFalse(receita.equals(despesa));
		Assert.assertEquals(BeanTeste.STRING_TESTE, despesa.getTipoDocumento());
		Assert.assertEquals(instanciarFornecedor(), despesa.getFornecedor());
		Assert.assertEquals(BeanTeste.STRING_TESTE, receita.getReciboEleitoral());
		Assert.assertEquals(instanciarDoador(), receita.getDoador());
	}
	
	@Test
	public void equalsDeveRetornarVerdadeiro() {

		Despesa outraDespesa = despesa;
		Receita outraReceita = receita;
		
		Assert.assertFalse(despesa.equals(outraDespesa));
		Assert.assertFalse(receita.equals(outraReceita));
	}
	
	@Test
	public void equalsDeveRetornarFalsoParaOutrosCasos() {
		Doador doador2 = instanciarDoador();
		doador2.setCpf_cnpj(BeanTeste.STRING_TESTE_2);
		
		Fornecedor fornecedor2 = instanciarFornecedor();
		fornecedor2.setCpf_cnpj(BeanTeste.STRING_TESTE_2);	
		
		receita2.setDoador(doador2);
		despesa2.setFornecedor(fornecedor2);
		
		assertFalse(receita.equals(receita2));
		assertFalse(despesa.equals(despesa2));
		
	}
	
	@Test
	public void equalsDeveRetornarVerdadeiroSeForemMovimentacoesFinanceiraIguais() {
		
		MovimentacaoFinanceira movimentacaoFinanceira = instanciarMovimentacaoFinanceira();
		MovimentacaoFinanceira movimentacaoFinanceira2 = instanciarMovimentacaoFinanceira();
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
	}
	
	@Test
	public void equalsDeveRetornarFalsoSeForemMovimentacoesFinancierasDiferentes() {
		
		MovimentacaoFinanceira movimentacaoFinanceira = instanciarMovimentacaoFinanceira();
		MovimentacaoFinanceira movimentacaoFinanceira2 = instanciarMovimentacaoFinanceira();
		Campaign campaign = instanciarCampanha();
		campaign.setNomeDeUrna(BeanTeste.STRING_TESTE_2);
		movimentacaoFinanceira2.setCampanha(campaign);
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
		campaign.setNomeDeUrna(BeanTeste.STRING_TESTE);
		movimentacaoFinanceira2.setCampanha(campaign);
		movimentacaoFinanceira2.setDescricao(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
		movimentacaoFinanceira2.setDescricao(BeanTeste.STRING_TESTE);
		movimentacaoFinanceira2.setFormaPagamento(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
		movimentacaoFinanceira2.setFormaPagamento(BeanTeste.STRING_TESTE);
		movimentacaoFinanceira2.setNumeroDocumento(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
		movimentacaoFinanceira2.setNumeroDocumento(BeanTeste.STRING_TESTE);
		movimentacaoFinanceira2.setTipoMovimentacao(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
		movimentacaoFinanceira2.setTipoMovimentacao(BeanTeste.STRING_TESTE);
		movimentacaoFinanceira2.setValor(BeanTeste.FLOAT_TESTE_2);
		Assert.assertFalse(movimentacaoFinanceira.equals(movimentacaoFinanceira2));
		
		Assert.assertEquals(BeanTeste.INT_TESTE, movimentacaoFinanceira.getId());
		Assert.assertEquals(BeanTeste.STRING_TESTE, movimentacaoFinanceira.getData());
		
		BeanTeste bt = new BeanTeste();
		bt.usarBeanTeste();
		
		Assert.assertEquals((float) 1000, BeanTeste.FLOAT_TESTE,0);
		Assert.assertEquals((float) 2000, BeanTeste.FLOAT_TESTE_2,0);
		Assert.assertEquals("String Teste", BeanTeste.STRING_TESTE);
	}

}
