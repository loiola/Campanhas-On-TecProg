package teste.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Party;
import model.dao.PartidoDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class PartidoDAOTeste extends TemplateTeste {

	private PartidoDAO partidoDAO;

	@Override
	public void beforeTest() throws Exception {
		
		this.partidoDAO = new PartidoDAO();
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

		resultado = PartidoDAO.Comparacao.SIGLA.compare(P1, P2);

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

		this.partidoDAO.cadastrarLista(listaPartidos);
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

		this.partidoDAO.cadastrarLista(listaPartidos);
		int numeroDePartidosNaLista = this.partidoDAO.getLista().size();

		this.partidoDAO.cadastrarLista(listaPartidos);

		Assert.assertEquals(numeroDePartidosNaLista, this.partidoDAO.getLista()
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
		
		this.partidoDAO.cadastrarLista(listaPartidos);
		partidoRecuperado = this.partidoDAO.getPelaSigla("PI1");
		
		Assert.assertEquals(p1, partidoRecuperado);
	}

	@Test
	public void deveRecuperarUmPartidoPeloNumero() throws SQLException {
		
		partidoDAO.getPeloNumero(null);
	}
	
}
