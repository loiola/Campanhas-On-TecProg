package parse.indices;

import modelo.beans.Despesa;
import modelo.beans.Fornecedor;

public class DespesaIndicesParse extends MovimentacaoFinanceiraIndicesParse<Despesa> {
	
	/*
	 * Class to control the contents of information inherent to the expenses
	 */

	// Attributes
	private int indiceFornecedorNome;
	private int indiceFornecedorCpfCnpj;
	private int indiceTipoDocumento;
	
	// Constructors
	public DespesaIndicesParse(String ano) {
		super(ano);
		this.indiceFornecedorNome = INDICE_INVALIDO;
		this.indiceFornecedorCpfCnpj = INDICE_INVALIDO;
		this.indiceTipoDocumento = INDICE_INVALIDO;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the expenses in the file
	 * @param an instance of the class Expense
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Despesa despesa, String[] campo) {
		super.setIndicesValidos(despesa, campo);
		Fornecedor fornecedor = new Fornecedor();

		if(indiceValido(this.indiceFornecedorNome)) {
			fornecedor.setNome(campo[this.indiceFornecedorNome]);
		}
		if(indiceValido(this.indiceFornecedorCpfCnpj)) {
			fornecedor.setCpf_cnpj(campo[this.indiceFornecedorCpfCnpj]);
		}
		if(indiceValido(this.indiceTipoDocumento)) {
			despesa.setTipoDocumento(campo[this.indiceTipoDocumento]);
		}
		despesa.setFornecedor(fornecedor);

	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Expense
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Despesa despesa) {
		super.setVazioEmTodosOsSetters(despesa);
		despesa.setFornecedor((Fornecedor)Despesa.OBJETO_VAZIO);
		despesa.setTipoDocumento(Despesa.STRING_VAZIO);
	}
	
	//Mutators for indexes of the array of fields
	public void setIndiceFornecedorNome(int indiceFornecedorNome) {
		this.indiceFornecedorNome = indiceFornecedorNome;
	}
	
	public void setIndiceFornecedorCpfCnpj(int indiceFornecedorCpfCnpj) {
		this.indiceFornecedorCpfCnpj = indiceFornecedorCpfCnpj;
	}

	public void setIndiceTipoDocumento(int indiceTipoDocumento) {
		this.indiceTipoDocumento = indiceTipoDocumento;
	}
	
}