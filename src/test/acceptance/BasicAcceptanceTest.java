package test.acceptance;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BasicAcceptanceTest {

	// Firefox Version need to be 28 or less
	protected WebDriver driver = new FirefoxDriver();

	protected final String PROJECT_URL = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
	protected final String INDEX_URL = "index.jsp";

	protected final long WAITING_TIME_FOR_TEST_RESPONSE = 7;
	protected final long WAITING_NORMAL_TIME_FOR_VIEW = 1000;
	protected final long WAITING_EXTENDED_TIME_FOR_VIEW = 2000;
	protected final long WAITING_SHORT_TIME_FOR_VIEW = 500;

	protected final String CANDIDATE_HEADER_CLASS_NAME = "candidatos";
	protected final String POLITICAL_PARTY_HEADER_CLASS_NAME = "partidos";
	protected final String SEE_ALL_LIST_LINKTEXT = "Ver Todos";
	
	@Before
	public void setUp() throws Exception {
		driver.manage()
				.timeouts()
				.implicitlyWait(WAITING_TIME_FOR_TEST_RESPONSE,
						TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	protected WebElement getWebElementByName(String webElementName) {
		WebElement webElementToBeFound = driver.findElement(By
				.name(webElementName));
		Assert.assertTrue(webElementToBeFound.isDisplayed());
		return webElementToBeFound;
	}

	protected WebElement getWebElementByClassName(String webElementClassName) {
		WebElement webElementToBeFound = driver.findElement(By
				.className(webElementClassName));
		Assert.assertTrue(webElementToBeFound.isDisplayed());
		return webElementToBeFound;
	}

	protected WebElement getWebElementByID(String webElementID) {
		WebElement webElementToBeFound = driver
				.findElement(By.id(webElementID));
		Assert.assertTrue(webElementToBeFound.isDisplayed());
		return webElementToBeFound;
	}

	protected WebElement getWebElementByLinkText(String webElementLinkText) {
		WebElement webElementToBeFound = driver.findElement(By
				.linkText(webElementLinkText));
		Assert.assertTrue(webElementToBeFound.isDisplayed());
		return webElementToBeFound;
	}

	protected void writeInWebElement(String keysToWriteInWebElement,
			WebElement webElement, long waitingTime)
			throws Exception {
		Assert.assertTrue(webElement.isDisplayed());
		webElement.clear();
		webElement.sendKeys(keysToWriteInWebElement);
		Thread.sleep(waitingTime);
	}

	protected void goToCandidateSearchingPage(long waitingTime)
			throws Exception {	
		driver.get(PROJECT_URL + INDEX_URL);
		
		WebElement candidateHeaderElement = getWebElementByClassName(CANDIDATE_HEADER_CLASS_NAME);
		Thread.sleep(waitingTime);
		candidateHeaderElement.click();
	}
	
	protected void goToPoliticalPartyListPage(long waitingTime)
			throws Exception {	
		driver.get(PROJECT_URL + INDEX_URL);
		
		WebElement politicalPartyHeaderElement = getWebElementByClassName(POLITICAL_PARTY_HEADER_CLASS_NAME);
		Thread.sleep(waitingTime);
		politicalPartyHeaderElement.click();
	}
	
	protected void goToPoliticalPartyPageInPoliticalPartyListPage(String politicalPartyLinkText, long waitingTime) throws Exception {
		WebElement democratasPoliticalPartyElement = getWebElementByLinkText(politicalPartyLinkText);
		democratasPoliticalPartyElement.click();
		Thread.sleep(waitingTime);
	}
	
	protected void goToPoliticalCandidatesListPageFromCampaignYearInPoliticalPartyPage(String campaignYearLinkText, long waitingTime) throws Exception {
		WebElement campaignYear2010Element = getWebElementByLinkText(campaignYearLinkText);
		campaignYear2010Element.click();
		Thread.sleep(waitingTime);
	}
	
	protected void goToPoliticalCandidatesListPage(String politicalPartyLinkText, String campaignYearLinkText, long waitingTime) throws Exception {
		goToPoliticalPartyListPage(waitingTime);
		goToPoliticalPartyPageInPoliticalPartyListPage(politicalPartyLinkText, waitingTime);
		goToPoliticalCandidatesListPageFromCampaignYearInPoliticalPartyPage(campaignYearLinkText, waitingTime);
	}
	
	protected void clickInSeeAllList(long waitingTime) throws Exception {
		Thread.sleep(waitingTime);
		WebElement SEE_ALL_LIST_ELEMENT = getWebElementByLinkText(SEE_ALL_LIST_LINKTEXT);
		SEE_ALL_LIST_ELEMENT.click();
	}
	
}
