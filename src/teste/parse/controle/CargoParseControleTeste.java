package teste.parse.controle;

import model.beans.Position;
import model.dao.PositionDAO;

import org.junit.Assert;
import org.junit.Test;

import parse.control.CargoParseControle;
import parse.index.CargoIndicesParse;
import teste.TemplateTeste;

public class CargoParseControleTeste extends TemplateTeste {

	public static final int CODIGO = 0;
	public static final int DESCRICAO = 1;
	
	private String campo[];
	private PositionDAO positionDAO;
	private CargoIndicesParse cargoIndicesParse;
	private CargoParseControle cargoParseControle;

	@Override
	public void beforeTest() throws Exception {
		this.campo = new String[2];
		this.positionDAO = new PositionDAO();
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
		this.cargoParseControle.registeringInstances();
		this.cargoParseControle.resetar();
		
		Position cargoCadastrado = this.positionDAO.getObjectArrayListFromDatabase().get(0);
				
		Assert.assertEquals(this.campo[CODIGO], cargoCadastrado.getPositionCode().toString());
		Assert.assertEquals(this.campo[DESCRICAO], cargoCadastrado.getPositionDescription());
	}
	
	@Test
	public void naoDeveCadastrarDoisCargosIguais() throws Exception {
		
		this.cargoParseControle.addInstancia(campo);
		this.cargoParseControle.addInstancia(campo);
		this.cargoParseControle.registeringInstances();
		this.cargoParseControle.resetar();
		
		int numeroCargosCadastrados = this.positionDAO.getObjectArrayListFromDatabase().size();
		
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
