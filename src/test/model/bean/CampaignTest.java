package test.model.bean;

import static test.model.bean.BeanTest.instantiateCampaign;
import static test.model.bean.BeanTest.instantiateCandidate;
import static test.model.bean.BeanTest.instantiatePosition;
import static test.model.bean.BeanTest.instantiateParty;
import static test.model.bean.BeanTest.instantiateResult;
import model.beans.Campaign;
import model.beans.Position;
import model.beans.Result;

import org.junit.Assert;
import org.junit.Test;

public class CampaignTest {
	
	@Test
	public void equalsShouldReturnTrueIfTwoCampaignsAreTheSame() {
		
		Campaign campaignOne = instantiateCampaign();
		Campaign campaignTwo = instantiateCampaign();
		Assert.assertTrue(campaignOne.equals(campaignTwo));
	}
	
	@Test
	public void equalsShouldReturnFalseIfTwoCampaignsAreDifferent() {
		
		Campaign campaignOne = instantiateCampaign();
		Campaign campaignTwo = instantiateCampaign();
		campaignTwo.setCampaignYear(BeanTest.INT_TEST_2);
		Assert.assertFalse(campaignOne.equals(campaignTwo));
		campaignTwo.setCampaignYear(BeanTest.INT_TEST);
		Position position = instantiatePosition();
		position.setPositionDescription(BeanTest.STRING_TEST_2);
		campaignTwo.setCampaignPosition(position);
		Assert.assertFalse(campaignOne.equals(campaignTwo));
		position.setPositionDescription(BeanTest.STRING_TEST);
		campaignTwo.setCampaignNameOfUrn(BeanTest.STRING_TEST_2);
		Assert.assertFalse(campaignOne.equals(campaignTwo));
		campaignTwo.setCampaignNameOfUrn(BeanTest.STRING_TEST);
		campaignTwo.setCampaignCandidateNumber(BeanTest.INT_TEST_2);
		Assert.assertFalse(campaignOne.equals(campaignTwo));
	}
	
	@Test
	public void equalsTestThree() {
		
		Campaign campaign = instantiateCampaign();
		Result result = instantiateResult();
		
		Assert.assertFalse(campaign.equals(result));
		Assert.assertFalse(result.equals(campaign));
		Assert.assertEquals(result,campaign.getCampaignResult());
		Assert.assertEquals(BeanTest.INT_TEST,campaign.getCampaignIdentifier());
		Assert.assertEquals(instantiateParty(),campaign.getCampaignParty());
		Assert.assertEquals(instantiateCandidate(),campaign.getCampaignCandidate());
		Assert.assertEquals(BeanTest.STRING_TEST,campaign.getCampaignCountryState());
		Assert.assertEquals(BeanTest.FLOAT_TEST,campaign.getCampaignMaximumExpenseDeclared(),0);
		Assert.assertEquals(BeanTest.FLOAT_TEST,campaign.getCampaignTotalExpenseCalculated(),0);
		Assert.assertEquals(BeanTest.FLOAT_TEST,campaign.getCampaignTotalRevenueCalculated(),0);
		Assert.assertEquals(BeanTest.INT_TEST,result.getResultType());
	}
	
}