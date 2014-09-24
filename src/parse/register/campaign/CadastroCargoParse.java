package parse.register.campaign;

import model.beans.Position;
import parse.ParseException;
import parse.control.ParseControlPosition;
import parse.control.ParseControl;
import parse.index.PositionParseIndex;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public class CadastroCargoParse extends RegisterParse<Position> {
	
	/* 
	 * Class used to extract Position attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register informations from a Position
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public CadastroCargoParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}

	// Methods
	
	/*
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParsePositionControl to be used by constructor
	 * @return a ParsePositionControl
	 */
	@Override
	public ParseControl<Position> newIntance(
			ParseIndex<Position> indicesParse) {
		ParseControlPosition parseControlPosition = new ParseControlPosition(indicesParse);
		return parseControlPosition;
	}

	/*
	 * @see parse.register.RegisterParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParsePositionIndex, setting the index number for each attribute
	 * @return a ParsePositionIndex
	 */
	@Override
	protected ParseIndex<Position> getParseIndex(String tipoArquivo,
			String ano) throws ParseException {
		
		PositionParseIndex positionParseIndex;
		positionParseIndex = new PositionParseIndex();
		
		positionParseIndex.setIndexCode(8);
		positionParseIndex.setIndexDescription(9);
		
		return positionParseIndex;
	}

}
