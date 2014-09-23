package parse.index;

import model.beans.Position;

public class PositionParseIndex extends IndicesParse<Position> {
	
	/*
	 * Class to control the contents of information inherent to the positions
	 */
	
	// Attributes
	private int indiceCodigo;
	private int indiceDescricao;

	// Constructors
	public PositionParseIndex() {
		super();
		this.indiceCodigo = INVALID_INDEX;
		this.indiceDescricao = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the positions in the file
	 * @param an instance of Class Position
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Position position, String campo[]) {
		if(validIndex(this.indiceCodigo)) {
			position.setPositionCode(Integer.parseInt(campo[this.indiceCodigo]));
		}
		if(validIndex(this.indiceDescricao)) {
			position.setPositionDescription(campo[this.indiceDescricao]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Position
	 */
	@Override
	protected void setEmptyInAllSetters(Position position) {
		position.setPositionCode(Position.EMPTY_TYPE_INTEGER);
		position.setPositionDescription(Position.EMPTY_TYPE_STRING);
	}

	// Mutators for indexes of the array of fields
	public void setIndiceCodigo(int indiceCodigo) {
		this.indiceCodigo = indiceCodigo;
	}

	public void setIndiceDescricao(int indiceDescricao) {
		this.indiceDescricao = indiceDescricao;
	}

}