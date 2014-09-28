package teste.parse.controle;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlSupplier;
import parse.index.SupplierParseIndex;
import teste.TemplateTest;

public class FornecedorParseControleTeste extends TemplateTest {
	
	public static final int CPF_CNPJ = 0;
	public static final int NOME = 1;

	private String campo[];
	private SupplierDAO supplierDAO;
	private SupplierParseIndex supplierParseIndex;
	private ParseControlSupplier parseControlSupplier;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.supplierDAO = new SupplierDAO();
		this.supplierParseIndex = new SupplierParseIndex();
		this.parseControlSupplier = new ParseControlSupplier(this.supplierParseIndex);

		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {

	}

	@Test
	public void cadastrarFornecedor() throws Exception {

		this.parseControlSupplier.addInstance(campo);
		this.parseControlSupplier.registeringInstances();
		this.parseControlSupplier.clear();

		Supplier fornecedorCadastrado = this.supplierDAO.getObjectArrayListFromDatabase().get(0);

		Assert.assertEquals(this.campo[CPF_CNPJ], fornecedorCadastrado.getSupplierPersonRegister()
				.toString());
		Assert.assertEquals(this.campo[NOME],
				fornecedorCadastrado.getSupplierName());
	}

	@Test
	public void naoDeveCadastrarDoisFornecedoresIguais() throws Exception {

		this.parseControlSupplier.addInstance(campo);
		this.parseControlSupplier.addInstance(campo);
		this.parseControlSupplier.registeringInstances();
		this.parseControlSupplier.clear();

		int numeroFornecedorCadastrados = this.supplierDAO.getObjectArrayListFromDatabase().size();

		Assert.assertEquals(1, numeroFornecedorCadastrados);
	}

	private void iniciarIndices() {

		this.supplierParseIndex.setIndexSupplierCpfCnpj(CPF_CNPJ);
		this.supplierParseIndex.setIndexName(NOME);

	}

	private void iniciarCampos() {

		this.campo[CPF_CNPJ] = "125";
		this.campo[NOME] = "NOME TESTE";
	}

}
