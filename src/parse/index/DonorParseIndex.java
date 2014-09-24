package parse.index;

import model.beans.Donor;

public class DonorParseIndex extends IndicesParse<Donor> {
	
	/*
	 * Class to control the contents of information inherent to the donors
	 */

	// Constants
	public static final int INVALID_INDEX = -1;
	
	// Attributes
	private int indexDonorCpfCnpj;
	private int indexName;
	private int indexUnitFederation;
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
		if(validIndex(this.indexDonorCpfCnpj)) {
			donor.setDonorPersonRegister(field[this.indexDonorCpfCnpj]);
		}
		if(validIndex(this.indexName)) {
			donor.setDonorName(field[this.indexName]);
		}
		if(validIndex(this.indexUnitFederation)) {
			donor.setDonorCountryState(field[this.indexUnitFederation]);
		}
		if(validIndex(this.indexRegistrationStatus)) {
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