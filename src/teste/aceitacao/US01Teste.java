package teste.aceitacao;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US01Teste {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	/**
	 * Cenário 01: Candidate existe.
	 * DADO QUE:
	 * 		o usuário digitou "Luiz"
	 * E:
	 * 		existe o candidato Luiz Inácio Lula da Silva no Banco de Dados.
	 * QUANDO:
	 * 		o usuário solicita a busca.
	 * ENTÃO:
	 * 		uma lista de nomes de candidato contendo a palavra Luiz deve ser exibida.
	 * E:
	 * 		um dos nomes deve ser Luiz Inácio Lula da Silva.
	 */
	@Test
	public void cenario01US01CanditatoExiste() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("candidatos"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("candidatos")).click();
		Assert.assertTrue(driver.findElement(By.name("nome")).isDisplayed());
		driver.findElement(By.name("nome")).clear();
		driver.findElement(By.name("nome")).sendKeys("Luiz");
		Assert.assertTrue(driver.findElement(By.id("botao")).isDisplayed());
		Thread.sleep(2000);

		driver.findElement(By.id("botao")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Ver Todos"))
				.isDisplayed());
		Thread.sleep(2000);

		driver.findElement(By.linkText("Ver Todos")).click();
		Assert.assertTrue(driver.findElement(
				By.linkText("LUIZ INACIO LULA DA SILVA")).isDisplayed());
		Thread.sleep(2000);
	}
	
	
	/**
	 * Cenário 02: Candidate não existe.
	 * DADO QUE:
	 * 		o usuário digitou "xXxYxYzZz"
	 * E:
	 * 		nenhum candidato no Banco da Dados possui essa string no seu nome.
	 * QUANDO:
	 * 		o usuário solicita a busca.
	 * ENTÃO:
	 * 		o sistema deve informar que nenhum candidato foi encontrado.
	 */
	@Test
	public void cenario02US01CanditatoNaoExiste() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("candidatos"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("candidatos")).click();
		Assert.assertTrue(driver.findElement(By.name("nome")).isDisplayed());
		driver.findElement(By.name("nome")).clear();
		driver.findElement(By.name("nome")).sendKeys("xXxYxYzZz");
		Assert.assertTrue(driver.findElement(By.id("botao")).isDisplayed());
		Thread.sleep(2000);

		driver.findElement(By.id("botao")).click();
		Assert.assertTrue(driver.getPageSource().contains(
				"Um erro ocorreu! O candidato não existe!"));
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}