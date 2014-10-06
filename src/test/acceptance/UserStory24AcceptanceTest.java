package test.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class UserStory24AcceptanceTest extends BasicAcceptanceTest {

	// TO DO: User Story Reference

	/**
	 * TO DO: ROADMAP
	 */

	// Constants Strings Used in This Acceptance Test
	private final String DEMOCRATAS_POLITICAL_PARTY_LINKTEXT = "DEMOCRATAS";
	private final String CAMPAIGN_YEAR_2010_LINKTEXT = "2010";
	private final String CANDIDATE_EXPECTED_LINKTEXT = "ROBERTO EDUARDO VENTURA GIFFONI";
	private final String CANDIDATE_CAMPAIGN_YEAR_2010_LINKTEXT = "Campanha de 2010";
	private final String EXPECTED_TOTAL_VALUE_TEXT = "Saldo da Campanha";
	private final String EXPECTED_TOTAL_EXPENSE_CALCULATED_TEXT = "Despesa Total Calculada:";
	private final String EXPECTED_TOTAL_REVENUE_CALCULATED_TEXT = "Receita Total Calculada:";

	/**
	 * Cenário Único: Busca de Candidatos.
	 * 
	 * 1 - Na página de receitas e despesas, ao final da listagem, o valor total
	 * de receitas e despesas deve ser mostrado.
	 * 
	 * 2 - O saldo final da campanha (Saldo = Receita - Despesa) também deve ser
	 * exibido ao final da página.
	 */
	@Test
	public void userStory24VerifyingTransactionsFromCandidate() throws Exception {
		goToCandidatePageInPoliticalPartyWay(
				DEMOCRATAS_POLITICAL_PARTY_LINKTEXT,
				CAMPAIGN_YEAR_2010_LINKTEXT, CANDIDATE_EXPECTED_LINKTEXT,
				WAITING_EXTENDED_TIME_FOR_VIEW);
		goToCandidateCampaignPageInCandidatePage(
				CANDIDATE_CAMPAIGN_YEAR_2010_LINKTEXT,
				WAITING_EXTENDED_TIME_FOR_VIEW);
		checkRevenueAndExpenseValuesAreInThePage(WAITING_EXTENDED_TIME_FOR_VIEW);
	}

	/**
	 * TO DO: Description
	 * @param candidateCampaignYearLinkText
	 * @param waitingTime
	 * @throws Exception
	 */
	private void goToCandidateCampaignPageInCandidatePage(
			String candidateCampaignYearLinkText, long waitingTime)
			throws Exception {
		WebElement candidateCampaignYearElement = getWebElementByLinkText(candidateCampaignYearLinkText);
		candidateCampaignYearElement.click();
		Thread.sleep(waitingTime);
	}

	/**
	 * TO DO: Description
	 * @param waitingTime
	 * @throws Exception
	 */
	private void checkRevenueAndExpenseValuesAreInThePage(long waitingTime)
			throws Exception {
		Assert.assertTrue(driver.getPageSource().contains(
				EXPECTED_TOTAL_VALUE_TEXT));
		Assert.assertTrue(driver.getPageSource().contains(
				EXPECTED_TOTAL_REVENUE_CALCULATED_TEXT));
		Assert.assertTrue(driver.getPageSource().contains(
				EXPECTED_TOTAL_EXPENSE_CALCULATED_TEXT));
		Thread.sleep(waitingTime);
	}
}