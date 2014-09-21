package teste.parse.indices;

import model.beans.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.indices.ResultadoIndicesParse;

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
		this.resultadoIndicesParse.iniciarInstancia(result, campo);
		Assert.assertEquals(this.campo[0], result.getCodigo().toString());
		Assert.assertEquals(this.campo[1], result.getDescricao());
	}
	
	@Test
	public void iniciarUmResultadoComIndicesInvalidos() throws Exception {
		
		this.resultadoIndicesParse = new ResultadoIndicesParse();
		Result result = new Result();
		this.resultadoIndicesParse.iniciarInstancia(result, campo);
		Assert.assertNotEquals(this.campo[0], result.getCodigo().toString());
		Assert.assertNotEquals(this.campo[1], result.getDescricao());
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
