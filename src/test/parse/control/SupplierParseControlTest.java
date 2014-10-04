package test.parse.control;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlSupplier;
import parse.index.SupplierParseIndex;
import test.TemplateTest;

public class SupplierParseControlTest extends TemplateTest {
	
	public static final int PERSON_REGISTER = 0;
	public static final int NAME = 1;

	private String fields[];
	private SupplierDAO supplierDAO;
	private SupplierParseIndex supplierParseIndex;
	private ParseControlSupplier parseControlSupplier;

	@Override
	public void beforeTest() throws Exception {
		
		this.fields = new String[2];
		this.supplierDAO = new SupplierDAO();
		this.supplierParseIndex = new SupplierParseIndex();
		this.parseControlSupplier = new ParseControlSupplier(this.supplierParseIndex);

		fieldsStart();
		indexStart();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void registeringSupplier() throws Exception {

		this.parseControlSupplier.addInstance(fields);
		this.parseControlSupplier.registeringInstances();
		this.parseControlSupplier.clear();

		Supplier supplierRegistered = this.supplierDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.fields[PERSON_REGISTER], supplierRegistered.getSupplierPersonRegister()
				.toString());
		Assert.assertEquals(this.fields[NAME],
				supplierRegistered.getSupplierName());
	}

	@Test
	public void notToSignTwoEqualSupplier() throws Exception {

		this.parseControlSupplier.addInstance(fields);
		this.parseControlSupplier.addInstance(fields);
		this.parseControlSupplier.registeringInstances();
		this.parseControlSupplier.clear();

		int supplierNumberDatabase = this.supplierDAO.getObjectArrayListFromDatabase().size();

		Assert.assertEquals(1, supplierNumberDatabase);
	}

	private void indexStart() {

		this.supplierParseIndex.setIndexSupplierCpfCnpj(PERSON_REGISTER);
		this.supplierParseIndex.setIndexName(NAME);

	}

	private void fieldsStart() {

		this.fields[PERSON_REGISTER] = "125";
		this.fields[NAME] = "NAME TESTE";
	}

}
