package parse.register;

import parse.ParseException;
import parse.control.ParseControl;
import parse.index.ParseIndex;

public abstract class RegisterParse<O> {
	
	/* 
	 * Class used to be inherited from the others ParseRegisters classes to be used by their
	 * own needs to register and coordinated to ParseControl classes add informations to DataBase
	 * based on an "O" Object
	 */

	// Attributes
	protected int linhasLidas;
	protected int linhasParaFazerCadastro;
	protected ParseIndex<O> parseIndex;
	protected ParseControl<O> parseControl;
	
	// Constructor
	
	/*
	 * This constructor registers a new instance of ParseIndex based on type of file and year
	 * to be used afterward with a ParseControl object to register the informations extracted by
	 * the file to the DataBase
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 */
	public RegisterParse(String tipoArquivo, String ano) throws ParseException {
		this.linhasLidas = 0;
		this.linhasParaFazerCadastro = 1500;
		
		this.parseIndex = getIndicesParse(tipoArquivo, ano);
		this.parseControl = novaInstancia(this.parseIndex);
	}
	
	// Methods
	
	/*
	 * This method read a line of the file based on fields
	 * @param vector of Strings
	 */
	public void executarLinhaDoArquivo(String campo[]) throws ParseException {
		this.parseControl.addInstance(campo);
		this.linhasLidas++;
		if(this.linhasLidas >= this.linhasParaFazerCadastro) {
			cadastrarInstancias();
		}
	}
	
	/*
	 * This method register instances stored in an Array List on ParseControl attribute
	 */
	public void cadastrarInstancias() throws ParseException {
		this.parseControl.registeringInstances();
		this.parseControl.clear();
		this.linhasLidas = 0;
	}
	
	/*
	 * Abstract method who'll be used by children classes to add the instance of the
	 * ParseControl attribute in the constructor
	 * @param a ParseIndex who'll be used by the ParseControl constructor
	 * @return a ParseControl object
	 */
	public abstract ParseControl<O> novaInstancia(ParseIndex<O> indicesParse);
	
	/*
	 * Abstract method who'll be used by children classes to return a ParseIndex
	 * based on file type and year of campaign
	 * @param String who define the type of the list file to be used to get the ParseIndex
	 * @param String who define the year of the campaign to be used to get the ParseIndex
	 * @return a ParseIndex object
	 */
	protected abstract ParseIndex<O> getIndicesParse(String tipoArquivo, String ano) throws ParseException;

	/*
	 * This method return the limit of lines to register
	 * @return an Integer object value
	 */
	public int getLinhasParaFazerCadastro() {
		return linhasParaFazerCadastro;
	}

	/*
	 * This method set a new limit of lines to register
	 * @param an Integer object value
	 */
	public void setLinhasParaFazerCadastro(int linhasParaFazerCadastro) {
		this.linhasParaFazerCadastro = linhasParaFazerCadastro;
	}
	
}