package parse.index;

import model.beans.Donor;
import model.beans.Revenue;

public class RevenueParseIndex extends FinancialTransactionParseIndex<Revenue> {
	
	/*
	 * Class to control the contents of information inherent to the revenue
	 */

	// Attributes
	
	// Variable to the index of the electoral receipt
	private int indexElectoralReceipt;
	
	// Variable to the index of the donor's name
	private int indexNameDonor;
	
	// Variable to the index of the donor's person register
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
		
		//Variable to store the result of index validation
		boolean validationResult;
				
		validationResult = validIndex(this.indexElectoralReceipt);
		if(validationResult) {
			revenue.setRevenueElectoralReceipt(field[this.indexElectoralReceipt]);
		}
		
		validationResult = validIndex(this.indexNameDonor);
		if(validationResult) {
			donor.setDonorName(field[this.indexNameDonor]);
		}
		
		validationResult = validIndex(this.indexCpfCnpjDonor);
		if(validationResult) {
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