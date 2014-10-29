package control.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.servlet.basic.BasicParseServlet;

@WebServlet("/carregarParseMovimentacoes")
public class LoadTransactionParse extends BasicParseServlet {

	/*
	 * Servlet asked by parseView.jsp to extract a Data File of Expense or
	 * Revenues and Send to Expense or Revenue Parse Control Classes to populate
	 * the information extracted in the database
	 */

	// Attribute
	private static final long serialVersionUID = 5625867877274809499L;

	/**
	 * 
	 * Method that makes the call load and parse controls its execution
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */

	// See LoadPoliticalPartyParse to see Refactoring Suggestion
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		readDataFile(request, response, TRANSACTION_FILE_TYPE);
	}
}
