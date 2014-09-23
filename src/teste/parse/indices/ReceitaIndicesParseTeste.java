package teste.parse.indices;

import model.beans.Revenue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.ReceitaIndicesParse;

public class ReceitaIndicesParseTeste {

	private String campo[];
	private ReceitaIndicesParse receitaIndicesParse;
	private String ano = "2006";

	@Before
	public void setUp() throws Exception {
		
		this.receitaIndicesParse = new ReceitaIndicesParse(ano);
		this.campo = new String[3];
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmaReceitaComIndicesValidos() throws Exception {
		
		Revenue revenue = new Revenue();
		this.receitaIndicesParse.startInstance(revenue, campo);
		Assert.assertEquals(this.campo[0], revenue.getRevenueElectoralReceipt());
		Assert.assertEquals(this.campo[1], revenue.getRevenueDonor().getDonorName());
		Assert.assertEquals(this.campo[2], revenue.getRevenueDonor().getDonorPersonRegister());
	}
	
	@Test
	public void iniciarUmaReceitaComIndicesInvalidos() {
		
		this.receitaIndicesParse = new ReceitaIndicesParse(ano);
		Revenue revenue = new Revenue();
		this.receitaIndicesParse.startInstance(revenue, campo);
		Assert.assertNotEquals(this.campo[0], revenue.getRevenueElectoralReceipt());
		Assert.assertNotEquals(this.campo[1], revenue.getRevenueDonor().getDonorName());
		Assert.assertNotEquals(this.campo[2], revenue.getRevenueDonor().getDonorPersonRegister());
	}
	
	private void iniciarIndices() {
		
		this.receitaIndicesParse.setIndiceReciboEleitoral(0);
		this.receitaIndicesParse.setIndiceDoadorNome(1);
		this.receitaIndicesParse.setIndiceDoadorCpfCnpj(2);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "55325424149";
		this.campo[1] = "DOADOR";
		this.campo[2] = "123456789";
	}

}
