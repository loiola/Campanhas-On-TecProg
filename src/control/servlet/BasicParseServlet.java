package control.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

	// TODO NEED MORE REFACTORING
	
	private static final long serialVersionUID = -2550694521233885904L;

	protected final short PARTY_FILE_TYPE = 0;
	protected final short CAMPAIGN_FILE_TYPE = 1;
	protected final short TRANSACTION_FILE_TYPE = 2;
	
	private final String PARTY_PARSE_NAME = "party";
	private final String CAMPAIGN_PARSE_NAME = "campaign";
	private final String EXPENSE_PARSE_NAME = "expense";
	private final String REVENUE_PARSE_NAME = "revenue";
	private final String FILE_YEAR = "file_year";
	private final String FILE_TYPE = "file_type";
	
	private final String INFORM_PARSE_FINISHED = "Parse Completed!";
	private final String EXCEPTION_ERROR = "ERROR test upload: ";
	private final String FILE_LINE_INITIAL = "file_line_initial";
	private final String INFORM_INITIAL_LINE = "initial_line: ";
	private final String DIVISION_STRING = ";";
	private final Integer INITIAL_LINE = 1;
	private final Part EMPTY_REQUEST_PART = null;

	private short fileType;
	private String parseType;
	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;
	private PrintWriter responseOutput;
	private Part requestPart;
	private Scanner servletScanner;
	private FileItemFactory fileItemFactory;
	private ServletFileUpload servletFileUpload;
	private List<FileItem> fileFields;
	private FileItem fileItem;
	private Parse parse;
	private String electionYear;

	// Init execute method from Super (HttpServlet)
	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void readDataFile(HttpServletRequest request, HttpServletResponse response, short fileType) {
		try {
			setAttributes(request,response,fileType);
			scanDataFile();
		} catch (Exception e) {
			getResponseOutput().println(EXCEPTION_ERROR + e.getMessage());
		}
	}

	private void scanDataFile() throws Exception {
		setResponseOutput(getServletResponse().getWriter());

		if(getFileType() != TRANSACTION_FILE_TYPE) {
			setRequestPart(getServletRequest().getPart(FILE_LINE_INITIAL));

			if (getRequestPart() != EMPTY_REQUEST_PART) {
				setServletScanner(new Scanner(getRequestPart().getInputStream()));
				getResponseOutput().println(INFORM_INITIAL_LINE + getServletScanner().nextLine());
				getServletScanner().close();
			} else {
				// TODO Maybe: Inform the Request Part is Empty
			}
		} else {
			// TODO Maybe: Log it is a Transaction File Type
		}

		extractDataInfo();
		generateFields();
		callControlToRunParse();
		printFinishParsingToUser();
	}

	private void extractDataInfo() throws Exception {
		setFileItemFactory(new DiskFileItemFactory());
		setServletFileUpload(new ServletFileUpload(getFileItemFactory()));
		setFileFields(getServletFileUpload().parseRequest(getServletRequest()));
	}

	private void generateFields() {
		for(FileItem fileItemPointer : getFileFields()) {
			if(!fileItemPointer.isFormField()) {
				setFileItem(fileItemPointer);
			} else if(getFileType() == TRANSACTION_FILE_TYPE) {
				if (fileItemPointer.getFieldName().equals(FILE_TYPE)) {
					// Checks the file type, whether income or expense
					if (fileItemPointer.getString().equals(EXPENSE_PARSE_NAME)) {
						setParseType(RegisterToParseSupplier.EXPENSE);
					} else if (fileItemPointer.getString().equals(REVENUE_PARSE_NAME)){
						setParseType(RegisterToParseDonor.REVENUE);
					} else {
						// TODO This condition is an Error
					}
				} else if (fileItemPointer.getFieldName().equals(FILE_YEAR)) {
					setElectionYear(fileItemPointer.getString());
				} else {
					// TODO This condition is an Error
				}
			} else {
				// TODO Maybe This condition is an Error
			}
		}		
	}

	private void printFinishParsingToUser() {
		getResponseOutput().println(INFORM_PARSE_FINISHED);

	}

	private void callControlToRunParse() throws Exception {
		setParse();
		getParse().runParse(getFileItem(), DIVISION_STRING, INITIAL_LINE);
	}

	private void setAttributes(HttpServletRequest request, HttpServletResponse response, short fileType) {
		setServletRequest(request);
		setServletResponse(response);
		setFileType(fileType);
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
			this.parse = new ParseFinancialTransactions(getParseType(), getElectionYear());
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

