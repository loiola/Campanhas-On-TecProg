package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Position;
import model.dao.PositionDAO;

import org.junit.Assert;
import org.junit.Test;

import test.TemplateTest;

public class PositionDAOTest extends TemplateTest {

	private PositionDAO positionDAO;

	@Override
	public void beforeTest() throws Exception {
		
		this.positionDAO = new PositionDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void shouldRetrievePositionByCode() throws SQLException {

		ArrayList<Position> positionList = new ArrayList<>();
		Position positionRecovered = new Position();

		Position positionOne = new Position();
		positionOne.setPositionCode(1);
		positionOne.setPositionDescription("POSITION UM");
		positionList.add(positionOne);

		Position positionTwo = new Position();
		positionTwo.setPositionCode(2);
		positionTwo.setPositionDescription("POSITION DOIS");
		positionList.add(positionTwo);

		this.positionDAO.registerUnregisteredObjectArrayListOnDatabase(positionList);

		positionRecovered = this.positionDAO.getPositionByCode(1);
		Assert.assertEquals(positionOne, positionRecovered);
	}
	
	@Test
	public void shouldRetrievePositionByDescription() throws SQLException {

		ArrayList<Position> positionList = new ArrayList<>();
		Position positionRecovered = new Position();

		Position positionOne = new Position();
		positionOne.setPositionCode(1);
		positionOne.setPositionDescription("POSITION UM");
		positionList.add(positionOne);

		Position positionTwo = new Position();
		positionTwo.setPositionCode(2);
		positionTwo.setPositionDescription("POSITION DOIS");
		positionList.add(positionTwo);

		this.positionDAO.registerUnregisteredObjectArrayListOnDatabase(positionList);

		positionRecovered = this.positionDAO.getPositionByDescription("POSITION UM");
		Assert.assertEquals(positionOne, positionRecovered);
	}
	
	@Test
	public void shouldRetrieveAnArrayOfPositions() throws SQLException {
		
		ArrayList<Position> positionList = new ArrayList<>();
		ArrayList<Position> positionListRecovered = new ArrayList<>();
		
		Position positionOne = new Position();
		positionOne.setPositionCode(1);
		positionOne.setPositionDescription("POSITION UM");
		positionList.add(positionOne);

		Position positionTwo = new Position();
		positionTwo.setPositionCode(2);
		positionTwo.setPositionDescription("POSITION DOIS");
		positionList.add(positionTwo);
		
		Position positionThree = new Position();
		positionThree.setPositionCode(3);
		positionThree.setPositionDescription("POSITION TR�S");
		positionList.add(positionThree);
		
		this.positionDAO.registerUnregisteredObjectArrayListOnDatabase(positionList);
		positionListRecovered = this.positionDAO.getObjectArrayListFromDatabase();
		
		Assert.assertEquals(positionListRecovered, this.positionDAO.getObjectArrayListFromDatabase());
	}

	@Test
	public void comparationValues() throws Exception {

		Position positionOne = new Position();
		Position positionTwo = new Position();
		positionOne.setPositionCode(1);
		positionTwo.setPositionCode(2);
		int result;

		result = PositionDAO.CompareTwoPositionsCode.POSITION_CODE.compare(positionOne, positionTwo);

		Assert.assertNotEquals(0, result);
	}

}
