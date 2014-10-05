package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.revenue_expense.RegisterToParseSupplier;
import test.TemplateTest;

public class RegisterToParseSupplierTest extends TemplateTest {

	private RegisterToParseSupplier register1;
	private RegisterToParseSupplier register2;
	private RegisterToParseSupplier register3;
	private SupplierDAO supplierDAO;
	String  fileType = "expense";
	String  year1         = "2006";
	String  year2         = "2002";
	String  year3         = "2010";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register1 = new RegisterToParseSupplier(this.fileType, this.year1);
		this.register2 = new RegisterToParseSupplier(this.fileType, this.year2);
		this.register3 = new RegisterToParseSupplier(this.fileType, this.year3);
		this.supplierDAO = new SupplierDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void shouldReturnSupplierRegisteredFor2006() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[19] = "123";
		field[18] = "NAME";
		field[20] = "UF";
		field[21] = "SITUACAO";		
		register1.runFileLine(field);
		register1.registerInstances();
		
		ArrayList<Supplier> supplierList = supplierDAO.getObjectArrayListFromDatabase();
		assertEquals(supplierList.get(0).getSupplierPersonRegister(), "123");
	}
	
	@Test
	public void shouldReturnSupplierRegisteredFor2002() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[6] = "12345";
		field[8] = "NAME";
		field[7] = "UF";
		register2.runFileLine(field);
		register2.registerInstances();
		
		ArrayList<Supplier> supplierList = supplierDAO.getObjectArrayListFromDatabase();
		assertEquals(supplierList.get(0).getSupplierPersonRegister(), "12345");
	}
	
	@Test
	public void shouldReturnSupplierRegisteredFor2010() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[10] = "12345";
		field[11] = "NAME";
		register3.runFileLine(field);
		register3.registerInstances();
		
		ArrayList<Supplier> supplierList = supplierDAO.getObjectArrayListFromDatabase();
		assertEquals(supplierList.get(0).getSupplierPersonRegister(), "12345");
	}

}
