package test.model.bean;

import static test.model.bean.BeanTest.instantiateDonor;
import static test.model.bean.BeanTest.instantiateFinancialTransaction;
import model.beans.Donor;
import model.beans.FinancialTransaction;

import org.junit.Assert;
import org.junit.Test;

public class DonorTest {

	@Test
	public void equalsShouldReturnTrueIfTwoDonorsAreTheSame() {
		
		Donor donorOne = instantiateDonor();
		Donor donorTwo = instantiateDonor();
		Assert.assertTrue(donorOne.equals(donorTwo));
	}

	@Test
	public void equalsShouldReturnFalseIfTwoDonorsAreDifferent() {
		
		Donor donorOne = instantiateDonor();
		Donor donorTwo = instantiateDonor();
		donorOne.setDonorPersonRegister(BeanTest.STRING_TEST_2);
		Assert.assertFalse(donorOne.equals(donorTwo));		
	}
	
	@Test
	public void equalsTestThree() {
		
		Donor donor = instantiateDonor();
		FinancialTransaction financialTransaction = instantiateFinancialTransaction();
		
		Assert.assertFalse(donor.equals(financialTransaction));
		Assert.assertFalse(financialTransaction.equals(donor));
		Assert.assertEquals(BeanTest.STRING_TEST,donor.getDonorName());
		Assert.assertEquals(BeanTest.STRING_TEST,donor.getDonorCountryState());
		Assert.assertEquals(BeanTest.STRING_TEST,donor.getDonorRegisterSituation());
	}
	
}