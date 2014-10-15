package parse.register.revenue_expense;

import model.beans.Donor;
import parse.ParseException;
import parse.control.ParseControlDonor;
import parse.control.ParseControl;
import parse.index.DonorParseIndex;
import parse.index.ParseIndex;

public class RegisterToParseDonor extends RegisterToParseRevenueAndExpenses<Donor> {

	public RegisterToParseDonor(String fileType, String year) throws ParseException {
		super(fileType, year);

	}

	@Override
	public ParseControl<Donor> newIntance(ParseIndex<Donor> parseIndex) {
		ParseControlDonor parseControlDonor = new ParseControlDonor(parseIndex);
		return parseControlDonor;
	}
	
	public DonorParseIndex getIndicesParseReceita2002() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndexDonorCpfCnpj(6);
		donorParseIndex.setIndexName(8);
		donorParseIndex.setIndexUnitFederation(7);
		
		return donorParseIndex;
	}
	
	public DonorParseIndex getIndicesParseReceita2006() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndexDonorCpfCnpj(16);
		donorParseIndex.setIndexName(15);
		donorParseIndex.setIndexUnitFederation(17);
		donorParseIndex.setIndexRegistrationStatus(18);
		
		return donorParseIndex;
	}
	
	public DonorParseIndex getIndicesParseReceita2010() {
		DonorParseIndex donorParseIndex = new DonorParseIndex();
		donorParseIndex.setIndexDonorCpfCnpj(10);
		donorParseIndex.setIndexName(11);
		
		return donorParseIndex;
	}

	@Override
	protected ParseIndex<Donor> getIndicesParseDespesa2002() {
		return new DonorParseIndex();
	}

	@Override
	protected ParseIndex<Donor> getIndicesParseDespesa2006() {
		return new DonorParseIndex();
	}

	@Override
	protected ParseIndex<Donor> getIndicesParseDespesa2010() {
		return new DonorParseIndex();
	}

}
