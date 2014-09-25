package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParseCandidate;
import teste.TemplateTeste;

public class CadastroCandidatoParseTeste extends TemplateTeste {

	private RegisterToParseCandidate cadastro;
	private CandidateDAO candidateDAO;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new RegisterToParseCandidate(this.tipoArquivo, this.ano);	
		this.candidateDAO = new CandidateDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRetornarUmCandidatoCadastrado() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[10] = "CANDIDATO TESTE";
		campo[26] = "55325424149";
		cadastro.runFileLine(campo);
		cadastro.registerInstances();
		
		Candidate candidate = this.candidateDAO.getCandidateByElectoralTitle("55325424149");
		assertEquals(candidate.getCandidateElectoralTitle(), "55325424149");
	}

}
