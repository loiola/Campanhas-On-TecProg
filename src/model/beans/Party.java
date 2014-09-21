package model.beans;

public class Party {
	
	/*
	 * Model class Party.java
	 * This class is responsable for getting the Political Party's informations
	 */ 
	
	// Constants 
	public static final String STRING_VAZIO = "";
	public static final Integer INTEGER_VAZIO = 0;

	// Attributes
	private Integer numero;
	private String sigla;
	private String deferimento;
	private String nome;
	
	// Empty constructors
	public Party() {
		this.nome = STRING_VAZIO;
		this.sigla = STRING_VAZIO;
		this.numero = INTEGER_VAZIO;
		this.deferimento = STRING_VAZIO;
	}
	
	// Getters and Setters
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDeferimento() {
		return deferimento;
	}

	public void setDeferimento(String deferimento) {
		this.deferimento = deferimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Party))
			return false;
		
		Party outroPartido = (Party) object;
		return this.sigla.equals(outroPartido.getSigla());
	}
}
