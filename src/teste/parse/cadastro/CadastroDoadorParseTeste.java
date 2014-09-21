package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Donor;
import model.dao.DonorDAO;

import org.junit.Test;

import parse.ParseException;
import parse.cadastro.receita_despesa.CadastroDoadorParse;
import teste.TemplateTeste;

public class CadastroDoadorParseTeste extends TemplateTeste {

	private CadastroDoadorParse cadastro1;
	private CadastroDoadorParse cadastro2;
	private CadastroDoadorParse cadastro3;
	private DonorDAO donorDAO;
	String  tipoArquivo = "revenue";
	String  ano1         = "2006";
	String  ano2         = "2002";
	String  ano3         = "2010";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro1 = new CadastroDoadorParse(this.tipoArquivo, this.ano1);
		this.cadastro2 = new CadastroDoadorParse(this.tipoArquivo, this.ano2);
		this.cadastro3 = new CadastroDoadorParse(this.tipoArquivo, this.ano3);
		this.donorDAO = new DonorDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmDoadorCadastradoPara2006() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[16] = "123";
		campo[15] = "NOME";
		campo[17] = "UF";
		campo[18] = "SITUACAO";		
		cadastro1.executarLinhaDoArquivo(campo);
		cadastro1.cadastrarInstancias();
		
		ArrayList<Donor> listaDoadores = donorDAO.getObjectArrayListFromDatabase();
		assertEquals(listaDoadores.get(0).getDonorPersonRegister(), "123");
	}
	
	@Test
	public void deveRetornarUmDoadorCadastradoPara2002() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[6] = "12345";
		campo[8] = "NOME NOME";
		campo[7] = "UF UF";	
		cadastro2.executarLinhaDoArquivo(campo);
		cadastro2.cadastrarInstancias();
		
		ArrayList<Donor> listaDoadores = donorDAO.getObjectArrayListFromDatabase();
		assertEquals(listaDoadores.get(0).getDonorPersonRegister(), "12345");
	}
	
	@Test
	public void deveRetornarUmDoadorCadastradoPara2010() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[10] = "12345";
		campo[11] = "NOME NOME";
		cadastro3.executarLinhaDoArquivo(campo);
		cadastro3.cadastrarInstancias();
		
		ArrayList<Donor> listaDoadores = donorDAO.getObjectArrayListFromDatabase();
		assertEquals(listaDoadores.get(0).getDonorPersonRegister(), "12345");
	}

}
