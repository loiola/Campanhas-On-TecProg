package parse.indices;

import model.beans.Donor;
import model.beans.Revenue;

public class ReceitaIndicesParse extends MovimentacaoFinanceiraIndicesParse<Revenue> {
	
	/*
	 * Class to control the contents of information inherent to the revenue
	 */

	// Attributes
	private int indiceReciboEleitoral;
	private int indiceDoadorNome;
	private int indiceDoadorCpfCnpj;
	
	// Constructors
	public ReceitaIndicesParse(String ano) {
		super(ano);
		this.indiceReciboEleitoral = INDICE_INVALIDO;
		this.indiceDoadorNome = INDICE_INVALIDO;
		this.indiceDoadorCpfCnpj = INDICE_INVALIDO; 

	}
	
	/*
	 * This method formalizes the indices for reading the information about the revenue in the file
	 * @param an instance of the Class Revenue
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Revenue revenue, String[] campo) {
		super.setIndicesValidos(revenue, campo);
		
		Donor donor = new Donor();
		if(indiceValido(this.indiceReciboEleitoral)) {
			revenue.setReciboEleitoral(campo[this.indiceReciboEleitoral]);
		}
		if(indiceValido(this.indiceDoadorNome)) {
			donor.setNome(campo[this.indiceDoadorNome]);
		}
		if(indiceValido(this.indiceDoadorCpfCnpj)) {
			donor.setCpf_cnpj(campo[this.indiceDoadorCpfCnpj]);
		}
		revenue.setDoador(donor);

	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Revenue
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Revenue revenue) {
		super.setVazioEmTodosOsSetters(revenue);

		revenue.setReciboEleitoral(Revenue.EMPTY_TYPE_STRING);
		revenue.setDoador((Donor)Revenue.OBJETO_VAZIO);
		
	}

	// Mutators for indexes of the array of fields
	public void setIndiceReciboEleitoral(int indiceReciboEleitoral) {
		this.indiceReciboEleitoral = indiceReciboEleitoral;
	}

	public void setIndiceDoadorNome(int indiceDoadorNome) {
		this.indiceDoadorNome = indiceDoadorNome;
	}
	
	public void setIndiceDoadorCpfCnpj(int indiceDoadorCpfCnpj) {
		this.indiceDoadorCpfCnpj = indiceDoadorCpfCnpj;
	}
	
}