package modelo.beans;

public class Receita extends FinancialTransaction {

	/*
	 *  Class Receita.java
	 * This class is responsable for getting the Receipts's informations
	 */
	 
	// Constants 
	public static final Donor DOADOR_VAZIO = new Donor();
	
	// Attributes
	private String reciboEleitoral;
	private Donor donor;
	
	// Empty Constructor
	public Receita() {
		super();
		this.reciboEleitoral = STRING_VAZIO;
		this.donor = DOADOR_VAZIO;
	}
	
	// Getters and Setters
	public String getReciboEleitoral() {
		return reciboEleitoral;
	}
	
	public void setReciboEleitoral(String reciboEleitoral) {
		this.reciboEleitoral = reciboEleitoral;
	}
	
	public Donor getDoador() {
		return donor;
	}
	
	public void setDoador(Donor donor) {
		this.donor = donor;
	}
	
}
