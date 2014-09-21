package teste.modelo.dao;

import java.util.ArrayList;
import java.util.LinkedList;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class CandidatoDAOTeste extends TemplateTeste {

	private CandidateDAO candidateDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.candidateDAO = new CandidateDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void valoresComparacao() throws Exception {
		
		CandidateDAO.CompareTwoCandidatesElectoralTitle.valueOf(CandidateDAO.CompareTwoCandidatesElectoralTitle.TITULO_ELEITORAL.toString());
	}
	
	@Test
	public void naoDeveLancarExcecaoAoCadastrarUmCandidatoInexistente() throws Exception {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("CANDIDATO INEXISTENTE");
		candidate.setCandidateElectoralTitle("00000");
		listaCandidatos.add(candidate);
		
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);
	}
	
	@Test
	public void naoDeveCadastrarUmCandidatoJaExistente() throws Exception {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("CANDIDATO INEXISTENTE");
		candidate.setCandidateElectoralTitle("00000");
		listaCandidatos.add(candidate);

		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);
		
		Assert.assertEquals(1, this.candidateDAO.getObjectArrayListFromDatabase().size());
	}
	
	@Test
	public void deveRecuperarUmaListaComOsCandidatosCadastrados() throws Exception {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("CANDIDATO INEXISTENTE");
		candidate.setCandidateElectoralTitle("00000");
		listaCandidatos.add(candidate);
		
		Candidate candidatoDois = new Candidate();
		candidatoDois.setCandidateName("CANDIDATO INEXISTENTE DOIS");
		candidatoDois.setCandidateElectoralTitle("00001");
		listaCandidatos.add(candidatoDois);
		
		candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);

		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);
		Assert.assertEquals(listaCandidatos, candidateDAO.getObjectArrayListFromDatabase());
	}

	@Test
	public void deveLancarFalsoSeUmCandidatoNaoExiste() throws Exception {
		
		Candidate candidate = new Candidate();
		candidate.setCandidateName("FULANO");
		candidate.setCandidateElectoralTitle("00000");
		
		Candidate candidatoDois = new Candidate();
		candidatoDois.setCandidateName("FULANO");
		candidatoDois.setCandidateElectoralTitle("00001");

		Assert.assertFalse(candidate.equals(candidatoDois));
	}
	
	@Test
	public void deveRetornarFalseDuranteAhComparacao() throws Exception {
		
		CandidateDAO.CompareTwoCandidatesElectoralTitle.valueOf(CandidateDAO.CompareTwoCandidatesElectoralTitle.TITULO_ELEITORAL.toString());
		
		Candidate C1 = new Candidate();
		Candidate C2 = new Candidate();
		int resultado;

		resultado = CandidateDAO.CompareTwoCandidatesElectoralTitle.TITULO_ELEITORAL.compare(C1, C2);		
		
		Assert.assertEquals(0,resultado);
	}
	
	
	@Test
	public void testeGetCandidatoComUmTituloVazio() throws Exception {
		LinkedList<Candidate> LK = null;
		LK = candidateDAO.getCandidateListByName(null);
		
		Assert.assertEquals(0,LK.size());
	}	
	
}
