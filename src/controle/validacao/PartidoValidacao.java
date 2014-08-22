package controle.validacao;

import controle.excecao.PartidoExcecao;
import modelo.beans.Partido;

public class PartidoValidacao {
	
	/*
	 * Validation class electoral party, which mediates the application layer with the model
	 */

	// Attributes
	private static final String SIGLA_VAZIA = "Campo Sigla do Partido vazia!";
	private static final String NUMERO_PARTIDO_VAZIO = "Campo Número do Partido vazio!";

	// Constructors
	public PartidoValidacao() {

	}

	// Other methods
	/*
	 * Method that checks if the abbreviation entered is zero and returns a positive error case 
	 * @param a political party
	 */
	public void siglaNaoNula(Partido partido) throws PartidoExcecao {
		if((partido.getSigla() == null)) {
			throw new PartidoExcecao(SIGLA_VAZIA);
		}
	}
	
	/*
	 * Method that checks if the entered number is null and returns a positive error if
	 * @param a political party
	 */
	public void numeroNaoNulo(Partido partido) throws PartidoExcecao {
		if((partido.getNumero() == null)) {
			throw new PartidoExcecao(NUMERO_PARTIDO_VAZIO);
		}
	}
}
