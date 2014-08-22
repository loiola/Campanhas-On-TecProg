package modelo.beans;

public class Candidato {
	
	// Constants
	public static final String  STRING_VAZIO = "";
	public static final Integer  INTEGER_VAZIO = 0;
	
	// Attributes
	private String nome;
	private String tituloEleitoral;
	
	// Empty Constructor
	public Candidato() {
		this.nome = STRING_VAZIO;
		this.tituloEleitoral = STRING_VAZIO;
	}

	// Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTituloEleitoral() {
		return tituloEleitoral;
	}

	public void setTituloEleitoral(String tituloEleitoral) {
		this.tituloEleitoral = tituloEleitoral;
	}
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Candidato))
			return false;

		Candidato outroCandidato = (Candidato) object;

		return this.tituloEleitoral.equals(outroCandidato.getTituloEleitoral());
	}
}
