package teste.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import teste.TemplateTeste;
import control.servlet.RequisitarPartido;

public class RequisitarPartidoTeste extends TemplateTeste {
	
	RequisitarPartido requisitarPartido;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComVerTodosFalsoEQtdPorPaginaDiferenteDeZero() throws Exception {
		when(req.getParameter("inicio")).thenReturn("0");
		when(req.getParameter("qtdPorPagina")).thenReturn("10");
		when(req.getParameter("verTodos")).thenReturn("false");
		
		this.requisitarPartido.execute(req, res);
	}
	
	@Test
	public void simulaServletComVerTodosVerdadeiroEQtdPorPaginaIgualAZero() throws Exception {
		when(req.getParameter("inicio")).thenReturn("0");
		when(req.getParameter("qtdPorPagina")).thenReturn("0");
		when(req.getParameter("verTodos")).thenReturn("true");
		
		this.requisitarPartido.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.requisitarPartido = new RequisitarPartido();
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
	}

}
