package teste.parse.controle;

import model.beans.Candidate;
import model.dao.CandidateDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.CandidatoParseControle;
import parse.index.CandidatoIndicesParse;
import teste.TemplateTeste;

public class CandidatoParseControleTeste extends TemplateTeste {

	public static final int NOME = 0;
	public static final int TITULO_ELEITORAL = 1;
	
	private String campo[];
	private CandidateDAO candidateDAO;
	private CandidatoIndicesParse candidatoIndicesParse;
	private CandidatoParseControle candidatoParseControle;
	
	@Override
	public void beforeTest() throws Exception {
		
		this.campo = new String[2];
		this.candidateDAO = new CandidateDAO();
		this.candidatoIndicesParse = new CandidatoIndicesParse();
		this.candidatoParseControle = new CandidatoParseControle(this.candidatoIndicesParse);
		
		iniciarCampos();
		iniciarIndices();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void cadastrarCandidato() throws Exception {
		
		this.candidatoParseControle.addInstancia(campo);
		this.candidatoParseControle.registeringInstances();
		this.candidatoParseControle.resetar();
		
		Candidate candidatoCadastrado = this.candidateDAO.getObjectArrayListFromDatabase().get(0);
		
		Assert.assertEquals(this.campo[NOME], candidatoCadastrado.getCandidateName());
		Assert.assertEquals(this.campo[TITULO_ELEITORAL],candidatoCadastrado.getCandidateElectoralTitle());
	}
	
	@Test
	public void naoDeveCadastrarDoisCandidatosIguais() throws Exception {
		
		this.candidatoParseControle.addInstancia(campo);
		this.candidatoParseControle.addInstancia(campo);
		this.candidatoParseControle.registeringInstances();
		this.candidatoParseControle.resetar();
		
		int numeroCandidatosCadastrados = this.candidateDAO.getObjectArrayListFromDatabase().size();
		
		Assert.assertEquals(1, numeroCandidatosCadastrados);
	}
	
	private void iniciarIndices() {
		
		this.candidatoIndicesParse.setIndiceNome(NOME);
		this.candidatoIndicesParse.setIndiceTituloEleitoral(TITULO_ELEITORAL);
	}
	
	private void iniciarCampos() {
		
		this.campo[NOME] = "LUCIA HELENA DE CARVALHO";
		this.campo[TITULO_ELEITORAL] = "123456";
	}
	
}