package model.beans;

public class Supplier {
	
	/*
	 * Model Class Supplier.java
	 * This class is responsable for getting the Supplier's informations
	 */
	 
	// Constant
	public static final String EMPTY_TYPE_STRING = "";
	
	// Attributes
	private String supplierPersonRegister;
	private String supplierName;
	private String supplierCountryState;
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
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Supplier))
			return false;
		
		Supplier otherSupplier = (Supplier) object;
		return this.getSupplierName().equalsIgnoreCase(otherSupplier.getSupplierName()) &&
			   this.getSupplierPersonRegister().equalsIgnoreCase(otherSupplier.getSupplierPersonRegister()) ;
	}
}
