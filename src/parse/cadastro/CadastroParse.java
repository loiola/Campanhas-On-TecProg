package parse.cadastro;

import parse.ParseException;
import parse.controle.ParseControle;
import parse.indices.IndicesParse;

/* Abstract Class: ParseRegister from Module Parse
 * This class you be inherited from the others ParseRegisters to be used by their own needs to register
 * and coordinated to ParseControl classes add informations to DataBase based on an "O" Object.
 * @Attributes:
 *	protected Integer linesReaded
 *	protected Integer linesToRegister
 *	protected ParseIndex<O> parseIndex
 *	protected ParseControl<O> parseControl
 * @Methods:
 *	public void parseRegister(String fileType, String year)
 *	public void executeFileLine(String field[])
 *	public void registerInstance()
 *	protected abstract ParseControl<O> newInstance(ParseIndex<O> parseIndex)
 *	protected abstract ParseIndex<O> getParseIndexes(String fileType, String year)
 *	public Integer getLinesToRegister()
 *	public void setLinesToRegister(Integer linesToRegister)
 */
public abstract class CadastroParse<O> {

	// Attributes
	protected int linhasLidas;
	protected int linhasParaFazerCadastro;
	protected IndicesParse<O> indicesParse;
	protected ParseControle<O> parseControle;
	
	// Methods
	
	/* Public Void Method: ParseRegister
	 * ParseRegister registers a new instance of ParseIndex based on type of file and year
	 * to be used afterward with a ParseControl object to register the informations extracted by
	 * the file to the DataBase
	 * @Parameters:
	 *	fileType: This parameter define the type of the list file to be used to get the ParseIndex
	 *	year: Thids parameter define the year of the <CAMPANHA ELEITORAL> to be used to get the
	 *	ParseIndex
	 * @Returns:
 	 *	This method has no return
	 */
	public CadastroParse(String tipoArquivo, String ano) throws ParseException {
		this.linhasLidas = 0;
		this.linhasParaFazerCadastro = 1500;
		
		this.indicesParse = getIndicesParse(tipoArquivo, ano);
		this.parseControle = novaInstancia(this.indicesParse);
	}
	
	/*
	 *
	 */
	public void executarLinhaDoArquivo(String campo[]) throws ParseException {
		this.parseControle.addInstancia(campo);
		this.linhasLidas++;
		if(this.linhasLidas >= this.linhasParaFazerCadastro) {
			cadastrarInstancias();
		}
	}
	
	/*
	 *
	 */
	public void cadastrarInstancias() throws ParseException {
		this.parseControle.cadastrarInstancias();
		this.parseControle.resetar();
		this.linhasLidas = 0;
	}
	
	/*
	 *
	 */
	public abstract ParseControle<O> novaInstancia(IndicesParse<O> indicesParse);
	
	/*
	 *
	 */
	protected abstract IndicesParse<O> getIndicesParse(String tipoArquivo, String ano) throws ParseException;

	/*
	 *
	 */
	public int getLinhasParaFazerCadastro() {
		return linhasParaFazerCadastro;
	}

	/*
	 *
	 */
	public void setLinhasParaFazerCadastro(int linhasParaFazerCadastro) {
		this.linhasParaFazerCadastro = linhasParaFazerCadastro;
	}
	
}
