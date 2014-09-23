package parse.register.revenue_expense;

import model.beans.Supplier;
import parse.ParseException;
import parse.control.ParseControlSupplier;
import parse.control.ParseControl;
import parse.index.FornecedorIndicesParse;
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
	
	public FornecedorIndicesParse getIndicesParseDespesa2002() {
		FornecedorIndicesParse fornecedorIndicesParse = new FornecedorIndicesParse();
		fornecedorIndicesParse.setIndiceCpf_Cnpj(6);
		fornecedorIndicesParse.setIndiceNome(8);
		fornecedorIndicesParse.setIndiceUf(7);
		
		return fornecedorIndicesParse;
	}
	
	public FornecedorIndicesParse getIndicesParseDespesa2006() {
		FornecedorIndicesParse fornecedorIndicesParse = new FornecedorIndicesParse();
		fornecedorIndicesParse.setIndiceCpf_Cnpj(19);
		fornecedorIndicesParse.setIndiceNome(18);
		fornecedorIndicesParse.setIndiceUf(20);
		fornecedorIndicesParse.setIndiceSituacaoCadastral(21);
		
		return fornecedorIndicesParse;
	}
	
	public FornecedorIndicesParse getIndicesParseDespesa2010() {
		FornecedorIndicesParse fornecedorIndicesParse = new FornecedorIndicesParse();
		fornecedorIndicesParse.setIndiceCpf_Cnpj(10);
		fornecedorIndicesParse.setIndiceNome(11);
		
		return fornecedorIndicesParse;
	}

	@Override
	protected IndicesParse<Supplier> getIndicesParseReceita2002() {
		return new FornecedorIndicesParse();
	}

	@Override
	protected IndicesParse<Supplier> getIndicesParseReceita2006() {
		return new FornecedorIndicesParse();
	}

	@Override
	protected IndicesParse<Supplier> getIndicesParseReceita2010() {
		return new FornecedorIndicesParse();

	}

}
