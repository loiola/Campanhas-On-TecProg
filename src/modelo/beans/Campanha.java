package modelo.beans;

public class Campanha {
	
	/*
	 * Class Campanha.java
	 * This class is responsable for getting the Campaign's informations
 	 */
	
	// Constants
	public static final String STRING_VAZIO = "";
	public static final Integer INTEGER_VAZIO = 0;
	public static final Resultado RESULTADO_VAZIO = new Resultado();
	public static final Partido PARTIDO_VAZIO = new Partido();
	public static final Cargo CARGO_VAZIO = new Cargo();
	public static final Candidato CANDIDATO_VAZIO = new Candidato(); 
	public static final float FLOAT_VAZIO = (float) 0.0;
	
	// Attributes
	private Integer id;
	private Resultado resultado;
	private Cargo cargo;
	private Partido partido;
	private Candidato candidato;
	private Integer ano;
	private Integer numeroCandidato;
	private String nomeDeUrna;
	private String uf;
	private Float despesaMaxDeclarada;
	private Float despesaTotalCalculada;
	private Float receitaTotalCalculada;
	
	// Empty constructor
	public Campanha() {
		this.id = INTEGER_VAZIO;
		this.resultado = RESULTADO_VAZIO;
		this.cargo = CARGO_VAZIO;
		this.partido = PARTIDO_VAZIO;
		this.candidato = CANDIDATO_VAZIO;
		this.ano = INTEGER_VAZIO;
		this.numeroCandidato = INTEGER_VAZIO;
		this.nomeDeUrna = STRING_VAZIO;
		this.uf = STRING_VAZIO;
		this.despesaMaxDeclarada = FLOAT_VAZIO;
		this.despesaTotalCalculada = FLOAT_VAZIO;
		this.receitaTotalCalculada = FLOAT_VAZIO;
	}
	
	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getNumeroCandidato() {
		return numeroCandidato;
	}

	public void setNumeroCandidato(Integer numeroCandidato) {
		this.numeroCandidato = numeroCandidato;
	}

	public String getNomeDeUrna() {
		return nomeDeUrna;
	}

	public void setNomeDeUrna(String nomeDeUrna) {
		this.nomeDeUrna = nomeDeUrna;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Float getDespesaMaxDeclarada() {
		return despesaMaxDeclarada;
	}

	public void setDespesaMaxDeclarada(Float despesaMaxDeclarada) {
		this.despesaMaxDeclarada = despesaMaxDeclarada;
	}

	public Float getDespesaTotalCalculada() {
		return despesaTotalCalculada;
	}

	public void setDespesaTotalCalculada(Float despesaTotalCalculada) {
		this.despesaTotalCalculada = despesaTotalCalculada;
	}

	public Float getReceitaTotalCalculada() {
		return receitaTotalCalculada;
	}

	public void setReceitaTotalCalculada(Float receitaTotalCalculada) {
		this.receitaTotalCalculada = receitaTotalCalculada;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Campanha))
			return false;
		else
		{
			Campanha outraCampanha = (Campanha) object;
			return this.ano.equals(outraCampanha.getAno()) &&
				   this.numeroCandidato.equals(outraCampanha.getNumeroCandidato()) &&
				   this.cargo.equals(outraCampanha.getCargo()) &&
				   this.nomeDeUrna.equals(outraCampanha.getNomeDeUrna());
		}

	}
}
