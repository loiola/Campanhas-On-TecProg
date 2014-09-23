package teste.parse.controle;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlCandidate;
import parse.index.CandidateParseIndex;
import teste.TemplateTeste;

public class CandidatoParseControleTeste extends TemplateTeste {

	public static final int NOME = 0;
	public static final int TITULO_ELEITORAL = 1;
	
	private String campo[];
	private CandidateDAO candidateDAO;
	private CandidateParseIndex candidateParseIndex;
	private ParseControlCandidate parseControlCandidate;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.candidateDAO = new CandidateDAO();
		this.candidateParseIndex = new CandidateParseIndex();
		this.parseControlCandidate = new ParseControlCandidate(this.candidateParseIndex);
		
		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void cadastrarCandidato() throws Exception {
		
		this.parseControlCandidate.addInstance(campo);
		this.parseControlCandidate.registeringInstances();
		this.parseControlCandidate.clear();
		
		Candidate candidatoCadastrado = this.candidateDAO.getObjectArrayListFromDatabase().get(0);
		
		Assert.assertEquals(this.campo[NOME], candidatoCadastrado.getCandidateName());
		Assert.assertEquals(this.campo[TITULO_ELEITORAL],candidatoCadastrado.getCandidateElectoralTitle());
	}
	
	@Test
	public void naoDeveCadastrarDoisCandidatosIguais() throws Exception {
		
		this.parseControlCandidate.addInstance(campo);
		this.parseControlCandidate.addInstance(campo);
		this.parseControlCandidate.registeringInstances();
		this.parseControlCandidate.clear();
		
		int numeroCandidatosCadastrados = this.candidateDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, numeroCandidatosCadastrados);
	}
	
	private void iniciarIndices() {
		
		this.candidateParseIndex.setIndexName(NOME);
		this.candidateParseIndex.setIndexElectionTitle(TITULO_ELEITORAL);
	}
	
	private void iniciarCampos() {
		
		this.campo[NOME] = "LUCIA HELENA DE CARVALHO";
		this.campo[TITULO_ELEITORAL] = "123456";
	}
	
}