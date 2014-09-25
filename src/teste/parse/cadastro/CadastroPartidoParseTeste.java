package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Party;
import model.dao.PartyDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.party.RegisterToParseParty;
import teste.TemplateTeste;

public class CadastroPartidoParseTeste extends TemplateTeste {

	String  tipoArquivoA = "partido";
	String  tipoArquivoB = "campanha";
	String  tipoArquivoC = "errado";
	String  ano1         = "2002";
	String  ano2         = "2006";
	String  ano3         = "2010";
	PartyDAO partyDAO;
	private RegisterToParseParty cadastro1;
	private RegisterToParseParty cadastro2;
	private RegisterToParseParty cadastro3;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro3 = new RegisterToParseParty(this.tipoArquivoC, this.ano1);
		this.partyDAO = new PartyDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmPartidoCadastradoPara2002PorPartido() throws ParseException, SQLException {
		
		this.cadastro1 = new RegisterToParseParty(this.tipoArquivoA, this.ano1);
		
		String campo[] = new String[50];
		campo[2] = "Nome";
		campo[1] = "Sigla";
		campo[5] = "123";
		campo[3] = "1234";
		cadastro1.runFileLine(campo);
		cadastro1.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
		
	}
	
	@Test
	public void deveRetornarUmPartidoCadastradoPara2002PorCampanha() throws ParseException, SQLException {
		
		this.cadastro2 = new RegisterToParseParty(this.tipoArquivoB, this.ano1);
		
		String campo[] = new String[50];
		campo[18] = "Nome";
		campo[17] = "Sigla";
		campo[16] = "123";
		cadastro2.runFileLine(campo);
		cadastro2.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void deveRetornarUmPartidoCadastradoPara2006PorPartido() throws ParseException, SQLException {
		
		this.cadastro1 = new RegisterToParseParty(this.tipoArquivoA, this.ano2);
		
		String campo[] = new String[50];
		campo[2] = "Nome";
		campo[1] = "Sigla";
		campo[5] = "123";
		campo[3] = "1234";
		cadastro1.runFileLine(campo);
		cadastro1.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void deveRetornarUmPartidoCadastradoPara2006PorCampanha() throws ParseException, SQLException {
		
		this.cadastro2 = new RegisterToParseParty(this.tipoArquivoB, this.ano2);
		
		String campo[] = new String[50];
		campo[18] = "Nome";
		campo[17] = "Sigla";
		campo[16] = "123";
		cadastro2.runFileLine(campo);
		cadastro2.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void deveRetornarUmPartidoCadastradoPara2010PorPartido() throws ParseException, SQLException {
		
		this.cadastro1 = new RegisterToParseParty(this.tipoArquivoA, this.ano3);
		
		String campo[] = new String[50];
		campo[2] = "Nome";
		campo[1] = "Sigla";
		campo[5] = "123";
		campo[3] = "1234";
		cadastro1.runFileLine(campo);
		cadastro1.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void deveRetornarUmPartidoCadastradoPara2010PorCampanha() throws ParseException, SQLException {
		
		this.cadastro2 = new RegisterToParseParty(this.tipoArquivoB, this.ano3);
		
		String campo[] = new String[50];
		campo[18] = "Nome";
		campo[17] = "Sigla";
		campo[16] = "123";
		cadastro2.runFileLine(campo);
		cadastro2.registerInstances();
		
		Party party = this.partyDAO.getPartyByNumber("123");
		assertEquals(party.getPartyNumber().toString(), "123");
	}
	
	@Test
	public void deveRetornarUmPartidoIndicesParseVazioQuandoPassadoUmArquivoComNomeInvalido() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		cadastro3.runFileLine(campo);
		cadastro3.registerInstances();
	}

}
