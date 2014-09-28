package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Test;

import parse.ParseException;
import parse.register.revenue_expense.RegisterToParseSupplier;
import teste.TemplateTest;

public class CadastroFornecedorParseTeste extends TemplateTest {

	private RegisterToParseSupplier cadastro1;
	private RegisterToParseSupplier cadastro2;
	private RegisterToParseSupplier cadastro3;
	private SupplierDAO supplierDAO;
	String  tipoArquivo = "expense";
	String  ano1         = "2006";
	String  ano2         = "2002";
	String  ano3         = "2010";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro1 = new RegisterToParseSupplier(this.tipoArquivo, this.ano1);
		this.cadastro2 = new RegisterToParseSupplier(this.tipoArquivo, this.ano2);
		this.cadastro3 = new RegisterToParseSupplier(this.tipoArquivo, this.ano3);
		this.supplierDAO = new SupplierDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmFornecedorCadastradoPara2006() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[19] = "123";
		campo[18] = "NOME";
		campo[20] = "UF";
		campo[21] = "SITUACAO";		
		cadastro1.runFileLine(campo);
		cadastro1.registerInstances();
		
		ArrayList<Supplier> listaFornecedores = supplierDAO.getObjectArrayListFromDatabase();
		assertEquals(listaFornecedores.get(0).getSupplierPersonRegister(), "123");
	}
	
	@Test
	public void deveRetornarUmFornecedorCadastradoPara2002() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[6] = "12345";
		campo[8] = "NOME";
		campo[7] = "UF";
		cadastro2.runFileLine(campo);
		cadastro2.registerInstances();
		
		ArrayList<Supplier> listaFornecedores = supplierDAO.getObjectArrayListFromDatabase();
		assertEquals(listaFornecedores.get(0).getSupplierPersonRegister(), "12345");
	}
	
	@Test
	public void deveRetornarUmFornecedorCadastradoPara2010() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[10] = "12345";
		campo[11] = "NOME";
		cadastro3.runFileLine(campo);
		cadastro3.registerInstances();
		
		ArrayList<Supplier> listaFornecedores = supplierDAO.getObjectArrayListFromDatabase();
		assertEquals(listaFornecedores.get(0).getSupplierPersonRegister(), "12345");
	}

}
