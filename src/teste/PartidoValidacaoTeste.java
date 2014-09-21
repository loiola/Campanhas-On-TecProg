package teste;

import model.beans.Candidate;
import model.beans.Party;

import org.junit.Test;

import controle.excecao.PartidoExcecao;
import controle.validacao.PartidoValidacao;

public class PartidoValidacaoTeste extends TemplateTeste {
	
	private PartidoValidacao partidoValidacao;
	private PartidoExcecao partidoExcecao;
	private Party party;
	private Candidate candidate;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.partidoValidacao = new PartidoValidacao();
		this.partidoExcecao = new PartidoExcecao();
		this.party = new Party();
		this.candidate = new Candidate();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void naoLancaExcecaoSeSiglaNaoEhNula() throws PartidoExcecao {
		
		this.party.setPartyAcronym("PT");
		this.partidoValidacao.siglaNaoNula(party);
	}
	
	@Test
	public void naoLancaExcecaoSeSiglaNaoEhNula2() throws PartidoExcecao {
		
		this.party.setPartyAcronym("PT");
		this.partidoValidacao.numeroNaoNulo(party);
	}
	
	@Test
	public void naoLancaExcecaoSeNumeroPartidoNaoEhNulo() throws PartidoExcecao {
		
		this.party.setPartyNumber(13);
		this.partidoValidacao.numeroNaoNulo(party);
	}
	
	@Test
	public void testeMetodoEqualsParteI() throws PartidoExcecao {
		
		party.equals(null);
	}

	@Test
	public void testeMetodoEqualsParteII() throws PartidoExcecao {
		
		party.equals(party);
	}
	
	@Test
	public void testeMetodoEqualsParteIII() throws PartidoExcecao {
		
		party.equals(candidate);
	}
}
