package teste.modelo.bean;

import static teste.modelo.bean.BeanTeste.instanciarDoador;
import static teste.modelo.bean.BeanTeste.instanciarMovimentacaoFinanceira;
import modelo.beans.Donor;
import modelo.beans.MovimentacaoFinanceira;

import org.junit.Assert;
import org.junit.Test;

public class DoadorTeste {

	@Test
	public void equalsDeveRetornarVerdadeiroSeForemDoadoresIguais() {
		
		Donor donor = instanciarDoador();
		Donor doador2 = instanciarDoador();
		Assert.assertTrue(donor.equals(doador2));
	}

	@Test
	public void equalsDeveRetornarFalsoSeForemDoadoresDiferentes() {
		
		Donor donor = instanciarDoador();
		Donor doador2 = instanciarDoador();
		donor.setCpf_cnpj(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(donor.equals(doador2));		
	}
	
	@Test
	public void equalsDeveRetornarFalsoSeNaoCompararComDoador() {
		
		Donor donor = instanciarDoador();
		MovimentacaoFinanceira movimentacaoFinanceira = instanciarMovimentacaoFinanceira();
		
		Assert.assertFalse(donor.equals(movimentacaoFinanceira));
		Assert.assertFalse(movimentacaoFinanceira.equals(donor));
		Assert.assertEquals(BeanTeste.STRING_TESTE,donor.getNome());
		Assert.assertEquals(BeanTeste.STRING_TESTE,donor.getUf());
		Assert.assertEquals(BeanTeste.STRING_TESTE,donor.getSituacaoCadastral());
	}
	
}
