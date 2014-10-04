package test.parse.register;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Revenue;
import model.dao.RevenueDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.revenue_expense.RegisterToParseRevenue;
import test.TemplateTest;

public class CadastroReceitaParseTeste extends TemplateTest {

	private RegisterToParseRevenue cadastro1;
	private RegisterToParseRevenue cadastro2;
	private RegisterToParseRevenue cadastro3;
	private RevenueDAO revenueDAO;
	String  tipoArquivo = "revenue";
	String  ano1         = "2002";
	String  ano2         = "2006";
	String  ano3         = "2010";
	String  tipoArquivoErrado = "ArquivoErrado";
	String  AnoInvalido = "2050";
	
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro1 = new RegisterToParseRevenue(this.tipoArquivo, this.ano1);
		this.cadastro2 = new RegisterToParseRevenue(this.tipoArquivo, this.ano2);
		this.cadastro3 = new RegisterToParseRevenue(this.tipoArquivo, this.ano3);
		this.revenueDAO = new RevenueDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmaReceitaCadastradoPara2002() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[0] = "DF";
		campo[4] = "123";
		campo[2] = "Position";
		campo[10] = "FormaPag";
		campo[5] = "Data";
		campo[6] = "1234";
		campo[8] = "NomeDoador";
		campo[9] = "12345";
		cadastro1.runFileLine(campo);
		cadastro1.registerInstances();
		
		ArrayList<Revenue> listaReceita = revenueDAO.getObjectArrayListFromDatabase();
		assertEquals(listaReceita.get(0).getRevenueDonor().getDonorPersonRegister(), "1234");	
	}
	
	@Test
	public void deveRetornarUmaReceitaCadastradoPara2006() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[4] = "DF";
		campo[3] = "123";
		campo[1] = "Position";
		campo[11] = "TipoPag";
		campo[13] = "FormaPag";
		campo[10] = "Data";
		campo[16] = "1234";
		campo[15] = "NomeDoador";
		campo[9] = "12345";
		cadastro2.runFileLine(campo);
		cadastro2.registerInstances();
		
		ArrayList<Revenue> listaReceita = revenueDAO.getObjectArrayListFromDatabase();
		assertEquals(listaReceita.get(0).getRevenueDonor().getDonorPersonRegister(), "1234");
	}
	
	@Test
	public void deveRetornarUmaReceitaCadastradoPara2010() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[1] = "DF";
		campo[3] = "123";
		campo[4] = "Position";
		campo[14] = "TipoPag";
		campo[16] = "FormaPag";
		campo[8] = "ReciboEle";
		campo[9] = "1234";
		campo[12] = "Data";
		campo[10] = "12345";
		campo[11] = "NomeDoador";
		campo[13] = "12345";
		campo[17] = "DescricaoDoa";
		cadastro3.runFileLine(campo);
		cadastro3.registerInstances();
		
		ArrayList<Revenue> listaReceita = revenueDAO.getObjectArrayListFromDatabase();
		assertEquals(listaReceita.get(0).getRevenueDonor().getDonorPersonRegister(), "12345");
	}

}