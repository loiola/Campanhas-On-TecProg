package modelo.dao;

public class DAOExcecao extends Exception {
	
	/*
	 * Class used for exception flow DAO package
	 */
	
	// Constants
	private static final long serialVersionUID = 4196360225860055203L;

	// Constructors
	public DAOExcecao() {
		
	}

	public DAOExcecao(String message) {
		super(message);
	}

}