package modelo.beans;

public class Expense extends FinancialTransaction {
	
	/*
	 * Model Class Expense.java
	 * This class is responsable for getting the Charge's informations
	 */
	 
	// Constant
	public static final Object EMPTY_OBJECT = null; 
	
	// Attributes
	private String expenseDocumentType;
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
