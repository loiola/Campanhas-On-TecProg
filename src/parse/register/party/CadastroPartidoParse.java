package parse.register.party;

import parse.ParseException;
import parse.control.ParseControl;
import parse.control.PartidoParseControle;
import parse.index.IndicesParse;
import parse.index.PartidoIndicesParse;
import parse.register.CadastroParse;
import model.beans.Party;

public class CadastroPartidoParse extends CadastroParse<Party> {
	
	/*
	 * Class responsible for identifying the contents of the file to read and get information
	 */

	// Constructors
	public CadastroPartidoParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}

	/*
	 * This method formalizes the instance index to be used
	 * @param an instance of class IndicesParse<Party>
	 * @return an instance of class PartyParseControl
	 */
	@Override
	public ParseControl<Party> novaInstancia(
			IndicesParse<Party> indicesParse) {
		PartidoParseControle partidoParseControle;
		partidoParseControle = new PartidoParseControle(indicesParse);
		return partidoParseControle;
	}

	/*
	 * This method checks the file type to correctly identify the indexes that have the information
	 * to be read and stored
	 * @param an String tipoArquivo
	 * @param an String ano
	 * @return an instance of class PartyIndicesParse
	 */
	@Override
	protected IndicesParse<Party> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		PartidoIndicesParse partidoIndicesParse;
		partidoIndicesParse = new PartidoIndicesParse();
		if(tipoArquivo.equals("partido"))
		{
			partidoIndicesParse.setIndiceNome(2);
			partidoIndicesParse.setIndiceSigla(1);
			partidoIndicesParse.setIndiceNumero(5);
			partidoIndicesParse.setIndiceDeferimento(3);
		}else if(tipoArquivo.equals("campanha"))
		{
			partidoIndicesParse.setIndiceNome(18);
			partidoIndicesParse.setIndiceSigla(17);
			partidoIndicesParse.setIndiceNumero(16);
		}
		return partidoIndicesParse;
	}

}