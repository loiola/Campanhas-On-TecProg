package parse.indices;

import model.beans.Supplier;

public class FornecedorIndicesParse extends IndicesParse<Supplier> {
	
	/*
	 * Class to control the contents of information inherent to the suppliers
	 */

	// Constants
	public static final int INDICE_INVALIDO = -1;

	// Attributes
	private int indiceCpf_Cnpj;
	private int indiceNome;
	private int indiceUf;
	private int indiceSituacaoCadastral;
	
	// Constructors
	public FornecedorIndicesParse() {
		this.indiceCpf_Cnpj = INDICE_INVALIDO;
		this.indiceNome = INDICE_INVALIDO;
		this.indiceUf = INDICE_INVALIDO;
		this.indiceSituacaoCadastral = INDICE_INVALIDO;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the suppliers in the file
	 * @param an instance of the Class Supplier
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Supplier supplier, String[] campo) {
		if(indiceValido(this.indiceCpf_Cnpj)) {
			supplier.setCpf_cnpj(campo[this.indiceCpf_Cnpj]);
		}
		if(indiceValido(this.indiceNome)) {
			supplier.setNome(campo[this.indiceNome]);
		}
		if(indiceValido(this.indiceUf)) {
			supplier.setUf(campo[this.indiceUf]);
		}
		if(indiceValido(this.indiceSituacaoCadastral)) {
			supplier.setSituacaoCadastral(campo[this.indiceSituacaoCadastral]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Supplier
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Supplier supplier) {
		supplier.setCpf_cnpj(Supplier.STRING_VAZIO);
		supplier.setNome(Supplier.STRING_VAZIO);
		supplier.setUf(Supplier.STRING_VAZIO);
		supplier.setSituacaoCadastral(Supplier.STRING_VAZIO);
	}

	// Mutators for indexes of the array of fields
	public void setIndiceCpf_Cnpj(int indiceCpf_Cnpj) {
		this.indiceCpf_Cnpj = indiceCpf_Cnpj;
	}

	public void setIndiceNome(int indiceNome) {
		this.indiceNome = indiceNome;
	}

	public void setIndiceUf(int indiceUf) {
		this.indiceUf = indiceUf;
	}

	public void setIndiceSituacaoCadastral(int indiceSituacaoCadastral) {
		this.indiceSituacaoCadastral = indiceSituacaoCadastral;
	}
	
}