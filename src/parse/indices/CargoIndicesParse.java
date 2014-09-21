package parse.indices;

import model.beans.Position;

public class CargoIndicesParse extends IndicesParse<Position> {
	
	/*
	 * Class to control the contents of information inherent to the positions
	 */
	
	// Attributes
	private int indiceCodigo;
	private int indiceDescricao;

	// Constructors
	public CargoIndicesParse() {
		super();
		this.indiceCodigo = INDICE_INVALIDO;
		this.indiceDescricao = INDICE_INVALIDO;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the positions in the file
	 * @param an instance of Class Position
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Position position, String campo[]) {
		if(indiceValido(this.indiceCodigo)) {
			position.setPositionCode(Integer.parseInt(campo[this.indiceCodigo]));
		}
		if(indiceValido(this.indiceDescricao)) {
			position.setPositionDescription(campo[this.indiceDescricao]);
		}
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Position
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Position position) {
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