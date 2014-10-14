package parse.index;

import model.beans.Supplier;

public class SupplierParseIndex extends ParseIndex<Supplier> {
	
	/*
	 * Class to control the contents of information inherent to the suppliers
	 */

	// Constants
	public static final int INVALID_INDEX = -1;

	// Attributes
	private int indexSupplierCpfCnpj;
	private int indexName;
	private int indexUnitFederation;
	private int indexRegistrationStatus;
	
	// Constructors
	public SupplierParseIndex() {
		this.indexSupplierCpfCnpj = INVALID_INDEX;
		this.indexName = INVALID_INDEX;
		this.indexUnitFederation = INVALID_INDEX;
		this.indexRegistrationStatus = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the suppliers in the file
	 * @param an instance of the Class Supplier
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Supplier supplier, String[] field) {
		//Variable to store the result of index validation
		boolean validationResult;
				
		validationResult = validIndex(this.indexSupplierCpfCnpj);
		if(validationResult) {
			supplier.setSupplierPersonRegister(field[this.indexSupplierCpfCnpj]);
		}
		
		validationResult = validIndex(this.indexName);
		if(validationResult) {
			supplier.setSupplierName(field[this.indexName]);
		}
		
		validationResult = validIndex(this.indexUnitFederation);
		if(validationResult) {
			supplier.setSupplierCountryState(field[this.indexUnitFederation]);
		}
		
		validationResult = validIndex(this.indexRegistrationStatus);
		if(validationResult) {
			supplier.setSupplierRegisterSituation(field[this.indexRegistrationStatus]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Supplier
	 */
	@Override
	protected void setEmptyInAllSetters(Supplier supplier) {
		supplier.setSupplierPersonRegister(Supplier.EMPTY_TYPE_STRING);
		supplier.setSupplierName(Supplier.EMPTY_TYPE_STRING);
		supplier.setSupplierCountryState(Supplier.EMPTY_TYPE_STRING);
		supplier.setSupplierRegisterSituation(Supplier.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndexSupplierCpfCnpj(int indexSupplierCpfCnpj) {
		this.indexSupplierCpfCnpj = indexSupplierCpfCnpj;
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