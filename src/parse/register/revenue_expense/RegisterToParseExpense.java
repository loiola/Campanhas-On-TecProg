package parse.register.revenue_expense;

import model.beans.Expense;
import parse.ParseException;
import parse.control.ParseControlDonor;
import parse.control.ParseControlExpense;
import parse.control.ParseControl;
import parse.index.ExpenseParseIndex;
import parse.index.ParseIndex;

public class RegisterToParseExpense extends RegisterToParseRevenueAndExpenses<Expense> {
	
	/* 
	 * Class used to extract Expense attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseExpenseRevenueRegister inherited constructor to
	 * register informations from an Expense
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterToParseExpense(String fileType, String year) throws ParseException {
		super(fileType, year);
	}
	
	// Methods
	
	/*
	 * @see parse.register.RegisterParse#newInstance(parse.index.ParseIndex)
	 * This method generate a ParseExpenseControl to be used by constructor
	 * @return a ParseExpenseControl
	 */
	@Override
	public ParseControl<Expense> newIntance(ParseIndex<Expense> parseIndex) {
		ParseControlExpense parseControlExpense = new ParseControlExpense(parseIndex);
		return parseControlExpense;
	}

	/*
	 * This method generate the ParseExpenseIndex, setting the index number for each attribute from
	 * the Campaign of 2002
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected ParseIndex<Expense> getIndicesParseExpense2002() {
		ExpenseParseIndex expenseParseIndex = new ExpenseParseIndex(YEAR_2002);
		expenseParseIndex.setIndexUnitFederationCampaign(0);
		expenseParseIndex.setIndexNumberCampaign(4);
		expenseParseIndex.setIndexPositionCampaign(2);
		expenseParseIndex.setIndexTypeOfFinancialTransaction(10);
		expenseParseIndex.setIndexDate(5);
		expenseParseIndex.setIndexCpfCnpjSupplier(6);
		expenseParseIndex.setIndexNameSupplier(8);
		expenseParseIndex.setIndexValue(9);	
		
		return expenseParseIndex;
	}

	/*
	 * This method generate the ParseExpenseIndex, setting the index number for each attribute from
	 * the Campaign of 2006
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected ParseIndex<Expense> getIndicesParseExpense2006() {
		ExpenseParseIndex expenseParseIndex = new ExpenseParseIndex(YEAR_2006);
		expenseParseIndex.setIndexUnitFederationCampaign(4);
		expenseParseIndex.setIndexNumberCampaign(3);
		expenseParseIndex.setIndexPositionCampaign(1);
		expenseParseIndex.setIndexTypeOfFinancialTransaction(11);
		expenseParseIndex.setIndexDocumentType(16);
		expenseParseIndex.setIndexFormOfPayment(13);
		expenseParseIndex.setIndexDocumentNumber(15);
		expenseParseIndex.setIndexDate(10);
		expenseParseIndex.setIndexCpfCnpjSupplier(19);
		expenseParseIndex.setIndexCpfCnpjSupplier(18);
		expenseParseIndex.setIndexValue(9);	
		
		return expenseParseIndex;
	}

	/*
	 * This method generate the ParseExpenseIndex, setting the index number for each attribute from
	 * the Campaign of 2010
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected ParseIndex<Expense> getIndicesParseExpense2010() {
		ExpenseParseIndex expenseParseIndex = new ExpenseParseIndex(YEAR_2010);
		expenseParseIndex.setIndexUnitFederationCampaign(1);
		expenseParseIndex.setIndexNumberCampaign(3);
		expenseParseIndex.setIndexPositionCampaign(4);
		expenseParseIndex.setIndexTypeOfFinancialTransaction(14);
		expenseParseIndex.setIndexDocumentType(8);
		expenseParseIndex.setIndexFormOfPayment(16);
		expenseParseIndex.setIndexDocumentNumber(9);
		expenseParseIndex.setIndexDate(12);
		expenseParseIndex.setIndexCpfCnpjSupplier(10);
		expenseParseIndex.setIndexNameSupplier(11);
		expenseParseIndex.setIndexValue(13);	
		expenseParseIndex.setIndexDescription(17);
		
		return expenseParseIndex;
	}

	/*
	 * This method generate the ParseExpenseIndex from the Campaign of 2002
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected ParseIndex<Expense> getIndicesParseRevenue2002() {
		return new ExpenseParseIndex(YEAR_2002);
	}

	/*
	 * This method generate the ParseExpenseIndex from the Campaign of 2006
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected ParseIndex<Expense> getIndicesParseRevenue2006() {
		return new ExpenseParseIndex(YEAR_2006);
	}

	/*
	 * This method generate the ParseExpenseIndex from the Campaign of 2010
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected ParseIndex<Expense> getIndicesParseRevenue2010() {
		return new ExpenseParseIndex(YEAR_2010);
	}

}
