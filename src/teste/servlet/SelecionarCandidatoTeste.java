package teste.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import teste.TemplateTeste;
import control.servlet.SelectCandidate;

public class SelecionarCandidatoTeste extends TemplateTeste {

	SelectCandidate selectCandidate;
	HttpServletRequest req;
	HttpServletResponse res;
	
	@Test
	public void simulaServletComAno2002() throws Exception {
		when(req.getParameter("tituloEleitoral")).thenReturn("001364742003");
		
		this.selectCandidate.execute(req, res);
	}

	@Test
	public void simulaServletComAno2006() throws Exception {
		when(req.getParameter("tituloEleitoral")).thenReturn("002143582054");
		
		this.selectCandidate.execute(req, res);
	}
	
	@Test
	public void simulaServletComAno2010() throws Exception {
		when(req.getParameter("tituloEleitoral")).thenReturn("007235102003");
		
		this.selectCandidate.execute(req, res);
	}

	@Override
	public void beforeTest() throws Exception {
		this.selectCandidate = new SelectCandidate();
		
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_OFICIAL);
		
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_TESTES);
	}
}
