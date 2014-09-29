package test.acceptance;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US24Teste {
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
	 * 1 - 	Na página de receitas e despesas,
	 * 		ao final da listagem, o valor total de
	 * 		receitas e despesas deve ser mostrado.
	 * 2 - 	O saldo final da campanha (Saldo = Revenue - Expense)
	 * 		também deve ser exibido ao final da página.
	 */
	@Test
	public void cenarioUS24ValoresESaldo() throws Exception {
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
		Assert.assertTrue(driver.findElement(By.linkText("ROBERTO EDUARDO VENTURA GIFFONI")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("ROBERTO EDUARDO VENTURA GIFFONI")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Campaign de 2010")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("Campaign de 2010")).click();
		Assert.assertTrue(driver.getPageSource().contains("Saldo da Campaign:"));
		Assert.assertTrue(driver.getPageSource().contains("Revenue Total Calculada:"));
		Assert.assertTrue(driver.getPageSource().contains("Expense Total Calculada:"));
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}