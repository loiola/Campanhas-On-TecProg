package teste;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Partido;
import modelo.beans.Resultado;
import modelo.dao.CampanhaDAO;
import modelo.dao.CandidatoDAO;

import org.junit.Assert;
import org.junit.Test;

import controle.CampanhaControle;

public class CampanhaControleTeste extends TemplateTeste {
	
	private CampanhaDAO campanhaDAO;
	private CandidatoDAO candidatoDAO;
	private CampanhaControle campanhaControle;
	private Candidate candidate;
	private Partido partido1;
	private Campaign campanha1;
	private Resultado resultado1;
	private Position cargo1;
	private Partido partido2;
	private Campaign campanha2;
	private Resultado resultado2;
	private Position cargo2;

	@Override
	public void beforeTest() throws Exception {
		
		this.campanhaDAO = new CampanhaDAO();
		this.candidatoDAO = new CandidatoDAO();
		this.campanhaControle = new CampanhaControle();
		this.campanha1 = new Campaign();
		this.partido1 = new Partido();
		this.candidate = new Candidate();
		this.resultado1 = new Resultado();
		this.cargo1 = new Position();
		this.resultado2 = new Resultado();
		this.cargo2 = new Position();
		this.campanha2 = new Campaign();
		this.partido2 = new Partido();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmaListaDeCampanhasDeUmDeterminadoCandidato() throws SQLException {
		
		ArrayList<Campaign> listaCampanhas = new ArrayList<>();
		ArrayList<Candidate> listaCandidato = new ArrayList<>();

		
		this.candidate.setNome("CANDIDATO");
		this.candidate.setTituloEleitoral("5532424149");
		listaCandidato.add(candidate);
		
		this.resultado1.setCodigo(1);
		this.resultado1.setDescricao("NAO ELEITO");
		this.cargo1.setCodigo(2);
		this.cargo1.setDescricao("DEPUTADO DISTRITAL");
		this.partido1.setSigla("SGL");
		this.partido1.setNumero(45);
		this.campanha1.setId(1);
		this.campanha1.setAno(2002);
		this.campanha1.setNumeroCandidato(45555);
		this.campanha1.setResultado(resultado1);
		this.campanha1.setCargo(cargo1);
		this.campanha1.setPartido(partido1);
		this.campanha1.setCandidato(candidate);
		this.campanha1.setNomeDeUrna("CAND");
		this.campanha1.setUf("DF");
		this.campanha1.setDespesaMaxDeclarada((float) 450000.0);
		this.campanha1.setDespesaTotalCalculada((float) 450000.0);
		this.campanha1.setReceitaTotalCalculada((float) 450000.0);
		listaCampanhas.add(campanha1);
		
		this.resultado2.setCodigo(2);
		this.resultado2.setDescricao("ELEITO");
		this.cargo2.setCodigo(1);
		this.cargo2.setDescricao("DEPUTADO FEDERAL");
		this.partido2.setSigla("SGLL");
		this.partido2.setNumero(13);
		this.campanha2.setId(2);
		this.campanha2.setAno(2006);
		this.campanha2.setNumeroCandidato(1313);
		this.campanha2.setResultado(resultado2);
		this.campanha2.setCargo(cargo2);
		this.campanha2.setPartido(partido2);
		this.campanha2.setCandidato(candidate);
		this.campanha2.setNomeDeUrna("CAND");
		this.campanha2.setUf("DF");
		this.campanha2.setDespesaMaxDeclarada((float) 450000.0);
		this.campanha2.setDespesaTotalCalculada((float) 450000.0);
		this.campanha2.setReceitaTotalCalculada((float) 450000.0);
		listaCampanhas.add(campanha2);
		
		this.campanhaDAO.cadastrarLista(listaCampanhas);
		this.candidatoDAO.cadastrarLista(listaCandidato);
		
		Assert.assertEquals(this.campanhaDAO.getCampanhasPeloTituloEleitoral(candidate), this.campanhaControle.getListaCampanhas(candidate));
		Assert.assertEquals(this.campanhaDAO.getCampanhasPorSiglaEAno("SGLL", "2006"), this.campanhaControle.getListaCampanhasPorSiglaPartidoEAno("SGLL", "2006"));
		Assert.assertEquals(this.campanhaDAO.getPeloAnoNumeroCodCargoEUf(campanha1), this.campanhaControle.getPeloAnoNumeroCodCargoEUf(campanha1));
		Assert.assertNotEquals(this.campanhaDAO.getPeloAnoNumeroCodCargoEUf(campanha1), this.campanhaControle.getPeloAnoNumeroCodCargoEUf(campanha2));
	}

}
