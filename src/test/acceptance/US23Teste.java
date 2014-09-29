package test.acceptance;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US23Teste {
	private WebDriver driver;
	private String baseUrl;
	private ArrayList<String> tabs;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	/**
	 * Cenário Único: O link para o TSE existe.
	 * 1 - 	Na página de perfil de um partido, deve haver um
	 * 		link para o endereço: http://www.tse.jus.br/partidos/partidos-politicos/&lt;nome-do-partido>
	 * 
	 * 2 - 	O link, ao ser acessado, deve achar a página do TSE,
	 * 		com informações do partido como, por exemplo, seu estatuto e suas normas.
	 */
	@Test
	public void cenarioUS23PerfilPartido() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("candidatos"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("partidos")).click();
		Assert.assertTrue(driver.findElement(By.linkText("DEMOCRATAS")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("DEMOCRATAS")).click();
		Assert.assertTrue(driver.getPageSource().contains("clique aqui"));
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("clique aqui")).click();
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.tse.jus.br/partidos/partidos-politicos/democratas");
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}