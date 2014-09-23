package teste.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import teste.TemplateTeste;
import control.servlet.SelecionarPartido;

public class SelecionarPartidoTeste extends TemplateTeste {

	SelecionarPartido selecionarPartido;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComSiglaDiferenteDeZero() throws Exception {
		when(req.getParameter("sigla")).thenReturn("SD");

		this.selecionarPartido.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.selecionarPartido = new SelecionarPartido();
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
	}
}
