package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.beans.Receita;
import modelo.dao.ReceitaDAO;

import org.junit.Test;

import parse.ParseException;
import parse.cadastro.receita_despesa.CadastroReceitaParse;
import teste.TemplateTeste;

public class CadastroReceitaParseTeste extends TemplateTeste {

	private CadastroReceitaParse cadastro1;
	private CadastroReceitaParse cadastro2;
	private CadastroReceitaParse cadastro3;
	private ReceitaDAO receitaDAO;
	String  tipoArquivo = "receita";
	String  ano1         = "2002";
	String  ano2         = "2006";
	String  ano3         = "2010";
	String  tipoArquivoErrado = "ArquivoErrado";
	String  AnoInvalido = "2050";
	
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro1 = new CadastroReceitaParse(this.tipoArquivo, this.ano1);
		this.cadastro2 = new CadastroReceitaParse(this.tipoArquivo, this.ano2);
		this.cadastro3 = new CadastroReceitaParse(this.tipoArquivo, this.ano3);
		this.receitaDAO = new ReceitaDAO();
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
		cadastro1.executarLinhaDoArquivo(campo);
		cadastro1.cadastrarInstancias();
		
		ArrayList<Receita> listaReceita = receitaDAO.getLista();
		assertEquals(listaReceita.get(0).getDoador().getCpf_cnpj(), "1234");	
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
		cadastro2.executarLinhaDoArquivo(campo);
		cadastro2.cadastrarInstancias();
		
		ArrayList<Receita> listaReceita = receitaDAO.getLista();
		assertEquals(listaReceita.get(0).getDoador().getCpf_cnpj(), "1234");
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
		cadastro3.executarLinhaDoArquivo(campo);
		cadastro3.cadastrarInstancias();
		
		ArrayList<Receita> listaReceita = receitaDAO.getLista();
		assertEquals(listaReceita.get(0).getDoador().getCpf_cnpj(), "12345");
	}

}