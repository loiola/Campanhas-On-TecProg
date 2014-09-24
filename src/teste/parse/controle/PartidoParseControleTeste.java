package teste.parse.controle;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlParty;
import parse.index.PartyParseIndex;
import teste.TemplateTeste;

public class PartidoParseControleTeste extends TemplateTeste {
	
	public static final int SIGLA = 0;
	public static final int NUMERO = 1;
	public static final int DEFERIMENTO = 2;
	public static final int NOME = 3;
	
	private String campo[];
	private PartyDAO partyDAO;
	private PartyParseIndex partyParseIndex;
	private ParseControlParty parseControlParty;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[4];
		this.partyDAO = new PartyDAO();
		this.partyParseIndex = new PartyParseIndex();
		this.parseControlParty = new ParseControlParty(this.partyParseIndex);
		
		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void cadastrarPartido() throws Exception {
		
		this.parseControlParty.addInstance(campo);
		this.parseControlParty.registeringInstances();
		this.parseControlParty.clear();
		
		Party partidoCadastrado = this.partyDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.campo[SIGLA], partidoCadastrado.getPartyAcronym());
		Assert.assertEquals(this.campo[NUMERO], partidoCadastrado.getPartyNumber().toString());
		Assert.assertEquals(this.campo[DEFERIMENTO], partidoCadastrado.getPartyConcession());
		Assert.assertEquals(this.campo[NOME], partidoCadastrado.getPartyName());
	}
	
	@Test
	public void naoDeveCadastrarDoisPartidosIguais() throws Exception {
		
		this.parseControlParty.addInstance(campo);
		this.parseControlParty.addInstance(campo);
		this.parseControlParty.registeringInstances();
		this.parseControlParty.clear();
		
		int numeroPartidosCadastrados = this.partyDAO.getObjectArrayListFromDatabase().size();
				
		Assert.assertEquals(1, numeroPartidosCadastrados);
	}
	
	private void iniciarIndices() {
		
		this.partyParseIndex.setIndexAcronym(SIGLA);
		this.partyParseIndex.setIndexNumberParty(NUMERO);
		this.partyParseIndex.setIndexDeferral(DEFERIMENTO);
		this.partyParseIndex.setIndexPartyName(NOME);
	}
	
	private void iniciarCampos() {
		this.campo[SIGLA] = "DEM";
		this.campo[NUMERO] = "25";
		this.campo[DEFERIMENTO] = "11.9.1986";
		this.campo[NOME] = "DEMOCRATAS";
	}
	
}
