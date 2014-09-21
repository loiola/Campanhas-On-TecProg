package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Candidate;
import model.dao.CandidatoDAO;

import org.junit.Test;

import parse.ParseException;
import parse.cadastro.campanha.CadastroCandidatoParse;
import teste.TemplateTeste;

public class CadastroCandidatoParseTeste extends TemplateTeste {

	private CadastroCandidatoParse cadastro;
	private CandidatoDAO candidatoDAO;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new CadastroCandidatoParse(this.tipoArquivo, this.ano);	
		this.candidatoDAO = new CandidatoDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRetornarUmCandidatoCadastrado() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[10] = "CANDIDATO TESTE";
		campo[26] = "55325424149";
		cadastro.executarLinhaDoArquivo(campo);
		cadastro.cadastrarInstancias();
		
		Candidate candidate = this.candidatoDAO.getCandidatoPeloTitulo("55325424149");
		assertEquals(candidate.getCandidateElectoralTitle(), "55325424149");
	}

}
