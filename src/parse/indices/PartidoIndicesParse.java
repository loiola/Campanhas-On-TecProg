package parse.indices;

import modelo.beans.Party;

public class PartidoIndicesParse extends IndicesParse<Party> {
	
	/*
	 * Class to control the contents of information inherent to the political parties
	 */

	// Attributes
	private int indiceSigla;
	private int indiceNumero;
	private int indiceDeferimento;
	private int indiceNome;
	
	// Constructors
	public PartidoIndicesParse() {
		super();
		this.indiceSigla = INDICE_INVALIDO;
		this.indiceNumero = INDICE_INVALIDO;
		this.indiceDeferimento = INDICE_INVALIDO;
		this.indiceNome = INDICE_INVALIDO;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the political parties in the file
	 * @param an instance of the Class Party
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Party party, String campo[]) {
		if(indiceValido(this.indiceSigla)) {
			party.setSigla(campo[this.indiceSigla]);
		}
		if(indiceValido(this.indiceNumero)) {
			party.setNumero(Integer.parseInt(campo[this.indiceNumero]));
		}
		if(indiceValido(this.indiceDeferimento)){
			party.setDeferimento(campo[this.indiceDeferimento]);
		}
		if(indiceValido(this.indiceNome)){
			party.setNome(campo[this.indiceNome]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Party
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Party party) {
		party.setSigla(Party.STRING_VAZIO);
		party.setNumero(Party.INTEGER_VAZIO);
		party.setNome(Party.STRING_VAZIO);
		party.setDeferimento(Party.STRING_VAZIO);
	}
	
	// Mutators for indexes of the array of fields
	public void setIndiceSigla(int indiceSigla) {
		this.indiceSigla = indiceSigla;
	}

	public void setIndiceNumero(int indiceNumero) {
		this.indiceNumero = indiceNumero;
	}

	public void setIndiceDeferimento(int indiceDeferimento) {
		this.indiceDeferimento = indiceDeferimento;
	}

	public void setIndiceNome(int indiceNome) {
		this.indiceNome = indiceNome;
	}

}