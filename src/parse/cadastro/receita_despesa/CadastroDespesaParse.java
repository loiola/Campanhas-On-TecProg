package parse.cadastro.receita_despesa;

import modelo.beans.Expense;
import parse.ParseException;
import parse.controle.DespesaParseControle;
import parse.controle.ParseControle;
import parse.indices.DespesaIndicesParse;
import parse.indices.IndicesParse;

public class CadastroDespesaParse extends CadastroParseReceitasDespesas<Expense> {
	
	/* 
	 * Class used to extract Expense attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseExpenseRevenueRegister inherited constructor to
	 * register informations from an Expense
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public CadastroDespesaParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}
	
	// Methods
	
	/*
	 * @see parse.cadastro.CadastroParse#novaInstancia(parse.indices.IndicesParse)
	 * This method generate a ParseExpenseControl to be used by constructor
	 * @return a ParseExpenseControl
	 */
	@Override
	public ParseControle<Expense> novaInstancia(
			IndicesParse<Expense> indicesParse) {
		return new DespesaParseControle(indicesParse);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2002()
	 * This method generate the ParseExpenseIndex, setting the index number for each attribute from
	 * the Campaign of 2002
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected IndicesParse<Expense> getIndicesParseDespesa2002() {
		DespesaIndicesParse despesaIndicesParse = new DespesaIndicesParse(ANO_2002);
		despesaIndicesParse.setIndiceCampanhaUf(0);
		despesaIndicesParse.setIndiceCampanhaNumero(4);
		despesaIndicesParse.setIndiceCampanhaCargo(2);
		despesaIndicesParse.setIndiceTipoMovimentacao(10);
		despesaIndicesParse.setIndiceData(5);
		despesaIndicesParse.setIndiceFornecedorCpfCnpj(6);
		despesaIndicesParse.setIndiceFornecedorNome(8);
		despesaIndicesParse.setIndiceValor(9);	
		return despesaIndicesParse;
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2006()
	 * This method generate the ParseExpenseIndex, setting the index number for each attribute from
	 * the Campaign of 2006
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected IndicesParse<Expense> getIndicesParseDespesa2006() {
		DespesaIndicesParse despesaIndicesParse = new DespesaIndicesParse(ANO_2006);
		despesaIndicesParse.setIndiceCampanhaUf(4);
		despesaIndicesParse.setIndiceCampanhaNumero(3);
		despesaIndicesParse.setIndiceCampanhaCargo(1);
		despesaIndicesParse.setIndiceTipoMovimentacao(11);
		despesaIndicesParse.setIndiceTipoDocumento(16);
		despesaIndicesParse.setIndiceFormaPagamento(13);
		despesaIndicesParse.setIndiceNumeroDocumento(15);
		despesaIndicesParse.setIndiceData(10);
		despesaIndicesParse.setIndiceFornecedorCpfCnpj(19);
		despesaIndicesParse.setIndiceFornecedorCpfCnpj(18);
		despesaIndicesParse.setIndiceValor(9);	
		return despesaIndicesParse;

	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2010()
	 * This method generate the ParseExpenseIndex, setting the index number for each attribute from
	 * the Campaign of 2010
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected IndicesParse<Expense> getIndicesParseDespesa2010() {
		DespesaIndicesParse despesaIndicesParse = new DespesaIndicesParse(ANO_2010);
		despesaIndicesParse.setIndiceCampanhaUf(1);
		despesaIndicesParse.setIndiceCampanhaNumero(3);
		despesaIndicesParse.setIndiceCampanhaCargo(4);
		despesaIndicesParse.setIndiceTipoMovimentacao(14);
		despesaIndicesParse.setIndiceTipoDocumento(8);
		despesaIndicesParse.setIndiceFormaPagamento(16);
		despesaIndicesParse.setIndiceNumeroDocumento(9);
		despesaIndicesParse.setIndiceData(12);
		despesaIndicesParse.setIndiceFornecedorCpfCnpj(10);
		despesaIndicesParse.setIndiceFornecedorNome(11);
		despesaIndicesParse.setIndiceValor(13);	
		despesaIndicesParse.setIndiceDescricao(17);
		return despesaIndicesParse;
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2002()
	 * This method generate the ParseExpenseIndex from the Campaign of 2002
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected IndicesParse<Expense> getIndicesParseReceita2002() {
		return new DespesaIndicesParse(ANO_2002);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2006()
	 * This method generate the ParseExpenseIndex from the Campaign of 2006
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected IndicesParse<Expense> getIndicesParseReceita2006() {
		return new DespesaIndicesParse(ANO_2006);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2010()
	 * This method generate the ParseExpenseIndex from the Campaign of 2010
	 * @return a ParseExpenseIndex
	 */
	@Override
	protected IndicesParse<Expense> getIndicesParseReceita2010() {
		return new DespesaIndicesParse(ANO_2010);
	}

}
