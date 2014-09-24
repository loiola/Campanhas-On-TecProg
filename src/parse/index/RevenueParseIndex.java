package parse.index;

import model.beans.Donor;
import model.beans.Revenue;

public class RevenueParseIndex extends FinancialTransactionParseIndex<Revenue> {
	
	/*
	 * Class to control the contents of information inherent to the revenue
	 */

	// Attributes
	private int indexElectoralReceipt;
	private int indexNameDonor;
	private int indexCpfCnpjDonor;
	
	// Constructors
	public RevenueParseIndex(String year) {
		super(year);
		this.indexElectoralReceipt = INVALID_INDEX;
		this.indexNameDonor = INVALID_INDEX;
		this.indexCpfCnpjDonor = INVALID_INDEX; 

	}
	
	/*
	 * This method formalizes the indices for reading the information about the revenue in the file
	 * @param an instance of the Class Revenue
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Revenue revenue, String[] field) {
		super.setValidIndex(revenue, field);
		
		Donor donor = new Donor();
		if(validIndex(this.indexElectoralReceipt)) {
			revenue.setRevenueElectoralReceipt(field[this.indexElectoralReceipt]);
		}
		if(validIndex(this.indexNameDonor)) {
			donor.setDonorName(field[this.indexNameDonor]);
		}
		if(validIndex(this.indexCpfCnpjDonor)) {
			donor.setDonorPersonRegister(field[this.indexCpfCnpjDonor]);
		}
		revenue.setRevenueDonor(donor);

	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Revenue
	 */
	@Override
	protected void setEmptyInAllSetters(Revenue revenue) {
		super.setEmptyInAllSetters(revenue);

		revenue.setRevenueElectoralReceipt(Revenue.EMPTY_TYPE_STRING);
		revenue.setRevenueDonor((Donor)Revenue.EMPTY_OBJECT);
		
	}

	// Mutators for indexes of the array of fields
	public void setIndexElectoralReceipt(int indexElectoralReceipt) {
		this.indexElectoralReceipt = indexElectoralReceipt;
	}

	public void setIndexNameDonor(int indexNameDonor) {
		this.indexNameDonor = indexNameDonor;
	}
	
	public void setIndexCpfCnpjDonor(int indexCpfCnpjDonor) {
		this.indexCpfCnpjDonor = indexCpfCnpjDonor;
	}
	
}