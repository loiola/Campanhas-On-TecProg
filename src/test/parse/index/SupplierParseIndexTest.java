package test.parse.index;

import model.beans.Supplier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.SupplierParseIndex;

public class SupplierParseIndexTest {
	
	private String field[];
	private SupplierParseIndex supplierParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.supplierParseIndex = new SupplierParseIndex();
		this.field = new String[4];
		
		fieldsStart();
		indexStart();
	}
	
	@Test
	public void startSupplierWithValidIndices() throws Exception {
		
		Supplier supplier = new Supplier();
		this.supplierParseIndex.startInstance(supplier, field);
		Assert.assertEquals(this.field[0], supplier.getSupplierPersonRegister().toString());
		Assert.assertEquals(this.field[1], supplier.getSupplierName());
		Assert.assertEquals(this.field[2], supplier.getSupplierCountryState());
		Assert.assertEquals(this.field[3], supplier.getSupplierRegisterSituation());
	}
	
	@Test
	public void startSupplierWithInvalidIndices() {
		
		this.supplierParseIndex = new SupplierParseIndex();
		Supplier supplier = new Supplier();
		this.supplierParseIndex.startInstance(supplier, field);
		Assert.assertNotEquals(this.field[0], supplier.getSupplierPersonRegister().toString());
		Assert.assertNotEquals(this.field[1], supplier.getSupplierName());
		Assert.assertNotEquals(this.field[2], supplier.getSupplierCountryState());
		Assert.assertNotEquals(this.field[3], supplier.getSupplierRegisterSituation());
	}
	
	private void indexStart() {
		
		this.supplierParseIndex.setIndexSupplierCpfCnpj(0);
		this.supplierParseIndex.setIndexName(1);
		this.supplierParseIndex.setIndexUnitFederation(2);
		this.supplierParseIndex.setIndexRegistrationStatus(3);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "55325424149";
		this.field[1] = "VANDERLEI";
		this.field[2] = "DF";
		this.field[3] = "REGULAR";
	}
	
}
