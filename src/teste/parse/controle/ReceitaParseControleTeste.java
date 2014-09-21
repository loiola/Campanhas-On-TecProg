package teste.parse.controle;

import model.beans.Revenue;
import model.dao.ReceitaDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.controle.ReceitaParseControle;
import parse.indices.ReceitaIndicesParse;
import teste.TemplateTeste;

public class ReceitaParseControleTeste extends TemplateTeste {
	
	public static final int RECIBO = 0;
	public static final int NOME = 1;
	public static final int CPF_CNPJ = 2;
	public static final String ANO = "2010";

	private String campo[];
	private ReceitaDAO receitaDAO;
	private ReceitaIndicesParse receitaIndicesParse;
	private ReceitaParseControle receitaParseControle;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[3];
		this.receitaDAO = new ReceitaDAO();
		this.receitaIndicesParse = new ReceitaIndicesParse(ANO);
		this.receitaParseControle = new ReceitaParseControle(this.receitaIndicesParse);

		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void cadastrarReceita() throws Exception {

		this.receitaParseControle.addInstancia(campo);
		this.receitaParseControle.cadastrarInstancias();
		this.receitaParseControle.resetar();

		Revenue receitaCadastrado = this.receitaDAO.getLista().get(0);

		Assert.assertEquals(this.campo[RECIBO], receitaCadastrado.
				getReciboEleitoral());
		Assert.assertEquals(this.campo[NOME], receitaCadastrado.
				getDoador().getNome());
		Assert.assertEquals(this.campo[CPF_CNPJ], receitaCadastrado.
				getDoador().getCpf_cnpj());
	}

	private void iniciarIndices() {

		this.receitaIndicesParse.setIndiceReciboEleitoral(RECIBO);
		this.receitaIndicesParse.setIndiceDoadorNome(NOME);
		this.receitaIndicesParse.setIndiceDoadorCpfCnpj(CPF_CNPJ);
	}

	private void iniciarCampos() {

		this.campo[RECIBO] = "RECIBOELEITORAL";
		this.campo[NOME] = "NOME";
		this.campo[CPF_CNPJ] = "123";
	}

}
