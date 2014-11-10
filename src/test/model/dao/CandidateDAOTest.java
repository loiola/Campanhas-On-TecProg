package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.ParseException;
import test.TemplateTest;

public class CandidateDAOTest extends TemplateTest {

	private CandidateDAO candidateDAO;
	
	private static final String DATABASE_CANDIDATE_TABLE_NAME = "candidato";
	private final String DATABASE_CANDIDATE_ELECTORAL_TITLE = "titulo_eleitoral";
	private final String DATABASE_SQL_COMMAND_SELECT = "SELECT * FROM " + DATABASE_CANDIDATE_TABLE_NAME;
	
	@Override
	public void beforeTest() throws Exception {
		this.candidateDAO = new CandidateDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void comparationValues() throws Exception {
		
		CandidateDAO.CompareTwoCandidatesElectoralTitle.valueOf(CandidateDAO.CompareTwoCandidatesElectoralTitle.ELECTORAL_TITLE.toString());
	}
	
	@Test
	public void shouldNotThrowsExceptionToRegisterANewCandidate() throws Exception {
		
		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("CANDIDATO INEXISTENTE");
		candidate.setCandidateElectoralTitle("00000");
		candidateList.add(candidate);
		
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
	}
	
	@Test
	public void shouldNotRegisterAgainAnExistingCandidate() throws Exception {
		
		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("CANDIDATO INEXISTENTE");
		candidate.setCandidateElectoralTitle("00000");
		candidateList.add(candidate);

		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		
		Assert.assertEquals(1, this.candidateDAO.getObjectArrayListFromDatabase().size());
	}
	
	@Test
	public void shouldRetrieveAnArrayOfCandidates() throws Exception {
		
		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		Candidate candidateOne = new Candidate();
		candidateOne.setCandidateName("CANDIDATO INEXISTENTE");
		candidateOne.setCandidateElectoralTitle("00000");
		candidateList.add(candidateOne);
		
		Candidate candidatoTwo = new Candidate();
		candidatoTwo.setCandidateName("CANDIDATO INEXISTENTE DOIS");
		candidatoTwo.setCandidateElectoralTitle("00001");
		candidateList.add(candidatoTwo);
		
		candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);

		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		Assert.assertEquals(candidateList, candidateDAO.getObjectArrayListFromDatabase());
	}

	@Test
	public void shouldShedFalseToDifferentCandidates() throws Exception {
		
		Candidate candidateOne = new Candidate();
		candidateOne.setCandidateName("FULANO");
		candidateOne.setCandidateElectoralTitle("00000");
		
		Candidate candidatoTwo = new Candidate();
		candidatoTwo.setCandidateName("FULANO");
		candidatoTwo.setCandidateElectoralTitle("00001");

		Assert.assertFalse(candidateOne.equals(candidatoTwo));
	}
	
	@Test
	public void shouldReturnFalseDuringComparation() throws Exception {
		
		CandidateDAO.CompareTwoCandidatesElectoralTitle.valueOf(CandidateDAO.CompareTwoCandidatesElectoralTitle.ELECTORAL_TITLE.toString());
		
		Candidate candidateOne = new Candidate();
		Candidate candidateTwo = new Candidate();
		int result;

		result = CandidateDAO.CompareTwoCandidatesElectoralTitle.ELECTORAL_TITLE.compare(candidateOne, candidateTwo);		
		
		Assert.assertEquals(0,result);
	}
	
	
	@Test
	public void getCandidateWithEmptyElectoralTitle() throws Exception {
		
		LinkedList<Candidate> candidateList = null;
		candidateList = candidateDAO.getCandidateListByName(null);
		
		Assert.assertEquals(0,candidateList.size());
	}
	
	@Test
	public void shouldRetrieveSQLConsultationForElectoralTitle() {
		Candidate candidate = new Candidate();
		candidate.setCandidateElectoralTitle("55325424149");
		
		String electoralTitle = candidate.getCandidateElectoralTitle();
		String sqlCommand = DATABASE_SQL_COMMAND_SELECT + " WHERE "
				+ DATABASE_CANDIDATE_ELECTORAL_TITLE + " = '"+ electoralTitle + "'";
		
		Assert.assertEquals(sqlCommand, this.candidateDAO.mountingSQLConsultationForElectoralTitle(electoralTitle));
	}
	
	@Test
	public void shouldRetrieveCandidateByElectoralTitle() throws ParseException, SQLException {
		ArrayList<Candidate> candidateList = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateElectoralTitle("55325424149");
		candidateList.add(candidate);
		
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(candidateList);
		
		Assert.assertEquals(candidate, this.candidateDAO.getCandidateByElectoralTitle("55325424149"));
	}
	
}
