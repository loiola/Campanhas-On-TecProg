package test.parse;

import org.junit.Test;

import parse.ParseCampaign;
import parse.ParseException;
import parse.ParseFinancialTransactions;
import parse.ParseParty;

public class ParseTeste {
	
	private String fileTypeExpense = "expense"; 
	private String fileTypeRevenue = "revenue"; 
	private String year = "2002";
	
	@Test  
	public void testingTheBootParseCampaing() throws ParseException {
		new ParseCampaign(this.fileTypeExpense, this.year);
	}
	
	@Test 
	public void testingTheBootParseFinancialTransactionsWithExpense() throws ParseException {
		new ParseFinancialTransactions(this.fileTypeExpense, this.year);
	}
	
	@Test 
	public void testingTheBootParseFinancialTransactionsWithRevenue() throws ParseException {
		new ParseFinancialTransactions(this.fileTypeRevenue, this.year);
	}
	
	@Test 
	public void testingTheBootParseParty() throws ParseException {
		new ParseParty(this.fileTypeExpense, this.year);
	}
	
	@Test 
	public void testingTheBuilderParseException() throws ParseException {
		new ParseException(this.fileTypeExpense);
	}
}
