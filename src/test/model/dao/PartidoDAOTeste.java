package test.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Assert;
import org.junit.Test;

import test.TemplateTest;

public class PartidoDAOTeste extends TemplateTest {

	private PartyDAO partyDAO;

	@Override
	public void beforeTest() throws Exception {
		
		this.partyDAO = new PartyDAO();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void valoresComparacao() throws Exception {

		Party P1 = new Party();
		Party P2 = new Party();
		P1.setPartyAcronym("AEIOU");
		P2.setPartyAcronym("AEIOU");
		int resultado;

		resultado = PartyDAO.CompareTwoPartiesAcronym.SIGLA.compare(P1, P2);

		Assert.assertEquals(0, resultado);
	}

	@Test
	public void naoDeveLancarExcecaoAoCadastrarUmPartidoInexistente() throws Exception {

		ArrayList<Party> listaPartidos = new ArrayList<>();

		Party party = new Party();
		party.setPartyNumber(1);
		party.setPartyAcronym("A");
		party.setPartyConcession("11.2.1982");
		party.setPartyName("AEIOU");
		listaPartidos.add(party);

		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(listaPartidos);
	}

	@Test
	public void naoDeveCadastrarUmPartidoJaCadastrado() throws Exception {

		ArrayList<Party> listaPartidos = new ArrayList<>();

		Party party = new Party();
		party.setPartyNumber(1);
		party.setPartyAcronym("A");
		party.setPartyConcession("11.2.1982");
		party.setPartyName("AEIOU");
		listaPartidos.add(party);

		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(listaPartidos);
		int numeroDePartidosNaLista = this.partyDAO.getObjectArrayListFromDatabase().size();

		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(listaPartidos);

		Assert.assertEquals(numeroDePartidosNaLista, this.partyDAO.getObjectArrayListFromDatabase()
				.size());
	}

	@Test
	public void deveRecuperarUmPartidoPelaSigla() throws SQLException {
		
		ArrayList<Party> listaPartidos = new ArrayList<>();
		Party partidoRecuperado = new Party();
		
		Party p1 = new Party();
		p1.setPartyNumber(45);
		p1.setPartyAcronym("PI1");
		p1.setPartyName("PARTIDO INEXISTENTE 1");
		p1.setPartyConcession("11.8.1996");
		listaPartidos.add(p1);
		
		Party p2 = new Party();
		p2.setPartyNumber(23);
		p2.setPartyAcronym("PI2");
		p2.setPartyName("PARTIDO INEXISTENTE 2");
		p2.setPartyConcession("11.8.1994");
		listaPartidos.add(p2);
		
		this.partyDAO.registerUnregisteredObjectArrayListOnDatabase(listaPartidos);
		partidoRecuperado = this.partyDAO.getPartyByAcronym("PI1");
		
		Assert.assertEquals(p1, partidoRecuperado);
	}

	@Test
	public void deveRecuperarUmPartidoPeloNumero() throws SQLException {
		
		partyDAO.getPartyByNumber(null);
	}
	
}
