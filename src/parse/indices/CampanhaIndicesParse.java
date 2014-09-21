package parse.indices;

import modelo.beans.Campaign;
import modelo.beans.Candidate;
import modelo.beans.Position;
import modelo.beans.Partido;
import modelo.beans.Resultado;

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
			Resultado resultado = new Resultado();
			resultado.setCodigo(Integer.parseInt(campo[this.indiceResultadoCod]));
			campaign.setResultado(resultado);
		}
		if (indiceValido(this.indiceCargoCod)) {
			Position position = new Position();
			position.setCodigo(Integer.parseInt(campo[this.indiceCargoCod]));
			campaign.setCargo(position);
		}	
		if (indiceValido(this.indicePartidoNumero)) {
			Partido partido = new Partido();
			partido.setNumero(Integer.parseInt(campo[this.indicePartidoNumero]));
			campaign.setPartido(partido);
		}	
		if (indiceValido(this.indiceCandidatoTitulo)) {
			Candidate candidate = new Candidate();
			candidate.setTituloEleitoral(campo[this.indiceCandidatoTitulo]);
			campaign.setCandidato(candidate);
		}	
		if (indiceValido(this.indiceAno)) {
			campaign.setAno(Integer.parseInt(campo[this.indiceAno]));
		}	
		if (indiceValido(this.indiceNumeroCandidato)) {
			campaign.setNumeroCandidato(Integer.parseInt(campo[this.indiceNumeroCandidato]));
		}	
		if (indiceValido(this.indiceNomeDeUrna)) {
			campaign.setNomeDeUrna(campo[this.indiceNomeDeUrna]);
		}	
		if (indiceValido(this.indiceUf)) {
			campaign.setUf(campo[this.indiceUf]);
		}	
		if (indiceValido(this.indiceDespesaMaxDeclarada)) {
			campaign.setDespesaMaxDeclarada(
					Float.parseFloat(campo[this.indiceDespesaMaxDeclarada].replace(',', '.')));
		}		
	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Campaign
	 */
	@Override
	protected void setVazioEmTodosOsSetters(Campaign campaign) {
		campaign.setId(Campaign.INTEGER_VAZIO);
		campaign.setResultado(Campaign.RESULTADO_VAZIO);	
		campaign.setCargo(Campaign.CARGO_VAZIO);	
		campaign.setPartido(Campaign.PARTIDO_VAZIO);	
		campaign.setCandidato(Campaign.CANDIDATO_VAZIO);	
		campaign.setAno(Campaign.INTEGER_VAZIO);	
		campaign.setNumeroCandidato(Campaign.INTEGER_VAZIO);	
		campaign.setNomeDeUrna(Campaign.STRING_VAZIO);	
		campaign.setUf(Campaign.STRING_VAZIO);	
		campaign.setDespesaMaxDeclarada(Campaign.FLOAT_VAZIO);	
		campaign.setDespesaTotalCalculada(Campaign.FLOAT_VAZIO);	
		campaign.setReceitaTotalCalculada(Campaign.FLOAT_VAZIO);	
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
