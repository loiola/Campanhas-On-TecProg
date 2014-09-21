package teste.modelo.bean;

import static teste.modelo.bean.BeanTeste.instanciarCargo;
import static teste.modelo.bean.BeanTeste.instanciarFornecedor;
import modelo.beans.Position;
import modelo.beans.Fornecedor;

import org.junit.Assert;
import org.junit.Test;

public class CargoTeste {

	@Test
	public void equalsDeveRetornarVerdadeiroSeForemCargosIguais() {
		
		Position position = instanciarCargo();
		Position cargo2 = instanciarCargo();	
		Assert.assertTrue(position.equals(cargo2));
	}
	
	@Test
	public void equalsDeveRetornarFalsoSeForemCargosDiferentes() {
		
		Position position = instanciarCargo();
		Position cargo2 = instanciarCargo();
		cargo2.setDescricao(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(position.equals(cargo2));
	}
	
	@Test
	public void equalsDeveRetornarFalsoSeNaoCompararComCargo() {
		
		Position position = instanciarCargo();
		Fornecedor fornecedor = instanciarFornecedor();
		
		Assert.assertFalse(position.equals(fornecedor));
		Assert.assertFalse(fornecedor.equals(position));
		Assert.assertEquals(BeanTeste.INT_TESTE,position.getCodigo());
		Assert.assertEquals(BeanTeste.STRING_TESTE,fornecedor.getNome());
		Assert.assertEquals(BeanTeste.STRING_TESTE,fornecedor.getUf());
		Assert.assertEquals(BeanTeste.STRING_TESTE,fornecedor.getSituacaoCadastral());
	}

}
