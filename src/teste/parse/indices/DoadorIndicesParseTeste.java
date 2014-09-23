package teste.parse.indices;

import model.beans.Donor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.DoadorIndicesParse;

public class DoadorIndicesParseTeste {

	private String campo[];
	private DoadorIndicesParse doadorIndicesParse;

	@Before
	public void setUp() throws Exception {
		
		this.doadorIndicesParse = new DoadorIndicesParse();
		this.campo = new String[4];
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmDoadorComIndicesValidos() throws Exception {
		
		Donor donor = new Donor();
		this.doadorIndicesParse.startInstance(donor, campo);
		Assert.assertEquals(this.campo[0], donor.getDonorPersonRegister().toString());
		Assert.assertEquals(this.campo[1], donor.getDonorName());
		Assert.assertEquals(this.campo[2], donor.getDonorCountryState());
		Assert.assertEquals(this.campo[3], donor.getDonorRegisterSituation());
	}
	
	@Test
	public void iniciarUmDoadorComIndicesInvalidos() {
		
		this.doadorIndicesParse = new DoadorIndicesParse();
		Donor donor = new Donor();
		this.doadorIndicesParse.startInstance(donor, campo);
		Assert.assertNotEquals(this.campo[0], donor.getDonorPersonRegister().toString());
		Assert.assertNotEquals(this.campo[1], donor.getDonorName());
		Assert.assertNotEquals(this.campo[2], donor.getDonorCountryState());
		Assert.assertNotEquals(this.campo[3], donor.getDonorRegisterSituation());
	}
	
	private void iniciarIndices() {
		
		this.doadorIndicesParse.setIndiceCpf_Cnpj(0);
		this.doadorIndicesParse.setIndiceNome(1);
		this.doadorIndicesParse.setIndiceUf(2);
		this.doadorIndicesParse.setIndiceSituacaoCadastral(3);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "55325424149";
		this.campo[1] = "VANDERLEI";
		this.campo[2] = "DF";
		this.campo[3] = "REGULAR";
	}

}
