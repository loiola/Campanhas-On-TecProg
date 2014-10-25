package parse.register.revenue_expense;

import model.beans.Donor;
import parse.ParseException;
import parse.control.ParseControlDonor;
import parse.control.ParseControl;
import parse.index.DonorParseIndex;
import parse.index.ParseIndex;

public class RegisterToParseDonor extends RegisterToParseRevenueAndExpenses<Donor> {

	/* 
	 * Class used to extract Donor attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseRegisterDonor inherited constructor to
	 * register informations from an Donor
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterToParseDonor(String fileType, String year) throws ParseException {
		super(fileType, year);

	}

	// Methods
	
	/*
	 * @see parse.register.RegisterParse#newInstance(parse.index.ParseIndex)
	 * This method generate a ParseControlDonor to be used by constructor
	 * @return a ParseControlDonor
	 */
	@Override
	public ParseControl<Donor> newIntance(ParseIndex<Donor> parseIndex) {
		ParseControlDonor parseControlDonor = new ParseControlDonor(parseIndex);
		return parseControlDonor;
	}
	
	/*
	 * This method generate the ParseIndexDonor, setting the index number for each attribute from
	 * the Revenue of 2002
	 * @return a donorParseIndex
	 */
	public DonorParseIndex getIndicesParseRevenue2002() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndexDonorCpfCnpj(6);
		donorParseIndex.setIndexName(8);
		donorParseIndex.setIndexUnitFederation(7);
		
		return donorParseIndex;
	}
	
	/*
	 * This method generate the ParseIndexDonor, setting the index number for each attribute from
	 * the Revenue of 2006
	 * @return a donorParseIndex
	 */
	public DonorParseIndex getIndicesParseRevenue2006() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndexDonorCpfCnpj(16);
		donorParseIndex.setIndexName(15);
		donorParseIndex.setIndexUnitFederation(17);
		donorParseIndex.setIndexRegistrationStatus(18);
		
		return donorParseIndex;
	}
	
	/*
	 * This method generate the ParseIndexDonor, setting the index number for each attribute from
	 * the Revenue of 2010
	 * @return a donorParseIndex
	 */
	public DonorParseIndex getIndicesParseRevenue2010() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndexDonorCpfCnpj(10);
		donorParseIndex.setIndexName(11);
		
		return donorParseIndex;
	}

	/*
	 * This method generate the ParseIndexDonor, setting the index number for each attribute from
	 * the Expense of 2002
	 * Note: Not implemented because the donor has no expense!
	 * @return a donorParseIndex
	 */
	@Override
	protected ParseIndex<Donor> getIndicesParseExpense2002() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		return donorParseIndex;
	}

	/*
	 * This method generate the ParseIndexDonor, setting the index number for each attribute from
	 * the Expense of 2006
	 * Note: Not implemented because the donor has no expense!
	 * @return a donorParseIndex
	 */
	@Override
	protected ParseIndex<Donor> getIndicesParseExpense2006() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		return donorParseIndex;
	}

	/*
	 * This method generate the ParseIndexDonor, setting the index number for each attribute from
	 * the Expense of 2010
	 * Note: Not implemented because the donor has no expense!
	 * @return a donorParseIndex
	 */
	@Override
	protected ParseIndex<Donor> getIndicesParseExpense2010() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		return donorParseIndex;
	}

}
