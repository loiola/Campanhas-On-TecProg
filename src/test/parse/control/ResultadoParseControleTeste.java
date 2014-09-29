package test.parse.control;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlResult;
import parse.index.ResultParseIndex;
import test.TemplateTest;

public class ResultadoParseControleTeste extends TemplateTest {

	public static final int CODIGO = 0;
	public static final int DESCRICAO = 1;
	
	private String campo[];
	private ResultDAO resultDAO;
	private ResultParseIndex resultParseIndex;
	private ParseControlResult parseControlResult;

	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.resultDAO = new ResultDAO();
		this.resultParseIndex = new ResultParseIndex();
		this.parseControlResult = new ParseControlResult(this.resultParseIndex);
		
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
		
		this.resultParseIndex.setIndexCodeResult(CODIGO);
		this.resultParseIndex.setIndexDescriptionResult(DESCRICAO);
	}
	
	private void iniciarCampos() {
		
		this.campo[CODIGO] = "125";
		this.campo[DESCRICAO] = "RESULTADO INEXISTENTE";
	}

}
