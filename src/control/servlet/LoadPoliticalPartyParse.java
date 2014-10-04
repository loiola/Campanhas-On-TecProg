package control.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import parse.Parse;
import parse.ParseParty;

@WebServlet("/loadPoliticalPartyParse")
public class LoadPoliticalPartyParse extends HttpServlet {

	/*
	 * Servlet asked by parseView.jsp to extract a Data File of Political
	 * Parties and Send to Party Parse Control Classes to populate the
	 * information extracted in the database
	 */

	// Attribute
	private static final long serialVersionUID = 1L;

	// Constructor execute init() method from Super (HttpServlet)
	@Override
	public void init() throws ServletException {
		super.init();
	}

	// Other method

	/**
	 * 
	 * Method that makes the call load and parse controls its execution
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * General Class to be Instantiated Suggested: BasicParseServlet
		 */
		/*
		 * [1] GENERAL METHOD SUGGESTED: BasicParseServlet.readDataFile() throws
		 * Servlet Exception, IOException
		 */
		/*
		 * request and response is taken by Attributes from that Class (set)
		 */

		/*
		 * [2] GENERAL METHOD SUGGESTED: BasicParseServlet.scanDataFile()
		 */
		/*
		 * output as Attribute (Better name?)
		 */
		PrintWriter output = response.getWriter();

		/*
		 * file_line_initital as Constant (Better name?) "initital line: "
		 * String as Constant
		 */
		Part part = request.getPart("file_line_initial");
		if (part != null) {
			Scanner scanner = new Scanner(part.getInputStream());
			output.println("initial line: " + scanner.nextLine());
			scanner.close();
		}
		// [2] END SUGGESTION

		/*
		 * GENERAL METHOD SUGGESTED: BasicParseServlet.extractDataInfo(String
		 * fileType)
		 */
		try {
			boolean isMultpart = ServletFileUpload.isMultipartContent(request);
			// This if/else is really necessary?
			if (isMultpart) {
				/*
				 * factory, upload, fields, file, filetype (settable) as
				 * Attributes initialLine as Constant
				 */
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);

				List<FileItem> fields = upload.parseRequest(request);

				FileItem file = null;
				int initialLine = 1;
				String fileType = "party";

				/*
				 * [3] GENERAL METHOD SUGGESTED:
				 * BasicParseServlet.generateFields()
				 */
				for (FileItem fileItem : fields) {
					if (!fileItem.isFormField()) {
						file = fileItem;
					}
				}
				// [3] END SUGGESTION

				/*
				 * [4] GENERAL METHOD SUGGESTED:
				 * BasicParseServlet.callControlToRunParse() [4.1] Additional
				 * Method Suggested: getParseType()
				 */
				/*
				 * division as Constant deprecated second parameter from
				 * ParseParty Constructor
				 */
				String division = ";";
				Parse parse = new ParseParty(fileType, "");
				parse.runParse(file, division, initialLine);
				// [4] END SUGGESTION
				/*
				 * [5] GENERAL METHOD SUGGESTED: printFinishParsingToUser()
				 */
				output.println("Parse Completed!");
				// [5] END SUGGESTION
			}
			/*
			 * Insert Void Else here
			 */
		} catch (Exception e) {
			// Maybe a better Exception Diagnosis
			output.println("ERROR test upload: " + e.getMessage());
		}
	}
	// [1] END SUGGESTION
}
