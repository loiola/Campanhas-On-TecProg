package test.parse.register;

import java.sql.SQLException;

import org.junit.Test;

import parse.ParseException;
import parse.register.campaign.RegisterToParseCampaign;
import test.TemplateTest;

public class CadastroCampanhaParseTeste extends TemplateTest {

	private RegisterToParseCampaign cadastro;
	String  tipoArquivo = "campanha";
	String  ano         = "2006";
	
	@Override
	public void beforeTest() throws Exception {
		
		this.cadastro = new RegisterToParseCampaign(this.tipoArquivo, this.ano);	
	}

	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void deveRetornarUmaCampanhaCadastrado() throws ParseException, SQLException {
		
		String campo[] = new String[50];
		campo[2] = "2006";
		campo[12] = "45555";
		campo[13] = "NOME URNA";
		campo[5] = "DF";
		campo[8] = "1";
		campo[26] = "55325424149";
		campo[16] = "45";
		campo[40] = "2";
		campo[39] = "450000.0";
		cadastro.runFileLine(campo);
		cadastro.registerInstances();
	}

}
