package parse.index;

public abstract class IndicesParse<O> {

	/*
	 * Abstract class for inserting a template for subclasses of indexes parse
	 */
	
	// Constants
	public static final int INDICE_INVALIDO = -1;
	
	// Constructors
	public IndicesParse() {
		
	}
	
	/*
	 * This method formalizes the initialization of an instance of any class
	 * @param an instance of any class
	 * @param an array of strings
	 */
	public void startInstance(O objeto, String campo[]) {
		reiniciarInstancia(objeto);
		setIndicesValidos(objeto, campo);
	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of any class
	 */
	private void reiniciarInstancia(O objeto) {
		setVazioEmTodosOsSetters(objeto);
	}	
	
	// Signature of abstract methods
	protected abstract void setIndicesValidos(O objeto, String campo[]);
	protected abstract void setVazioEmTodosOsSetters(O objeto);
	
	/*
	 * This method validates an index
	 * @param an integer value
	 * @return a Boolean value
	 */
	protected boolean indiceValido(int indice) {
		return indice > INDICE_INVALIDO;
	}
	
}