package teste;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Party;
import model.dao.PartidoDAO;

import org.junit.Test;

import parse.ParseException;
import controle.PartidoControle;

public class PartidoControleTeste extends TemplateTeste {
	
	private PartidoDAO partidoDAO;
	private PartidoControle partidoControle;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.partidoControle = new PartidoControle();
		this.partidoDAO = new PartidoDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRecuperarUmaListaDePartidos() throws SQLException, ParseException {
		
		ArrayList<Party> listaPartidosACadastrar = new ArrayList<>();
		ArrayList<Party> listaPartidosRecuperados = new ArrayList<>();
		
		Party partido1 = new Party();
		partido1.setPartyName("PARTIDO EXISTENTE 1");
		partido1.setPartyAcronym("PE1");
		partido1.setPartyNumber(46);
		partido1.setPartyConcession("15.8.1996");
		listaPartidosACadastrar.add(partido1);
		
		Party partido2 = new Party();
		partido2.setPartyName("PARTIDO EXISTENTE 2");
		partido2.setPartyAcronym("PE2");
		partido1.setPartyNumber(78);
		partido1.setPartyConcession("15.4.1995");
		listaPartidosACadastrar.add(partido2);
		
		this.partidoDAO.cadastrarLista(listaPartidosACadastrar);
		listaPartidosRecuperados = this.partidoDAO.getLista();
		
		assertEquals(listaPartidosRecuperados, this.partidoControle.getListaTodosPartidos());
	}
	
	@Test
	public void deveRecuperarUmPartidoPelaSigla() throws SQLException {
		
		ArrayList<Party> listaPartidos = new ArrayList<>();
		Party partidoRecuperado = new Party();
		
		Party partido1 = new Party();
		partido1.setPartyName("PARTIDO EXISTENTE 1");
		partido1.setPartyAcronym("PE1");
		partido1.setPartyNumber(46);
		partido1.setPartyConcession("15.8.1996");
		listaPartidos.add(partido1);
		
		Party partido2 = new Party();
		partido2.setPartyName("PARTIDO EXISTENTE 2");
		partido2.setPartyAcronym("PE2");
		partido1.setPartyNumber(78);
		partido1.setPartyConcession("15.4.1995");
		listaPartidos.add(partido2);
		
		this.partidoDAO.cadastrarLista(listaPartidos);
		partidoRecuperado = this.partidoDAO.getPelaSigla("PE1");
		
		assertEquals(partidoRecuperado, this.partidoControle.getPelaSigla("PE1"));
	}
	
	@Test
	public void deveRecuperarUmPartidoPeloNumero() throws SQLException {
		
		ArrayList<Party> listaPartidos = new ArrayList<>();
		Party partidoRecuperado = new Party();
		
		Party partido1 = new Party();
		partido1.setPartyName("PARTIDO EXISTENTE 3");
		partido1.setPartyAcronym("PE3");
		partido1.setPartyNumber(47);
		partido1.setPartyConcession("15.8.1985");
		listaPartidos.add(partido1);
		
		Party partido2 = new Party();
		partido2.setPartyName("PARTIDO EXISTENTE 4");
		partido2.setPartyAcronym("PE4");
		partido1.setPartyNumber(78);
		partido1.setPartyConcession("15.5.1996");
		listaPartidos.add(partido2);
		
		this.partidoDAO.cadastrarLista(listaPartidos);
		partidoRecuperado = this.partidoDAO.getPeloNumero("47");
		
		assertEquals(partidoRecuperado, this.partidoControle.getPeloNumero("47"));
	}
	
}
