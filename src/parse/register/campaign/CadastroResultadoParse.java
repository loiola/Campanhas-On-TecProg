package parse.register.campaign;

import model.beans.Result;
import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlResult;
import parse.index.ParseIndex;
import parse.index.ResultParseIndex;
import parse.register.CadastroParse;

public class CadastroResultadoParse extends CadastroParse<Result>{
	
	/* 
	 * Class used to extract Result attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register informations from a Result
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public CadastroResultadoParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}
	
	// Methods
	
	/*
	 * @see parse.register.CadastroParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseResultControl to be used by constructor
	 * @return a ParseResultControl
	 */
	@Override
	public ParseControl<Result> novaInstancia(
			ParseIndex<Result> indicesParse) {
		ParseControlResult parseControlResult = new ParseControlResult(indicesParse);
		return parseControlResult;
	}

	/*
	 * @see parse.register.CadastroParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseResultIndex, setting the index number for each attribute
	 * @return a ParseResultIndex
	 */
	@Override
	protected ParseIndex<Result> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		
		ResultParseIndex resultParseIndex;
		resultParseIndex = new ResultParseIndex();
		
		resultParseIndex.setIndexCodeResult(40);
		resultParseIndex.setIndexDescriptionResult(41);
		
		return resultParseIndex;
	}

}
