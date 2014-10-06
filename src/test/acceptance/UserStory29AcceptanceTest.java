package test.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class UserStory29AcceptanceTest extends BasicAcceptanceTest {

	// TO DO: User Story Reference

	/**
	 * TO DO: ROADMAP
	 */

	// Constants Strings Used in This Acceptance Test
	private final String TOP_FIVE_INDEX_ID = "top5";
	private final String TOP_FIVE_CLASS_NAME = "top5";
	private final String TOP5_YEAR_NAME = "electionYear";
	private final String TOP5_2006_YEAR_SELECTION = "2006";
	private final String TOP5_POSITION_NAME = "position";
	private final String TOP5_POSITION_GOVERNOR_SELECTION = "Governador";
	private final String TOP5_BUTTON_ID = "botaoTop5";

	/**
	 * Cenário 01: Deverá haver um Top 5 de presidente na Home Page DADO QUE: o
	 * usuário entre na Home Page. QUANDO: ele visualizar a seção do Top 5.
	 * ENTÃO: deve ser exibido uma gráfico, dinâmico, com as receitas e/ou
	 * expense de um determinado candidato.
	 */
	@Test
	public void scenario01UserStory29ChartDivInIndexPage() throws Exception {
		driver.get(PROJECT_URL + INDEX_URL);
		Assert.assertTrue(driver.findElement(By.id(TOP_FIVE_INDEX_ID))
				.isEnabled());
		checkIfGoogleChartsDivIsInTheCurrentPage(WAITING_EXTENDED_TIME_FOR_VIEW);
	}

	/**
	 * Cenário 02: Um link para o gráficos de receitas e/ou expense. DADO QUE: o
	 * usuário acessar a seção Top 5. QUANDO: o usuário requisitar o gráfico.
	 * ENTÃO: deve ser exibido uma gráfico, dinâmico, com as receitas e/ou
	 * despesas.
	 */
	@Test
	public void scenario02UserStory29ChartDivInTop5Page() throws Exception {
		goToTopFivePage(WAITING_EXTENDED_TIME_FOR_VIEW);
		checkIfTopFiveWorksFine(WAITING_EXTENDED_TIME_FOR_VIEW);
	}

	/**
	 * TO DO: Description
	 * 
	 * @param waitingTime
	 * @throws Exception
	 */
	private void goToTopFivePage(long waitingTime) throws Exception {
		driver.get(PROJECT_URL + INDEX_URL);
		WebElement topFiveElement = getWebElementByClassName(TOP_FIVE_CLASS_NAME);
		Thread.sleep(waitingTime);
		topFiveElement.click();
	}

	/**
	 * TO DO: Description
	 * 
	 * @param waitingTime
	 * @throws Exception
	 */
	private void checkIfTopFiveWorksFine(long waitingTime) throws Exception {
		WebElement top5YearElement = getWebElementByName(TOP5_YEAR_NAME);
		new Select(top5YearElement)
				.selectByVisibleText(TOP5_2006_YEAR_SELECTION);
		WebElement top5PositionElement = getWebElementByName(TOP5_POSITION_NAME);
		new Select(top5PositionElement)
				.selectByVisibleText(TOP5_POSITION_GOVERNOR_SELECTION);
		WebElement top5ButtonElement = getWebElementByID(TOP5_BUTTON_ID);
		top5ButtonElement.click();
		checkIfGoogleChartsDivIsInTheCurrentPage(WAITING_EXTENDED_TIME_FOR_VIEW);
	}
}