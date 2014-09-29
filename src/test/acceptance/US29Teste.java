package test.acceptance;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class US29Teste {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	/**
	 * Cenário 01: Deverá haver um Top 5 de presidente na Home Page
	 * DADO QUE:
	 * 		o usuário entre na Home Page.
	 * QUANDO:
	 * 		ele visualizar a seção do Top 5.
	 * ENTÃO:
	 * 		deve ser exibido uma gráfico, dinâmico, com as
	 * 		receitas e/ou expense de um determinado candidato.
	 */
	@Test
	public void cenario01US29GraficoPresidenteIndex() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.id("top5")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("chart_div")).isEnabled());
		Thread.sleep(2000);
	}
	
	/**
	 * Cenário 02: Um link para o gráficos de receitas e/ou expense.
	 * DADO QUE:
	 * 		o usuário acessar a seção Top 5.
	 * QUANDO:
	 * 		o usuário requisitar o gráfico.
	 * ENTÃO:
	 * 		deve ser exibido uma gráfico, dinâmico, com as receitas
	 * 		e/ou despesas.
	 */
	@Test
	public void cenario02US29SecaoTopFive()throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("top5"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("top5")).click();
		Assert.assertTrue(driver.findElement(By.name("ano")).isDisplayed());
		new Select(driver.findElement(By.name("ano"))).selectByVisibleText("2006");
		Assert.assertTrue(driver.findElement(By.name("cargo")).isDisplayed());
	    new Select(driver.findElement(By.name("cargo"))).selectByVisibleText("Governador");
	    Assert.assertTrue(driver.findElement(By.id("botaoTop5")).isDisplayed());
		Thread.sleep(2000);
		
		driver.findElement(By.id("botaoTop5")).click();
		Assert.assertTrue(driver.findElement(By.id("chart_div")).isEnabled());
		Thread.sleep(2000);
	}
	
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}