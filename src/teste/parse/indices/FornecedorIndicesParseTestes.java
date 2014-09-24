package teste.parse.indices;

import model.beans.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.SupplierParseIndex;

public class FornecedorIndicesParseTestes {
	
	private String campo[];
	private SupplierParseIndex supplierParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.supplierParseIndex = new SupplierParseIndex();
		this.campo = new String[4];
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmFornecedorComIndicesValidos() throws Exception {
		
		Supplier supplier = new Supplier();
		this.supplierParseIndex.startInstance(supplier, campo);
		Assert.assertEquals(this.campo[0], supplier.getSupplierPersonRegister().toString());
		Assert.assertEquals(this.campo[1], supplier.getSupplierName());
		Assert.assertEquals(this.campo[2], supplier.getSupplierCountryState());
		Assert.assertEquals(this.campo[3], supplier.getSupplierRegisterSituation());
	}
	
	@Test
	public void iniciarUmFornecedorComIndicesInvalidos() {
		
		this.supplierParseIndex = new SupplierParseIndex();
		Supplier supplier = new Supplier();
		this.supplierParseIndex.startInstance(supplier, campo);
		Assert.assertNotEquals(this.campo[0], supplier.getSupplierPersonRegister().toString());
		Assert.assertNotEquals(this.campo[1], supplier.getSupplierName());
		Assert.assertNotEquals(this.campo[2], supplier.getSupplierCountryState());
		Assert.assertNotEquals(this.campo[3], supplier.getSupplierRegisterSituation());
	}
	
	private void iniciarIndices() {
		
		this.supplierParseIndex.setIndiceCpf_Cnpj(0);
		this.supplierParseIndex.setIndiceNome(1);
		this.supplierParseIndex.setIndiceUf(2);
		this.supplierParseIndex.setIndiceSituacaoCadastral(3);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "55325424149";
		this.campo[1] = "VANDERLEI";
		this.campo[2] = "DF";
		this.campo[3] = "REGULAR";
	}
	
}
