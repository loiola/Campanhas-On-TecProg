package parse;

import java.util.ArrayList;

import parse.cadastro.campanha.CadastroCampanhaParse;
import parse.cadastro.campanha.CadastroCandidatoParse;
import parse.cadastro.campanha.CadastroCargoParse;
import parse.cadastro.campanha.CadastroResultadoParse;
import parse.cadastro.partido.CadastroPartidoParse;
import parse.register.CadastroParse;

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
