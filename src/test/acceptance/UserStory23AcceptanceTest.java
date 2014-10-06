package test.acceptance;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class UserStory23AcceptanceTest extends BasicAcceptanceTest {

	// TO DO: User Story Reference

	/**
	 * TO DO: ROADMAP
	 */

	// Constants Strings Used in This Acceptance Test
	private final String DEMOCRATAS_POLITICAL_PARTY_LINKTEXT = "DEMOCRATAS";
	private final String TSE_EXPECTED_LINKTEXT = "clique aqui";
	private final String TSE_EXPECTED_URL = "http://www.tse.jus.br/partidos/partidos-politicos/democratas";

	/**
	 * Cenário Único: O link para o TSE existe.
	 * 
	 * 1 - Na página de perfil de um partido, deve haver um link para o
	 * endereço:
	 * http://www.tse.jus.br/partidos/partidos-politicos/&lt;nome-do-partido>
	 * 
	 * 2 - O link, ao ser acessado, deve achar a página do TSE, com informações
	 * do partido como, por exemplo, seu estatuto e suas normas.
	 */
	@Test
	public void userStory23VerifyingDemocratasTSELinkByPoliticalPartyPage() throws Exception {
		goToPoliticalPartyListPage(WAITING_NORMAL_TIME_FOR_VIEW);
		goToPoliticalPartyPageInPoliticalPartyListPage(DEMOCRATAS_POLITICAL_PARTY_LINKTEXT, WAITING_EXTENDED_TIME_FOR_VIEW);
		checkIfTSEWorksFine(WAITING_EXTENDED_TIME_FOR_VIEW);
	}
	
	/**
	 * TO DO: Description
	 * @param waitingTime
	 * @throws Exception
	 */
	private void checkIfTSEWorksFine(long waitingTime) throws Exception {
		WebElement tseExpectedLinkText = getWebElementByLinkText(TSE_EXPECTED_LINKTEXT);
		tseExpectedLinkText.click();
		
		ArrayList<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1));
		Assert.assertEquals(driver.getCurrentUrl(), TSE_EXPECTED_URL);
		Thread.sleep(WAITING_EXTENDED_TIME_FOR_VIEW);
	}
}