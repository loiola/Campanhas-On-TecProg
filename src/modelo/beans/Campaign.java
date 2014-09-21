package modelo.beans;

public class Campaign {
	
	/*
	 * Class Campaign.java
	 * This class is responsable for getting the Campaign's informations
 	 */
	
	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final Integer EMPTY_TYPE_INTEGER = 0;
	public static final Result EMPTY_CLASS_RESULT = new Result();
	public static final Party EMPTY_CLASS_PARTY = new Party();
	public static final Position EMPTY_CLASS_POSITION = new Position();
	public static final Candidate EMPTY_CLASS_CANDIDATE = new Candidate(); 
	public static final float EMPTY_TYPE_FLOAT = (float) 0.0;
	
	// Attributes
	private Integer campaignIdentifier;
	private Result campaignResult;
	private Position campaignPosition;
	private Party campaignParty;
	private Candidate campaignCandidate;
	private Integer campaignYear;
	private Integer campaignCandidateNumber;
	private String campaignNameOfUrn;
	private String campaignCountryState;
	private Float campaignMaximumExpenseDeclared;
	private Float campaignTotalExpenseCalculated;
	private Float campaignTotalRevenueCalculated;
	
	// Empty constructor
	public Campaign() {
		this.campaignIdentifier = EMPTY_TYPE_INTEGER;
		this.campaignResult = EMPTY_CLASS_RESULT;
		this.campaignPosition = EMPTY_CLASS_POSITION;
		this.campaignParty = EMPTY_CLASS_PARTY;
		this.campaignCandidate = EMPTY_CLASS_CANDIDATE;
		this.campaignYear = EMPTY_TYPE_INTEGER;
		this.campaignCandidateNumber = EMPTY_TYPE_INTEGER;
		this.campaignNameOfUrn = EMPTY_TYPE_STRING;
		this.campaignCountryState = EMPTY_TYPE_STRING;
		this.campaignMaximumExpenseDeclared = EMPTY_TYPE_FLOAT;
		this.campaignTotalExpenseCalculated = EMPTY_TYPE_FLOAT;
		this.campaignTotalRevenueCalculated = EMPTY_TYPE_FLOAT;
	}
	
	// Getters and Setters
	public Integer getId() {
		return campaignIdentifier;
	}

	public void setId(Integer id) {
		this.campaignIdentifier = id;
	}

	public Result getResultado() {
		return campaignResult;
	}

	public void setResultado(Result result) {
		this.campaignResult = result;
	}

	public Position getCargo() {
		return campaignPosition;
	}

	public void setCargo(Position position) {
		this.campaignPosition = position;
	}

	public Party getPartido() {
		return campaignParty;
	}

	public void setPartido(Party party) {
		this.campaignParty = party;
	}

	public Candidate getCandidato() {
		return campaignCandidate;
	}

	public void setCandidato(Candidate candidate) {
		this.campaignCandidate = candidate;
	}

	public Integer getAno() {
		return campaignYear;
	}

	public void setAno(Integer ano) {
		this.campaignYear = ano;
	}

	public Integer getNumeroCandidato() {
		return campaignCandidateNumber;
	}

	public void setNumeroCandidato(Integer numeroCandidato) {
		this.campaignCandidateNumber = numeroCandidato;
	}

	public String getNomeDeUrna() {
		return campaignNameOfUrn;
	}

	public void setNomeDeUrna(String nomeDeUrna) {
		this.campaignNameOfUrn = nomeDeUrna;
	}

	public String getUf() {
		return campaignCountryState;
	}

	public void setUf(String uf) {
		this.campaignCountryState = uf;
	}

	public Float getDespesaMaxDeclarada() {
		return campaignMaximumExpenseDeclared;
	}

	public void setDespesaMaxDeclarada(Float despesaMaxDeclarada) {
		this.campaignMaximumExpenseDeclared = despesaMaxDeclarada;
	}

	public Float getDespesaTotalCalculada() {
		return campaignTotalExpenseCalculated;
	}

	public void setDespesaTotalCalculada(Float despesaTotalCalculada) {
		this.campaignTotalExpenseCalculated = despesaTotalCalculada;
	}

	public Float getReceitaTotalCalculada() {
		return campaignTotalRevenueCalculated;
	}

	public void setReceitaTotalCalculada(Float receitaTotalCalculada) {
		this.campaignTotalRevenueCalculated = receitaTotalCalculada;
	}
	
	
	@Override
	public boolean equals(Object object) {
		if( !(object instanceof Campaign))
			return false;
		else
		{
			Campaign outraCampanha = (Campaign) object;
			return this.campaignYear.equals(outraCampanha.getAno()) &&
				   this.campaignCandidateNumber.equals(outraCampanha.getNumeroCandidato()) &&
				   this.campaignPosition.equals(outraCampanha.getCargo()) &&
				   this.campaignNameOfUrn.equals(outraCampanha.getNomeDeUrna());
		}

	}
}
