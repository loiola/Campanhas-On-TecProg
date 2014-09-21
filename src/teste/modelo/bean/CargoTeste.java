package teste.modelo.bean;

import static teste.modelo.bean.BeanTeste.instanciarCargo;
import static teste.modelo.bean.BeanTeste.instanciarFornecedor;
import modelo.beans.Position;
import modelo.beans.Supplier;

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
		cargo2.setPositionDescription(BeanTeste.STRING_TESTE_2);
		Assert.assertFalse(position.equals(cargo2));
	}
	
	@Test
	public void equalsDeveRetornarFalsoSeNaoCompararComCargo() {
		
		Position position = instanciarCargo();
		Supplier supplier = instanciarFornecedor();
		
		Assert.assertFalse(position.equals(supplier));
		Assert.assertFalse(supplier.equals(position));
		Assert.assertEquals(BeanTeste.INT_TESTE,position.getPositionCode());
		Assert.assertEquals(BeanTeste.STRING_TESTE,supplier.getNome());
		Assert.assertEquals(BeanTeste.STRING_TESTE,supplier.getUf());
		Assert.assertEquals(BeanTeste.STRING_TESTE,supplier.getSituacaoCadastral());
	}

}
