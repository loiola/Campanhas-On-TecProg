package control.servlet.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logic {
    
	/*
	 * Interface to implement the logic execute to all Servlets who use it
	 */
	
	/**
	 * Method signatures to implement the logic used by all Servlets, excluding Parse Servlets
	 * @param request values received from the View
	 * @param response values received from the View
	 * @return the string link to the next page or other informations
	 * @throws Exception
	 */
    String execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception;    
}
