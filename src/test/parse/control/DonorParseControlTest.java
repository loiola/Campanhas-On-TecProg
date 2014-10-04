package test.parse.control;

import model.beans.Donor;
import model.dao.DonorDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlDonor;
import parse.index.DonorParseIndex;
import test.TemplateTest;

public class DonorParseControlTest extends TemplateTest {

	public static final int PERSON_REGISTER = 0;
	public static final int NAME = 1;
	public static final int UF = 2;
	public static final int REGISTER_SITUATION = 3;
	
	private String fields[];
	private DonorDAO donorDAO;
	private DonorParseIndex donorParseIndex;
	private ParseControlDonor parseControlDonor;

	@Override
	public void beforeTest() throws Exception {
		
		this.fields = new String[4];
		this.donorDAO = new DonorDAO();
		this.donorParseIndex = new DonorParseIndex();
		this.parseControlDonor = new ParseControlDonor(this.donorParseIndex);
		
		fieldsStart();
		indexStart();
	}
	
	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void registeringDonor() throws Exception {
		
		this.parseControlDonor.addInstance(fields);
		this.parseControlDonor.registeringInstances();
		this.parseControlDonor.clear();
		
		Donor donorRegistered = this.donorDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.fields[PERSON_REGISTER], donorRegistered.getDonorPersonRegister() );
		Assert.assertEquals(this.fields[NAME], donorRegistered.getDonorName());
		Assert.assertEquals(this.fields[UF], donorRegistered.getDonorCountryState());
		Assert.assertEquals(this.fields[REGISTER_SITUATION], donorRegistered.getDonorRegisterSituation());
	}
	
	@Test
	public void notToSignTwoEqualDonor() throws Exception {
		
		this.parseControlDonor.addInstance(fields);
		this.parseControlDonor.addInstance(fields);
		this.parseControlDonor.registeringInstances();
		this.parseControlDonor.clear();
		
		int donorNumberDatabase = this.donorDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, donorNumberDatabase);
	}
	
	private void indexStart() {
		
		this.donorParseIndex.setIndexDonorCpfCnpj(PERSON_REGISTER);
		this.donorParseIndex.setIndexName(NAME);
		this.donorParseIndex.setIndexUnitFederation(UF);
		this.donorParseIndex.setIndexRegistrationStatus(REGISTER_SITUATION);
	}
	
	private void fieldsStart() {
		
		this.fields[PERSON_REGISTER] = "55325424149";
		this.fields[NAME] = "DOADOR INEXISTENTE";
		this.fields[UF] = "DF";
		this.fields[REGISTER_SITUATION] = "REGULAR";
	}

}
