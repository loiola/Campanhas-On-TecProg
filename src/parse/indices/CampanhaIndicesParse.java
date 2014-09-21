package parse.indices;

import model.beans.Campaign;
import model.beans.Candidate;
import model.beans.Party;
import model.beans.Position;
import model.beans.Result;

public class CampanhaIndicesParse extends IndicesParse<Campaign> {

	/*
	 * Class to control the indices of the information inherent in electoral campaigns
	 */
	
	// Attributes
	private int indiceResultadoCod;
	private int indiceCargoCod;
	private int indicePartidoNumero;
	private int indiceCandidatoTitulo;
	private int indiceAno;
	private int indiceNumeroCandidato;
	private int indiceNomeDeUrna;
	private int indiceUf;
	private int indiceDespesaMaxDeclarada;

	// Constructors
	public CampanhaIndicesParse() {
		this.indiceResultadoCod = INDICE_INVALIDO;	
		this.indiceCargoCod = INDICE_INVALIDO;	
		this.indicePartidoNumero = INDICE_INVALIDO;	
		this.indiceCandidatoTitulo = INDICE_INVALIDO;	
		this.indiceAno = INDICE_INVALIDO;	
		this.indiceNumeroCandidato = INDICE_INVALIDO;	
		this.indiceNomeDeUrna = INDICE_INVALIDO;	
		this.indiceUf = INDICE_INVALIDO;	
		this.indiceDespesaMaxDeclarada = INDICE_INVALIDO;	
	}
	
	// Other methods
	/*
	 * This method formalizes the indices for reading the information about the campaigns in the file
	 * @param an instance of Class Campaign
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(Campaign campaign, String[] campo) {
		if (indiceValido(this.indiceResultadoCod)) {
			Result result = new Result();
			result.setCodigo(Integer.parseInt(campo[this.indiceResultadoCod]));
			campaign.setCampaignResult(result);
		}
		if (indiceValido(this.indiceCargoCod)) {
			Position position = new Position();
			position.setPositionCode(Integer.parseInt(campo[this.indiceCargoCod]));
			campaign.setCampaignPosition(position);
		}	
		if (indiceValido(this.indicePartidoNumero)) {
			Party party = new Party();
			party.setPartyNumber(Integer.parseInt(campo[this.indicePartidoNumero]));
			campaign.setCampaignParty(party);
		}	
		if (indiceValido(this.indiceCandidatoTitulo)) {
			Candidate candidate = new Candidate();
			candidate.setCandidateElectoralTitle(campo[this.indiceCandidatoTitulo]);
			campaign.setCampaignCandidate(candidate);
		}	
		if (indiceValido(this.indiceAno)) {
			campaign.setCampaignYear(Integer.parseInt(campo[this.indiceAno]));
		}	
		if (indiceValido(this.indiceNumeroCandidato)) {
			campaign.setCampaignCandidateNumber(Integer.parseInt(campo[this.indiceNumeroCandidato]));
		}	
		if (indiceValido(this.indiceNomeDeUrna)) {
			campaign.setCampaignNameOfUrn(campo[this.indiceNomeDeUrna]);
		}	
		if (indiceValido(this.indiceUf)) {
			campaign.setCampaignCountryState(campo[this.indiceUf]);
		}	
		if (indiceValido(this.indiceDespesaMaxDeclarada)) {
			campaign.setCampaignMaximumExpenseDeclared(
					Float.parseFloat(campo[this.indiceDespesaMaxDeclarada].replace(',', '.')));
		}		
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Campaign
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Campaign campaign) {
		campaign.setCampaignIdentifier(Campaign.EMPTY_TYPE_INTEGER);
		campaign.setCampaignResult(Campaign.EMPTY_CLASS_RESULT);	
		campaign.setCampaignPosition(Campaign.EMPTY_CLASS_POSITION);	
		campaign.setCampaignParty(Campaign.EMPTY_CLASS_PARTY);	
		campaign.setCampaignCandidate(Campaign.EMPTY_CLASS_CANDIDATE);	
		campaign.setCampaignYear(Campaign.EMPTY_TYPE_INTEGER);	
		campaign.setCampaignCandidateNumber(Campaign.EMPTY_TYPE_INTEGER);	
		campaign.setCampaignNameOfUrn(Campaign.EMPTY_TYPE_STRING);	
		campaign.setCampaignCountryState(Campaign.EMPTY_TYPE_STRING);	
		campaign.setCampaignMaximumExpenseDeclared(Campaign.EMPTY_TYPE_FLOAT);	
		campaign.setCampaignTotalExpenseCalculated(Campaign.EMPTY_TYPE_FLOAT);	
		campaign.setCampaignTotalRevenueCalculated(Campaign.EMPTY_TYPE_FLOAT);	
	}

	// Mutators for indexes of the array of fields
	public void setIndiceResultadoCod(int indiceResultadoId) {
		this.indiceResultadoCod = indiceResultadoId;
	}

	public void setIndiceCargoCod(int indiceCargoId) {
		this.indiceCargoCod = indiceCargoId;
	}

	public void setIndicePartidoNumero(int indicePartidoNumero) {
		this.indicePartidoNumero = indicePartidoNumero;
	}

	public void setIndiceCandidatoTitulo(int indiceCandidatoTitulo) {
		this.indiceCandidatoTitulo = indiceCandidatoTitulo;
	}

	public void setIndiceAno(int indiceAno) {
		this.indiceAno = indiceAno;
	}

	public void setIndiceNumeroCandidato(int indiceNumeroCandidato) {
		this.indiceNumeroCandidato = indiceNumeroCandidato;
	}

	public void setIndiceNomeDeUrna(int indiceNomeDeUrna) {
		this.indiceNomeDeUrna = indiceNomeDeUrna;
	}

	public void setIndiceUf(int indiceUf) {
		this.indiceUf = indiceUf;
	}

	public void setIndiceDespesaMaxDeclarada(int indiceDespesaMaxDeclarada) {
		this.indiceDespesaMaxDeclarada = indiceDespesaMaxDeclarada;
	}

}
