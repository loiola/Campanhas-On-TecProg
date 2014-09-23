package teste.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import teste.TemplateTeste;
import control.servlet.VisualizarTopFive;

public class VisualizarTopFiveTeste extends TemplateTeste {

	VisualizarTopFive visualizarTopFive;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComSiglaDiferenteDeZero() throws Exception {
		when(req.getParameter("cargo")).thenReturn("presidente");
		when(req.getParameter("ano")).thenReturn("2002");

		this.visualizarTopFive.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.visualizarTopFive = new VisualizarTopFive();
		
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_OFICIAL);
		
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_TESTES);
	}
}
