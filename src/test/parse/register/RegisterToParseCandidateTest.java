package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParseCandidate;
import test.TemplateTest;

public class RegisterToParseCandidateTest extends TemplateTest {

	private RegisterToParseCandidate register;
	private CandidateDAO candidateDAO;
	String  fileType = "campaign";
	String  year         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.register = new RegisterToParseCandidate(this.fileType, this.year);	
		this.candidateDAO = new CandidateDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void shouldReturnCandidateRegistered() throws ParseException, SQLException {
		
		String field[] = new String[50];
		field[10] = "CANDIDATO TESTE";
		field[26] = "55325424149";
		register.runFileLine(field);
		register.registerInstances();
		
		Candidate candidate = this.candidateDAO.getCandidateByElectoralTitle("55325424149");
		assertEquals(candidate.getCandidateElectoralTitle(), "55325424149");
	}

}
