package test.acceptance;

import org.junit.Test;
import org.openqa.selenium.WebElement;

public class UserStories03And27AcceptanceTest extends BasicAcceptanceTest {

	// TO DO: User Story Reference

	/**
	 * TO DO: ROADMAP
	 */

	// Constants Strings Used in This Acceptance Test
	private final String DEMOCRATAS_POLITICAL_PARTY_LINKTEXT = "DEMOCRATAS";
	private final String CAMPAIGN_YEAR_2010_LINKTEXT = "2010";
	private final String CANDIDATE_EXPECTED_LINKTEXT = "ADELMIR ARAUJO SANTANA";

	/**
	 * Cenário Único: Busca de Candidatos. DADO QUE: o usuário já acessou o
	 * perfil do partido desejado. QUANDO: o usuário clicar em um determinado
	 * ano, através de um link. ENTÃO: deve ser exibida uma lista de candidatos
	 * que concorreram naquele ano através do partido selecionado. E: o nome dos
	 * candidatos devem aparecer na forma de links que direcionem ao perfil do
	 * mesmo
	 */
	@Test
	public void userStories03and27ReachingCandidateProfileByPoliticalPartyWay() throws Exception {
		goToPoliticalCandidatesListPage(DEMOCRATAS_POLITICAL_PARTY_LINKTEXT,
				CAMPAIGN_YEAR_2010_LINKTEXT, WAITING_EXTENDED_TIME_FOR_VIEW);
		goToDemocratasCandidateAdelmir(WAITING_EXTENDED_TIME_FOR_VIEW);
	}

	/**
	 * TO DO: Description
	 */
	private void goToDemocratasCandidateAdelmir(long waitingTime) throws Exception {
		WebElement candidateExpectedElement = getWebElementByLinkText(CANDIDATE_EXPECTED_LINKTEXT);
		candidateExpectedElement.click();
		Thread.sleep(waitingTime);
	}

}