package teste.parse.controle;

import model.beans.Revenue;
import model.dao.RevenueDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ReceitaParseControle;
import parse.index.ReceitaIndicesParse;
import teste.TemplateTeste;

public class ReceitaParseControleTeste extends TemplateTeste {
	
	public static final int RECIBO = 0;
	public static final int NOME = 1;
	public static final int CPF_CNPJ = 2;
	public static final String ANO = "2010";

	private String campo[];
	private RevenueDAO revenueDAO;
	private ReceitaIndicesParse receitaIndicesParse;
	private ReceitaParseControle receitaParseControle;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[3];
		this.revenueDAO = new RevenueDAO();
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
		this.receitaParseControle.registeringInstances();
		this.receitaParseControle.resetar();

		Revenue receitaCadastrado = this.revenueDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.campo[RECIBO], receitaCadastrado.
				getRevenueElectoralReceipt());
		Assert.assertEquals(this.campo[NOME], receitaCadastrado.
				getRevenueDonor().getDonorName());
		Assert.assertEquals(this.campo[CPF_CNPJ], receitaCadastrado.
				getRevenueDonor().getDonorPersonRegister());
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
