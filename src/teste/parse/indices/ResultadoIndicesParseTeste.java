package teste.parse.indices;

import model.beans.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.ResultadoIndicesParse;

public class ResultadoIndicesParseTeste {

	public String campo[];
	public ResultadoIndicesParse resultadoIndicesParse;
	
	@Before
	public void setUp() throws Exception {
		
		this.resultadoIndicesParse = new ResultadoIndicesParse();
		this.campo = new String[2];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmResultadoComIndicesValidos() throws Exception {
		
		Result result = new Result();
		this.resultadoIndicesParse.startInstance(result, campo);
		Assert.assertEquals(this.campo[0], result.getResultType().toString());
		Assert.assertEquals(this.campo[1], result.getResultDescription());
	}
	
	@Test
	public void iniciarUmResultadoComIndicesInvalidos() throws Exception {
		
		this.resultadoIndicesParse = new ResultadoIndicesParse();
		Result result = new Result();
		this.resultadoIndicesParse.startInstance(result, campo);
		Assert.assertNotEquals(this.campo[0], result.getResultType().toString());
		Assert.assertNotEquals(this.campo[1], result.getResultDescription());
	}
	
	private void iniciarIndices() {
		
		this.resultadoIndicesParse.setIndiceCodigo(0);
		this.resultadoIndicesParse.setIndiceDescricao(1);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "123";
		this.campo[1] = "RESULTADO INEXISTENTE";
	}

}
