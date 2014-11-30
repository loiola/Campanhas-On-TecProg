package test.parse.register;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Revenue;
import model.dao.RevenueDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.revenue_expense.RegisterToParseRevenue;
import test.TemplateTest;

public class RegisterToParseRevenueTest extends TemplateTest {

	private RegisterToParseRevenue register1;
	private RegisterToParseRevenue register2;
	private RegisterToParseRevenue register3;
	private RevenueDAO revenueDAO;
	String  fileType = "revenue";
	String  year1         = "2002";
	String  year2         = "2006";
	String  year3         = "2010";
	String  yearFalse     = "yearFalse";
	String  wrongFileType = "ArquivoErrado";
	String  invalidYear = "2050";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register1 = new RegisterToParseRevenue(this.fileType, this.year1);
		this.register2 = new RegisterToParseRevenue(this.fileType, this.year2);
		this.register3 = new RegisterToParseRevenue(this.fileType, this.year3);
		
		this.revenueDAO = new RevenueDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void shouldReturnRevenueRegisteredFor2002() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[0] = "DF";
		field[4] = "123";
		field[2] = "Position";
		field[10] = "FormaPag";
		field[5] = "Data";
		field[6] = "1234";
		field[8] = "NomeDoador";
		field[9] = "12345";
		register1.runFileLine(field);
		register1.registerInstances();
		
		ArrayList<Revenue> revenueList = revenueDAO.getObjectArrayListFromDatabase();
		assertEquals(revenueList.get(0).getRevenueDonor().getDonorPersonRegister(), "1234");	
	}
	
	@Test
	public void shouldReturnRevenueRegisteredFor2006() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[4] = "DF";
		field[3] = "123";
		field[1] = "Position";
		field[11] = "TipoPag";
		field[13] = "FormaPag";
		field[10] = "Data";
		field[16] = "1234";
		field[15] = "NomeDoador";
		field[9] = "12345";
		register2.runFileLine(field);
		register2.registerInstances();
		
		ArrayList<Revenue> revenueList = revenueDAO.getObjectArrayListFromDatabase();
		assertEquals(revenueList.get(0).getRevenueDonor().getDonorPersonRegister(), "1234");
	}
	
	@Test
	public void shouldReturnRevenueRegisteredFor2010() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[1] = "DF";
		field[3] = "123";
		field[4] = "Position";
		field[14] = "TipoPag";
		field[16] = "FormaPag";
		field[8] = "ReciboEle";
		field[9] = "1234";
		field[12] = "Data";
		field[10] = "12345";
		field[11] = "NomeDoador";
		field[13] = "12345";
		field[17] = "DescricaoDoa";
		register3.runFileLine(field);
		register3.registerInstances();
		
		ArrayList<Revenue> revenueList = revenueDAO.getObjectArrayListFromDatabase();
		assertEquals(revenueList.get(0).getRevenueDonor().getDonorPersonRegister(), "12345");
	}
	
	@Test
	public void startupTestingMethodsNotImplemented() throws ParseException, SQLException {
		register1.getIndicesParseExpense2002();
		register1.getIndicesParseExpense2006();
		register1.getIndicesParseExpense2010();	
	}
	
	@Test(expected=ParseException.class)  
	public void shouldReturnAnExceptionToTheFalseFile() throws ParseException {
		new RegisterToParseRevenue(this.wrongFileType, this.year1);
		assertTrue(true);
	}
	
	@Test(expected=ParseException.class)  
	public void shouldReturnAnExceptionToTheFalseYear() throws ParseException {
		new RegisterToParseRevenue(this.fileType, this.yearFalse);
		assertTrue(true);
	}
}