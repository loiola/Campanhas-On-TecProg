package parse.index;

public abstract class ParseIndex<O> {

	/*
	 * Abstract class for inserting a template for subclasses of indexes parse
	 */
	
	// Constants
	public static final int INVALID_INDEX = -1;
	
	// Constructors
	public ParseIndex() {
		
	}
	
	/*
	 * This method formalizes the initialization of an instance of any class
	 * @param an instance of any class
	 * @param an array of strings
	 */
	public void startInstance(O objeto, String campo[]) {
		reiniciarInstancia(objeto);
		setValidIndex(objeto, campo);
	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of any class
	 */
	private void reiniciarInstancia(O objeto) {
		setEmptyInAllSetters(objeto);
	}	
	
	// Signature of abstract methods
	protected abstract void setValidIndex(O objeto, String campo[]);
	protected abstract void setEmptyInAllSetters(O objeto);
	
	/*
	 * This method validates an index
	 * @param an integer value
	 * @return a Boolean value
	 */
	protected boolean validIndex(int indice) {
		return indice > INVALID_INDEX;
	}
	
}