package parse.register.campaign;

import model.beans.Campaign;
import parse.ParseException;
import parse.control.ParseControlCampaign;
import parse.control.ParseControl;
import parse.index.CampaignParseIndex;
import parse.index.IndicesParse;
import parse.register.CadastroParse;

public class CadastroCampanhaParse extends CadastroParse<Campaign> {
	
	/* 
	 * Class used to extract Campaign attributes and forward the register to the Database
	 */

	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register informations from a Campaign
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public CadastroCampanhaParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
		
		setLinhasParaFazerCadastro(100000);
	}

	// Methods
	
	/*
	 * @see parse.register.CadastroParse#novaInstancia(parse.index.IndicesParse)
	 * This method generate a ParseCampaignControl to be used by constructor
	 * @return a ParseCampaignControl
	 */
	@Override
	public ParseControl<Campaign> novaInstancia(
			IndicesParse<Campaign> indicesParse) {
		ParseControlCampaign parseControlCampaign = new ParseControlCampaign(indicesParse);	
		return parseControlCampaign;
	}

	/*
	 * @see parse.register.CadastroParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseCampaignIndex, setting the index number for each attribute
	 * @return a ParseCampaignIndex
	 */
	@Override
	protected IndicesParse<Campaign> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		
		CampaignParseIndex campaignParseIndex;
		campaignParseIndex = new CampaignParseIndex();
		
		campaignParseIndex.setIndiceAno(2);
		campaignParseIndex.setIndiceNumeroCandidato(12);
		campaignParseIndex.setIndiceNomeDeUrna(13);
		campaignParseIndex.setIndiceUf(5);
		campaignParseIndex.setIndiceCargoCod(8);
		campaignParseIndex.setIndiceCandidatoTitulo(26);
		campaignParseIndex.setIndicePartidoNumero(16);
		campaignParseIndex.setIndiceResultadoCod(40);
		campaignParseIndex.setIndiceDespesaMaxDeclarada(39);
	
		return campaignParseIndex;
	}
	
	/*
	 * @see parse.register.CadastroParse#executarLinhaDoArquivo(java.lang.String[])
	 */
	@Override
	public void executarLinhaDoArquivo(String[] campo) throws ParseException {
		this.parseControl.addEqualInstance(campo);
		this.linhasLidas++;
		if(this.linhasLidas >= this.linhasParaFazerCadastro) {
			cadastrarInstancias();
		}
	}

}
