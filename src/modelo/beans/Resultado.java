package modelo.beans;

public class Resultado {

	/*
	 * Class Resultado.java
	 * This class is responsable for getting the Result's informations
	 */
	
	// Constants
	public static final String STRING_VAZIO = "";
	public static final int INTEGER_VAZIO = 0;
	
	// Attributes
	private Integer codigo;
	private String  descricao;
	
	// Empty constructor
	public Resultado() {
		this.codigo = INTEGER_VAZIO;
		this.descricao = STRING_VAZIO;
	}
	
	// Getters and Setters
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Resultado))
			return false;
		
		Resultado outroResultado = (Resultado) object;
		return this.descricao.equalsIgnoreCase(outroResultado.getDescricao());
	}
}
