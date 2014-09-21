package teste.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Party;
import modelo.beans.Result;
import modelo.dao.CampanhaDAO;
import modelo.dao.CandidatoDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class CampanhaDAOTeste extends TemplateTeste {
	
	private CampanhaDAO campanhaDAO;
	private CandidatoDAO candidatoDAO;
	private Result resultado1;
	private Position position;
	private Party partido1;
	private Candidate candidato1;
	private Result resultado2;
	private Party partido2;
	private Candidate candidato2;
	private ArrayList<Campaign> listaCampanhas;

	@Override
	public void beforeTest() throws Exception {
		
		this.campanhaDAO = new CampanhaDAO();
		this.candidatoDAO = new CandidatoDAO();
		this.resultado1 = new Result();
		this.position = new Position();
		this.partido1 = new Party();
		this.candidato1 = new Candidate();
		this.resultado2 = new Result();
		this.partido2 = new Party();
		this.candidato2 = new Candidate();
		this.listaCampanhas = new ArrayList<>();

		cadastraNoBanco();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	private void cadastraNoBanco() throws SQLException {

		ArrayList<Candidate> listaCandidato = new ArrayList<>();
		
		Campaign camp1 = new Campaign();
		this.resultado1.setCodigo(2);
		this.position.setCodigo(1);
		this.partido1.setNumero(45);
		this.candidato1.setTituloEleitoral("55325424149");
		camp1.setId(1);
		camp1.setAno(2006);
		camp1.setNumeroCandidato(45555);
		camp1.setResultado(resultado1);
		camp1.setCargo(position);
		camp1.setPartido(partido1);
		camp1.setCandidato(candidato1);
		camp1.setNomeDeUrna("NOME DE URNA UM");
		camp1.setUf("DF");
		camp1.setDespesaMaxDeclarada((float) 450000.);
		camp1.setDespesaTotalCalculada((float) 450000.0);
		camp1.setReceitaTotalCalculada((float) 450000.0);
		listaCampanhas.add(camp1);
		listaCandidato.add(candidato1);
		
		Campaign camp2 = new Campaign();
		this.resultado2.setCodigo(3);
		this.partido2.setNumero(13);
		this.candidato2.setTituloEleitoral("04725698130");
		camp2.setId(2);
		camp2.setAno(2006);
		camp2.setNumeroCandidato(13122);
		camp2.setResultado(resultado2);
		camp2.setCargo(position);
		camp2.setPartido(partido2);
		camp2.setCandidato(candidato2);
		camp2.setNomeDeUrna("NOME DE URNA DOIS");
		camp2.setUf("DF");
		camp2.setDespesaMaxDeclarada((float) 500000.);
		camp2.setDespesaTotalCalculada((float) 500000.0);
		camp2.setReceitaTotalCalculada((float) 500000.0);
		listaCampanhas.add(camp2);
		listaCandidato.add(candidato2);
		
		this.candidatoDAO.cadastrarLista(listaCandidato);				
		this.campanhaDAO.cadastrarLista(listaCampanhas);
	}
	
	@Test
	public void deveVerificarSeOTopFiveEstaOrdenado() throws SQLException {	
		
		Assert.assertEquals(listaCampanhas, this.campanhaDAO.getLista());
	}
	
	@Test
	public void deveListarCandidatosPorOrdemDeDespesa() throws Exception {
		
		ArrayList<Campaign> listaTop1= this.campanhaDAO.TopFive("presidente",2006);
		Assert.assertTrue(listaTop1.get(0).getDespesaMaxDeclarada() >= listaTop1.get(1).getDespesaMaxDeclarada());
		
		//ArrayList<Campaign> listaTop2 = this.campanhaDAO.TopFive("senador",2010);
		//Assert.assertTrue(listaTop2.get(0).getDespesaMaxDeclarada() >= listaTop2.get(1).getDespesaMaxDeclarada());
		
		//ArrayList<Campaign> listaTop3 = this.campanhaDAO.TopFive("governador",2002);
		//Assert.assertTrue(listaTop3.get(0).getDespesaMaxDeclarada() >= listaTop3.get(1).getDespesaMaxDeclarada());
	}

}
