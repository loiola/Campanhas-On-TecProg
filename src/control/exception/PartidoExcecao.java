package control.exception;

public class PartidoExcecao extends Exception {
	
	/*
	 * Exception class electoral party, which mediates the application layer with the model
	 */

	// Attributes
	private static final long serialVersionUID = 1L;

	// Constructors
	public PartidoExcecao() {
		
	}
	
	public PartidoExcecao(String message) {
		super(message);
	}
}
