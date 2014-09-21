package modelo.beans;

public class Campaign {
	
	/*
	 * Class Campaign.java
	 * This class is responsable for getting the Campaign's informations
 	 */
	
	// Constants
	public static final String STRING_VAZIO = "";
	public static final Integer INTEGER_VAZIO = 0;
	public static final Result RESULTADO_VAZIO = new Result();
	public static final Party PARTIDO_VAZIO = new Party();
	public static final Position CARGO_VAZIO = new Position();
	public static final Candidate CANDIDATO_VAZIO = new Candidate(); 
	public static final float FLOAT_VAZIO = (float) 0.0;
	
	// Attributes
	private Integer id;
	private Result result;
	private Position position;
	private Party party;
	private Candidate candidate;
	private Integer ano;
	private Integer numeroCandidato;
	private String nomeDeUrna;
	private String uf;
	private Float despesaMaxDeclarada;
	private Float despesaTotalCalculada;
	private Float receitaTotalCalculada;
	
	// Empty constructor
	public Campaign() {
		this.id = INTEGER_VAZIO;
		this.result = RESULTADO_VAZIO;
		this.position = CARGO_VAZIO;
		this.party = PARTIDO_VAZIO;
		this.candidate = CANDIDATO_VAZIO;
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

	public Result getResultado() {
		return result;
	}

	public void setResultado(Result result) {
		this.result = result;
	}

	public Position getCargo() {
		return position;
	}

	public void setCargo(Position position) {
		this.position = position;
	}

	public Party getPartido() {
		return party;
	}

	public void setPartido(Party party) {
		this.party = party;
	}

	public Candidate getCandidato() {
		return candidate;
	}

	public void setCandidato(Candidate candidate) {
		this.candidate = candidate;
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
		if( !(object instanceof Campaign))
			return false;
		else
		{
			Campaign outraCampanha = (Campaign) object;
			return this.ano.equals(outraCampanha.getAno()) &&
				   this.numeroCandidato.equals(outraCampanha.getNumeroCandidato()) &&
				   this.position.equals(outraCampanha.getCargo()) &&
				   this.nomeDeUrna.equals(outraCampanha.getNomeDeUrna());
		}

	}
}
