package teste.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Position;
import modelo.dao.CargoDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class CargoDAOTeste extends TemplateTeste {

	private CargoDAO cargoDAO;

	@Override
	public void beforeTest() throws Exception {
		
		this.cargoDAO = new CargoDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRecuperarUmCargoPeloCodigo() throws SQLException {

		ArrayList<Position> listaCargos = new ArrayList<>();
		Position cargoRecuperado = new Position();

		Position c1 = new Position();
		c1.setPositionCode(1);
		c1.setPositionDescription("CARGO UM");
		listaCargos.add(c1);

		Position c2 = new Position();
		c2.setPositionCode(2);
		c2.setPositionDescription("CARGO DOIS");
		listaCargos.add(c2);

		this.cargoDAO.cadastrarLista(listaCargos);

		cargoRecuperado = this.cargoDAO.getPeloCod(1);
		Assert.assertEquals(c1, cargoRecuperado);
	}
	
	@Test
	public void deveRecuperarUmCargoPelaDescricao() throws SQLException {

		ArrayList<Position> listaCargos = new ArrayList<>();
		Position cargoRecuperado = new Position();

		Position c1 = new Position();
		c1.setPositionCode(1);
		c1.setPositionDescription("CARGO UM");
		listaCargos.add(c1);

		Position c2 = new Position();
		c2.setPositionCode(2);
		c2.setPositionDescription("CARGO DOIS");
		listaCargos.add(c2);

		this.cargoDAO.cadastrarLista(listaCargos);

		cargoRecuperado = this.cargoDAO.getPelaDescricao("CARGO UM");
		Assert.assertEquals(c1, cargoRecuperado);
	}
	
	@Test
	public void deveRecuperarUmaListaDeCargos() throws SQLException {
		
		ArrayList<Position> listaCargos = new ArrayList<>();
		ArrayList<Position> listaRecuperada = new ArrayList<>();
		
		Position c1 = new Position();
		c1.setPositionCode(1);
		c1.setPositionDescription("CARGO UM");
		listaCargos.add(c1);

		Position c2 = new Position();
		c2.setPositionCode(2);
		c2.setPositionDescription("CARGO DOIS");
		listaCargos.add(c2);
		
		Position c3 = new Position();
		c3.setPositionCode(3);
		c3.setPositionDescription("CARGO TRÊS");
		listaCargos.add(c3);
		
		this.cargoDAO.cadastrarLista(listaCargos);
		listaRecuperada = this.cargoDAO.getLista();
		
		Assert.assertEquals(listaRecuperada, this.cargoDAO.getLista());
	}

	@Test
	public void valoresComparacao() throws Exception {

		Position c1 = new Position();
		Position c2 = new Position();
		c1.setPositionCode(1);
		c2.setPositionCode(2);
		int resultado;

		resultado = CargoDAO.Comparacao.CODIGO.compare(c1, c2);

		Assert.assertNotEquals(0, resultado);
	}

}
