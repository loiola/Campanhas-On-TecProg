package parse.register.campaign;

import model.beans.Position;
import parse.ParseException;
import parse.control.ParseControlPosition;
import parse.control.ParseControl;
import parse.index.PositionParseIndex;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public class RegisterToParsePosition extends RegisterParse<Position> {
	
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
	public RegisterToParsePosition(String fileType, String year) throws ParseException {
		super(fileType, year);
	}

	// Methods
	
	/*
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParsePositionControl to be used by constructor
	 * @return a ParsePositionControl
	 */
	@Override
	public ParseControl<Position> newIntance(ParseIndex<Position> parseIndex) {
		ParseControlPosition parseControlPosition = new ParseControlPosition(parseIndex);
		return parseControlPosition;
	}

	/*
	 * @see parse.register.RegisterParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParsePositionIndex, setting the index number for each attribute
	 * @return a ParsePositionIndex
	 */
	@Override
	protected ParseIndex<Position> getParseIndex(String fileType, String year) throws ParseException {
		PositionParseIndex positionParseIndex;
		positionParseIndex = new PositionParseIndex();
		
		positionParseIndex.setIndexCode(8);
		positionParseIndex.setIndexDescription(9);
		
		return positionParseIndex;
	}
}
