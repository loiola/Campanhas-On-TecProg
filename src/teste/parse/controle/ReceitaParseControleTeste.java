package teste.parse.controle;

import model.beans.Revenue;
import model.dao.RevenueDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlRevenue;
import parse.index.RevenueParseIndex;
import teste.TemplateTeste;

public class ReceitaParseControleTeste extends TemplateTeste {
	
	public static final int RECIBO = 0;
	public static final int NOME = 1;
	public static final int CPF_CNPJ = 2;
	public static final String ANO = "2010";

	private String campo[];
	private RevenueDAO revenueDAO;
	private RevenueParseIndex revenueParseIndex;
	private ParseControlRevenue parseControlRevenue;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[3];
		this.revenueDAO = new RevenueDAO();
		this.revenueParseIndex = new RevenueParseIndex(ANO);
		this.parseControlRevenue = new ParseControlRevenue(this.revenueParseIndex);

		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void cadastrarReceita() throws Exception {

		this.parseControlRevenue.addInstance(campo);
		this.parseControlRevenue.registeringInstances();
		this.parseControlRevenue.clear();

		Revenue receitaCadastrado = this.revenueDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.campo[RECIBO], receitaCadastrado.
				getRevenueElectoralReceipt());
		Assert.assertEquals(this.campo[NOME], receitaCadastrado.
				getRevenueDonor().getDonorName());
		Assert.assertEquals(this.campo[CPF_CNPJ], receitaCadastrado.
				getRevenueDonor().getDonorPersonRegister());
	}

	private void iniciarIndices() {

		this.revenueParseIndex.setIndexElectoralReceipt(RECIBO);
		this.revenueParseIndex.setIndexNameDonor(NOME);
		this.revenueParseIndex.setIndexCpfCnpjDonor(CPF_CNPJ);
	}

	private void iniciarCampos() {

		this.campo[RECIBO] = "RECIBOELEITORAL";
		this.campo[NOME] = "NOME";
		this.campo[CPF_CNPJ] = "123";
	}

}
