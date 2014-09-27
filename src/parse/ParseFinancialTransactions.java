package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.revenue_expense.RegisterToParseExpense;
import parse.register.revenue_expense.RegisterToParseDonor;
import parse.register.revenue_expense.RegisterToParseSupplier;
import parse.register.revenue_expense.RegisterToParseRevenueAndExpenses;
import parse.register.revenue_expense.RegisterToParseRevenue;

public class ParseFinancialTransactions extends Parse {
	
	/*
	 * Class responsible for performing the parse of transactions
	 */
	 
	// Constructors
	public ParseFinancialTransactions(String fileType, String year) throws ParseException {
		super(fileType, year);

	}

	/*
	 * This method adds instances of classes related to financial transactions to register
	 * @param an ArrayList<RegisterParse<?>>
	 * @param an String fileType
	 * @param an String year
	 */
	@Override
	protected void addRegisterParseOnList(
			ArrayList<RegisterParse<?>> listRegisterParse,
			String fileType, String year) throws ParseException {
		
		if(fileType.equals(RegisterToParseRevenueAndExpenses.EXPENSE))
		{
			listRegisterParse.add(new RegisterToParseSupplier(fileType, year));
			listRegisterParse.add(new RegisterToParseExpense(fileType, year));
		}
		else
		{
			listRegisterParse.add(new RegisterToParseDonor(fileType, year));
			listRegisterParse.add(new RegisterToParseRevenue(fileType, year));
		}
	}

}