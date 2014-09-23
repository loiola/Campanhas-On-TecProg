package teste;

import model.beans.Candidate;
import model.beans.Party;

import org.junit.Test;

import control.exception.PartyException;
import control.validation.PartyValidation;

public class PartidoValidacaoTeste extends TemplateTeste {
	
	private PartyValidation partyValidation;
	private PartyException partyException;
	private Party party;
	private Candidate candidate;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.partyValidation = new PartyValidation();
		this.partyException = new PartyException();
		this.party = new Party();
		this.candidate = new Candidate();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void naoLancaExcecaoSeSiglaNaoEhNula() throws PartyException {
		
		this.party.setPartyAcronym("PT");
		this.partyValidation.siglaNaoNula(party);
	}
	
	@Test
	public void naoLancaExcecaoSeSiglaNaoEhNula2() throws PartyException {
		
		this.party.setPartyAcronym("PT");
		this.partyValidation.numeroNaoNulo(party);
	}
	
	@Test
	public void naoLancaExcecaoSeNumeroPartidoNaoEhNulo() throws PartyException {
		
		this.party.setPartyNumber(13);
		this.partyValidation.numeroNaoNulo(party);
	}
	
	@Test
	public void testeMetodoEqualsParteI() throws PartyException {
		
		party.equals(null);
	}

	@Test
	public void testeMetodoEqualsParteII() throws PartyException {
		
		party.equals(party);
	}
	
	@Test
	public void testeMetodoEqualsParteIII() throws PartyException {
		
		party.equals(candidate);
	}
}
