package parse.register.campaign;

import model.beans.Position;
import parse.ParseException;
import parse.control.ParseControlPosition;
import parse.control.ParseControl;
import parse.index.CargoIndicesParse;
import parse.index.IndicesParse;
import parse.register.CadastroParse;

public class CadastroCargoParse extends CadastroParse<Position> {
	
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
	 * @see parse.register.CadastroParse#novaInstancia(parse.index.IndicesParse)
	 * This method generate a ParsePositionControl to be used by constructor
	 * @return a ParsePositionControl
	 */
	@Override
	public ParseControl<Position> novaInstancia(
			IndicesParse<Position> indicesParse) {
		ParseControlPosition parseControlPosition = new ParseControlPosition(indicesParse);
		return parseControlPosition;
	}

	/*
	 * @see parse.register.CadastroParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParsePositionIndex, setting the index number for each attribute
	 * @return a ParsePositionIndex
	 */
	@Override
	protected IndicesParse<Position> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		
		CargoIndicesParse cargoIndicesParse;
		cargoIndicesParse = new CargoIndicesParse();
		
		cargoIndicesParse.setIndiceCodigo(8);
		cargoIndicesParse.setIndiceDescricao(9);
		
		return cargoIndicesParse;
	}

}
