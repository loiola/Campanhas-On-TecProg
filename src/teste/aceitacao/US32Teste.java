package teste.aceitacao;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US32Teste {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/prestacao_de_contas_das_campanhas_eleitorais/";
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	/**
	 * Cenário 01: Glossário a Partir da Index
	 * DADO QUE:
	 * 		o usuário acessou a página principal do site;
	 * QUANDO:
	 * 		o usuário solicitou maiores informações do glossário;
	 * ENTÃO:
	 * 		deve ser exibida uma nova página com o glossário.
	 */
	
	/**
	 * Cenário 02: Referências a Partir da Index
	 * DADO QUE:
	 * 		o usuário acessou a página principal do site;
	 * QUANDO:
	 * 		o usuário solicitou maiores informações das referências;
	 * ENTÃO:
	 * 		deve ser exibida uma nova página com as referências.
	 */
	
	/**
	 * Cenário 03: Contatos no Rodapé
	 * DADO QUE:
	 * 		o usuário acessou uma página qualquer do site;
	 * QUANDO:
	 * 		o usuário recorreu ao rodapé da página;
	 * ENTÃO:
	 * 		deve estar descrito no rodapé o e-mail de contato da equipe.
	 */
	
	/**
	 * Cenário 04: Lista de Partidos
	 * DADO QUE:
	 * 		o usuário acessou uma página qualquer do site;
	 * QUANDO:
	 * 		o usuário clicou no campo de partido no cabeçalho;
	 * ENTÃO:
	 * 		a tabela de partido deve conter como informações nome e partido;
	 * E:
	 * 		deve possuir um design agradável.
	 */
	@Test
	public void cenario04US32InformacoesPartido() throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("partidos"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("partidos")).click();
		Assert.assertTrue(driver.findElement(By.linkText("DEMOCRATAS")).isDisplayed());
		Assert.assertTrue(driver.getPageSource().contains("DEM"));
		Thread.sleep(2000);
	}
	
	/**
	 * Cenário 05: Informações Adicionais em Campos de Party
	 * DADO QUE:
	 * 		o usuário acessou a página de um partido;
	 * QUANDO:
	 * 		o usuário direcionou para um campo do partido;
	 * ENTÃO:
	 * 		deve aparecer em uma janela ou estar descrito próximo
	 * 		ao rodapé a descrição do campo em questão.
	 */
	
	/**
	 * Cenário 06: Símbolo do Party
	 * DADO QUE:
	 * 		o usuário acessou a página de um partido;
	 * QUANDO:
	 * 		no momento em que a página for acessada;
	 * ENTÃO:
	 * 		deve estar visível o símbolo do partido em forma de imagem.
	 */
	@Test
	public void cenario06US32SimboloPartido()throws Exception {
		driver.get(baseUrl + "index.jsp");
		Assert.assertTrue(driver.findElement(By.className("partidos"))
				.isDisplayed());
		Thread.sleep(1000);

		driver.findElement(By.className("partidos")).click();
		Assert.assertTrue(driver.findElement(By.linkText("DEMOCRATAS")).isDisplayed());
		Thread.sleep(2000);

		driver.findElement(By.linkText("DEMOCRATAS")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#logo_partido > a > img")).isDisplayed());
		Thread.sleep(2000);
	}
	
	/**
	 * Cenário 07: Lista de Candidatos de um Party
	 * DADO QUE:
	 * 		o usuário acessou a página de um partido;
	 * QUANDO:
	 * 		acessou a página de candidatos do partido de um determinado ano;
	 * ENTÃO:
	 * 		a tabela de candidatos do partido deve conter como
	 * 		informações nome, nome de voto, numero de voto e cargo pleiteado;
	 * E:
	 * 		deve possuir um design agradável.
	 */
	@Test
	public void cenario07US32CandidatosEmPartido()throws Exception {
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
		Assert.assertTrue(driver.getPageSource().contains("Nome de Urna"));
		Assert.assertTrue(driver.getPageSource().contains("Position"));
		Assert.assertTrue(driver.getPageSource().contains("Número de Eleição"));
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}