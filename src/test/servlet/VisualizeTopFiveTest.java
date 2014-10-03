package test.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import test.TemplateTest;
import control.servlet.VisualizeTopFive;

public class VisualizeTopFiveTest extends TemplateTest {

	VisualizeTopFive visualizeTopFive;
	HttpServletRequest requestServlet;
	HttpServletResponse responseServlet;
	
	private final String PRESIDENT_DATABASE_TAG = "presidente";
	private final String CURRENT_ELECTION_YEAR = "2002";

	@Test
	public void mockServletWithPresidentPositionAnd2002ElectionYear() throws Exception {
		when(requestServlet.getParameter("position")).thenReturn(PRESIDENT_DATABASE_TAG);
		when(requestServlet.getParameter("electionYear")).thenReturn(CURRENT_ELECTION_YEAR);

		this.visualizeTopFive.execute(requestServlet, responseServlet);
	}

	@Override
	public void beforeTest() throws Exception {
		this.visualizeTopFive = new VisualizeTopFive();
		
		this.databaseConnection.adjustDatabaseSchemaName(OFICIAL_DATABASE_NAME);
		
		this.requestServlet = mock(HttpServletRequest.class);
		this.responseServlet = mock(HttpServletResponse.class);
	}

	@Override
	public void afterTest() throws Exception {
		this.databaseConnection.adjustDatabaseSchemaName(TEST_DATABASE_NAME);
	}
}
