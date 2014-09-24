package parse.register.campaign;

import model.beans.Candidate;
import parse.ParseException;
import parse.control.ParseControlCandidate;
import parse.control.ParseControl;
import parse.index.CandidateParseIndex;
import parse.index.ParseIndex;
import parse.register.RegisterParse;

public class CadastroCandidatoParse extends RegisterParse<Candidate> {
	
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
	 * @see parse.register.RegisterParse#novaInstancia(parse.index.ParseIndex)
	 * This method generate a ParseCandidateControl to be used by constructor
	 * @return a ParseCandidateControl
	 */
	@Override
	public ParseControl<Candidate> newIntance(
			ParseIndex<Candidate> indicesParse) {
		ParseControlCandidate parseControlCandidate = new ParseControlCandidate(indicesParse);
		return parseControlCandidate;
	}

	/*
	 * @see parse.register.RegisterParse#getIndicesParse(java.lang.String, java.lang.String)
	 * This method generate the ParseCandidateIndex, setting the index number for each attribute
	 * @return a ParseCandidateIndex
	 */
	@Override
	protected ParseIndex<Candidate> getParseIndex(String tipoArquivo,
			String ano) throws ParseException {
		
		CandidateParseIndex candidateParseIndex;
		candidateParseIndex = new CandidateParseIndex();
		
		candidateParseIndex.setIndexName(10);
		candidateParseIndex.setIndexElectionTitle(26);
		
		return candidateParseIndex;
	}
	
}
