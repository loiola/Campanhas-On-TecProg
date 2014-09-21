package teste.modelo.dao;

import java.util.ArrayList;

import model.beans.Supplier;
import model.dao.SupplierDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class FornecedorDAOTeste extends TemplateTeste {

	private SupplierDAO supplierDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.supplierDAO = new SupplierDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void valoresComparacao() throws Exception {
		
		Supplier F1 = new Supplier();
		Supplier F2 = new Supplier();
		F1.setSupplierName("FORNECEDOR UM");
		F2.setSupplierName("FORNECEDOR UM");
		int resultado;

		resultado = SupplierDAO.Comparacao.NOME.compare(F1, F2);
		
		Assert.assertEquals(0,resultado);
	}
	
	@Test
	public void naoDeveLancarExcecaoAoCadastrarUmFornecedorInexistente() throws Exception {
		
		ArrayList<Supplier> listaFornecedores = new ArrayList<>();
		
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Nome");
		listaFornecedores.add(supplier);
		
		this.supplierDAO.cadastrarLista(listaFornecedores);
	}
	
	@Test
	public void naoDeveCadastrarUmFornecedorJaExistente() throws Exception {
		
		ArrayList<Supplier> listaFornecedores = new ArrayList<>();
		
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Nome");
		listaFornecedores.add(supplier);

		this.supplierDAO.cadastrarLista(listaFornecedores);
		this.supplierDAO.cadastrarLista(listaFornecedores);
		
		Assert.assertEquals(1, this.supplierDAO.getLista().size());
	}
	
	@Test
	public void deveRecuperarUmaListaComOsFornecedoresCadastrados() throws Exception {
		
		ArrayList<Supplier> listaFornecedores = new ArrayList<>();
		
		Supplier supplier = new Supplier();
		supplier.setSupplierName("Nome");
		supplier.setSupplierPersonRegister("123");
		supplier.setSupplierRegisterSituation("Cadastrado");
		supplier.setSupplierCountryState("DF");
		listaFornecedores.add(supplier);
		
		supplier = new Supplier();
		supplier.setSupplierName("Nome2");
		supplier.setSupplierPersonRegister("1234");
		supplier.setSupplierRegisterSituation("Cadastrado");
		supplier.setSupplierCountryState("DF");
		listaFornecedores.add(supplier);

		this.supplierDAO.cadastrarLista(listaFornecedores);
		Assert.assertEquals(listaFornecedores, this.supplierDAO.getLista());
	}
	
	@Test
	public void deveRecuperarUmFornecedorPeloNomeOuCpfCnpj() throws Exception {
		
		ArrayList<Supplier> listaFornecedoresACadastrar = new ArrayList<>();
		Supplier fornecedorRecuperado;
		
		Supplier fornecedor1 = new Supplier();
		fornecedor1.setSupplierName("nome");
		fornecedor1.setSupplierPersonRegister("123456");
		fornecedor1.setSupplierRegisterSituation("REGULAR");
		fornecedor1.setSupplierCountryState("DF");
		listaFornecedoresACadastrar.add(fornecedor1);
		
		Supplier fornecedor2 = new Supplier();
		fornecedor2.setSupplierName("nome2");
		fornecedor2.setSupplierPersonRegister("12345678");
		fornecedor2.setSupplierRegisterSituation("IRREGULAR");
		fornecedor2.setSupplierCountryState("DF");
		listaFornecedoresACadastrar.add(fornecedor2);
		
		this.supplierDAO.cadastrarLista(listaFornecedoresACadastrar);
		fornecedorRecuperado = this.supplierDAO.getPeloNomeOuCpfCnpj(fornecedor1);
		
		Assert.assertEquals(fornecedor1, fornecedorRecuperado);
	}
	
}
