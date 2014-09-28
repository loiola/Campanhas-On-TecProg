package test.model.bean;

import static test.model.bean.BeanTest.instantiateCandidate;
import static test.model.bean.BeanTest.instantiateParty;
import model.beans.Candidate;
import model.beans.Party;

import org.junit.Assert;
import org.junit.Test;

public class CandidateTest {

	@Test
	public void equalsShouldReturnTrueIfTwoCandidatesAreTheSame() {
		
		Candidate candidateOne = instantiateCandidate();
		Candidate candidateTwo = instantiateCandidate();
		Assert.assertTrue(candidateOne.equals(candidateTwo));
	}
	
	@Test
	public void equalsShouldReturnFalseIfTwoCandidatesAreDifferent() {
		
		Candidate candidateOne = instantiateCandidate();
		Candidate candidateTwo = instantiateCandidate();
		candidateTwo.setCandidateElectoralTitle(BeanTest.STRING_TEST_2);
		Assert.assertFalse(candidateOne.equals(candidateTwo));
	}
	
	@Test
	public void equalsTestThree() {
		
		Candidate candidate = instantiateCandidate();
		Party party = instantiateParty();
		Assert.assertFalse(candidate.equals(party));
		Assert.assertFalse(party.equals(candidate));
		
		Assert.assertEquals(BeanTest.STRING_TEST,candidate.getCandidateName());
		
		Assert.assertEquals(BeanTest.STRING_TEST,party.getPartyAcronym());
		Assert.assertEquals(BeanTest.STRING_TEST,party.getPartyConcession());
		Assert.assertEquals(BeanTest.STRING_TEST,party.getPartyName());
	}

}