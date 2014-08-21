package parse.indices;

import modelo.beans.Candidato;

public class CandidatoIndicesParse extends IndicesParse<Candidato> {
	
	/*
	 * Class to control the contents of information inherent to the candidates
	 */

	// Attributes
	private int indiceNome;
	private int indiceTituloEleitoral;

	// Constructors
	public CandidatoIndicesParse() {
		this.indiceNome = INDICE_INVALIDO;
		this.indiceTituloEleitoral = INDICE_INVALIDO;
	}

	/*
	 * This method formalizes the indices for reading the information about the candidates in the file
	 * @param an instance of Class Candidate
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Candidato candidato, String[] campo) {
		if (indiceValido(this.indiceNome)) {
			candidato.setNome(campo[this.indiceNome]);
		}
		if (indiceValido(this.indiceTituloEleitoral)) {
			candidato.setTituloEleitoral(campo[this.indiceTituloEleitoral]);

		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Candidate
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Candidato candidato) {
		candidato.setNome(Candidato.STRING_VAZIO);
		candidato.setTituloEleitoral(Candidato.STRING_VAZIO);
	}

	//Mutators for indexes of the array of fields
	public void setIndiceTituloEleitoral(int indiceTituloEleitoral) {
		this.indiceTituloEleitoral = indiceTituloEleitoral;
	}

	public void setIndiceNome(int indiceNome) {
		this.indiceNome = indiceNome;
	}

}