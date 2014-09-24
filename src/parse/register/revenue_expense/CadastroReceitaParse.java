package parse.register.revenue_expense;

import model.beans.Revenue;
import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlRevenue;
import parse.index.ParseIndex;
import parse.index.ReceitaIndicesParse;

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
	 * @see parse.register.CadastroParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseRevenueControl to be used by constructor
	 * @return a ParseRevenueControl
	 */
	@Override
	public ParseControl<Revenue> novaInstancia(
			ParseIndex<Revenue> indicesParse) {
		return new ParseControlRevenue(indicesParse);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2002()
	 * This method generate the ParseRevenueIndex from the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseDespesa2002() {
		return new ReceitaIndicesParse(YEAR_2002);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2006()
	 * This method generate the ParseRevenueIndex from the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseDespesa2006() {
		return new ReceitaIndicesParse(YEAR_2006);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2010()
	 * This method generate the ParseRevenueIndex from the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseDespesa2010() {
		return new ReceitaIndicesParse(YEAR_2010);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2002()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseReceita2002() {
		ReceitaIndicesParse receitaIndicesParse = new ReceitaIndicesParse(YEAR_2002);
		receitaIndicesParse.setIndexUnitFederationCampaign(0);
		receitaIndicesParse.setIndexNumberCampaign(4);
		receitaIndicesParse.setIndexPositionCampaign(2);
		receitaIndicesParse.setIndexFormOfPayment(10);
		receitaIndicesParse.setIndexDate(5);
		receitaIndicesParse.setIndiceDoadorCpfCnpj(6);
		receitaIndicesParse.setIndiceDoadorNome(8);
		receitaIndicesParse.setIndexValue(9);
		
		return receitaIndicesParse;
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2006()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseReceita2006() {
		ReceitaIndicesParse receitaIndicesParse = new ReceitaIndicesParse(YEAR_2006);
		receitaIndicesParse.setIndexUnitFederationCampaign(4);
		receitaIndicesParse.setIndexNumberCampaign(3);
		receitaIndicesParse.setIndexPositionCampaign(1);
		receitaIndicesParse.setIndexTypeOfFinancialTransaction(11);
		receitaIndicesParse.setIndexFormOfPayment(13);
		receitaIndicesParse.setIndexDate(10);
		receitaIndicesParse.setIndiceDoadorCpfCnpj(16);
		receitaIndicesParse.setIndiceDoadorNome(15);
		receitaIndicesParse.setIndexValue(9);
		
		return receitaIndicesParse;
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2010()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseReceita2010() {
		ReceitaIndicesParse receitaIndicesParse = new ReceitaIndicesParse(YEAR_2010);
		receitaIndicesParse.setIndexUnitFederationCampaign(1);
		receitaIndicesParse.setIndexNumberCampaign(3);
		receitaIndicesParse.setIndexPositionCampaign(4);
		receitaIndicesParse.setIndexTypeOfFinancialTransaction(14);
		receitaIndicesParse.setIndexFormOfPayment(16);
		receitaIndicesParse.setIndiceReciboEleitoral(8);
		receitaIndicesParse.setIndexDocumentNumber(9);
		receitaIndicesParse.setIndexDate(12);
		receitaIndicesParse.setIndiceDoadorCpfCnpj(10);
		receitaIndicesParse.setIndiceDoadorNome(11);
		receitaIndicesParse.setIndexValue(13);
		receitaIndicesParse.setIndexDescription(17);
		
		return receitaIndicesParse;
	}

}
