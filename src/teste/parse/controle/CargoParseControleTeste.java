package teste.parse.controle;

import modelo.beans.Position;
import modelo.dao.CargoDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.controle.CargoParseControle;
import parse.indices.CargoIndicesParse;
import teste.TemplateTeste;

public class CargoParseControleTeste extends TemplateTeste {

	public static final int CODIGO = 0;
	public static final int DESCRICAO = 1;
	
	private String campo[];
	private CargoDAO cargoDAO;
	private CargoIndicesParse cargoIndicesParse;
	private CargoParseControle cargoParseControle;

	@Override
	public void beforeTest() throws Exception {
		this.campo = new String[2];
		this.cargoDAO = new CargoDAO();
		this.cargoIndicesParse = new CargoIndicesParse();
		this.cargoParseControle = new CargoParseControle(this.cargoIndicesParse);
		
		iniciarCampos();
		iniciarIndices();
	}
	
	@Override
	public void afterTest() throws Exception {
		
	}

	@Test
	public void cadastrarCargo() throws Exception {
		
		this.cargoParseControle.addInstancia(campo);
		this.cargoParseControle.cadastrarInstancias();
		this.cargoParseControle.resetar();
		
		Position cargoCadastrado = this.cargoDAO.getLista().get(0);
				
		Assert.assertEquals(this.campo[CODIGO], cargoCadastrado.getPositionCode().toString());
		Assert.assertEquals(this.campo[DESCRICAO], cargoCadastrado.getPositionDescription());
	}
	
	@Test
	public void naoDeveCadastrarDoisCargosIguais() throws Exception {
		
		this.cargoParseControle.addInstancia(campo);
		this.cargoParseControle.addInstancia(campo);
		this.cargoParseControle.cadastrarInstancias();
		this.cargoParseControle.resetar();
		
		int numeroCargosCadastrados = this.cargoDAO.getLista().size();
		
		Assert.assertEquals(1, numeroCargosCadastrados);
	}
	
	private void iniciarIndices() {
		
		this.cargoIndicesParse.setIndiceCodigo(CODIGO);
		this.cargoIndicesParse.setIndiceDescricao(DESCRICAO);
	}
	
	private void iniciarCampos() {
		
		this.campo[CODIGO] = "125";
		this.campo[DESCRICAO] = "CARGO INEXISTENTE";
	}

}
