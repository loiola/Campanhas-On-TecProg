package teste.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import teste.TemplateTeste;
import control.servlet.VisualizarCandidatosPartido;

public class VisualizarCandidatosPartidoTeste extends TemplateTeste {
	
	VisualizarCandidatosPartido visualizarCandidatosPartido;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComVerTodosFalsoEQtdPorPaginaDiferenteDeZero() throws Exception {
		when(req.getParameter("sigla")).thenReturn("SD");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("inicio")).thenReturn("0");
		when(req.getParameter("qtdPorPagina")).thenReturn("10");
		when(req.getParameter("verTodos")).thenReturn("false");
		
		this.visualizarCandidatosPartido.execute(req, res);
	}
	
	@Test
	public void simulaServletComVerTodosVerdadeiroEQtdPorPaginaIgualAZero() throws Exception {
		when(req.getParameter("sigla")).thenReturn("SD");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("inicio")).thenReturn("0");
		when(req.getParameter("qtdPorPagina")).thenReturn("0");
		when(req.getParameter("verTodos")).thenReturn("true");
		
		this.visualizarCandidatosPartido.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.visualizarCandidatosPartido = new VisualizarCandidatosPartido();
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
	}
}
