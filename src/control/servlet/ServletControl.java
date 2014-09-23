package control.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc")
public class ServletControl extends HttpServlet {
	
	/*
	 * Controller servlet instance
	 */

	// Attributes
	private static final long serialVersionUID = 2663398443592480668L;
	
	// Other methods
	/*
	 * Method that checks the instance and return an exception or resumes the execution
	 * @param an HTTP request and HTTP response
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String parameter = request.getParameter("logic");
		String nameOfClass = "control.servlet." + parameter;
		
		
		//RETIRAR, APENAS PARA TESTE
		System.out.println(parameter);
		//RETIRAR, APENAS PARA TESTE

		
		try { 
			
			//Conseguir uma tradução para CLASSE (Não pode ser class)
			Class<?> classe = Class.forName(nameOfClass);

			Logic logic = (Logic) classe.newInstance();
			String page = logic.execute(request, response);

			request.getRequestDispatcher(page).forward(request, response);

		} catch(Exception e) {
			throw new ServletException(
					"Business logic caused an exception!", e);
		}
	}
}
