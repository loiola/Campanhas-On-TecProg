package test.parse.control;

import model.beans.Result;
import model.dao.ResultDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlResult;
import parse.index.ResultParseIndex;
import test.TemplateTest;

public class ResultParseControlTest extends TemplateTest {

	public static final int CODE = 0;
	public static final int DESCRIPTION = 1;
	
	private String fields[];
	private ResultDAO resultDAO;
	private ResultParseIndex resultParseIndex;
	private ParseControlResult parseControlResult;

	public void beforeTest() throws Exception {
		
		this.fields = new String[2];
		this.resultDAO = new ResultDAO();
		this.resultParseIndex = new ResultParseIndex();
		this.parseControlResult = new ParseControlResult(this.resultParseIndex);
		
		fieldsStart();
		indexStart();
	}
	
	public void afterTest() {
		
	}

	
	@Test
	public void registeringResult() throws Exception {
		
		this.parseControlResult.addInstance(fields);
		this.parseControlResult.registeringInstances();
		this.parseControlResult.clear();
		
		Result resultRegistered = this.resultDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.fields[CODE], resultRegistered.getResultType().toString());
		Assert.assertEquals(this.fields[DESCRIPTION], resultRegistered.getResultDescription());
	}
	
	private void indexStart() {
		
		this.resultParseIndex.setIndexCodeResult(CODE);
		this.resultParseIndex.setIndexDescriptionResult(DESCRIPTION);
	}
	
	private void fieldsStart() {
		
		this.fields[CODE] = "125";
		this.fields[DESCRIPTION] = "RESULT INEXISTENTE";
	}

}
