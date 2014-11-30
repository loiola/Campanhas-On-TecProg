package model.beans;

public class Supplier {

	/*
	 * Model Class Supplier.java This class is responsible for getting the
	 * Supplier's informations
	 */

	// Constant
	public static final String EMPTY_TYPE_STRING = "";

	// Attributes
	
	// Attribute that characterizes the person register [RG - Registro Geral] of supplier
	private String supplierPersonRegister;
	
	// Attribute that characterizes name of supplier
	private String supplierName;
	
	// Attribute that characterizes country state of supplier
	private String supplierCountryState;
	
	// Attribute that characterizes register situation of supplier
	private String supplierRegisterSituation;

	// Empty Constructor
	public Supplier() {
		this.supplierPersonRegister = EMPTY_TYPE_STRING;
		this.supplierName = EMPTY_TYPE_STRING;
		this.supplierCountryState = EMPTY_TYPE_STRING;
		this.supplierRegisterSituation = EMPTY_TYPE_STRING;
	}

	// Getters and Setters
	public String getSupplierPersonRegister() {
		return supplierPersonRegister;
	}

	public void setSupplierPersonRegister(String supplierPersonRegister) {
		this.supplierPersonRegister = supplierPersonRegister;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCountryState() {
		return supplierCountryState;
	}

	public void setSupplierCountryState(String supplierCountryState) {
		this.supplierCountryState = supplierCountryState;
	}

	public String getSupplierRegisterSituation() {
		return supplierRegisterSituation;
	}

	public void setSupplierRegisterSituation(String supplierRegisterSituation) {
		this.supplierRegisterSituation = supplierRegisterSituation;
	}

	/*
	 * This method verified if an object provided is an instance of supplier
	 * @param an object for comparison 
	 * @return boolean with the result of comparison
	 */
	@Override
	public boolean equals(Object object) {
		
		// Variable that is not instance of a Supplier
		boolean notInstanceOfObject = (!(object instanceof Supplier));
		
		if(!(notInstanceOfObject)) {
			return false;
		} else {

			Supplier otherSupplier = (Supplier) object;

			// Variable that stores the logic state of the comparison between
			// two suppliers
			boolean auxiliaryReturn = this.getSupplierName().equalsIgnoreCase(
					otherSupplier.getSupplierName())
					&& this.getSupplierPersonRegister().equalsIgnoreCase(
							otherSupplier.getSupplierPersonRegister());

			return auxiliaryReturn;
		}
	}
}