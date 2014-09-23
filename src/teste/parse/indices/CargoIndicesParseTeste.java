package teste.parse.indices;

import model.beans.Position;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.index.PositionParseIndex;

public class CargoIndicesParseTeste {
	
	public String campo[];
	public PositionParseIndex positionParseIndex;
	
	@Before
	public void setUp() throws Exception {
		
		this.positionParseIndex = new PositionParseIndex();
		this.campo = new String[2];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmCargoComIndicesValidos() throws Exception {
		
		Position position = new Position();
		this.positionParseIndex.startInstance(position, campo);
		Assert.assertEquals(this.campo[0], position.getPositionCode().toString());
		Assert.assertEquals(this.campo[1], position.getPositionDescription());
	}
	
	@Test
	public void iniciarUmCargoComIndicesInvalidos() throws Exception {
		
		this.positionParseIndex = new PositionParseIndex();
		Position position = new Position();
		this.positionParseIndex.startInstance(position, campo);
		Assert.assertNotEquals(this.campo[0], position.getPositionCode().toString());
		Assert.assertNotEquals(this.campo[1], position.getPositionDescription());
	}
	
	private void iniciarIndices() {
		
		this.positionParseIndex.setIndiceCodigo(0);
		this.positionParseIndex.setIndiceDescricao(1);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "123";
		this.campo[1] = "CARGO INEXISTENTE";
	}
	
}
