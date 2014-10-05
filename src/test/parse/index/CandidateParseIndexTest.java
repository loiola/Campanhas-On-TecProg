package test.parse.index;

import model.beans.Candidate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.CandidateParseIndex;

public class CandidateParseIndexTest {

	private CandidateParseIndex candidateParseIndex;
	private String field[];

	@Before
	public void setUp() throws Exception {
		
		this.candidateParseIndex = new CandidateParseIndex();
		this.field = new String[2];
		
		fieldsStart();
		indexStart();
	}
	
	@Test
	public void startCandidateWithValidIndices() throws Exception {
		
		Candidate candidate = new Candidate();
		this.candidateParseIndex.startInstance(candidate, field);
		Assert.assertEquals(this.field[0], candidate.getCandidateName());
		Assert.assertEquals(this.field[1], candidate.getCandidateElectoralTitle());
	}
	
	@Test
	public void startCandidateWithInvalidIndices() throws Exception {
		
		this.candidateParseIndex = new CandidateParseIndex();
		Candidate candidate = new Candidate();
		this.candidateParseIndex.startInstance(candidate, field);		
		Assert.assertNotEquals(this.field[0], candidate.getCandidateName());
		Assert.assertNotEquals(this.field[1], candidate.getCandidateElectoralTitle());
	}
	
	private void indexStart() {
		this.candidateParseIndex.setIndexName(0);
		this.candidateParseIndex.setIndexElectionTitle(1);
	}
	
	private void fieldsStart() {
		this.field[0] = "LUCIA HELENA DE CARVALHO";
		this.field[1] = "123456";
	}
	
}