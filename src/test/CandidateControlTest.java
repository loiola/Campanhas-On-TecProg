package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import control.CandidateControl;

public class CandidateControlTest extends TemplateTest {

	private CandidateDAO candidateDAO;
	private CandidateControl candidateControl;

	@Override 
	public void beforeTest() throws Exception {
		
		this.candidateDAO = new CandidateDAO();
		this.candidateControl = new CandidateControl();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void mustRetrieveAListOfCandidatesByACommonName() throws SQLException {

		ArrayList<Candidate> candidateList = new ArrayList<>();

		LinkedList<Candidate> candidateListCommonName = new LinkedList<>();
		
		Candidate candidateOne = new Candidate();
		candidateOne.setCandidateName("CANDIDATO INEXISTENTE 1");
		candidateOne.setCandidateElectoralTitle("000000");
		candidateList.add(candidateOne);

		Candidate candidateTwo = new Candidate();
		candidateTwo.setCandidateName("CANDIDATO INEXISTENTE 2");
		candidateTwo.setCandidateElectoralTitle("000001");
		candidateList.add(candidateTwo);
		
		Candidate candidateThree = new Candidate();
		candidateThree.setCandidateName("CANDIDATO EXISTENTE 1");
		candidateThree.setCandidateElectoralTitle("000002");
		candidateList.add(candidateThree);

		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		candidateListCommonName = this.candidateDAO.getCandidateListByName("INEXISTENTE");
		this.candidateControl.getListCandidate("INEXISTENTE");
		
		Assert.assertEquals(candidateListCommonName, this.candidateControl.getListCandidate("INEXISTENTE"));
	}
	
	@Test
	public void mustRetrieveACandidateByElectoralTitle() throws SQLException {
		
		ArrayList<Candidate> candidateList = new ArrayList<>();
		Candidate candidateRecovered = new Candidate();
		
		Candidate candidateOne = new Candidate();
		candidateOne.setCandidateName("CANDIDATO EXISTENTE 1");
		candidateOne.setCandidateElectoralTitle("000000");
		candidateList.add(candidateOne);

		Candidate candidateTwo = new Candidate();
		candidateTwo.setCandidateName("CANDIDATO EXISTENTE 2");
		candidateTwo.setCandidateElectoralTitle("000001");
		candidateList.add(candidateTwo);
		
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		candidateRecovered = this.candidateDAO.getCandidateByElectoralTitle("000000");
		this.candidateControl.getACandidate("000000");
		
		Assert.assertEquals(candidateRecovered, this.candidateControl.getACandidate("000000"));
	}
	
}