package teste;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import control.CandidatoControle;

public class CandidatoControleTeste extends TemplateTeste {

	private CandidateDAO candidateDAO;
	private CandidatoControle candidatoControle;

	@Override 
	public void beforeTest() throws Exception {
		
		this.candidateDAO = new CandidateDAO();
		this.candidatoControle = new CandidatoControle();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void deveRecuperarUmaListaDeCandidatosPorUmNomeComum() throws SQLException {

		ArrayList<Candidate> listaCandidatos = new ArrayList<>();

		LinkedList<Candidate> listaCandidatosNomeComum = new LinkedList<>();
		
		Candidate candidato1 = new Candidate();
		candidato1.setCandidateName("CANDIDATO INEXISTENTE 1");
		candidato1.setCandidateElectoralTitle("000000");
		listaCandidatos.add(candidato1);

		Candidate candidato2 = new Candidate();
		candidato2.setCandidateName("CANDIDATO INEXISTENTE 2");
		candidato2.setCandidateElectoralTitle("000001");
		listaCandidatos.add(candidato2);
		
		Candidate candidato3 = new Candidate();
		candidato3.setCandidateName("CANDIDATO EXISTENTE 1");
		candidato3.setCandidateElectoralTitle("000002");
		listaCandidatos.add(candidato3);

		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);
		listaCandidatosNomeComum = this.candidateDAO.getCandidateListByName("INEXISTENTE");
		this.candidatoControle.getListaCandidatos("INEXISTENTE");
		
		Assert.assertEquals(listaCandidatosNomeComum, this.candidatoControle.getListaCandidatos("INEXISTENTE"));
	}
	
	@Test
	public void deveRecuperarUmCandidatoPeloTituloEleitoral() throws SQLException {
		
		ArrayList<Candidate> listaCandidatos = new ArrayList<>();
		Candidate candidatoRecuperado = new Candidate();
		
		Candidate candidato1 = new Candidate();
		candidato1.setCandidateName("CANDIDATO EXISTENTE 1");
		candidato1.setCandidateElectoralTitle("000000");
		listaCandidatos.add(candidato1);

		Candidate candidato2 = new Candidate();
		candidato2.setCandidateName("CANDIDATO EXISTENTE 2");
		candidato2.setCandidateElectoralTitle("000001");
		listaCandidatos.add(candidato2);
		
		this.candidateDAO.registerUnregisteredObjectArrayListOnDatabase(listaCandidatos);
		candidatoRecuperado = this.candidateDAO.getCandidateByElectoralTitle("000000");
		this.candidatoControle.getUmCandidato("000000");
		
		Assert.assertEquals(candidatoRecuperado, this.candidatoControle.getUmCandidato("000000"));
	}
	
}
