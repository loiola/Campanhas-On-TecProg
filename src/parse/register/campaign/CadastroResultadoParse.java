package parse.register.campaign;

import model.beans.Result;
import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlResult;
import parse.index.IndicesParse;
import parse.index.ResultadoIndicesParse;
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
	 * @see parse.register.CadastroParse#novaInstancia(parse.index.IndicesParse)
	 * This method generate a ParseResultControl to be used by constructor
	 * @return a ParseResultControl
	 */
	@Override
	public ParseControl<Result> novaInstancia(
			IndicesParse<Result> indicesParse) {
		ParseControlResult parseControlResult = new ParseControlResult(indicesParse);
		return parseControlResult;
	}

	/*
	 * @see parse.register.CadastroParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseResultIndex, setting the index number for each attribute
	 * @return a ParseResultIndex
	 */
	@Override
	protected IndicesParse<Result> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		
		ResultadoIndicesParse resultadoIndicesParse;
		resultadoIndicesParse = new ResultadoIndicesParse();
		
		resultadoIndicesParse.setIndiceCodigo(40);
		resultadoIndicesParse.setIndiceDescricao(41);
		
		return resultadoIndicesParse;
	}

}
