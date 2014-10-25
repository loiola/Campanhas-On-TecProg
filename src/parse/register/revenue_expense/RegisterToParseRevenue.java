package parse.register.revenue_expense;

import model.beans.Revenue;
import parse.ParseException;
import parse.control.ParseControl;
import parse.control.ParseControlRevenue;
import parse.index.ParseIndex;
import parse.index.RevenueParseIndex;

public class RegisterToParseRevenue extends RegisterToParseRevenueAndExpenses<Revenue> {
	
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
	public RegisterToParseRevenue(String fileType, String year) throws ParseException {
		super(fileType, year);
	}
	
	// Methods

	/*
	 * This method generate a ParseRevenueControl to be used by constructor
	 * @return a ParseRevenueControl
	 */
	@Override
	public ParseControl<Revenue> newIntance(ParseIndex<Revenue> parseIndex) {
		ParseControlRevenue parseControlRevenue = new ParseControlRevenue(parseIndex);
		return parseControlRevenue;
	}

	/*
	 * This method generate the ParseRevenueIndex from the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseExpense2002() {
		RevenueParseIndex revenueParseIndex = new RevenueParseIndex(YEAR_2002);
		return revenueParseIndex;
	}

	/*
	 * This method generate the ParseRevenueIndex from the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseExpense2006() {
		RevenueParseIndex revenueParseIndex = new RevenueParseIndex(YEAR_2006);
		return revenueParseIndex;
	}

	/*
	 * This method generate the ParseRevenueIndex from the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseExpense2010() {
		RevenueParseIndex revenueParseIndex = new RevenueParseIndex(YEAR_2010);
		return revenueParseIndex;
	}

	/*
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2002
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseRevenue2002() {
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
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2006
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseRevenue2006() {
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
	 * This method generate the ParseRevenueIndex, setting the index number for each attribute from
	 * the Campaign of 2010
	 * @return a ParseRevenueIndex
	 */
	@Override
	protected ParseIndex<Revenue> getIndicesParseRevenue2010() {
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
