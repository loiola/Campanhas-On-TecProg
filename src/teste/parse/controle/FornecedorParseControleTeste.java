package teste.parse.controle;

import model.beans.Supplier;
import model.dao.FornecedorDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.controle.FornecedorParseControle;
import parse.indices.FornecedorIndicesParse;
import teste.TemplateTeste;

public class FornecedorParseControleTeste extends TemplateTeste {
	
	public static final int CPF_CNPJ = 0;
	public static final int NOME = 1;

	private String campo[];
	private FornecedorDAO fornecedorDAO;
	private FornecedorIndicesParse fornecedorIndicesParse;
	private FornecedorParseControle fornecedorParseControle;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.fornecedorDAO = new FornecedorDAO();
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
		this.fornecedorParseControle.cadastrarInstancias();
		this.fornecedorParseControle.resetar();

		Supplier fornecedorCadastrado = this.fornecedorDAO.getLista().get(0);

		Assert.assertEquals(this.campo[CPF_CNPJ], fornecedorCadastrado.getCpf_cnpj()
				.toString());
		Assert.assertEquals(this.campo[NOME],
				fornecedorCadastrado.getNome());
	}

	@Test
	public void naoDeveCadastrarDoisFornecedoresIguais() throws Exception {

		this.fornecedorParseControle.addInstancia(campo);
		this.fornecedorParseControle.addInstancia(campo);
		this.fornecedorParseControle.cadastrarInstancias();
		this.fornecedorParseControle.resetar();

		int numeroFornecedorCadastrados = this.fornecedorDAO.getLista().size();

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
