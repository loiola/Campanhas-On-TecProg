package test.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import test.TemplateTest;
import control.servlet.VisualizeCandidateOfPoliticalParty;

public class VisualizarCandidatosPartidoTeste extends TemplateTest {
	
	VisualizeCandidateOfPoliticalParty visualizeCandidateOfPoliticalParty;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComVerTodosFalsoEQtdPorPaginaDiferenteDeZero() throws Exception {
		when(req.getParameter("sigla")).thenReturn("SD");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("inicio")).thenReturn("0");
		when(req.getParameter("qtdPorPagina")).thenReturn("10");
		when(req.getParameter("verTodos")).thenReturn("false");
		
		this.visualizeCandidateOfPoliticalParty.execute(req, res);
	}
	
	@Test
	public void simulaServletComVerTodosVerdadeiroEQtdPorPaginaIgualAZero() throws Exception {
		when(req.getParameter("sigla")).thenReturn("SD");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("inicio")).thenReturn("0");
		when(req.getParameter("qtdPorPagina")).thenReturn("0");
		when(req.getParameter("verTodos")).thenReturn("true");
		
		this.visualizeCandidateOfPoliticalParty.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.visualizeCandidateOfPoliticalParty = new VisualizeCandidateOfPoliticalParty();
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
	}
}
