package control.servlet.basic;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import log.general.ControlLogger;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import parse.Parse;
import parse.ParseCampaign;
import parse.ParseFinancialTransactions;
import parse.ParseParty;
import parse.register.revenue_expense.RegisterToParseDonor;
import parse.register.revenue_expense.RegisterToParseSupplier;

public class BasicParseServlet extends HttpServlet {

	// UID for BasicParseServlet
	private static final long serialVersionUID = -2550694521233885904L;

	// Constants
	protected final short PARTY_FILE_TYPE = 0;
	protected final short CAMPAIGN_FILE_TYPE = 1;
	protected final short TRANSACTION_FILE_TYPE = 2;

	private final String PARTY_PARSE_NAME = "party";
	private final String CAMPAIGN_PARSE_NAME = "campaign";

	private final Part EMPTY_REQUEST_PART = null;

	// Attributes
	private short fileType;

	private String parseType;
	private String electionYear;

	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;
	private PrintWriter responseOutput;
	private Part requestPart;

	private Scanner servletScanner;
	private ServletFileUpload servletFileUpload;

	private FileItemFactory fileItemFactory;
	private FileItem fileItem;
	private List<FileItem> fileFields;

	private Parse parse;

	// Methods

	/**
	 * This method execute init method from Super (HttpServlet) This method
	 * assures the init will be called only by the HttpServlet method
	 */
	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * This method check a file from Parse Servlet, validate it and save the
	 * informations in DataBase
	 * 
	 * @param request
	 *            variable from the Servlet
	 * @param response
	 *            variable from the Servlet
	 * @param fileType
	 *            contains what kind of file is supposed to be
	 */
	protected void readDataFile(HttpServletRequest request,
			HttpServletResponse response, short fileType) {
		try {
			setAttributes(request, response, fileType);
			scanFields();
			callControlToRunParse();
			printFinishParsingToUser();
		} catch (Exception e) {
			// Show the exception to the user
			String exceptionError = "ERROR test upload: ";
			getResponseOutput().println(exceptionError + e.getMessage());
		}
	}

	/**
	 * This Method set the ordinary attributes to read and forward fields from a
	 * File received from Servlet
	 * 
	 * @param request
	 *            variable from the Servlet
	 * @param response
	 *            variable from the Servlet
	 * @param fileType
	 *            contains what kind of file is supposed to be
	 * @throws Exception
	 *             if Output couldn't get Response Writer, or Part couldn't get
	 *             Request Part, or other Exceptions thrown by
	 *             informWhatLineIsReadingByTheParser method This Exception
	 *             should be handled by readDataFile method
	 */
	private void setAttributes(HttpServletRequest request,
			HttpServletResponse response, short fileType) throws Exception {
		setServletRequest(request);
		setServletResponse(response);
		setFileType(fileType);

		setResponseOutput(getServletResponse().getWriter());
		if (isFileTypeATransaction() == false) {
			String fileLineInitial = "file_line_initial";
			setRequestPart(getServletRequest().getPart(fileLineInitial));
			informWhatLineIsReadingByTheParser();
		} else {
			/*
			 * Do Nothing, If is a Transaction, there's no need to inform what
			 * line is reading by the parser. The Transaction Parser Control
			 * methods assures this. MAYBE this can be optimized by other Parser
			 * Controls
			 */
		}

		setFileItemFactory(new DiskFileItemFactory());
		setServletFileUpload(new ServletFileUpload(getFileItemFactory()));
		setFileFields(getServletFileUpload().parseRequest(getServletRequest()));
	}

	/**
	 * This method Inform what Line the Parser is Reading
	 * 
	 * @throws Exception
	 *             if Scanner couldn't get Input Stream by the RequestPart This
	 *             Exception should be handled by readDataFile method
	 */
	private void informWhatLineIsReadingByTheParser() throws Exception {
		if (isRequestPartEmpty() == false) {
			setServletScanner(new Scanner(getRequestPart().getInputStream()));
			String informInitialLine = "initial_line: ";
			getResponseOutput().println(
					informInitialLine + getServletScanner().nextLine());
			getServletScanner().close();
		} else {
			/*
			 * Do Nothing, if RequestPart is empty, there's no need to inform
			 * what line is reading by the parser.
			 */
		}
	}

	/**
	 * This method scan the fields contained by the informations imported from
	 * Servlet and set their values in the attributes
	 */
	private void scanFields() {
		for (FileItem fileItemPointer : getFileFields()) {
			if (fileItemPointer.isFormField() == false) {
				setFileItem(fileItemPointer);
			} else if (isFileTypeATransaction() == true) {
				scanTransactionValues(fileItemPointer);
			} else {
				/*
				 * This is a Unexpected Condition, if fileItemPointer is a
				 * FormField and isn't a Transaction the Form in View Package
				 * contains an Error
				 */
				ControlLogger.warn(ControlLogger.SERVLET_LOG_STRING,
						ControlLogger.CONDITION_ELSE_UNEXPECTED);
			}
		}
	}

	/**
	 * This method scan the Transaction fields contained by the informations
	 * imported from Servlet and set their values in the attributes
	 * 
	 * @param fileItemReference
	 *            is a FileItem node from FileFields list
	 */
	private void scanTransactionValues(FileItem fileItemReference) {
		String fileType = "file_type";
		String fileYear = "file_year";
		if (fileItemReference.getFieldName().equals(fileType)) {
			if (fileItemReference.getString().equals(
					RegisterToParseSupplier.EXPENSE)) {
				setParseType(RegisterToParseSupplier.EXPENSE);
			} else if (fileItemReference.getString().equals(
					RegisterToParseDonor.REVENUE)) {
				setParseType(RegisterToParseDonor.REVENUE);
			} else {
				/*
				 * This is a Unexpected Condition, if fileItemReference String
				 * is neither Expense or Revenue the Form in View Package
				 * contains an Error or the Expenses/Revenues Parse Names
				 * referenced here are divergent from the View Package
				 */
				ControlLogger.warn(ControlLogger.SERVLET_LOG_STRING,
						ControlLogger.CONDITION_ELSE_UNEXPECTED);
			}
		} else if (fileItemReference.getFieldName().equals(fileYear)) {
			setElectionYear(fileItemReference.getString());
		} else {
			/*
			 * This is a Unexpected Condition, if fileItemReference field name
			 * is neither File Type or File Year, the Form in View Package
			 * contains an Error or the Type/Year Files Names referenced here
			 * are divergent from the View Package
			 */
			ControlLogger.warn(ControlLogger.SERVLET_LOG_STRING,
					ControlLogger.CONDITION_ELSE_UNEXPECTED);
		}
	}

	/**
	 * This Method set a Parse Class based on the informations extracted by
	 * Servlet and call the method runParse with the File Item setted before in
	 * scanFields method
	 * 
	 * @throws Exception
	 *             if couldn't set the Parse Class or other exceptions called by
	 *             runParse method This Exception should be handled by
	 *             readDataFile method
	 */
	private void callControlToRunParse() throws Exception {
		/*
		 * MAYBE we dont need to define the division string and initial line
		 * here, this is unique and used by all kind of parsers. Can be
		 * referenced automatically inside the runParse method
		 */
		setParse();
		String divisionString = ";";
		Integer initialLine = 1;
		getParse().runParse(getFileItem(), divisionString, initialLine);
	}

	/**
	 * This method inform to the user the Parsing is finished
	 */
	private void printFinishParsingToUser() {
		String parseFinishedString = "Parse Completed!";
		getResponseOutput().println(parseFinishedString);

	}

	/**
	 * Verify if fileType attribute is a Transaction
	 * 
	 * @return true if fileType is a Transaction, false if isn't
	 */
	private boolean isFileTypeATransaction() {
		boolean isFileTypeATransaction = false;
		if (getFileType() == TRANSACTION_FILE_TYPE) {
			isFileTypeATransaction = true;
		} else {
			isFileTypeATransaction = false;
		}
		return isFileTypeATransaction;
	}

	/**
	 * Verify if requestPart attribute is Empty
	 * 
	 * @return true if requestPart is Empty, false if isn't
	 */
	private boolean isRequestPartEmpty() {
		boolean isRequestPartEmpty = false;
		if (getRequestPart() == EMPTY_REQUEST_PART) {
			isRequestPartEmpty = true;
		} else {
			isRequestPartEmpty = false;
		}
		return isRequestPartEmpty;
	}

	private short getFileType() {
		return fileType;
	}

	private void setFileType(short fileType) {
		this.fileType = fileType;
	}

	private HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	private void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	private HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	private void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	private PrintWriter getResponseOutput() {
		return responseOutput;
	}

	private void setResponseOutput(PrintWriter responseOutput) {
		this.responseOutput = responseOutput;
	}

	private Part getRequestPart() {
		return requestPart;
	}

	private void setRequestPart(Part requestPart) {
		this.requestPart = requestPart;
	}

	private Scanner getServletScanner() {
		return servletScanner;
	}

	private void setServletScanner(Scanner servletScanner) {
		this.servletScanner = servletScanner;
	}

	private FileItemFactory getFileItemFactory() {
		return fileItemFactory;
	}

	private void setFileItemFactory(FileItemFactory fileItemFactory) {
		this.fileItemFactory = fileItemFactory;
	}

	private ServletFileUpload getServletFileUpload() {
		return servletFileUpload;
	}

	private void setServletFileUpload(ServletFileUpload servletFileUpload) {
		this.servletFileUpload = servletFileUpload;
	}

	private List<FileItem> getFileFields() {
		return fileFields;
	}

	private void setFileFields(List<FileItem> fileFields) {
		this.fileFields = fileFields;
	}

	private FileItem getFileItem() {
		return fileItem;
	}

	private void setFileItem(FileItem fileItem) {
		this.fileItem = fileItem;
	}

	private Parse getParse() {
		return parse;
	}

	private void setParse() throws Exception {
		switch (getFileType()) {
		case PARTY_FILE_TYPE:
			setParseType(PARTY_PARSE_NAME);
			this.parse = new ParseParty(getParseType(), "");
			break;

		case CAMPAIGN_FILE_TYPE:
			setParseType(CAMPAIGN_PARSE_NAME);
			this.parse = new ParseCampaign(getParseType(), "");
			break;

		case TRANSACTION_FILE_TYPE:
			this.parse = new ParseFinancialTransactions(getParseType(),
					getElectionYear());
			break;

		default:
			this.parse = null;
			break;
		}
	}

	private String getParseType() {
		return parseType;
	}

	private void setParseType(String parseType) {
		this.parseType = parseType;
	}

	private String getElectionYear() {
		return electionYear;
	}

	private void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

}
