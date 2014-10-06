package test.acceptance;

import org.junit.Test;

public class UserStory10AcceptanceTest extends BasicAcceptanceTest {

	// TO DO: User Story Reference

	/**
	 * TO DO: ROADMAP
	 */

	// Constants Strings Used in This Acceptance Test
	private final String DEMOCRATAS_POLITICAL_PARTY_LINKTEXT = "DEMOCRATAS";
	private final String CAMPAIGN_YEAR_2010_LINKTEXT = "2010";
	private final String CANDIDATE_EXPECTED_LINKTEXT = "ADELMIR ARAUJO SANTANA";

	/**
	 * Cenário Único: Gráfico foi desenvolvido. ENTÃO: deverá ser apresentado um
	 * gráfico que demonstre a relação entre receitas e despesas de um candidato
	 * requisitado.
	 */
	@Test
	public void userStory10GoogleChartsIsInCandidatePage() throws Exception {
		goToCandidatePageInPoliticalPartyWay(
				DEMOCRATAS_POLITICAL_PARTY_LINKTEXT,
				CAMPAIGN_YEAR_2010_LINKTEXT, CANDIDATE_EXPECTED_LINKTEXT,
				WAITING_EXTENDED_TIME_FOR_VIEW);
		checkIfGoogleChartsDivIsInTheCurrentPage(WAITING_EXTENDED_TIME_FOR_VIEW);
	}
}