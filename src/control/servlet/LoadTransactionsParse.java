package control.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import parse.Parse;
import parse.ParseMovimentacoes;
import parse.register.revenue_expense.CadastroDoadorParse;
import parse.register.revenue_expense.CadastroFornecedorParse;

@WebServlet("/carregarParseMovimentacoes")
public class LoadTransactionsParse extends HttpServlet {
	
	/*
	 * Servlet to control the loading parse financial transactions
	 */

	// Attributes
	private static final long serialVersionUID = 5625867877274809499L;

	// Empty Constructor
	@Override
	public void init() throws ServletException {
		super.init();
	}

	// Other methods
	/*
	 * Method that makes the call load and parse controls its execution
	 * @param an HTTP request and HTTP response
	 */
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter saida = response.getWriter();

		try {
			boolean isMultpart = ServletFileUpload.isMultipartContent(request);			
			if(isMultpart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);

				List<FileItem> fields = upload.parseRequest(request);

				FileItem arquivo = null;
				String tipoArquivo = "";
				String ano = "";
				String divisao = ";";
				int linhaInicial = 1;

				for(FileItem fileItem : fields) {
					if(!fileItem.isFormField()) {
						arquivo = fileItem;
					} else {
						if(fileItem.getFieldName().equals("arquivo_tipo")) {
							
							// Checks the file type, whether income or expense
							if(fileItem.getString().equals("expense")) {
								tipoArquivo = CadastroFornecedorParse.DESPESA;
							} else {
								tipoArquivo = CadastroDoadorParse.RECEITA;
							}
						} else if(fileItem.getFieldName().equals("arquivo_ano")) {
							
							// Checks joined donor according to selected year
							switch (fileItem.getString()) {
							case "2002":
								ano = CadastroDoadorParse.ANO_2002;
								break;

							case "2006":
								ano = CadastroDoadorParse.ANO_2006;
								break;

							case "2010":
								ano = CadastroDoadorParse.ANO_2010;
								break;

							default:
								break;
							}
						}
					}
				}

				Parse parse = new ParseMovimentacoes(tipoArquivo, ano);
				parse.executarParse(arquivo, divisao, linhaInicial);
				saida.println("Parse Realizado com Sucesso!");
			}

		} catch(Exception e) {
			saida.println("ERROR teste upload: " + e.getMessage());
		}
	}
}
