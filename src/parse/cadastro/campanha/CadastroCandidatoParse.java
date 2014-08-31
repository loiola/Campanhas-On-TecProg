package parse.cadastro.campanha;

import modelo.beans.Candidato;
import parse.ParseException;
import parse.cadastro.CadastroParse;
import parse.controle.CandidatoParseControle;
import parse.controle.ParseControle;
import parse.indices.CandidatoIndicesParse;
import parse.indices.IndicesParse;

public class CadastroCandidatoParse extends CadastroParse<Candidato> {
	
	/* 
	 * Class used to extract Candidate attributes and forward the register to the Database
	 */

	// Constructor
	
	/*
	 * This constructor use the ParseRegister inherited constructor to
	 * register informations from a Candidate
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public CadastroCandidatoParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);
	}

	// Methods
	
	/*
	 * @see parse.cadastro.CadastroParse#novaInstancia(parse.indices.IndicesParse)
	 * This method generate a ParseCandidateControl to be used by constructor
	 * @return a ParseCandidateControl
	 */
	@Override
	public ParseControle<Candidato> novaInstancia(
			IndicesParse<Candidato> indicesParse) {
		CandidatoParseControle candidatoParseControle = new CandidatoParseControle(indicesParse);
		return candidatoParseControle;
	}

	/*
	 * @see parse.cadastro.CadastroParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseCandidateIndex, setting the index number for each attribute
	 * @return a ParseCandidateIndex
	 */
	@Override
	protected IndicesParse<Candidato> getIndicesParse(String tipoArquivo,
			String ano) throws ParseException {
		
		CandidatoIndicesParse candidatoIndicesParse;
		candidatoIndicesParse = new CandidatoIndicesParse();
		
		candidatoIndicesParse.setIndiceNome(10);
		candidatoIndicesParse.setIndiceTituloEleitoral(26);
		
		return candidatoIndicesParse;
	}
	
}
