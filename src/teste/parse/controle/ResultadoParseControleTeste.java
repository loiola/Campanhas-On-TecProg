package teste.parse.controle;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlResult;
import parse.index.ResultadoIndicesParse;
import teste.TemplateTeste;

public class ResultadoParseControleTeste extends TemplateTeste {

	public static final int CODIGO = 0;
	public static final int DESCRICAO = 1;
	
	private String campo[];
	private ResultDAO resultDAO;
	private ResultadoIndicesParse resultadoIndicesParse;
	private ParseControlResult parseControlResult;

	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.resultDAO = new ResultDAO();
		this.resultadoIndicesParse = new ResultadoIndicesParse();
		this.parseControlResult = new ParseControlResult(this.resultadoIndicesParse);
		
		iniciarCampos();
		iniciarIndices();
	}
	
	public void afterTest() {
		
	}

	
	@Test
	public void cadastrarResultado() throws Exception {
		
		this.parseControlResult.addInstance(campo);
		this.parseControlResult.registeringInstances();
		this.parseControlResult.clear();
		
		Result resultadoCadastrado = this.resultDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.campo[CODIGO], resultadoCadastrado.getResultType().toString());
		Assert.assertEquals(this.campo[DESCRICAO], resultadoCadastrado.getResultDescription());
	}
	
	private void iniciarIndices() {
		
		this.resultadoIndicesParse.setIndiceCodigo(CODIGO);
		this.resultadoIndicesParse.setIndiceDescricao(DESCRICAO);
	}
	
	private void iniciarCampos() {
		
		this.campo[CODIGO] = "125";
		this.campo[DESCRICAO] = "RESULTADO INEXISTENTE";
	}

}
