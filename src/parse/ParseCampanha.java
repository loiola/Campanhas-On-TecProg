package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.campaign.RegisterToParseCampaign;
import parse.register.campaign.RegisterToParseCandidate;
import parse.register.campaign.RegisterToParsePosition;
import parse.register.campaign.RegisterToParseResult;
import parse.register.party.RegisterToParseParty;

public class ParseCampanha extends Parse {

	public ParseCampanha(String tipoArquivo, String ano) throws ParseException {
		super(tipoArquivo, ano);
	}

	@Override
	protected void adicionarCadastrosParseNaLista(
			ArrayList<RegisterParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException {

		listaCadastrosParse.add(new RegisterToParseCandidate(tipoArquivo, ano));
		listaCadastrosParse.add(new RegisterToParseResult(tipoArquivo, ano));
		listaCadastrosParse.add(new RegisterToParsePosition(tipoArquivo, ano));
		listaCadastrosParse.add(new RegisterToParseParty(tipoArquivo, ano));
		listaCadastrosParse.add(new RegisterToParseCampaign(tipoArquivo, ano));
	}
	
}
