package parse.register.revenue_expense;

import model.beans.Supplier;
import parse.ParseException;
import parse.control.ParseControlSupplier;
import parse.control.ParseControl;
import parse.index.SupplierParseIndex;
import parse.index.IndicesParse;

public class CadastroFornecedorParse extends CadastroParseReceitasDespesas<Supplier> {

	public CadastroFornecedorParse(String tipoArquivo, String ano)
			throws ParseException {
		super(tipoArquivo, ano);

	}

	@Override
	public ParseControl<Supplier> novaInstancia(IndicesParse<Supplier> indicesParse) {
		ParseControlSupplier parseControlSupplier = new ParseControlSupplier(indicesParse);
		return parseControlSupplier;
	}
	
	public SupplierParseIndex getIndicesParseDespesa2002() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndiceCpf_Cnpj(6);
		supplierParseIndex.setIndiceNome(8);
		supplierParseIndex.setIndiceUf(7);
		
		return supplierParseIndex;
	}
	
	public SupplierParseIndex getIndicesParseDespesa2006() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndiceCpf_Cnpj(19);
		supplierParseIndex.setIndiceNome(18);
		supplierParseIndex.setIndiceUf(20);
		supplierParseIndex.setIndiceSituacaoCadastral(21);
		
		return supplierParseIndex;
	}
	
	public SupplierParseIndex getIndicesParseDespesa2010() {
		SupplierParseIndex supplierParseIndex = new SupplierParseIndex();
		supplierParseIndex.setIndiceCpf_Cnpj(10);
		supplierParseIndex.setIndiceNome(11);
		
		return supplierParseIndex;
	}

	@Override
	protected IndicesParse<Supplier> getIndicesParseReceita2002() {
		return new SupplierParseIndex();
	}

	@Override
	protected IndicesParse<Supplier> getIndicesParseReceita2006() {
		return new SupplierParseIndex();
	}

	@Override
	protected IndicesParse<Supplier> getIndicesParseReceita2010() {
		return new SupplierParseIndex();

	}

}
