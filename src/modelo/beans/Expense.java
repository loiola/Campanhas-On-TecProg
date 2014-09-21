package modelo.beans;

public class Expense extends FinancialTransaction {
	
	/*
	 * Model Class Expense.java
	 * This class is responsable for getting the Charge's informations
	 */
	 
	// Constant
	public static final Object OBJETO_VAZIO = null; 
	
	// Attributes
	private String tipoDocumento;
	private Supplier supplier;
	
	// Empty Constructor
	public Expense(){
		this.tipoDocumento = STRING_VAZIO;
		this.supplier = (Supplier) OBJETO_VAZIO;
	}
	
	// Getters and Setters
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public Supplier getFornecedor() {
		return supplier;
	}
	
	public void setFornecedor(Supplier supplier) {
		this.supplier = supplier;
	}
}
