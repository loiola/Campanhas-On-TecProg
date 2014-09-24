package parse.index;

import model.beans.Result;

public class ResultParseIndex extends ParseIndex<Result> {
	
	/*
	 * Class to control the contents of information inherent to the results
	 */

	// Attributes
	private int indiceCodigo;
	private int indiceDescricao;
	
	// Constructors
	public ResultParseIndex() {
		this.indiceCodigo = INVALID_INDEX;
		this.indiceDescricao = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the results in the file
	 * @param an instance of the Class Result
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Result result, String[] campo) {
		if (validIndex(this.indiceCodigo)) {
			result.setResultType(Integer.parseInt(campo[this.indiceCodigo]));
		}
		if (validIndex(this.indiceDescricao)) {
			result.setResultDescription(campo[this.indiceDescricao]);
		}	
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Result
	 */
	@Override
	protected void setEmptyInAllSetters(Result result) {
		result.setResultType(Result.EMPTY_TYPE_INTEGER);
		result.setResultDescription(Result.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndiceCodigo(int indiceCodigo) {
		this.indiceCodigo = indiceCodigo;
	}

	public void setIndiceDescricao(int indiceDescricao) {
		this.indiceDescricao = indiceDescricao;
	}
	
}