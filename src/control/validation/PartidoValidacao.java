package control.validation;

import control.exception.PartidoExcecao;
import model.beans.Party;

public class PartidoValidacao {
	
	/*
	 * Validation class electoral party, which mediates the application layer with the model
	 */

	// Attributes
	private static final String SIGLA_VAZIA = "Campo Sigla do Party vazia!";
	private static final String NUMERO_PARTIDO_VAZIO = "Campo Número do Party vazio!";

	// Constructors
	public PartidoValidacao() {

	}

	// Other methods
	/*
	 * Method that checks if the abbreviation entered is zero and returns a positive error case 
	 * @param a political party
	 */
	public void siglaNaoNula(Party party) throws PartidoExcecao {
		if((party.getPartyAcronym() == null)) {
			throw new PartidoExcecao(SIGLA_VAZIA);
		}
	}
	
	/*
	 * Method that checks if the entered number is null and returns a positive error if
	 * @param a political party
	 */
	public void numeroNaoNulo(Party party) throws PartidoExcecao {
		if((party.getPartyNumber() == null)) {
			throw new PartidoExcecao(NUMERO_PARTIDO_VAZIO);
		}
	}
}
