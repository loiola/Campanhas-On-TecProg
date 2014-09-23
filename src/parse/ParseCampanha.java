package parse;

import java.util.ArrayList;

import parse.register.CadastroParse;
import parse.register.campaign.CadastroCampanhaParse;
import parse.register.campaign.CadastroCandidatoParse;
import parse.register.campaign.CadastroCargoParse;
import parse.register.campaign.CadastroResultadoParse;
import parse.register.party.CadastroPartidoParse;

public class ParseCampanha extends Parse {

	public ParseCampanha(String tipoArquivo, String ano) throws ParseException {
		super(tipoArquivo, ano);
	}

	@Override
	protected void adicionarCadastrosParseNaLista(
			ArrayList<CadastroParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException {

		listaCadastrosParse.add(new CadastroCandidatoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroResultadoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroCargoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroPartidoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroCampanhaParse(tipoArquivo, ano));
	}
	
}
