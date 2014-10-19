package test.model.dao;

import java.util.ArrayList;

import model.beans.Donor;
import model.dao.DonorDAO;

import org.junit.Assert;
import org.junit.Test;

import test.TemplateTest;

public class DonorDAOTest extends TemplateTest {

	private DonorDAO donorDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.donorDAO = new DonorDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void comparationValuesFirstCase() throws Exception {
		
		Donor donorOne = new Donor();
		Donor donorTwo = new Donor();
		donorOne.setDonorPersonRegister("1234567");
		donorTwo.setDonorPersonRegister("1234567");
		int result;

		result = DonorDAO.CompareTwoDonorsPersonRegister.PERSON_REGISTER.compare(donorOne, donorTwo);
		
		Assert.assertEquals(0,result);
	}

	@Test
	public void shouldNotThrowsExceptionToRegisterANewDonor() throws Exception {
		
		ArrayList<Donor> donorList = new ArrayList<>();
		
		Donor donor = new Donor();
		donor.setDonorName("Nome");
		donorList.add(donor);
		
		this.donorDAO.registerUnregisteredObjectArrayListOnDatabase(donorList);
	}
	
	@Test
	public void shouldNotRegisterAgainAnExistingDonor() throws Exception {
		
		ArrayList<Donor> donorList = new ArrayList<>();
		
		Donor donor = new Donor();
		donor.setDonorName("Nome");
		donorList.add(donor);

		this.donorDAO.registerUnregisteredObjectArrayListOnDatabase(donorList);
		this.donorDAO.registerUnregisteredObjectArrayListOnDatabase(donorList);
		
		Assert.assertEquals(1, this.donorDAO.getObjectArrayListFromDatabase().size());
	}
	
	@Test
	public void shouldRetrieveAnArrayOfDonors() throws Exception {
		
		ArrayList<Donor> donorList = new ArrayList<>();
		
		Donor donorOne = new Donor();
		donorOne.setDonorName("Nome");
		donorOne.setDonorPersonRegister("123");
		donorOne.setDonorRegisterSituation("Cadastrado");
		donorOne.setDonorCountryState("DF");
		donorList.add(donorOne);
		
		Donor donorTwo = new Donor();
		donorTwo.setDonorName("Nome2");
		donorTwo.setDonorPersonRegister("1234");
		donorTwo.setDonorRegisterSituation("Cadastrado");
		donorTwo.setDonorCountryState("DF");
		donorList.add(donorTwo);

		this.donorDAO.registerUnregisteredObjectArrayListOnDatabase(donorList);
		Assert.assertEquals(donorList, this.donorDAO.getObjectArrayListFromDatabase());
	}
	
	@Test
	public void shouldRetrieveDonorByPersonalRegister() throws Exception {
		
		ArrayList<Donor> donorListToBeRegistered = new ArrayList<>();
		Donor donorRecovered;
		
		Donor donorOne = new Donor();
		donorOne.setDonorName("nome");
		donorOne.setDonorPersonRegister("123456");
		donorOne.setDonorRegisterSituation("REGULAR");
		donorOne.setDonorCountryState("DF");
		donorListToBeRegistered.add(donorOne);
		
		Donor donorTwo = new Donor();
		donorTwo.setDonorName("nome2");
		donorTwo.setDonorPersonRegister("12345678");
		donorTwo.setDonorRegisterSituation("IRREGULAR");
		donorTwo.setDonorCountryState("DF");
		donorListToBeRegistered.add(donorTwo);
		
		this.donorDAO.registerUnregisteredObjectArrayListOnDatabase(donorListToBeRegistered);
		donorRecovered = this.donorDAO.getDonorByNameAndPersonRegister(donorOne);
		
		Assert.assertEquals(donorOne, donorRecovered);
	}
	
}
