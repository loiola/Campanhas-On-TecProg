package test.parse.control;

import model.beans.Revenue;
import model.dao.RevenueDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlRevenue;
import parse.index.RevenueParseIndex;
import test.TemplateTest;

public class RevenueParseControlTest extends TemplateTest {
	
	public static final int RECEIPT = 0;
	public static final int NAME = 1;
	public static final int PERSON_REGISTER = 2;
	public static final String YEAR = "2010";

	private String fields[];
	private RevenueDAO revenueDAO;
	private RevenueParseIndex revenueParseIndex;
	private ParseControlRevenue parseControlRevenue;

	@Override
	public void beforeTest() throws Exception {
		
		this.fields = new String[3];
		this.revenueDAO = new RevenueDAO();
		this.revenueParseIndex = new RevenueParseIndex(YEAR);
		this.parseControlRevenue = new ParseControlRevenue(this.revenueParseIndex);

		fieldsStart();
		indexStart();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void registeringRevenue() throws Exception {

		this.parseControlRevenue.addInstance(fields);
		this.parseControlRevenue.registeringInstances();
		this.parseControlRevenue.clear();

		Revenue revenueRegistered = this.revenueDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.fields[RECEIPT], revenueRegistered.
				getRevenueElectoralReceipt());
		Assert.assertEquals(this.fields[NAME], revenueRegistered.
				getRevenueDonor().getDonorName());
		Assert.assertEquals(this.fields[PERSON_REGISTER], revenueRegistered.
				getRevenueDonor().getDonorPersonRegister());
	}

	private void indexStart() {

		this.revenueParseIndex.setIndexElectoralReceipt(RECEIPT);
		this.revenueParseIndex.setIndexNameDonor(NAME);
		this.revenueParseIndex.setIndexCpfCnpjDonor(PERSON_REGISTER);
	}

	private void fieldsStart() {

		this.fields[RECEIPT] = "RECIBOELEITORAL";
		this.fields[NAME] = "NAME";
		this.fields[PERSON_REGISTER] = "123";
	}

}
