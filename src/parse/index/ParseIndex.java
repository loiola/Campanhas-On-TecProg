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
	public void startInstance(O object, String field[]) {
		restartInstance(object);
		setValidIndex(object, field);
	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of any class
	 */
	private void restartInstance(O object) {
		setEmptyInAllSetters(object);
	}	
	
	// Signature of abstract methods
	protected abstract void setValidIndex(O object, String field[]);
	protected abstract void setEmptyInAllSetters(O object);
	
	/*
	 * This method validates an index
	 * @param an integer value
	 * @return a Boolean value
	 */
	protected boolean validIndex(int index) {
		return index > INVALID_INDEX;
	}
	
}