package parse;

import java.util.ArrayList;

import parse.register.RegisterParse;
import parse.register.revenue_expense.RegisterToParseExpense;
import parse.register.revenue_expense.RegisterToParseDonor;
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
			ArrayList<RegisterParse<?>> listaCadastrosParse,
			String tipoArquivo, String ano) throws ParseException {
		
		if(tipoArquivo.equals(CadastroParseReceitasDespesas.EXPENSE))
		{
			listaCadastrosParse.add(new CadastroFornecedorParse(tipoArquivo, ano));
			listaCadastrosParse.add(new RegisterToParseExpense(tipoArquivo, ano));
		}
		else
		{
			listaCadastrosParse.add(new RegisterToParseDonor(tipoArquivo, ano));
			listaCadastrosParse.add(new CadastroReceitaParse(tipoArquivo, ano));
		}
	}

}