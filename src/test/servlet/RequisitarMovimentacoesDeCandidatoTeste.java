package test.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import test.TemplateTest;
import control.servlet.RequestFinancialTransactionOfCandidate;

public class RequisitarMovimentacoesDeCandidatoTeste extends TemplateTest {
	
	RequestFinancialTransactionOfCandidate requestFinancialTransactionOfCandidate;
	HttpServletRequest req;
	HttpServletResponse res;

	@Test
	public void simulaServletComVerTodosFalsoEQtdPorPaginaDiferenteDeZero() throws Exception {
		when(req.getParameter("despesaTC")).thenReturn("10000");
		when(req.getParameter("receitaTC")).thenReturn("10000");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("numero_cand")).thenReturn("14789");
		when(req.getParameter("cargo_cod")).thenReturn("8");
		when(req.getParameter("uf")).thenReturn("DF");
		when(req.getParameter("inicioR")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaR")).thenReturn("10");
		when(req.getParameter("verTodosR")).thenReturn("false");
		when(req.getParameter("centroR")).thenReturn("1");
		when(req.getParameter("inicioD")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaD")).thenReturn("10");
		when(req.getParameter("verTodosD")).thenReturn("false");
		when(req.getParameter("centroD")).thenReturn("1");
		
		this.requestFinancialTransactionOfCandidate.execute(req, res);
	}
	
	@Test
	public void simulaServletComVerTodosVerdadeiroEQtdPorPaginaIgualAZero() throws Exception {
		when(req.getParameter("despesaTC")).thenReturn("10000");
		when(req.getParameter("receitaTC")).thenReturn("10000");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("numero_cand")).thenReturn("14789");
		when(req.getParameter("cargo_cod")).thenReturn("8");
		when(req.getParameter("uf")).thenReturn("DF");
		when(req.getParameter("inicioR")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaR")).thenReturn("0");
		when(req.getParameter("verTodosR")).thenReturn("true");
		when(req.getParameter("centroR")).thenReturn("1");
		when(req.getParameter("inicioD")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaD")).thenReturn("0");
		when(req.getParameter("verTodosD")).thenReturn("true");
		when(req.getParameter("centroD")).thenReturn("1");
		
		this.requestFinancialTransactionOfCandidate.execute(req, res);
	}
	
	@Test
	public void simulaServletComCandidatoInexistente() throws Exception {
		when(req.getParameter("despesaTC")).thenReturn("10000");
		when(req.getParameter("receitaTC")).thenReturn("10000");
		when(req.getParameter("ano")).thenReturn("2006");
		when(req.getParameter("numero_cand")).thenReturn("05");
		when(req.getParameter("cargo_cod")).thenReturn("8");
		when(req.getParameter("uf")).thenReturn("DF");
		when(req.getParameter("inicioR")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaR")).thenReturn("0");
		when(req.getParameter("verTodosR")).thenReturn("true");
		when(req.getParameter("centroR")).thenReturn("1");
		when(req.getParameter("inicioD")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaD")).thenReturn("0");
		when(req.getParameter("verTodosD")).thenReturn("true");
		when(req.getParameter("centroD")).thenReturn("1");
		
		this.requestFinancialTransactionOfCandidate.execute(req, res);
	}
	
	@Test
	public void simulaServletComIndiceAltoDePaginacao() throws Exception {
		when(req.getParameter("despesaTC")).thenReturn("10000");
		when(req.getParameter("receitaTC")).thenReturn("10000");
		when(req.getParameter("ano")).thenReturn("2010");
		when(req.getParameter("numero_cand")).thenReturn("13");
		when(req.getParameter("cargo_cod")).thenReturn("1");
		when(req.getParameter("uf")).thenReturn("BR");
		when(req.getParameter("inicioR")).thenReturn("130");
		when(req.getParameter("qtdPorPaginaR")).thenReturn("0");
		when(req.getParameter("verTodosR")).thenReturn("true");
		when(req.getParameter("centroR")).thenReturn("65");
		when(req.getParameter("inicioD")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaD")).thenReturn("0");
		when(req.getParameter("verTodosD")).thenReturn("true");
		when(req.getParameter("centroD")).thenReturn("1");
		
		this.requestFinancialTransactionOfCandidate.execute(req, res);
	}
	
	@Test
	public void simulaServletComIndiceAltoDePaginacaoDois() throws Exception {
		when(req.getParameter("despesaTC")).thenReturn("10000");
		when(req.getParameter("receitaTC")).thenReturn("10000");
		when(req.getParameter("ano")).thenReturn("2010");
		when(req.getParameter("numero_cand")).thenReturn("13");
		when(req.getParameter("cargo_cod")).thenReturn("1");
		when(req.getParameter("uf")).thenReturn("BR");
		when(req.getParameter("inicioR")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaR")).thenReturn("10");
		when(req.getParameter("verTodosR")).thenReturn("true");
		when(req.getParameter("centroR")).thenReturn("65");
		when(req.getParameter("inicioD")).thenReturn("0");
		when(req.getParameter("qtdPorPaginaD")).thenReturn("10");
		when(req.getParameter("verTodosD")).thenReturn("true");
		when(req.getParameter("centroD")).thenReturn("695");
		
		this.requestFinancialTransactionOfCandidate.execute(req, res);
	}
	
	
	
	@Override
	public void beforeTest() throws Exception {
		this.requestFinancialTransactionOfCandidate = new RequestFinancialTransactionOfCandidate();
		
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_OFICIAL);
		
		this.req = mock(HttpServletRequest.class);
		this.res = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
		this.databaseConnection.adjustDatabaseSchemaName(NOME_BANCO_TESTES);
	}

}
