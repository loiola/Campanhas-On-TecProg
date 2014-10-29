package model.beans;

public class Expense extends FinancialTransaction {
	
	/*
	 * Model Class Expense.java
	 * This class is responsible for getting the Charge's informations
	 */
	 
	// Constant
	public static final Object EMPTY_OBJECT = null; 
	
	// Attributes
	
	// Attribute that characterizes document type of expense 
	private String expenseDocumentType;
	
	// Attribute that characterizes supplier of expense for campaign
	private Supplier expenseSupplier;
	
	// Empty Constructor
	public Expense(){
		this.expenseDocumentType = EMPTY_TYPE_STRING;
		this.expenseSupplier = (Supplier) EMPTY_OBJECT;
	}
	
	// Getters and Setters
	public String getExpenseDocumentType() {
		return expenseDocumentType;
	}
	
	public void setExpenseDocumentType(String expenseDocumentType) {
		this.expenseDocumentType = expenseDocumentType;
	}
	
	public Supplier getExpenseSupplier() {
		return expenseSupplier;
	}
	
	public void setExpenseSupplier(Supplier expenseSupplier) {
		this.expenseSupplier = expenseSupplier;
	}
}