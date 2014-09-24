package parse.register.revenue_expense;

import model.beans.Donor;
import parse.ParseException;
import parse.control.ParseControlDonor;
import parse.control.ParseControl;
import parse.index.DonorParseIndex;
import parse.index.IndicesParse;

public class CadastroDoadorParse extends CadastroParseReceitasDespesas<Donor> {

	public CadastroDoadorParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);

	}

	@Override
	public ParseControl<Donor> novaInstancia(IndicesParse<Donor> indicesParse) {
		ParseControlDonor parseControlDonor = new ParseControlDonor(indicesParse);
		return parseControlDonor;
	}
	
	public DonorParseIndex getIndicesParseReceita2002() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndiceCpf_Cnpj(6);
		donorParseIndex.setIndiceNome(8);
		donorParseIndex.setIndiceUf(7);
		
		return donorParseIndex;
	}
	
	public DonorParseIndex getIndicesParseReceita2006() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndiceCpf_Cnpj(16);
		donorParseIndex.setIndiceNome(15);
		donorParseIndex.setIndiceUf(17);
		donorParseIndex.setIndiceSituacaoCadastral(18);
		
		return donorParseIndex;
	}
	
	public DonorParseIndex getIndicesParseReceita2010() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndiceCpf_Cnpj(10);
		donorParseIndex.setIndiceNome(11);
		
		return donorParseIndex;
	}

	@Override
	protected IndicesParse<Donor> getIndicesParseDespesa2002() {
		return new DonorParseIndex();
	}

	@Override
	protected IndicesParse<Donor> getIndicesParseDespesa2006() {
		return new DonorParseIndex();
	}

	@Override
	protected IndicesParse<Donor> getIndicesParseDespesa2010() {
		return new DonorParseIndex();
	}

}
