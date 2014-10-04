package test.parse.control;

import model.beans.Position;
import model.dao.PositionDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlPosition;
import parse.index.PositionParseIndex;
import test.TemplateTest;

public class PositionParseControlTest extends TemplateTest {

	public static final int CODE = 0;
	public static final int DESCRIPTION = 1;
	
	private String fields[];
	private PositionDAO positionDAO;
	private PositionParseIndex positionParseIndex;
	private ParseControlPosition parseControlPosition;

	@Override
	public void beforeTest() throws Exception {
		this.fields = new String[2];
		this.positionDAO = new PositionDAO();
		this.positionParseIndex = new PositionParseIndex();
		this.parseControlPosition = new ParseControlPosition(this.positionParseIndex);
		
		fieldsStart();
		indexStart();
	}
	
	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void registeringPosition() throws Exception {
		
		this.parseControlPosition.addInstance(fields);
		this.parseControlPosition.registeringInstances();
		this.parseControlPosition.clear();
		
		Position positionRegistered = this.positionDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.fields[CODE], positionRegistered.getPositionCode().toString());
		Assert.assertEquals(this.fields[DESCRIPTION], positionRegistered.getPositionDescription());
	}
	
	@Test
	public void notToSignTwoEqualPosition() throws Exception {
		
		this.parseControlPosition.addInstance(fields);
		this.parseControlPosition.addInstance(fields);
		this.parseControlPosition.registeringInstances();
		this.parseControlPosition.clear();
		
		int positionNumberDatabase = this.positionDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, positionNumberDatabase);
	}
	
	private void indexStart() {
		
		this.positionParseIndex.setIndexCode(CODE);
		this.positionParseIndex.setIndexDescription(DESCRIPTION);
	}
	
	private void fieldsStart() {
		
		this.fields[CODE] = "125";
		this.fields[DESCRIPTION] = "POSITION INEXISTENTE";
	}

}
