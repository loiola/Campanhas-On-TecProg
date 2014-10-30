package parse.register.revenue_expense;

import parse.ParseException;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public abstract class RegisterToParseRevenueAndExpenses<O> extends RegisterParse<O> {
	
	/* 
	 * Generic class to be extended to treat the case if input is an expense
	 * or revenue for the year and what can configure the index of the parse
	 */

	// Attributes
	
	// Attribute that represents a campaign Expense
	public static final String EXPENSE = "expense";
	
	// Attribute that represents a campaign Revenue
	public static final String REVENUE = "revenue";

	// Attribute that represents the year 2002
	public static final String YEAR_2002 = "2002";
	
	// Attribute that represents the year 2006
	public static final String YEAR_2006 = "2006";
	
	// Attribute that represents the year 2010
	public static final String YEAR_2010 = "2010";
	
	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register generic information
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterToParseRevenueAndExpenses(String fileType, String year) throws ParseException {
		super(fileType, year);
	}

	/*
	 * This method checks if the file is income or expense and returns index of the parse according
	 * @param String who define the type of the list file to be used 
	 * @param String who define the year of the campaign to be used
	 * @return a ParseIndex the O object
	 */
	@Override
	protected ParseIndex<O> getParseIndex(String fileType, String year) throws ParseException {
		//Variables to store result of validation of file type
		boolean validationFileExpense = fileType.equals(EXPENSE);
		boolean validationFileRevenue = fileType.equals(REVENUE);
		
		if(validationFileExpense) {
			return getIndicesParseExpense(year);
		} else {
			if(validationFileRevenue) {
				return getIndicesParseRevenue(year);
			}
		}
		
		throw new ParseException("Tipo do Arquivo está invalido!");
	}
	
	/* 
	 * Set of abstract methods that were used to obtain the
	 * indices of expense parse the given year
	 */
	protected abstract ParseIndex<O> getIndicesParseExpense2002();
	protected abstract ParseIndex<O> getIndicesParseExpense2006();
	protected abstract ParseIndex<O> getIndicesParseExpense2010();

	/* 
	 * Set of abstract methods that were used to obtain the
	 * indices of revenue parse the given year
	 */
	protected abstract ParseIndex<O> getIndicesParseRevenue2002();
	protected abstract ParseIndex<O> getIndicesParseRevenue2006();
	protected abstract ParseIndex<O> getIndicesParseRevenue2010();

	/*
	 * This method verifies the year provided by the file and
	 * calls the method to catch parse the index according to the year
	 * (for Expense)
	 * @param String who define year of campaign to be used 
	 * @return a ParseIndex the O object
	 */
	private ParseIndex<O> getIndicesParseExpense(String year) throws ParseException {
		switch (year) {
		case YEAR_2002:
			return getIndicesParseExpense2002();
		case YEAR_2006:
			return getIndicesParseExpense2006();
		case YEAR_2010:
			return getIndicesParseExpense2010();
		}
		throw new ParseException("Ano do arquivo está invalido!");
	}
	
	/*
	 * This method verifies the year provided by the file and
	 * calls the method to catch parse the index according to the year
	 * (for Revenue)
	 * @param String who define year of campaign to be used 
	 * @return a ParseIndex the O object
	 */
	private ParseIndex<O> getIndicesParseRevenue(String year) throws ParseException {
		switch (year) {
		case YEAR_2002:
			return getIndicesParseRevenue2002();
		case YEAR_2006:
			return getIndicesParseRevenue2006();
		case YEAR_2010:
			return getIndicesParseRevenue2010();
		}
		throw new ParseException("Ano do arquivo está invalido!");
	}
	
}
