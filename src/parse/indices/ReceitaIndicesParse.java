package parse.indices;

import modelo.beans.Doador;
import modelo.beans.Receita;

public class ReceitaIndicesParse extends MovimentacaoFinanceiraIndicesParse<Receita> {
	
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
	protected void setIndicesValidos(Receita receita, String[] campo) {
		super.setIndicesValidos(receita, campo);
		
		Doador doador = new Doador();
		if(indiceValido(this.indiceReciboEleitoral)) {
			receita.setReciboEleitoral(campo[this.indiceReciboEleitoral]);
		}
		if(indiceValido(this.indiceDoadorNome)) {
			doador.setNome(campo[this.indiceDoadorNome]);
		}
		if(indiceValido(this.indiceDoadorCpfCnpj)) {
			doador.setCpf_cnpj(campo[this.indiceDoadorCpfCnpj]);
		}
		receita.setDoador(doador);

	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Revenue
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Receita receita) {
		super.setVazioEmTodosOsSetters(receita);

		receita.setReciboEleitoral(Receita.STRING_VAZIO);
		receita.setDoador((Doador)Receita.OBJETO_VAZIO);
		
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