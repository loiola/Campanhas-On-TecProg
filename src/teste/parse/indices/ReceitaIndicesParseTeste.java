package teste.parse.indices;

import model.beans.Revenue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.RevenueParseIndex;

public class ReceitaIndicesParseTeste {

	private String campo[];
	private RevenueParseIndex revenueParseIndex;
	private String ano = "2006";

	@Before
	public void setUp() throws Exception {
		
		this.revenueParseIndex = new RevenueParseIndex(ano);
		this.campo = new String[3];
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmaReceitaComIndicesValidos() throws Exception {
		
		Revenue revenue = new Revenue();
		this.revenueParseIndex.startInstance(revenue, campo);
		Assert.assertEquals(this.campo[0], revenue.getRevenueElectoralReceipt());
		Assert.assertEquals(this.campo[1], revenue.getRevenueDonor().getDonorName());
		Assert.assertEquals(this.campo[2], revenue.getRevenueDonor().getDonorPersonRegister());
	}
	
	@Test
	public void iniciarUmaReceitaComIndicesInvalidos() {
		
		this.revenueParseIndex = new RevenueParseIndex(ano);
		Revenue revenue = new Revenue();
		this.revenueParseIndex.startInstance(revenue, campo);
		Assert.assertNotEquals(this.campo[0], revenue.getRevenueElectoralReceipt());
		Assert.assertNotEquals(this.campo[1], revenue.getRevenueDonor().getDonorName());
		Assert.assertNotEquals(this.campo[2], revenue.getRevenueDonor().getDonorPersonRegister());
	}
	
	private void iniciarIndices() {
		
		this.revenueParseIndex.setIndexElectoralReceipt(0);
		this.revenueParseIndex.setIndexNameDonor(1);
		this.revenueParseIndex.setIndexCpfCnpjDonor(2);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "55325424149";
		this.campo[1] = "DOADOR";
		this.campo[2] = "123456789";
	}

}
