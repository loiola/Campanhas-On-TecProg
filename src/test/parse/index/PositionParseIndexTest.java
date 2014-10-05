package test.parse.index;

import model.beans.Position;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.PositionParseIndex;

public class PositionParseIndexTest {
	
	public String field[];
	public PositionParseIndex positionParseIndex;
	
	@Before
	public void setUp() throws Exception {
		
		this.positionParseIndex = new PositionParseIndex();
		this.field = new String[2];
		
		fieldsStart();
		indexStart();
	}

	@Test
	public void startPositionWithValidIndices() throws Exception {
		
		Position position = new Position();
		this.positionParseIndex.startInstance(position, field);
		Assert.assertEquals(this.field[0], position.getPositionCode().toString());
		Assert.assertEquals(this.field[1], position.getPositionDescription());
	}
	
	@Test
	public void startPositionWithInvalidIndices() throws Exception {
		
		this.positionParseIndex = new PositionParseIndex();
		Position position = new Position();
		this.positionParseIndex.startInstance(position, field);
		Assert.assertNotEquals(this.field[0], position.getPositionCode().toString());
		Assert.assertNotEquals(this.field[1], position.getPositionDescription());
	}
	
	private void indexStart() {
		
		this.positionParseIndex.setIndexCode(0);
		this.positionParseIndex.setIndexDescription(1);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "123";
		this.field[1] = "POSITION INEXISTENTE";
	}
	
}
