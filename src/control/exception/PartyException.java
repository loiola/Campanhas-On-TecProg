package control.exception;

public class PartyException extends Exception {
	
	/*
	 * Exception class electoral party, which mediates the application layer with the model
	 */

	// Attributes
	private static final long serialVersionUID = 1L;

	// Constructors
	public PartyException() {
		
	}
	
	public PartyException(String message) {
		super(message);
	}
}
