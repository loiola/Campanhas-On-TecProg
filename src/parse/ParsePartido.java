package parse;

import java.util.ArrayList;

import parse.register.CadastroParse;
import parse.register.party.CadastroPartidoParse;

public class ParsePartido extends Parse {
	
	/*
	 * Class responsible for performing the parse of political parties
	 */

	// Constructors
	public ParsePartido(String tipoArquivo, String ano) throws ParseException {
		super(tipoArquivo, ano);

	}

	/*
	 * This method adds an instance of class PartyRegisterParse to parse the list of registration
	 * @param an ArrayList<RegisterParse<?>>
	 * @param an String tipoArquivo
	 * @param an String ano
	 */
	@Override
	protected void adicionarCadastrosParseNaLista(
			ArrayList<CadastroParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException {

		listaCadastrosParse.add(new CadastroPartidoParse(tipoArquivo, ano));
	}

}