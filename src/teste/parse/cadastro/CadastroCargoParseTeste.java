package teste.parse.cadastro;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.beans.Position;
import model.dao.PositionDAO;

import org.junit.Test;

import parse.ParseException;
import parse.cadastro.campanha.CadastroCargoParse;
import teste.TemplateTeste;

public class CadastroCargoParseTeste extends TemplateTeste {

	private CadastroCargoParse cadastro;
	private PositionDAO positionDAO;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new CadastroCargoParse(this.tipoArquivo, this.ano);	
		this.positionDAO = new PositionDAO();
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRetornarUmCandidatoCadastrado() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[8] = "1";
		campo[9] = "CARGO TESTE";
		cadastro.executarLinhaDoArquivo(campo);
		cadastro.cadastrarInstancias();
		
		Position position = this.positionDAO.getPositionByCode(1);
		assertEquals(position.getPositionCode().toString(), "1");
	}

}
