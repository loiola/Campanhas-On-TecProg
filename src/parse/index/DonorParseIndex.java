package parse.index;

import model.beans.Donor;

public class DonorParseIndex extends ParseIndex<Donor> {
	
	/*
	 * Class to control the contents of information inherent to the donors
	 */

	// Constants
	public static final int INVALID_INDEX = -1;
	
	// Attributes
	
	// Variable to the index of the donor person register
	private int indexDonorCpfCnpj;
	
	// Variable to the index of the name of the donor
	private int indexName;
	
	// Variable to the index unit federation donor - country state
	private int indexUnitFederation;
	
	// Variable to the index on the status of the donor with the IRS
	private int indexRegistrationStatus;
	
	// Constructors
	public DonorParseIndex() {
		super();
		this.indexDonorCpfCnpj = INVALID_INDEX;
		this.indexName = INVALID_INDEX;
		this.indexUnitFederation = INVALID_INDEX;
		this.indexRegistrationStatus = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the donors in the file
	 * @param an instance of the Class Donor
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Donor donor, String[] field) {
		//Variable to store the result of index validation
		boolean validationResult;
		
		validationResult = validIndex(this.indexDonorCpfCnpj);
		if(validationResult) {
			donor.setDonorPersonRegister(field[this.indexDonorCpfCnpj]);
		}
		
		validationResult = validIndex(this.indexName);
		if(validationResult) {
			donor.setDonorName(field[this.indexName]);
		}
		
		validationResult = validIndex(this.indexUnitFederation);
		if(validationResult) {
			donor.setDonorCountryState(field[this.indexUnitFederation]);
		}
		
		validationResult = validIndex(this.indexRegistrationStatus);
		if(validationResult) {
			donor.setDonorRegisterSituation(field[this.indexRegistrationStatus]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Donor
	 */
	@Override
	protected void setEmptyInAllSetters(Donor donor) {
		donor.setDonorPersonRegister(Donor.EMPTY_TYPE_STRING);
		donor.setDonorName(Donor.EMPTY_TYPE_STRING);
		donor.setDonorCountryState(Donor.EMPTY_TYPE_STRING);
		donor.setDonorRegisterSituation(Donor.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndexDonorCpfCnpj(int indexDonorCpfCnpj) {
		this.indexDonorCpfCnpj = indexDonorCpfCnpj;
	}

	public void setIndexName(int indexName) {
		this.indexName = indexName;
	}

	public void setIndexUnitFederation(int indexUnitFederation) {
		this.indexUnitFederation = indexUnitFederation;
	}

	public void setIndexRegistrationStatus(int indexRegistrationStatus) {
		this.indexRegistrationStatus = indexRegistrationStatus;
	}
	
}