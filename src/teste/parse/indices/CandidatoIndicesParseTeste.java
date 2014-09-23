package teste.parse.indices;

import model.beans.Candidate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.CandidateParseIndex;

public class CandidatoIndicesParseTeste {

	private CandidateParseIndex candidateParseIndex;
	private String campo[];

	@Before
	public void setUp() throws Exception {
		
		this.candidateParseIndex = new CandidateParseIndex();
		this.campo = new String[2];
		iniciarCampos();
		iniciarIndices();
	}
	
	@Test
	public void iniciarUmCandidatoComIndicesValidos() throws Exception {
		
		Candidate candidate = new Candidate();
		this.candidateParseIndex.startInstance(candidate, campo);
		Assert.assertEquals(this.campo[0], candidate.getCandidateName());
		Assert.assertEquals(this.campo[1], candidate.getCandidateElectoralTitle());
	}
	
	@Test
	public void iniciarUmCandidatoComIndicesInvalidos() throws Exception {
		
		this.candidateParseIndex = new CandidateParseIndex();
		Candidate candidate = new Candidate();
		this.candidateParseIndex.startInstance(candidate, campo);		
		Assert.assertNotEquals(this.campo[0], candidate.getCandidateName());
		Assert.assertNotEquals(this.campo[1], candidate.getCandidateElectoralTitle());
	}
	
	private void iniciarIndices() {
		this.candidateParseIndex.setIndexName(0);
		this.candidateParseIndex.setIndexElectionTitle(1);
	}
	
	private void iniciarCampos() {
		this.campo[0] = "LUCIA HELENA DE CARVALHO";
		this.campo[1] = "123456";
	}
	
}