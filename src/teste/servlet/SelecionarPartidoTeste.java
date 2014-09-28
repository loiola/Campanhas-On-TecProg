package teste.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import teste.TemplateTest;
import control.servlet.SelectPoliticalParty;

public class SelecionarPartidoTeste extends TemplateTest {

	SelectPoliticalParty selectPoliticalParty;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComSiglaDiferenteDeZero() throws Exception {
		when(req.getParameter("sigla")).thenReturn("SD");

		this.selectPoliticalParty.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.selectPoliticalParty = new SelectPoliticalParty();
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
	}
}
