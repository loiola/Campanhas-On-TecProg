package parse;

import java.util.ArrayList;

import parse.register.CadastroParse;
import parse.register.revenue_expense.CadastroDespesaParse;
import parse.register.revenue_expense.CadastroDoadorParse;
import parse.register.revenue_expense.CadastroFornecedorParse;
import parse.register.revenue_expense.CadastroParseReceitasDespesas;
import parse.register.revenue_expense.CadastroReceitaParse;

public class ParseMovimentacoes extends Parse {
	
	/*
	 * Class responsible for performing the parse of transactions
	 */
	 
	// Constructors
	public ParseMovimentacoes(String tipoArquivo, String ano) throws ParseException {
		super(tipoArquivo, ano);

	}

	/*
	 * This method adds instances of classes related to financial transactions to register
	 * @param an ArrayList<RegisterParse<?>>
	 * @param an String tipoArquivo
	 * @param an String ano
	 */
	@Override
	protected void adicionarCadastrosParseNaLista(
			ArrayList<CadastroParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException {
		
		if(tipoArquivo.equals(CadastroParseReceitasDespesas.DESPESA))
		{
			listaCadastrosParse.add(new CadastroFornecedorParse(tipoArquivo, ano));
			listaCadastrosParse.add(new CadastroDespesaParse(tipoArquivo, ano));
		}
		else
		{
			listaCadastrosParse.add(new CadastroDoadorParse(tipoArquivo, ano));
			listaCadastrosParse.add(new CadastroReceitaParse(tipoArquivo, ano));
		}
	}

}