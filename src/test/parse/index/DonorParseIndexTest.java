package test.parse.index;

import model.beans.Donor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.DonorParseIndex;

public class DonorParseIndexTest {

	private String field[];
	private DonorParseIndex donorParseIndex;

	@Before
	public void setUp() throws Exception {
		
		this.donorParseIndex = new DonorParseIndex();
		this.field = new String[4];
		
		fieldsStart();
		indexStart();
	}
	
	@Test
	public void startDonorWithValidIndices() throws Exception {
		
		Donor donor = new Donor();
		this.donorParseIndex.startInstance(donor, field);
		Assert.assertEquals(this.field[0], donor.getDonorPersonRegister().toString());
		Assert.assertEquals(this.field[1], donor.getDonorName());
		Assert.assertEquals(this.field[2], donor.getDonorCountryState());
		Assert.assertEquals(this.field[3], donor.getDonorRegisterSituation());
	}
	
	@Test
	public void startDonorWithInvalidIndices() {
		
		this.donorParseIndex = new DonorParseIndex();
		Donor donor = new Donor();
		this.donorParseIndex.startInstance(donor, field);
		Assert.assertNotEquals(this.field[0], donor.getDonorPersonRegister().toString());
		Assert.assertNotEquals(this.field[1], donor.getDonorName());
		Assert.assertNotEquals(this.field[2], donor.getDonorCountryState());
		Assert.assertNotEquals(this.field[3], donor.getDonorRegisterSituation());
	}
	
	private void indexStart() {
		
		this.donorParseIndex.setIndexDonorCpfCnpj(0);
		this.donorParseIndex.setIndexName(1);
		this.donorParseIndex.setIndexUnitFederation(2);
		this.donorParseIndex.setIndexRegistrationStatus(3);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "55325424149";
		this.field[1] = "VANDERLEI";
		this.field[2] = "DF";
		this.field[3] = "REGULAR";
	}

}
