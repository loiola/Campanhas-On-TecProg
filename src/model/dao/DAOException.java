package model.dao;

public class DAOException extends Exception {
	
	/*
	 * Class used for exception flow DAO package
	 */
	
	// Constants
	private static final long serialVersionUID = 4196360225860055203L;

	// Constructors
	public DAOException() {
		
	}

	public DAOException(String message) {
		super(message);
	}

}