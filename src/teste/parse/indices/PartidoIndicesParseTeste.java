package teste.parse.indices;

import modelo.beans.Partido;

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
		this.campo = new String[2];
		
		iniciarCampos();
		iniciarIndices();
	}

	@Test
	public void iniciarUmPartidoComIndicesValidos() throws Exception {
		Partido partido = new Partido();
		this.partidoIndicesParse.iniciarInstancia(partido, campo);
		Assert.assertEquals(this.campo[0], partido.getSigla());
		Assert.assertEquals(this.campo[1], partido.getNumeroPartido());
	}
	
	@Test
	public void iniciarUmCandidatoComIndicesInvalidos() throws Exception {
		this.partidoIndicesParse = new PartidoIndicesParse();
		Partido partido = new Partido();
		this.partidoIndicesParse.iniciarInstancia(partido, campo);
		Assert.assertNotEquals(this.campo[0], partido.getSigla());
		Assert.assertNotEquals(this.campo[1], partido.getNumeroPartido());
	}
	
	private void iniciarIndices() {
		this.partidoIndicesParse.setIndiceSigla(0);
		this.partidoIndicesParse.setIndiceNumeroPartido(1);
	}
	
	private void iniciarCampos() {
		this.campo[0] = "AB";
		this.campo[1] = "1";
	}
}