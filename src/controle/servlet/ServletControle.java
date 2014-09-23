package controle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc")
public class ServletControle extends HttpServlet {
	
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

		String parametro = request.getParameter("logica");
		String nomeDaClasse = "control.servlet." + parametro;

		try {
			Class<?> classe = Class.forName(nomeDaClasse);

			Logica logica = (Logica) classe.newInstance();
			String pagina = logica.executa(request, response);

			request.getRequestDispatcher(pagina).forward(request, response);

		} catch(Exception e) {
			throw new ServletException(
					"A lógica de negócios causou uma exceção", e);
		}
	}
}
