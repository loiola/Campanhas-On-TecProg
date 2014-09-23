package teste.parse.controle;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.FornecedorParseControle;
import parse.index.FornecedorIndicesParse;
import teste.TemplateTeste;

public class FornecedorParseControleTeste extends TemplateTeste {
	
	public static final int CPF_CNPJ = 0;
	public static final int NOME = 1;

	private String campo[];
	private SupplierDAO supplierDAO;
	private FornecedorIndicesParse fornecedorIndicesParse;
	private FornecedorParseControle fornecedorParseControle;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.supplierDAO = new SupplierDAO();
		this.fornecedorIndicesParse = new FornecedorIndicesParse();
		this.fornecedorParseControle = new FornecedorParseControle(this.fornecedorIndicesParse);

		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void cadastrarFornecedor() throws Exception {

		this.fornecedorParseControle.addInstancia(campo);
		this.fornecedorParseControle.registeringInstances();
		this.fornecedorParseControle.resetar();

		Supplier fornecedorCadastrado = this.supplierDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.campo[CPF_CNPJ], fornecedorCadastrado.getSupplierPersonRegister()
				.toString());
		Assert.assertEquals(this.campo[NOME],
				fornecedorCadastrado.getSupplierName());
	}

	@Test
	public void naoDeveCadastrarDoisFornecedoresIguais() throws Exception {

		this.fornecedorParseControle.addInstancia(campo);
		this.fornecedorParseControle.addInstancia(campo);
		this.fornecedorParseControle.registeringInstances();
		this.fornecedorParseControle.resetar();

		int numeroFornecedorCadastrados = this.supplierDAO.getObjectArrayListFromDatabase().size();

		Assert.assertEquals(1, numeroFornecedorCadastrados);
	}

	private void iniciarIndices() {

		this.fornecedorIndicesParse.setIndiceCpf_Cnpj(CPF_CNPJ);
		this.fornecedorIndicesParse.setIndiceNome(NOME);

	}

	private void iniciarCampos() {

		this.campo[CPF_CNPJ] = "125";
		this.campo[NOME] = "NOME TESTE";
	}

}
