package parse.register.revenue_expense;

import model.beans.Supplier;
import parse.ParseException;
import parse.control.ParseControlSupplier;
import parse.control.ParseControl;
import parse.index.SupplierParseIndex;
import parse.index.ParseIndex;

public class RegisterToParseSupplier extends RegisterToParseRevenueAndExpenses<Supplier> {

	/* 
	 * Class used to extract Supplier attributes and forward the register to the Database
	 */
	
	// Constructor
	
	/*
	 * This constructor use the ParseRegisterSupplier inherited constructor to
	 * register informations from an Supplier
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterToParseSupplier(String fileType, String year) throws ParseException {
		super(fileType, year);

	}

	// Methods
	
	/*
	 * @see parse.register.RegisterParse#newInstance(parse.index.ParseIndex)
	 * This method generate a ParseControlSupplier to be used by constructor
	 * @return a ParseControlSupplier
	 */
	@Override
	public ParseControl<Supplier> newIntance(ParseIndex<Supplier> parseIndex) {
		ParseControlSupplier parseControlSupplier = new ParseControlSupplier(parseIndex);
		return parseControlSupplier;
	}
	
	/*
	 * This method generate the ParseIndexSupplier, setting the index number for each attribute from
	 * the Expense of 2002
	 * @return a supplierParseIndex
	 */
	public SupplierParseIndex getIndicesParseExpense2002() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndexSupplierCpfCnpj(6);
		supplierParseIndex.setIndexName(8);
		supplierParseIndex.setIndexUnitFederation(7);
		
		return supplierParseIndex;
	}
	
	/*
	 * This method generate the ParseIndexSupplier, setting the index number for each attribute from
	 * the Expense of 2006
	 * @return a supplierParseIndex
	 */
	public SupplierParseIndex getIndicesParseExpense2006() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndexSupplierCpfCnpj(19);
		supplierParseIndex.setIndexName(18);
		supplierParseIndex.setIndexUnitFederation(20);
		supplierParseIndex.setIndexRegistrationStatus(21);
		
		return supplierParseIndex;
	}
	
	/*
	 * This method generate the ParseIndexSupplier, setting the index number for each attribute from
	 * the Expense of 2010
	 * @return a supplierParseIndex
	 */
	public SupplierParseIndex getIndicesParseExpense2010() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndexSupplierCpfCnpj(10);
		supplierParseIndex.setIndexName(11);
		
		return supplierParseIndex;
	}
	
	/*
	 * This method generate the ParseIndexSupplier, setting the index number for each attribute from
	 * the Supplier of 2002
	 * Note: Not implemented because the supplier has no revenue!
	 * @return a donorParseIndex
	 */
	@Override
	public ParseIndex<Supplier> getIndicesParseRevenue2002() {
		return null;
	}

	/*
	 * This method generate the ParseIndexSupplier, setting the index number for each attribute from
	 * the Supplier of 2006
	 * Note: Not implemented because the supplier has no revenue!
	 * @return a donorParseIndex
	 */
	@Override
	public ParseIndex<Supplier> getIndicesParseRevenue2006() {
		return null;
	}

	/*
	 * This method generate the ParseIndexSupplier, setting the index number for each attribute from
	 * the Supplier of 2010
	 * Note: Not implemented because the supplier has no revenue!
	 * @return a donorParseIndex
	 */
	@Override
	public ParseIndex<Supplier> getIndicesParseRevenue2010() {
		return null;
	}

}
