package teste;

import modelo.beans.Partido;
import modelo.beans.Candidate;
import org.junit.Test;
import controle.excecao.PartidoExcecao;
import controle.validacao.PartidoValidacao;

public class PartidoValidacaoTeste extends TemplateTeste {
	
	private PartidoValidacao partidoValidacao;
	private PartidoExcecao partidoExcecao;
	private Partido partido;
	private Candidate candidate;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.partidoValidacao = new PartidoValidacao();
		this.partidoExcecao = new PartidoExcecao();
		this.partido = new Partido();
		this.candidate = new Candidate();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void naoLancaExcecaoSeSiglaNaoEhNula() throws PartidoExcecao {
		
		this.partido.setSigla("PT");
		this.partidoValidacao.siglaNaoNula(partido);
	}
	
	@Test
	public void naoLancaExcecaoSeSiglaNaoEhNula2() throws PartidoExcecao {
		
		this.partido.setSigla("PT");
		this.partidoValidacao.numeroNaoNulo(partido);
	}
	
	@Test
	public void naoLancaExcecaoSeNumeroPartidoNaoEhNulo() throws PartidoExcecao {
		
		this.partido.setNumero(13);
		this.partidoValidacao.numeroNaoNulo(partido);
	}
	
	@Test
	public void testeMetodoEqualsParteI() throws PartidoExcecao {
		
		partido.equals(null);
	}

	@Test
	public void testeMetodoEqualsParteII() throws PartidoExcecao {
		
		partido.equals(partido);
	}
	
	@Test
	public void testeMetodoEqualsParteIII() throws PartidoExcecao {
		
		partido.equals(candidate);
	}
}
