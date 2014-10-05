package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Donor;
import model.dao.DonorDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.revenue_expense.RegisterToParseDonor;
import test.TemplateTest;

public class RegisterToParseDonorTest extends TemplateTest {

	private RegisterToParseDonor register1;
	private RegisterToParseDonor register2;
	private RegisterToParseDonor register3;
	private DonorDAO donorDAO;
	String  fileType = "revenue";
	String  year1         = "2006";
	String  year2         = "2002";
	String  year3         = "2010";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register1 = new RegisterToParseDonor(this.fileType, this.year1);
		this.register2 = new RegisterToParseDonor(this.fileType, this.year2);
		this.register3 = new RegisterToParseDonor(this.fileType, this.year3);
		this.donorDAO = new DonorDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void shouldReturnDonorRegisteredFor2006() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[16] = "123";
		field[15] = "NAME";
		field[17] = "UF";
		field[18] = "SITUACAO";		
		register1.runFileLine(field);
		register1.registerInstances();
		
		ArrayList<Donor> donorList = donorDAO.getObjectArrayListFromDatabase();
		assertEquals(donorList.get(0).getDonorPersonRegister(), "123");
	}
	
	@Test
	public void shouldReturnDonorRegisteredFor2002() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[6] = "12345";
		field[8] = "NAME NAME";
		field[7] = "UF UF";	
		register2.runFileLine(field);
		register2.registerInstances();
		
		ArrayList<Donor> donorList = donorDAO.getObjectArrayListFromDatabase();
		assertEquals(donorList.get(0).getDonorPersonRegister(), "12345");
	}
	
	@Test
	public void shouldReturnDonorRegisteredFor2010() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[10] = "12345";
		field[11] = "NAME NAME";
		register3.runFileLine(field);
		register3.registerInstances();
		
		ArrayList<Donor> donorList = donorDAO.getObjectArrayListFromDatabase();
		assertEquals(donorList.get(0).getDonorPersonRegister(), "12345");
	}

}
