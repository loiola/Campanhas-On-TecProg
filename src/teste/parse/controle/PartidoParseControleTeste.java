package teste.parse.controle;

import modelo.beans.Party;
import modelo.dao.PartidoDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.controle.PartidoParseControle;
import parse.indices.PartidoIndicesParse;
import teste.TemplateTeste;

public class PartidoParseControleTeste extends TemplateTeste {
	
	public static final int SIGLA = 0;
	public static final int NUMERO = 1;
	public static final int DEFERIMENTO = 2;
	public static final int NOME = 3;
	
	private String campo[];
	private PartidoDAO partidoDAO;
	private PartidoIndicesParse partidoIndicesParse;
	private PartidoParseControle partidoParseControle;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[4];
		this.partidoDAO = new PartidoDAO();
		this.partidoIndicesParse = new PartidoIndicesParse();
		this.partidoParseControle = new PartidoParseControle(this.partidoIndicesParse);
		
		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void cadastrarPartido() throws Exception {
		
		this.partidoParseControle.addInstancia(campo);
		this.partidoParseControle.cadastrarInstancias();
		this.partidoParseControle.resetar();
		
		Party partidoCadastrado = this.partidoDAO.getLista().get(0);
				
		Assert.assertEquals(this.campo[SIGLA], partidoCadastrado.getSigla());
		Assert.assertEquals(this.campo[NUMERO], partidoCadastrado.getNumero().toString());
		Assert.assertEquals(this.campo[DEFERIMENTO], partidoCadastrado.getDeferimento());
		Assert.assertEquals(this.campo[NOME], partidoCadastrado.getNome());
	}
	
	@Test
	public void naoDeveCadastrarDoisPartidosIguais() throws Exception {
		
		this.partidoParseControle.addInstancia(campo);
		this.partidoParseControle.addInstancia(campo);
		this.partidoParseControle.cadastrarInstancias();
		this.partidoParseControle.resetar();
		
		int numeroPartidosCadastrados = this.partidoDAO.getLista().size();
				
		Assert.assertEquals(1, numeroPartidosCadastrados);
	}
	
	private void iniciarIndices() {
		
		this.partidoIndicesParse.setIndiceSigla(SIGLA);
		this.partidoIndicesParse.setIndiceNumero(NUMERO);
		this.partidoIndicesParse.setIndiceDeferimento(DEFERIMENTO);
		this.partidoIndicesParse.setIndiceNome(NOME);
	}
	
	private void iniciarCampos() {
		this.campo[SIGLA] = "DEM";
		this.campo[NUMERO] = "25";
		this.campo[DEFERIMENTO] = "11.9.1986";
		this.campo[NOME] = "DEMOCRATAS";
	}
	
}
