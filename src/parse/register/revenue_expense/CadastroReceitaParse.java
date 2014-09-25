package parse.register.revenue_expense;

import model.beans.Revenue;
import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlRevenue;
import parse.index.ParseIndex;
import parse.index.RevenueParseIndex;

public class CadastroReceitaParse extends RegisterToParseRevenueAndExpenses<Revenue> {
	
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
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseRevenueControl to be used by constructor
	 * @return a ParseRevenueControl
	 */
	@Override
	public ParseControl<Revenue> newIntance(
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
		return new RevenueParseIndex(YEAR_2002);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2006()
	 * This method generate the ParseRevenueIndex from the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseDespesa2006() {
		return new RevenueParseIndex(YEAR_2006);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseDespesa2010()
	 * This method generate the ParseRevenueIndex from the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseDespesa2010() {
		return new RevenueParseIndex(YEAR_2010);
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2002()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseReceita2002() {
		RevenueParseIndex revenueParseIndex = new RevenueParseIndex(YEAR_2002);
		revenueParseIndex.setIndexUnitFederationCampaign(0);
		revenueParseIndex.setIndexNumberCampaign(4);
		revenueParseIndex.setIndexPositionCampaign(2);
		revenueParseIndex.setIndexFormOfPayment(10);
		revenueParseIndex.setIndexDate(5);
		revenueParseIndex.setIndexCpfCnpjDonor(6);
		revenueParseIndex.setIndexNameDonor(8);
		revenueParseIndex.setIndexValue(9);
		
		return revenueParseIndex;
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2006()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseReceita2006() {
		RevenueParseIndex revenueParseIndex = new RevenueParseIndex(YEAR_2006);
		revenueParseIndex.setIndexUnitFederationCampaign(4);
		revenueParseIndex.setIndexNumberCampaign(3);
		revenueParseIndex.setIndexPositionCampaign(1);
		revenueParseIndex.setIndexTypeOfFinancialTransaction(11);
		revenueParseIndex.setIndexFormOfPayment(13);
		revenueParseIndex.setIndexDate(10);
		revenueParseIndex.setIndexCpfCnpjDonor(16);
		revenueParseIndex.setIndexNameDonor(15);
		revenueParseIndex.setIndexValue(9);
		
		return revenueParseIndex;
	}

	/*
	 * @see parse.register.receita_despesa.CadastroParseReceitasDespesas#getIndicesParseReceita2010()
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseReceita2010() {
		RevenueParseIndex revenueParseIndex = new RevenueParseIndex(YEAR_2010);
		revenueParseIndex.setIndexUnitFederationCampaign(1);
		revenueParseIndex.setIndexNumberCampaign(3);
		revenueParseIndex.setIndexPositionCampaign(4);
		revenueParseIndex.setIndexTypeOfFinancialTransaction(14);
		revenueParseIndex.setIndexFormOfPayment(16);
		revenueParseIndex.setIndexElectoralReceipt(8);
		revenueParseIndex.setIndexDocumentNumber(9);
		revenueParseIndex.setIndexDate(12);
		revenueParseIndex.setIndexCpfCnpjDonor(10);
		revenueParseIndex.setIndexNameDonor(11);
		revenueParseIndex.setIndexValue(13);
		revenueParseIndex.setIndexDescription(17);
		
		return revenueParseIndex;
	}

}
