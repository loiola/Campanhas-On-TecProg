package test.acceptance;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US10Teste {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	/**
	 * Cenário Único: Gráfico foi desenvolvido.
	 * ENTÃO:
	 * 		deverá ser apresentado um gráfico que demonstre a relação
	 * 		entre receitas e despesas de um candidato requisitado.
	 */
	@Test
	public void cenarioUS10PerfilCandidato() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("candidatos"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("partidos")).click();
		Assert.assertTrue(driver.findElement(By.linkText("DEMOCRATAS")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("DEMOCRATAS")).click();
		Assert.assertTrue(driver.findElement(By.linkText("2010")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("2010")).click();
		Assert.assertTrue(driver.findElement(By.linkText("ADELMIR ARAUJO SANTANA")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("ADELMIR ARAUJO SANTANA")).click();
		Assert.assertTrue(driver.findElement(By.id("chart_div")).isEnabled());
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}