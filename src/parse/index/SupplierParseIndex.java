package parse.index;

import model.beans.Supplier;

public class SupplierParseIndex extends IndicesParse<Supplier> {
	
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
	public SupplierParseIndex() {
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
	protected void setValidIndex(Supplier supplier, String[] campo) {
		if(validIndex(this.indiceCpf_Cnpj)) {
			supplier.setSupplierPersonRegister(campo[this.indiceCpf_Cnpj]);
		}
		if(validIndex(this.indiceNome)) {
			supplier.setSupplierName(campo[this.indiceNome]);
		}
		if(validIndex(this.indiceUf)) {
			supplier.setSupplierCountryState(campo[this.indiceUf]);
		}
		if(validIndex(this.indiceSituacaoCadastral)) {
			supplier.setSupplierRegisterSituation(campo[this.indiceSituacaoCadastral]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Supplier
	 */
	@Override
	protected void setEmptyInAllSetters(Supplier supplier) {
		supplier.setSupplierPersonRegister(Supplier.EMPTY_TYPE_STRING);
		supplier.setSupplierName(Supplier.EMPTY_TYPE_STRING);
		supplier.setSupplierCountryState(Supplier.EMPTY_TYPE_STRING);
		supplier.setSupplierRegisterSituation(Supplier.EMPTY_TYPE_STRING);
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