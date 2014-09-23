package parse.register.campaign;

import model.beans.Campaign;
import parse.ParseException;
import parse.control.CampanhaParseControle;
import parse.control.ParseControle;
import parse.index.CampanhaIndicesParse;
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
	public ParseControle<Campaign> novaInstancia(
			IndicesParse<Campaign> indicesParse) {
		CampanhaParseControle campanhaParseControle = new CampanhaParseControle(indicesParse);	
		return campanhaParseControle;
	}

	/*
	 * @see parse.register.CadastroParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseCampaignIndex, setting the index number for each attribute
	 * @return a ParseCampaignIndex
	 */
	@Override
	protected IndicesParse<Campaign> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		
		CampanhaIndicesParse campanhaIndicesParse;
		campanhaIndicesParse = new CampanhaIndicesParse();
		
		campanhaIndicesParse.setIndiceAno(2);
		campanhaIndicesParse.setIndiceNumeroCandidato(12);
		campanhaIndicesParse.setIndiceNomeDeUrna(13);
		campanhaIndicesParse.setIndiceUf(5);
		campanhaIndicesParse.setIndiceCargoCod(8);
		campanhaIndicesParse.setIndiceCandidatoTitulo(26);
		campanhaIndicesParse.setIndicePartidoNumero(16);
		campanhaIndicesParse.setIndiceResultadoCod(40);
		campanhaIndicesParse.setIndiceDespesaMaxDeclarada(39);
	
		return campanhaIndicesParse;
	}
	
	/*
	 * @see parse.register.CadastroParse#executarLinhaDoArquivo(java.lang.String[])
	 */
	@Override
	public void executarLinhaDoArquivo(String[] campo) throws ParseException {
		this.parseControle.addInstanciaIgual(campo);
		this.linhasLidas++;
		if(this.linhasLidas >= this.linhasParaFazerCadastro) {
			cadastrarInstancias();
		}
	}

}
