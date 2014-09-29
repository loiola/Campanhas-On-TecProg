package test.model.bean;

import static test.model.bean.BeanTest.instantiatePosition;
import static test.model.bean.BeanTest.instantiateSupplier;
import model.beans.Position;
import model.beans.Supplier;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

	@Test
	public void equalsShouldReturnTrueIfTwoPositionsAreTheSame() {
		
		Position positionOne = instantiatePosition();
		Position positionTwo = instantiatePosition();	
		Assert.assertTrue(positionOne.equals(positionTwo));
	}
	
	@Test
	public void equalsShouldReturnFalseIfTwoPositionsAreDifferent() {
		
		Position positionOne = instantiatePosition();
		Position positionTwo = instantiatePosition();
		positionTwo.setPositionDescription(BeanTest.STRING_TEST_2);
		Assert.assertFalse(positionOne.equals(positionTwo));
	}
	
	@Test
	public void equalsTestThree() {
		
		Position position = instantiatePosition();
		Supplier supplier = instantiateSupplier();
		
		Assert.assertFalse(position.equals(supplier));
		Assert.assertFalse(supplier.equals(position));
		Assert.assertEquals(BeanTest.INT_TEST,position.getPositionCode());
		Assert.assertEquals(BeanTest.STRING_TEST,supplier.getSupplierName());
		Assert.assertEquals(BeanTest.STRING_TEST,supplier.getSupplierCountryState());
		Assert.assertEquals(BeanTest.STRING_TEST,supplier.getSupplierRegisterSituation());
	}

}