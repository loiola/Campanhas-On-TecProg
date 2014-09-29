package test.acceptance;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US03e27Teste {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	/**
	 * Cenário Único: Busca de Candidatos.
	 * DADO QUE:
	 * 		 o usuário já acessou o perfil do partido desejado.
	 * QUANDO:
	 * 		o usuário clicar em um determinado ano, através de um link.
	 * ENTÃO:
	 * 		deve ser exibida uma lista de candidatos que concorreram
	 * 		naquele ano através do partido selecionado.
	 * E:
	 * 		o nome dos candidatos devem aparecer na forma de links
	 * 		que direcionem ao perfil do mesmo
	 */
	@Test
	public void cenarioUS03e27PerfilCandidato() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("partidos"))
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
		Assert.assertTrue(driver.findElement(By.linkText("Campaign de 2002")).isDisplayed());
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}