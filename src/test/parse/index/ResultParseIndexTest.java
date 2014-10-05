package test.parse.index;

import model.beans.Result;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.ResultParseIndex;

public class ResultParseIndexTest {

	public String field[];
	public ResultParseIndex resultParseIndex;
	
	@Before
	public void setUp() throws Exception {
		
		this.resultParseIndex = new ResultParseIndex();
		this.field = new String[2];
		
		fieldsStart();
		indexStart();
	}

	@Test
	public void startResultWithValidIndices() throws Exception {
		
		Result result = new Result();
		this.resultParseIndex.startInstance(result, field);
		Assert.assertEquals(this.field[0], result.getResultType().toString());
		Assert.assertEquals(this.field[1], result.getResultDescription());
	}
	
	@Test
	public void startResultWithInvalidIndices() throws Exception {
		
		this.resultParseIndex = new ResultParseIndex();
		Result result = new Result();
		this.resultParseIndex.startInstance(result, field);
		Assert.assertNotEquals(this.field[0], result.getResultType().toString());
		Assert.assertNotEquals(this.field[1], result.getResultDescription());
	}
	
	private void indexStart() {
		
		this.resultParseIndex.setIndexCodeResult(0);
		this.resultParseIndex.setIndexDescriptionResult(1);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "123";
		this.field[1] = "RESULT INEXISTENTE";
	}

}
