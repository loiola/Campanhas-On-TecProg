package teste.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Party;
import modelo.dao.PartidoDAO;

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
		P1.setSigla("AEIOU");
		P2.setSigla("AEIOU");
		int resultado;

		resultado = PartidoDAO.Comparacao.SIGLA.compare(P1, P2);

		Assert.assertEquals(0, resultado);
	}

	@Test
	public void naoDeveLancarExcecaoAoCadastrarUmPartidoInexistente() throws Exception {

		ArrayList<Party> listaPartidos = new ArrayList<>();

		Party party = new Party();
		party.setNumero(1);
		party.setSigla("A");
		party.setDeferimento("11.2.1982");
		party.setNome("AEIOU");
		listaPartidos.add(party);

		this.partidoDAO.cadastrarLista(listaPartidos);
	}

	@Test
	public void naoDeveCadastrarUmPartidoJaCadastrado() throws Exception {

		ArrayList<Party> listaPartidos = new ArrayList<>();

		Party party = new Party();
		party.setNumero(1);
		party.setSigla("A");
		party.setDeferimento("11.2.1982");
		party.setNome("AEIOU");
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
		p1.setNumero(45);
		p1.setSigla("PI1");
		p1.setNome("PARTIDO INEXISTENTE 1");
		p1.setDeferimento("11.8.1996");
		listaPartidos.add(p1);
		
		Party p2 = new Party();
		p2.setNumero(23);
		p2.setSigla("PI2");
		p2.setNome("PARTIDO INEXISTENTE 2");
		p2.setDeferimento("11.8.1994");
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
