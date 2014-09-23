package control.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logic {
    
	/*
	 * Interface to implement the logic of dynamic display of indices of pages
	 */

	// Method Signatures
	/*
	 * Method signatures to implement the logic of dynamic display of indices of pages
	 * @param an HTTP request
	 * @return an String
	 */
    String execute(HttpServletRequest req,
            HttpServletResponse res) throws Exception;    
}
