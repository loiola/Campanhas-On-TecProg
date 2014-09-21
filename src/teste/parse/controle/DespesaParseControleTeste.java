package teste.parse.controle;

import model.beans.Expense;
import model.dao.DespesaDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.controle.DespesaParseControle;
import parse.indices.DespesaIndicesParse;
import teste.TemplateTeste;

public class DespesaParseControleTeste extends TemplateTeste {
	
	public static final int NOME = 0;
	public static final int CPF_CNPJ = 1;
	public static final int TIPODOCUMENTO = 2;
	public static final String ANO = "2010";
	

	private String campo[];
	private DespesaDAO despesaDAO;
	private DespesaIndicesParse despesaIndicesParse;
	private DespesaParseControle despesaParseControle;

	@Override
	public void beforeTest() throws Exception {
		this.campo = new String[3];
		this.despesaDAO = new DespesaDAO();
		this.despesaIndicesParse = new DespesaIndicesParse(ANO);
		this.despesaParseControle = new DespesaParseControle(this.despesaIndicesParse);

		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void cadastrarDespesa() throws Exception {

		this.despesaParseControle.addInstancia(campo);
		this.despesaParseControle.cadastrarInstancias();
		this.despesaParseControle.resetar();

		Expense despesaCadastrado = this.despesaDAO.getLista().get(0);

		Assert.assertEquals(this.campo[TIPODOCUMENTO], despesaCadastrado.getExpenseDocumentType()
				.toString());
		Assert.assertEquals(this.campo[NOME],
				despesaCadastrado.getExpenseSupplier().getSupplierName());
		Assert.assertEquals(this.campo[CPF_CNPJ], despesaCadastrado.getExpenseSupplier().getSupplierPersonRegister());
	}

	private void iniciarIndices() {

		this.despesaIndicesParse.setIndiceFornecedorNome(NOME);
		this.despesaIndicesParse.setIndiceFornecedorCpfCnpj(CPF_CNPJ);
		this.despesaIndicesParse.setIndiceTipoDocumento(TIPODOCUMENTO);
	}

	private void iniciarCampos() {

		this.campo[NOME] = "NOME TESTE";
		this.campo[CPF_CNPJ] = "123456";
		this.campo[TIPODOCUMENTO] = "BOLETO";
	}

}
