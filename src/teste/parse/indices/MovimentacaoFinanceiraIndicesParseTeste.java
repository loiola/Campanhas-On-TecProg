package teste.parse.indices;

import modelo.beans.FinancialTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.indices.MovimentacaoFinanceiraIndicesParse;

public class MovimentacaoFinanceiraIndicesParseTeste {

	private String campo[];
	private MovimentacaoFinanceiraIndicesParse movimentacaoFinanceiraIndicesParse;
	private String ano = "2006";
	private String anoTeste = "2010";

	@Before
	public void setUp() throws Exception {
		
		this.movimentacaoFinanceiraIndicesParse = new MovimentacaoFinanceiraIndicesParse<>(ano);
		this.campo = new String[9];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmaMovimentacaoFinanceiraComIndicesValidos() throws Exception {
		
		FinancialTransaction financialTransaction = new FinancialTransaction();
		this.movimentacaoFinanceiraIndicesParse.iniciarInstancia(financialTransaction, campo);
		Assert.assertEquals(this.campo[0], financialTransaction.getCampanha().getCampaignYear().toString());
		Assert.assertEquals(this.campo[1], financialTransaction.getCampanha().getCampaignCandidateNumber().toString());
		Assert.assertEquals(this.campo[2], financialTransaction.getCampanha().getCampaignPosition().getDescricao());
		Assert.assertEquals(this.campo[3], financialTransaction.getNumeroDocumento());
		Assert.assertEquals(this.campo[4], financialTransaction.getData());
		Assert.assertEquals(this.campo[5], financialTransaction.getValor().toString());
		Assert.assertEquals(this.campo[6], financialTransaction.getTipoMovimentacao());
		Assert.assertEquals(this.campo[7], financialTransaction.getFormaPagamento());
		Assert.assertEquals(this.campo[8], financialTransaction.getDescricao());
	}
	
	@Test
	public void iniciarUmaMovimentacaoFinanceiraComIndicesInvalidos() throws Exception {
		
		this.movimentacaoFinanceiraIndicesParse = new MovimentacaoFinanceiraIndicesParse<>(anoTeste);
		FinancialTransaction financialTransaction = new FinancialTransaction();
		this.movimentacaoFinanceiraIndicesParse.iniciarInstancia(financialTransaction, campo);
		Assert.assertNotEquals(this.campo[0], financialTransaction.getCampanha().getCampaignYear().toString());
		Assert.assertNotEquals(this.campo[1], financialTransaction.getCampanha().getCampaignCandidateNumber().toString());
		Assert.assertNotEquals(this.campo[2], financialTransaction.getCampanha().getCampaignPosition().getDescricao());
		Assert.assertNotEquals(this.campo[3], financialTransaction.getNumeroDocumento());
		Assert.assertNotEquals(this.campo[4], financialTransaction.getData());
		Assert.assertNotEquals(this.campo[5], financialTransaction.getValor().toString());
		Assert.assertNotEquals(this.campo[6], financialTransaction.getTipoMovimentacao());
		Assert.assertNotEquals(this.campo[7], financialTransaction.getFormaPagamento());
		Assert.assertNotEquals(this.campo[8], financialTransaction.getDescricao());
	}
	
	private void iniciarIndices() {
		
		this.movimentacaoFinanceiraIndicesParse.setIndiceCampanhaAno(0);
		this.movimentacaoFinanceiraIndicesParse.setIndiceCampanhaNumero(1);
		this.movimentacaoFinanceiraIndicesParse.setIndiceCampanhaCargo(2);
		this.movimentacaoFinanceiraIndicesParse.setIndiceNumeroDocumento(3);
		this.movimentacaoFinanceiraIndicesParse.setIndiceData(4);
		this.movimentacaoFinanceiraIndicesParse.setIndiceValor(5);
		this.movimentacaoFinanceiraIndicesParse.setIndiceTipoMovimentacao(6);
		this.movimentacaoFinanceiraIndicesParse.setIndiceFormaPagamento(7);
		this.movimentacaoFinanceiraIndicesParse.setIndiceDescricao(8);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "2006";
		this.campo[1] = "45555";
		this.campo[2] = "CARGO";
		this.campo[3] = "55896321447";
		this.campo[4] = "12/10/2006";
		this.campo[5] = "450.0";
		this.campo[6] = "TIPO MOVIMENTACAO";
		this.campo[7] = "FORMA PAGAMENTO";
		this.campo[8] = "DESCRICAO";
	}

}
