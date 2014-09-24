package teste.parse.indices;

import model.beans.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.ResultParseIndex;

public class ResultadoIndicesParseTeste {

	public String campo[];
	public ResultParseIndex resultParseIndex;
	
	@Before
	public void setUp() throws Exception {
		
		this.resultParseIndex = new ResultParseIndex();
		this.campo = new String[2];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmResultadoComIndicesValidos() throws Exception {
		
		Result result = new Result();
		this.resultParseIndex.startInstance(result, campo);
		Assert.assertEquals(this.campo[0], result.getResultType().toString());
		Assert.assertEquals(this.campo[1], result.getResultDescription());
	}
	
	@Test
	public void iniciarUmResultadoComIndicesInvalidos() throws Exception {
		
		this.resultParseIndex = new ResultParseIndex();
		Result result = new Result();
		this.resultParseIndex.startInstance(result, campo);
		Assert.assertNotEquals(this.campo[0], result.getResultType().toString());
		Assert.assertNotEquals(this.campo[1], result.getResultDescription());
	}
	
	private void iniciarIndices() {
		
		this.resultParseIndex.setIndiceCodigo(0);
		this.resultParseIndex.setIndiceDescricao(1);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "123";
		this.campo[1] = "RESULTADO INEXISTENTE";
	}

}
