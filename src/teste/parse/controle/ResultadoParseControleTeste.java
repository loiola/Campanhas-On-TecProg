package teste.parse.controle;

import modelo.beans.Result;
import modelo.dao.ResultadoDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.controle.ResultadoParseControle;
import parse.indices.ResultadoIndicesParse;
import teste.TemplateTeste;

public class ResultadoParseControleTeste extends TemplateTeste {

	public static final int CODIGO = 0;
	public static final int DESCRICAO = 1;
	
	private String campo[];
	private ResultadoDAO resultadoDAO;
	private ResultadoIndicesParse resultadoIndicesParse;
	private ResultadoParseControle resultadoParseControle;

	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.resultadoDAO = new ResultadoDAO();
		this.resultadoIndicesParse = new ResultadoIndicesParse();
		this.resultadoParseControle = new ResultadoParseControle(this.resultadoIndicesParse);
		
		iniciarCampos();
		iniciarIndices();
	}
	
	public void afterTest() {
		
	}

	
	@Test
	public void cadastrarResultado() throws Exception {
		
		this.resultadoParseControle.addInstancia(campo);
		this.resultadoParseControle.cadastrarInstancias();
		this.resultadoParseControle.resetar();
		
		Result resultadoCadastrado = this.resultadoDAO.getLista().get(0);
				
		Assert.assertEquals(this.campo[CODIGO], resultadoCadastrado.getCodigo().toString());
		Assert.assertEquals(this.campo[DESCRICAO], resultadoCadastrado.getDescricao());
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
