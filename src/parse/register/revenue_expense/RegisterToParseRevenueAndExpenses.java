package parse.register.revenue_expense;

import parse.ParseException;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public abstract class RegisterToParseRevenueAndExpenses<O> extends RegisterParse<O> {

	public static final String EXPENSE = "expense";
	public static final String REVENUE = "revenue";

	public static final String YEAR_2002 = "2002";
	public static final String YEAR_2006 = "2006";
	public static final String YEAR_2010 = "2010";
	
	public RegisterToParseRevenueAndExpenses(String fileType, String year) throws ParseException {
		super(fileType, year);
	}

	@Override
	protected ParseIndex<O> getParseIndex(String fileType, String year) throws ParseException {
		//Variables to store result of validation of file type
		boolean validationFileExpense = fileType.equals(EXPENSE);
		boolean validationFileRevenue = fileType.equals(REVENUE);
		
		if(validationFileExpense) {
			return getIndicesParseDespesa(year);
		} else if (validationFileRevenue) {
			return getIndicesParseReceita(year);
		}
		
		throw new ParseException("Tipo do Arquivo está invalido!");
	}
	
	protected abstract ParseIndex<O> getIndicesParseDespesa2002();
	protected abstract ParseIndex<O> getIndicesParseDespesa2006();
	protected abstract ParseIndex<O> getIndicesParseDespesa2010();

	protected abstract ParseIndex<O> getIndicesParseReceita2002();
	protected abstract ParseIndex<O> getIndicesParseReceita2006();
	protected abstract ParseIndex<O> getIndicesParseReceita2010();

	private ParseIndex<O> getIndicesParseDespesa(String year) throws ParseException {
		switch (year) {
		case YEAR_2002:
			return getIndicesParseDespesa2002();
		case YEAR_2006:
			return getIndicesParseDespesa2006();
		case YEAR_2010:
			return getIndicesParseDespesa2010();
		}
		throw new ParseException("Ano do arquivo está invalido!");
	}
	
	private ParseIndex<O> getIndicesParseReceita(String year) throws ParseException {
		switch (year) {
		case YEAR_2002:
			return getIndicesParseReceita2002();
		case YEAR_2006:
			return getIndicesParseReceita2006();
		case YEAR_2010:
			return getIndicesParseReceita2010();
		}
		throw new ParseException("Ano do arquivo está invalido!");
	}
	
}
