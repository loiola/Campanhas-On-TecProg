package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.campaign.RegisterToParseCampaign;
import parse.register.campaign.RegisterToParseCandidate;
import parse.register.campaign.CadastroCargoParse;
import parse.register.campaign.CadastroResultadoParse;
import parse.register.party.CadastroPartidoParse;

public class ParseCampanha extends Parse {

	public ParseCampanha(String tipoArquivo, String ano) throws ParseException {
		super(tipoArquivo, ano);
	}

	@Override
	protected void adicionarCadastrosParseNaLista(
			ArrayList<RegisterParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException {

		listaCadastrosParse.add(new RegisterToParseCandidate(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroResultadoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroCargoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new CadastroPartidoParse(tipoArquivo, ano));
		listaCadastrosParse.add(new RegisterToParseCampaign(tipoArquivo, ano));
	}
	
}
