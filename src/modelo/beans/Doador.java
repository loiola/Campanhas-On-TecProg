package modelo.beans;

public class Doador {
	
	/*
	 * Model Class Doador.java
	 * This class is responsable for getting the Donor's informations
	 */
	 
	// Constants
	public static final String STRING_VAZIO = "";
	
	// Attributes
	private String cpf_cnpj;
	private String nome;
	private String uf;
	private String situacaoCadastral;
	
	// Empty Constructor
	public Doador() {
		this.cpf_cnpj = STRING_VAZIO;
		this.nome = STRING_VAZIO;
		this.uf = STRING_VAZIO;
		this.situacaoCadastral = STRING_VAZIO;
	}
	
	// Getters and Setters
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSituacaoCadastral() {
		return situacaoCadastral;
	}

	public void setSituacaoCadastral(String situacaoCadastral) {
		this.situacaoCadastral = situacaoCadastral;
	}
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Doador))
			return false;
		
		Doador outroDoador = (Doador) object;
		return this.getNome().equalsIgnoreCase(outroDoador.getNome()) &&
			   this.getCpf_cnpj().equalsIgnoreCase(outroDoador.getCpf_cnpj());
	}
}
