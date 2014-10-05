package test.parse.index;

import model.beans.Revenue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.RevenueParseIndex;

public class RevenueParseIndexTest {

	private String field[];
	private RevenueParseIndex revenueParseIndex;
	private String year = "2006";

	@Before
	public void setUp() throws Exception {
		
		this.revenueParseIndex = new RevenueParseIndex(year);
		this.field = new String[3];
		
		fieldsStart();
		indexStart();
	}
	
	@Test
	public void startRevenueWithValidIndices() throws Exception {
		
		Revenue revenue = new Revenue();
		this.revenueParseIndex.startInstance(revenue, field);
		Assert.assertEquals(this.field[0], revenue.getRevenueElectoralReceipt());
		Assert.assertEquals(this.field[1], revenue.getRevenueDonor().getDonorName());
		Assert.assertEquals(this.field[2], revenue.getRevenueDonor().getDonorPersonRegister());
	}
	
	@Test
	public void startRevenueWithInvalidIndices() {
		
		this.revenueParseIndex = new RevenueParseIndex(year);
		Revenue revenue = new Revenue();
		this.revenueParseIndex.startInstance(revenue, field);
		Assert.assertNotEquals(this.field[0], revenue.getRevenueElectoralReceipt());
		Assert.assertNotEquals(this.field[1], revenue.getRevenueDonor().getDonorName());
		Assert.assertNotEquals(this.field[2], revenue.getRevenueDonor().getDonorPersonRegister());
	}
	
	private void indexStart() {
		
		this.revenueParseIndex.setIndexElectoralReceipt(0);
		this.revenueParseIndex.setIndexNameDonor(1);
		this.revenueParseIndex.setIndexCpfCnpjDonor(2);
	}
	
	private void fieldsStart() {
		
		this.field[0] = "55325424149";
		this.field[1] = "DOADOR";
		this.field[2] = "123456789";
	}

}
