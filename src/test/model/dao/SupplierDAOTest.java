package test.model.dao;

import java.util.ArrayList;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Assert;
import org.junit.Test;

import test.TemplateTest;

public class SupplierDAOTest extends TemplateTest {

	private SupplierDAO supplierDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.supplierDAO = new SupplierDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void comparationValues() throws Exception {
		
		Supplier supplierOne = new Supplier();
		Supplier supplierTwo = new Supplier();
		supplierOne.setSupplierName("FORNECEDOR UM");
		supplierTwo.setSupplierName("FORNECEDOR UM");
		int result;

		result = SupplierDAO.CompareTwoSuppliersName.NAME.compare(supplierOne, supplierTwo);
		
		Assert.assertEquals(0,result);
	}
	
	@Test
	public void shouldNotThrowsExceptionToRegisterANewSupplier() throws Exception {
		
		ArrayList<Supplier> supplierList = new ArrayList<>();
		
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Nome");
		supplierList.add(supplier);
		
		this.supplierDAO.registerUnregisteredObjectArrayListOnDatabase(supplierList);
	}
	
	@Test
	public void shouldNotRegisterAgainAnExistingSupplier() throws Exception {
		
		ArrayList<Supplier> supplierList = new ArrayList<>();
		
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Nome");
		supplierList.add(supplier);

		this.supplierDAO.registerUnregisteredObjectArrayListOnDatabase(supplierList);
		this.supplierDAO.registerUnregisteredObjectArrayListOnDatabase(supplierList);
		
		Assert.assertEquals(1, this.supplierDAO.getObjectArrayListFromDatabase().size());
	}
	
	@Test
	public void shouldRetrieveAnArrayOfSuppliersRegistered() throws Exception {
		
		ArrayList<Supplier> supplierList = new ArrayList<>();
		
		Supplier supplierOne = new Supplier();
		supplierOne.setSupplierName("Nome");
		supplierOne.setSupplierPersonRegister("123");
		supplierOne.setSupplierRegisterSituation("Cadastrado");
		supplierOne.setSupplierCountryState("DF");
		supplierList.add(supplierOne);
		
		Supplier supplierTwo = new Supplier();
		supplierTwo.setSupplierName("Nome2");
		supplierTwo.setSupplierPersonRegister("1234");
		supplierTwo.setSupplierRegisterSituation("Cadastrado");
		supplierTwo.setSupplierCountryState("DF");
		supplierList.add(supplierTwo);

		this.supplierDAO.registerUnregisteredObjectArrayListOnDatabase(supplierList);
		Assert.assertEquals(supplierList, this.supplierDAO.getObjectArrayListFromDatabase());
	}
	
	@Test
	public void shouldRetrieveASupplierByPersonRegister() throws Exception {
		
		ArrayList<Supplier> supplierListToBeRegistered = new ArrayList<>();
		Supplier supplierRecovered;
		
		Supplier supplierOne = new Supplier();
		supplierOne.setSupplierName("nome");
		supplierOne.setSupplierPersonRegister("123456");
		supplierOne.setSupplierRegisterSituation("REGULAR");
		supplierOne.setSupplierCountryState("DF");
		supplierListToBeRegistered.add(supplierOne);
		
		Supplier supplierTwo = new Supplier();
		supplierTwo.setSupplierName("nome2");
		supplierTwo.setSupplierPersonRegister("12345678");
		supplierTwo.setSupplierRegisterSituation("IRREGULAR");
		supplierTwo.setSupplierCountryState("DF");
		supplierListToBeRegistered.add(supplierTwo);
		
		this.supplierDAO.registerUnregisteredObjectArrayListOnDatabase(supplierListToBeRegistered);
		supplierRecovered = this.supplierDAO.getSupplierByNameOfPersonRegister(supplierOne);
		
		Assert.assertEquals(supplierOne, supplierRecovered);
	}
	
}
