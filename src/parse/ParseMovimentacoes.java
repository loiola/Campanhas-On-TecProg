package parse;

import java.util.ArrayList;

import parse.cadastro.receita_despesa.CadastroDespesaParse;
import parse.cadastro.receita_despesa.CadastroDoadorParse;
import parse.cadastro.receita_despesa.CadastroFornecedorParse;
import parse.cadastro.receita_despesa.CadastroParseReceitasDespesas;
import parse.cadastro.receita_despesa.CadastroReceitaParse;
import parse.register.CadastroParse;

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