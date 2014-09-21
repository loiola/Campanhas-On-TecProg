package teste.parse.indices;

import modelo.beans.Position;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.indices.CargoIndicesParse;

public class CargoIndicesParseTeste {
	
	public String campo[];
	public CargoIndicesParse cargoIndicesParse;
	
	@Before
	public void setUp() throws Exception {
		
		this.cargoIndicesParse = new CargoIndicesParse();
		this.campo = new String[2];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmCargoComIndicesValidos() throws Exception {
		
		Position position = new Position();
		this.cargoIndicesParse.iniciarInstancia(position, campo);
		Assert.assertEquals(this.campo[0], position.getCodigo().toString());
		Assert.assertEquals(this.campo[1], position.getDescricao());
	}
	
	@Test
	public void iniciarUmCargoComIndicesInvalidos() throws Exception {
		
		this.cargoIndicesParse = new CargoIndicesParse();
		Position position = new Position();
		this.cargoIndicesParse.iniciarInstancia(position, campo);
		Assert.assertNotEquals(this.campo[0], position.getCodigo().toString());
		Assert.assertNotEquals(this.campo[1], position.getDescricao());
	}
	
	private void iniciarIndices() {
		
		this.cargoIndicesParse.setIndiceCodigo(0);
		this.cargoIndicesParse.setIndiceDescricao(1);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "123";
		this.campo[1] = "CARGO INEXISTENTE";
	}
	
}
