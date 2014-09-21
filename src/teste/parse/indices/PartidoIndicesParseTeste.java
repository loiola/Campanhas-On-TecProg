package teste.parse.indices;

import model.beans.Party;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parse.indices.PartidoIndicesParse;

public class PartidoIndicesParseTeste {
		
	private String campo[];
	private PartidoIndicesParse partidoIndicesParse;

	@Before
	public void setUp() throws Exception {
		
		this.partidoIndicesParse = new PartidoIndicesParse();
		this.campo = new String[4];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmPartidoComIndicesValidos() throws Exception {
		
		Party party = new Party();
		this.partidoIndicesParse.iniciarInstancia(party, campo);
		Assert.assertEquals(this.campo[0], party.getPartyAcronym());
		Assert.assertEquals(this.campo[1], party.getPartyNumber().toString());
		Assert.assertEquals(this.campo[2], party.getPartyConcession());
		Assert.assertEquals(this.campo[3], party.getPartyName());
	}
	
	@Test
	public void iniciarUmPartidoComIndicesInvalidos() throws Exception {
		
		this.partidoIndicesParse = new PartidoIndicesParse();
		Party party = new Party();
		this.partidoIndicesParse.iniciarInstancia(party, campo);
		Assert.assertNotEquals(this.campo[0], party.getPartyAcronym());
		Assert.assertNotEquals(this.campo[1], party.getPartyNumber().toString());
		Assert.assertNotEquals(this.campo[2], party.getPartyConcession());
		Assert.assertNotEquals(this.campo[3], party.getPartyName());
	}
	
	private void iniciarIndices() {
		
		this.partidoIndicesParse.setIndiceSigla(0);
		this.partidoIndicesParse.setIndiceNumero(1);
		this.partidoIndicesParse.setIndiceDeferimento(2);
		this.partidoIndicesParse.setIndiceNome(3);
	}
	
	private void iniciarCampos() {
		
		this.campo[0] = "DEM";
		this.campo[1] = "25";
		this.campo[2] = "11.9.1986";
		this.campo[3] = "DEMOCRATAS";
	}
	
}
