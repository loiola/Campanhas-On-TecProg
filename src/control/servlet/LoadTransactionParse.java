package control.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.servlet.basic.BasicParseServlet;

// Called in: parseView.jsp
@WebServlet("/carregarParseMovimentacoes")
public class LoadTransactionParse extends BasicParseServlet {

	/*
	 * Servlet asked by the View to extract a Data File of Expense or
	 * Revenues and Send to Expense or Revenue Parse Control Classes to populate
	 * the information extracted in the database
	 */

	// Attribute
	private static final long serialVersionUID = 5625867877274809499L;

	/**
	 * This service method pass the request and response forward and the Transaction File Type
	 * to be used by readDataFile method (super) to populate the file informations in database
	 * 
	 * @param request values received from the View
	 * @param response values received from the View
	 */

	// See LoadPoliticalPartyParse to see Refactoring Suggestion
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		readDataFile(request, response, TRANSACTION_FILE_TYPE);
	}
}
