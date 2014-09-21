package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Expense;
import model.dao.DespesaDAO;

import org.junit.Test;

import parse.ParseException;
import parse.cadastro.receita_despesa.CadastroDespesaParse;
import teste.TemplateTeste;

public class CadastroDespesaParseTeste extends TemplateTeste {

	private CadastroDespesaParse cadastro1;
	private CadastroDespesaParse cadastro2;
	private CadastroDespesaParse cadastro3;
	private DespesaDAO despesaDAO;
	String  tipoArquivo = "expense";
	String  ano1         = "2006";
	String  ano2         = "2002";
	String  ano3         = "2010";
	String  tipoArquivoErrado = "ArquivoErrado";
	String  AnoInvalido = "2050";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro1 = new CadastroDespesaParse(this.tipoArquivo, this.ano1);
		this.cadastro2 = new CadastroDespesaParse(this.tipoArquivo, this.ano2);
		this.cadastro3 = new CadastroDespesaParse(this.tipoArquivo, this.ano3);
		this.despesaDAO = new DespesaDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void deveRetornarUmaDespesaCadastradoPara2006() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[4] = "DF";
		campo[3] = "123";
		campo[1] = "Position";
		campo[11] = "TipoMov";
		campo[16] = "TipoDoc";
		campo[13] = "FormaPag";
		campo[15] = "1234";
		campo[10] = "Data";
		campo[19] = "12345";
		campo[18] = "123456";
		campo[9] = "1234567";
		cadastro1.executarLinhaDoArquivo(campo);
		cadastro1.cadastrarInstancias();
		
		ArrayList<Expense> listaDespesa = despesaDAO.getLista();
		assertEquals(listaDespesa.get(0).getFinancialTransactionType(), "TipoMov");
	}
	
	@Test
	public void deveRetornarUmaDespesaCadastradoPara2002() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[0] = "DF";
		campo[4] = "123";
		campo[2] = "Position";
		campo[10] = "TipoMov";
		campo[5] = "Data";
		campo[6] = "1234";
		campo[8] = "FornecedorNome";
		campo[9] = "12345";
		cadastro2.executarLinhaDoArquivo(campo);
		cadastro2.cadastrarInstancias();
		
		ArrayList<Expense> listaDespesa = despesaDAO.getLista();
		assertEquals(listaDespesa.get(0).getFinancialTransactionType(), "TipoMov");
	}
	
	@Test
	public void deveRetornarUmaDespesaCadastradoPara2010() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[1] = "DF";
		campo[3] = "123";
		campo[4] = "Position";
		campo[14] = "TipoMov";
		campo[8] = "TipoDoc";
		campo[16] = "FormaPag";
		campo[9] = "1234";
		campo[12] = "Data";
		campo[10] = "12345";
		campo[11] = "Nome";
		campo[13] = "123456";
		campo[17] = "Descricao";
		
		cadastro3.executarLinhaDoArquivo(campo);
		cadastro3.cadastrarInstancias();
		
		ArrayList<Expense> listaDespesa = despesaDAO.getLista();
		assertEquals(listaDespesa.get(0).getFinancialTransactionType(), "TipoMov");
	}

}
