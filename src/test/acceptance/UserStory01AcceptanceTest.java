package test.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class UserStory01AcceptanceTest extends BasicAcceptanceTest {

	// TO DO: User Story Reference
	
	/**
	 * Roadmap Pages in This Acceptance Test:
	 * index.jsp (header)
	 * 	-> request_candidate.jsp
	 * 		-> visualize_list_candidates.jsp (Scenario 1)
	 * 		-> error_candidate_nonexistent.jsp (Scenario 2)
	 */
	
	// Constants Strings Used in This Acceptance Test
	private final String CANDIDATE_TEXTBOX_NAME = "name";
	private final String CONFIRM_BUTTON_ID = "botao";
	private final String EXISTED_CANDIDATE_REFERENCED_TEXT = "Luiz";
	private final String CANDIDATE_EXPECTED_LINKTEXT = "LUIZ INACIO LULA DA SILVA";
	private final String UNEXISTED_CANDIDATE_REFERENCED_TEXT = "xXxYxYzZz";
	private final String EXPECTED_ERROR_ADVISE = "Um erro ocorreu! O candidato não existe!";

	/**
	 * Cenário 01: Candidate existe. DADO QUE: o usuário digitou "Luiz" E:
	 * existe o candidato Luiz Inácio Lula da Silva no Banco de Dados. QUANDO: o
	 * usuário solicita a busca. ENTÃO: uma lista de nomes de candidato contendo
	 * a palavra Luiz deve ser exibida. E: um dos nomes deve ser Luiz Inácio
	 * Lula da Silva.
	 */
	@Test
	public void scenario01UserStory01CandidateExist() throws Exception {
		goToCandidateSearchingPage(WAITING_NORMAL_TIME_FOR_VIEW);
		sendCandidateNameForSearch(EXISTED_CANDIDATE_REFERENCED_TEXT,
				WAITING_EXTENDED_TIME_FOR_VIEW);
		clickInSeeAllList(WAITING_EXTENDED_TIME_FOR_VIEW);
		getWebElementByLinkText(CANDIDATE_EXPECTED_LINKTEXT);
		Thread.sleep(WAITING_EXTENDED_TIME_FOR_VIEW);
	}

	/**
	 * Cenário 02: Candidate não existe. DADO QUE: o usuário digitou "xXxYxYzZz"
	 * E: nenhum candidato no Banco da Dados possui essa string no seu nome.
	 * QUANDO: o usuário solicita a busca. ENTÃO: o sistema deve informar que
	 * nenhum candidato foi encontrado.
	 */
	@Test
	public void scenario02UserStory01CandidateDidntExist() throws Exception {
		goToCandidateSearchingPage(WAITING_NORMAL_TIME_FOR_VIEW);
		sendCandidateNameForSearch(UNEXISTED_CANDIDATE_REFERENCED_TEXT,
				WAITING_EXTENDED_TIME_FOR_VIEW);
		Assert.assertTrue(driver.getPageSource()
				.contains(EXPECTED_ERROR_ADVISE));
		Thread.sleep(WAITING_EXTENDED_TIME_FOR_VIEW);
	}

	/**
	 * This Method Write a Candidate Name in a TextBox and click in a
	 * confirmation button, sending the candidate name for a search
	 * 
	 * @param candidateNameToBeSearch
	 * @param waitingTime
	 * @throws Exception
	 */
	public void sendCandidateNameForSearch(String candidateNameToBeSearch,
			long waitingTime) throws Exception {
		WebElement candidateTextboxElement = getWebElementByName(CANDIDATE_TEXTBOX_NAME);
		writeInWebElement(candidateNameToBeSearch, candidateTextboxElement,
				waitingTime);
		WebElement confirmButtonElement = getWebElementByID(CONFIRM_BUTTON_ID);
		confirmButtonElement.click();
	}

}