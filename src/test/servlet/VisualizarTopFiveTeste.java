package test.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import test.TemplateTest;
import control.servlet.VisualizeTopFive;

public class VisualizarTopFiveTeste extends TemplateTest {

	VisualizeTopFive visualizeTopFive;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComSiglaDiferenteDeZero() throws Exception {
		when(req.getParameter("cargo")).thenReturn("presidente");
		when(req.getParameter("ano")).thenReturn("2002");

		this.visualizeTopFive.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.visualizeTopFive = new VisualizeTopFive();
		
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_OFICIAL);
		
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_TESTES);
	}
}
