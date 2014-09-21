package teste.modelo.dao;

import java.util.ArrayList;
import java.util.LinkedList;

import modelo.beans.Candidate;
import modelo.dao.CandidatoDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class CandidatoDAOTeste extends TemplateTeste {

	private CandidatoDAO candidatoDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.candidatoDAO = new CandidatoDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void valoresComparacao() throws Exception {
		
		CandidatoDAO.Comparacao.valueOf(CandidatoDAO.Comparacao.TITULO_ELEITORAL.toString());
	}
	
	@Test
	public void naoDeveLancarExcecaoAoCadastrarUmCandidatoInexistente() throws Exception {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setNome("CANDIDATO INEXISTENTE");
		candidate.setTituloEleitoral("00000");
		listaCandidatos.add(candidate);
		
		this.candidatoDAO.cadastrarLista(listaCandidatos);
	}
	
	@Test
	public void naoDeveCadastrarUmCandidatoJaExistente() throws Exception {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setNome("CANDIDATO INEXISTENTE");
		candidate.setTituloEleitoral("00000");
		listaCandidatos.add(candidate);

		this.candidatoDAO.cadastrarLista(listaCandidatos);
		this.candidatoDAO.cadastrarLista(listaCandidatos);
		
		Assert.assertEquals(1, this.candidatoDAO.getLista().size());
	}
	
	@Test
	public void deveRecuperarUmaListaComOsCandidatosCadastrados() throws Exception {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		
		Candidate candidate = new Candidate();
		candidate.setNome("CANDIDATO INEXISTENTE");
		candidate.setTituloEleitoral("00000");
		listaCandidatos.add(candidate);
		
		Candidate candidatoDois = new Candidate();
		candidatoDois.setNome("CANDIDATO INEXISTENTE DOIS");
		candidatoDois.setTituloEleitoral("00001");
		listaCandidatos.add(candidatoDois);
		
		candidatoDAO.cadastrarLista(listaCandidatos);

		this.candidatoDAO.cadastrarLista(listaCandidatos);
		Assert.assertEquals(listaCandidatos, candidatoDAO.getLista());
	}

	@Test
	public void deveLancarFalsoSeUmCandidatoNaoExiste() throws Exception {
		
		Candidate candidate = new Candidate();
		candidate.setNome("FULANO");
		candidate.setTituloEleitoral("00000");
		
		Candidate candidatoDois = new Candidate();
		candidatoDois.setNome("FULANO");
		candidatoDois.setTituloEleitoral("00001");

		Assert.assertFalse(candidate.equals(candidatoDois));
	}
	
	@Test
	public void deveRetornarFalseDuranteAhComparacao() throws Exception {
		
		CandidatoDAO.Comparacao.valueOf(CandidatoDAO.Comparacao.TITULO_ELEITORAL.toString());
		
		Candidate C1 = new Candidate();
		Candidate C2 = new Candidate();
		int resultado;

		resultado = CandidatoDAO.Comparacao.TITULO_ELEITORAL.compare(C1, C2);		
		
		Assert.assertEquals(0,resultado);
	}
	
	
	@Test
	public void testeGetCandidatoComUmTituloVazio() throws Exception {
		LinkedList<Candidate> LK = null;
		LK = candidatoDAO.getListaPeloNome(null);
		
		Assert.assertEquals(0,LK.size());
	}	
	
}
