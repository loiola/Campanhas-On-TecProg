package test.model.bean;

import static test.model.bean.BeanTeste.instanciarDoador;
import static test.model.bean.BeanTeste.instanciarMovimentacaoFinanceira;
import model.beans.Donor;
import model.beans.FinancialTransaction;

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
		donor.setDonorPersonRegister(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(donor.equals(doador2));		
	}
	
	@Test
	public void equalsDeveRetornarFalsoSeNaoCompararComDoador() {
		
		Donor donor = instanciarDoador();
		FinancialTransaction financialTransaction = instanciarMovimentacaoFinanceira();
		
		Assert.assertFalse(donor.equals(financialTransaction));
		Assert.assertFalse(financialTransaction.equals(donor));
		Assert.assertEquals(BeanTeste.STRING_TESTE,donor.getDonorName());
		Assert.assertEquals(BeanTeste.STRING_TESTE,donor.getDonorCountryState());
		Assert.assertEquals(BeanTeste.STRING_TESTE,donor.getDonorRegisterSituation());
	}
	
}
