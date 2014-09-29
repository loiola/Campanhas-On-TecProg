package test.parse.control;

import model.beans.Donor;
import model.dao.DonorDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.ParseControlDonor;
import parse.index.DonorParseIndex;
import test.TemplateTest;

public class DoadorParseControleTeste extends TemplateTest {

	public static final int CPF_CNPJ = 0;
	public static final int NOME = 1;
	public static final int UF = 2;
	public static final int SITUACAO_CADASTRAL = 3;
	
	private String campo[];
	private DonorDAO donorDAO;
	private DonorParseIndex donorParseIndex;
	private ParseControlDonor parseControlDonor;

	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[4];
		this.donorDAO = new DonorDAO();
		this.donorParseIndex = new DonorParseIndex();
		this.parseControlDonor = new ParseControlDonor(this.donorParseIndex);
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void cadastrarDoadores() throws Exception {
		
		this.parseControlDonor.addInstance(campo);
		this.parseControlDonor.registeringInstances();
		this.parseControlDonor.clear();
		
		Donor doadorCadastrado = this.donorDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.campo[CPF_CNPJ], doadorCadastrado.getDonorPersonRegister() );
		Assert.assertEquals(this.campo[NOME], doadorCadastrado.getDonorName());
		Assert.assertEquals(this.campo[UF], doadorCadastrado.getDonorCountryState());
		Assert.assertEquals(this.campo[SITUACAO_CADASTRAL], doadorCadastrado.getDonorRegisterSituation());
	}
	
	@Test
	public void naoDeveCadastrarDoisDoadoresIguais() throws Exception {
		
		this.parseControlDonor.addInstance(campo);
		this.parseControlDonor.addInstance(campo);
		this.parseControlDonor.registeringInstances();
		this.parseControlDonor.clear();
		
		int numeroDoadoresCadastrados = this.donorDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, numeroDoadoresCadastrados);
	}
	
	private void iniciarIndices() {
		
		this.donorParseIndex.setIndexDonorCpfCnpj(CPF_CNPJ);
		this.donorParseIndex.setIndexName(NOME);
		this.donorParseIndex.setIndexUnitFederation(UF);
		this.donorParseIndex.setIndexRegistrationStatus(SITUACAO_CADASTRAL);
	}
	
	private void iniciarCampos() {
		
		this.campo[CPF_CNPJ] = "55325424149";
		this.campo[NOME] = "DOADOR INEXISTENTE";
		this.campo[UF] = "DF";
		this.campo[SITUACAO_CADASTRAL] = "REGULAR";
	}

}
