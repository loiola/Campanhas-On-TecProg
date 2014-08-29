package modelo.beans;

public class Receita extends MovimentacaoFinanceira {

	/*
	 *  Class Receita.java
	 * This class is responsable for getting the Receipts's informations
	 */
	 
	// Constants 
	public static final Doador DOADOR_VAZIO = new Doador();
	
	// Attributes
	private String reciboEleitoral;
	private Doador doador;
	
	// Empty Constructor
	public Receita() {
		super();
		this.reciboEleitoral = STRING_VAZIO;
		this.doador = DOADOR_VAZIO;
	}
	
	// Getters and Setters
	public String getReciboEleitoral() {
		return reciboEleitoral;
	}
	
	public void setReciboEleitoral(String reciboEleitoral) {
		this.reciboEleitoral = reciboEleitoral;
	}
	
	public Doador getDoador() {
		return doador;
	}
	
	public void setDoador(Doador doador) {
		this.doador = doador;
	}
	
}
