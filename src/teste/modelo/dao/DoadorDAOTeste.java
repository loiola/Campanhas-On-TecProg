package teste.modelo.dao;

import java.util.ArrayList;

import model.beans.Donor;
import model.dao.DoadorDAO;

import org.junit.Assert;
import org.junit.Test;

import teste.TemplateTeste;

public class DoadorDAOTeste extends TemplateTeste {

	private DoadorDAO doadorDAO;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.doadorDAO = new DoadorDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}
	
	@Test
	public void valoresComparacaoParteI() throws Exception {
		
		Donor D1 = new Donor();
		Donor D2 = new Donor();
		D1.setCpf_cnpj("1234567");
		D2.setCpf_cnpj("1234567");
		int resultado;

		resultado = DoadorDAO.Comparacao.NOME.compare(D1, D2);
		
		Assert.assertEquals(0,resultado);
	}

	@Test
	public void naoDeveLancarExcecaoAoCadastrarUmDoadorInexistente() throws Exception {
		
		ArrayList<Donor> listaDoadores = new ArrayList<>();
		
		Donor donor = new Donor();
		donor.setNome("Nome");
		listaDoadores.add(donor);
		
		this.doadorDAO.cadastrarLista(listaDoadores);
	}
	
	@Test
	public void naoDeveCadastrarUmDoadorJaExistente() throws Exception {
		
		ArrayList<Donor> listaDoadores = new ArrayList<>();
		
		Donor donor = new Donor();
		donor.setNome("Nome");
		listaDoadores.add(donor);

		this.doadorDAO.cadastrarLista(listaDoadores);
		this.doadorDAO.cadastrarLista(listaDoadores);
		
		Assert.assertEquals(1, this.doadorDAO.getLista().size());
	}
	
	@Test
	public void deveRecuperarUmaListaComOsDoadoresCadastrados() throws Exception {
		
		ArrayList<Donor> listaDoadores = new ArrayList<>();
		
		Donor donor = new Donor();
		donor.setNome("Nome");
		donor.setCpf_cnpj("123");
		donor.setSituacaoCadastral("Cadastrado");
		donor.setUf("DF");
		listaDoadores.add(donor);
		
		donor = new Donor();
		donor.setNome("Nome2");
		donor.setCpf_cnpj("1234");
		donor.setSituacaoCadastral("Cadastrado");
		donor.setUf("DF");
		listaDoadores.add(donor);

		this.doadorDAO.cadastrarLista(listaDoadores);
		Assert.assertEquals(listaDoadores, this.doadorDAO.getLista());
	}
	
	@Test
	public void deveRecuperarUmDoadorPeloNomeOuCpfCnpj() throws Exception {
		
		ArrayList<Donor> listaDoadoresACadastrar = new ArrayList<>();
		Donor doadorRecuperado;
		
		Donor doador1 = new Donor();
		doador1.setNome("nome");
		doador1.setCpf_cnpj("123456");
		doador1.setSituacaoCadastral("REGULAR");
		doador1.setUf("DF");
		listaDoadoresACadastrar.add(doador1);
		
		Donor doador2 = new Donor();
		doador2.setNome("nome2");
		doador2.setCpf_cnpj("12345678");
		doador2.setSituacaoCadastral("IRREGULAR");
		doador2.setUf("DF");
		listaDoadoresACadastrar.add(doador2);
		
		this.doadorDAO.cadastrarLista(listaDoadoresACadastrar);
		doadorRecuperado = this.doadorDAO.getPeloNomeOuCpfCnpj(doador1);
		
		Assert.assertEquals(doador1, doadorRecuperado);
	}
	
}
