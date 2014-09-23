package teste.parse.indices;

import model.beans.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.FornecedorIndicesParse;

public class FornecedorIndicesParseTestes {
	
	private String campo[];
	private FornecedorIndicesParse fornecedorIndicesParse;

	@Before
	public void setUp() throws Exception {
		
		this.fornecedorIndicesParse = new FornecedorIndicesParse();
		this.campo = new String[4];
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmFornecedorComIndicesValidos() throws Exception {
		
		Supplier supplier = new Supplier();
		this.fornecedorIndicesParse.startInstance(supplier, campo);
		Assert.assertEquals(this.campo[0], supplier.getSupplierPersonRegister().toString());
		Assert.assertEquals(this.campo[1], supplier.getSupplierName());
		Assert.assertEquals(this.campo[2], supplier.getSupplierCountryState());
		Assert.assertEquals(this.campo[3], supplier.getSupplierRegisterSituation());
	}
	
	@Test
	public void iniciarUmFornecedorComIndicesInvalidos() {
		
		this.fornecedorIndicesParse = new FornecedorIndicesParse();
		Supplier supplier = new Supplier();
		this.fornecedorIndicesParse.startInstance(supplier, campo);
		Assert.assertNotEquals(this.campo[0], supplier.getSupplierPersonRegister().toString());
		Assert.assertNotEquals(this.campo[1], supplier.getSupplierName());
		Assert.assertNotEquals(this.campo[2], supplier.getSupplierCountryState());
		Assert.assertNotEquals(this.campo[3], supplier.getSupplierRegisterSituation());
	}
	
	private void iniciarIndices() {
		
		this.fornecedorIndicesParse.setIndiceCpf_Cnpj(0);
		this.fornecedorIndicesParse.setIndiceNome(1);
		this.fornecedorIndicesParse.setIndiceUf(2);
		this.fornecedorIndicesParse.setIndiceSituacaoCadastral(3);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "55325424149";
		this.campo[1] = "VANDERLEI";
		this.campo[2] = "DF";
		this.campo[3] = "REGULAR";
	}
	
}
