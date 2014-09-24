package parse.index;

import model.beans.Party;

public class PartyParseIndex extends ParseIndex<Party> {
	
	/*
	 * Class to control the contents of information inherent to the political parties
	 */

	// Attributes
	private int indiceSigla;
	private int indiceNumero;
	private int indiceDeferimento;
	private int indiceNome;
	
	// Constructors
	public PartyParseIndex() {
		super();
		this.indiceSigla = INVALID_INDEX;
		this.indiceNumero = INVALID_INDEX;
		this.indiceDeferimento = INVALID_INDEX;
		this.indiceNome = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the political parties in the file
	 * @param an instance of the Class Party
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Party party, String campo[]) {
		if(validIndex(this.indiceSigla)) {
			party.setPartyAcronym(campo[this.indiceSigla]);
		}
		if(validIndex(this.indiceNumero)) {
			party.setPartyNumber(Integer.parseInt(campo[this.indiceNumero]));
		}
		if(validIndex(this.indiceDeferimento)){
			party.setPartyConcession(campo[this.indiceDeferimento]);
		}
		if(validIndex(this.indiceNome)){
			party.setPartyName(campo[this.indiceNome]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Party
	 */
	@Override
	protected void setEmptyInAllSetters(Party party) {
		party.setPartyAcronym(Party.EMPTY_TYPE_STRING);
		party.setPartyNumber(Party.EMPTY_TYPE_INTEGER);
		party.setPartyName(Party.EMPTY_TYPE_STRING);
		party.setPartyConcession(Party.EMPTY_TYPE_STRING);
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