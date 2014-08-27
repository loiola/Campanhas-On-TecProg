package parse.indices;

import modelo.beans.Resultado;

public class ResultadoIndicesParse extends IndicesParse<Resultado> {
	
	/*
	 * Class to control the contents of information inherent to the results
	 */

	// Attributes
	private int indiceCodigo;
	private int indiceDescricao;
	
	// Constructors
	public ResultadoIndicesParse() {
		this.indiceCodigo = INDICE_INVALIDO;
		this.indiceDescricao = INDICE_INVALIDO;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the results in the file
	 * @param an instance of the Class Result
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Resultado resultado, String[] campo) {
		if (indiceValido(this.indiceCodigo)) {
			resultado.setCodigo(Integer.parseInt(campo[this.indiceCodigo]));
		}
		if (indiceValido(this.indiceDescricao)) {
			resultado.setDescricao(campo[this.indiceDescricao]);
		}	
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Result
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Resultado resultado) {
		resultado.setCodigo(Resultado.INTEGER_VAZIO);
		resultado.setDescricao(Resultado.STRING_VAZIO);
	}

	// Mutators for indexes of the array of fields
	public void setIndiceCodigo(int indiceCodigo) {
		this.indiceCodigo = indiceCodigo;
	}

	public void setIndiceDescricao(int indiceDescricao) {
		this.indiceDescricao = indiceDescricao;
	}
	
}