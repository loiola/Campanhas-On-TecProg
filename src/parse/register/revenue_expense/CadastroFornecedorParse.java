package parse.register.revenue_expense;

import model.beans.Supplier;
import parse.ParseException;
import parse.control.ParseControlSupplier;
import parse.control.ParseControl;
import parse.index.SupplierParseIndex;
import parse.index.ParseIndex;

public class CadastroFornecedorParse extends CadastroParseReceitasDespesas<Supplier> {

	public CadastroFornecedorParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);

	}

	@Override
	public ParseControl<Supplier> newIntance(ParseIndex<Supplier> indicesParse) {
		ParseControlSupplier parseControlSupplier = new ParseControlSupplier(indicesParse);
		return parseControlSupplier;
	}
	
	public SupplierParseIndex getIndicesParseDespesa2002() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndexSupplierCpfCnpj(6);
		supplierParseIndex.setIndexName(8);
		supplierParseIndex.setIndexUnitFederation(7);
		
		return supplierParseIndex;
	}
	
	public SupplierParseIndex getIndicesParseDespesa2006() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndexSupplierCpfCnpj(19);
		supplierParseIndex.setIndexName(18);
		supplierParseIndex.setIndexUnitFederation(20);
		supplierParseIndex.setIndexRegistrationStatus(21);
		
		return supplierParseIndex;
	}
	
	public SupplierParseIndex getIndicesParseDespesa2010() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndexSupplierCpfCnpj(10);
		supplierParseIndex.setIndexName(11);
		
		return supplierParseIndex;
	}

	@Override
	protected ParseIndex<Supplier> getIndicesParseReceita2002() {
		return new SupplierParseIndex();
	}

	@Override
	protected ParseIndex<Supplier> getIndicesParseReceita2006() {
		return new SupplierParseIndex();
	}

	@Override
	protected ParseIndex<Supplier> getIndicesParseReceita2010() {
		return new SupplierParseIndex();

	}

}
