package parse.register.revenue_expense;

import parse.ParseException;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public abstract class CadastroParseReceitasDespesas<O> extends RegisterParse<O> {

	public static final String EXPENSE = "expense";
	public static final String REVENUE = "revenue";

	public static final String YEAR_2002 = "2002";
	public static final String YEAR_2006 = "2006";
	public static final String YEAR_2010 = "2010";
	
	public CadastroParseReceitasDespesas(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}

	@Override
	protected ParseIndex<O> getIndicesParse(String tipoArquivo, String ano)
			throws ParseException {
		if(tipoArquivo.equals(EXPENSE)) {
			return getIndicesParseDespesa(ano);
		} else if (tipoArquivo.equals(REVENUE)) {
			return getIndicesParseReceita(ano);
		}
		
		throw new ParseException("Tipo do Arquivo está invalido!");
	}
	
	protected abstract ParseIndex<O> getIndicesParseDespesa2002();
	protected abstract ParseIndex<O> getIndicesParseDespesa2006();
	protected abstract ParseIndex<O> getIndicesParseDespesa2010();

	protected abstract ParseIndex<O> getIndicesParseReceita2002();
	protected abstract ParseIndex<O> getIndicesParseReceita2006();
	protected abstract ParseIndex<O> getIndicesParseReceita2010();

	private ParseIndex<O> getIndicesParseDespesa(String ano) throws ParseException {
		switch (ano) {
		case YEAR_2002:
			return getIndicesParseDespesa2002();
		case YEAR_2006:
			return getIndicesParseDespesa2006();
		case YEAR_2010:
			return getIndicesParseDespesa2010();
		}
		throw new ParseException("Ano do arquivo está invalido!");
	}
	
	private ParseIndex<O> getIndicesParseReceita(String ano) throws ParseException {
		switch (ano) {
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
