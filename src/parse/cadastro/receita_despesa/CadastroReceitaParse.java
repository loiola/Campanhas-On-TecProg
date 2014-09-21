package parse.cadastro.receita_despesa;

import model.beans.Revenue;
import parse.ParseException;
import parse.controle.ParseControle;
import parse.controle.ReceitaParseControle;
import parse.indices.IndicesParse;
import parse.indices.ReceitaIndicesParse;

public class CadastroReceitaParse extends CadastroParseReceitasDespesas<Revenue> {
	
	/* 
	 * Class used to extract Revenue attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseExpenseRevenueRegister inherited constructor to
	 * register informations from an Revenue
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public CadastroReceitaParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}
	
	// Methods

	/*
	 * @see parse.cadastro.CadastroParse#novaInstancia(parse.indices.IndicesParse)
	 * This method generate a ParseRevenueControl to be used by constructor
	 * @return a ParseRevenueControl
	 */
	@Override
	public ParseControle<Revenue> novaInstancia(
			IndicesParse<Revenue> indicesParse) {
		return new ReceitaParseControle(indicesParse);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2002()
	 * This method generate the ParseRevenueIndex from the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected IndicesParse<Revenue> getIndicesParseDespesa2002() {
		return new ReceitaIndicesParse(ANO_2002);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2006()
	 * This method generate the ParseRevenueIndex from the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected IndicesParse<Revenue> getIndicesParseDespesa2006() {
		return new ReceitaIndicesParse(ANO_2006);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2010()
	 * This method generate the ParseRevenueIndex from the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected IndicesParse<Revenue> getIndicesParseDespesa2010() {
		return new ReceitaIndicesParse(ANO_2010);
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2002()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected IndicesParse<Revenue> getIndicesParseReceita2002() {
		ReceitaIndicesParse receitaIndicesParse = new ReceitaIndicesParse(ANO_2002);
		receitaIndicesParse.setIndiceCampanhaUf(0);
		receitaIndicesParse.setIndiceCampanhaNumero(4);
		receitaIndicesParse.setIndiceCampanhaCargo(2);
		receitaIndicesParse.setIndiceFormaPagamento(10);
		receitaIndicesParse.setIndiceData(5);
		receitaIndicesParse.setIndiceDoadorCpfCnpj(6);
		receitaIndicesParse.setIndiceDoadorNome(8);
		receitaIndicesParse.setIndiceValor(9);
		
		return receitaIndicesParse;
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2006()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected IndicesParse<Revenue> getIndicesParseReceita2006() {
		ReceitaIndicesParse receitaIndicesParse = new ReceitaIndicesParse(ANO_2006);
		receitaIndicesParse.setIndiceCampanhaUf(4);
		receitaIndicesParse.setIndiceCampanhaNumero(3);
		receitaIndicesParse.setIndiceCampanhaCargo(1);
		receitaIndicesParse.setIndiceTipoMovimentacao(11);
		receitaIndicesParse.setIndiceFormaPagamento(13);
		receitaIndicesParse.setIndiceData(10);
		receitaIndicesParse.setIndiceDoadorCpfCnpj(16);
		receitaIndicesParse.setIndiceDoadorNome(15);
		receitaIndicesParse.setIndiceValor(9);
		
		return receitaIndicesParse;
	}

	/*
	 * @see parse.cadastro.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2010()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected IndicesParse<Revenue> getIndicesParseReceita2010() {
		ReceitaIndicesParse receitaIndicesParse = new ReceitaIndicesParse(ANO_2010);
		receitaIndicesParse.setIndiceCampanhaUf(1);
		receitaIndicesParse.setIndiceCampanhaNumero(3);
		receitaIndicesParse.setIndiceCampanhaCargo(4);
		receitaIndicesParse.setIndiceTipoMovimentacao(14);
		receitaIndicesParse.setIndiceFormaPagamento(16);
		receitaIndicesParse.setIndiceReciboEleitoral(8);
		receitaIndicesParse.setIndiceNumeroDocumento(9);
		receitaIndicesParse.setIndiceData(12);
		receitaIndicesParse.setIndiceDoadorCpfCnpj(10);
		receitaIndicesParse.setIndiceDoadorNome(11);
		receitaIndicesParse.setIndiceValor(13);
		receitaIndicesParse.setIndiceDescricao(17);
		
		return receitaIndicesParse;
	}

}
