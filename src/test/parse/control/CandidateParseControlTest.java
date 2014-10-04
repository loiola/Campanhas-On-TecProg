package test.parse.control;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlCandidate;
import parse.index.CandidateParseIndex;
import test.TemplateTest;

public class CandidateParseControlTest extends TemplateTest {

	public static final int NAME = 0;
	public static final int ELECTORAL_TITLE = 1;
	
	private String field[];
	private CandidateDAO candidateDAO;
	private CandidateParseIndex candidateParseIndex;
	private ParseControlCandidate parseControlCandidate;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.field = new String[2];
		this.candidateDAO = new CandidateDAO();
		this.candidateParseIndex = new CandidateParseIndex();
		this.parseControlCandidate = new ParseControlCandidate(this.candidateParseIndex);
		
		fieldsStart();
		indexStart();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void registeringCandidate() throws Exception {
		
		this.parseControlCandidate.addInstance(field);
		this.parseControlCandidate.registeringInstances();
		this.parseControlCandidate.clear();
		
		Candidate candidateRegistered = this.candidateDAO.getObjectArrayListFromDatabase().get(0);
		
		Assert.assertEquals(this.field[NAME], candidateRegistered.getCandidateName());
		Assert.assertEquals(this.field[ELECTORAL_TITLE],candidateRegistered.getCandidateElectoralTitle());
	}
	
	@Test
	public void notToSignTwoEqualCandidate() throws Exception {
		
		this.parseControlCandidate.addInstance(field);
		this.parseControlCandidate.addInstance(field);
		this.parseControlCandidate.registeringInstances();
		this.parseControlCandidate.clear();
		
		int candidateNumberDatabase = this.candidateDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, candidateNumberDatabase);
	}
	
	private void indexStart() {
		
		this.candidateParseIndex.setIndexName(NAME);
		this.candidateParseIndex.setIndexElectionTitle(ELECTORAL_TITLE);
	}
	
	private void fieldsStart() {
		
		this.field[NAME] = "LUCIA HELENA DE CARVALHO";
		this.field[ELECTORAL_TITLE] = "123456";
	}
	
}