package parse.control;

import java.util.ArrayList;

import parse.ParseDAO;
import parse.ParseException;
import parse.index.IndicesParse;

public abstract class ParseControle<O> {
	
	/*
	 * Abstract class for template methods to be used by subclasses to parse control
	 */

	// Attributes
	private O objetoVazio;
	private ParseDAO<O> basicoDAO;
	private IndicesParse<O> indicesParse;
	protected ArrayList<O> listaInstancias;
	
	// Constructors
	public ParseControle(IndicesParse<O> indicesParse, ParseDAO<O> basicoDAO) {
		this.listaInstancias = new ArrayList<>();

		this.basicoDAO = basicoDAO;
		this.indicesParse = indicesParse;
		this.objetoVazio = novaInstancia();
	}

	// Method signature to make a new instance of an object of any class
	public abstract O novaInstancia();
	
	// Signature of the method to compare two objects of any class
	public abstract boolean iguais(O objetoUm, O objetoDois);
	
	/*
	 * This method adds an instance of a class in the list of instances
	 * @param an array of strings
	 */
	public void addInstancia(String campo[]) {
		O objeto = fazerNovaInstancia(campo);		
		if((!iguais(objeto, objetoVazio)) && 
				(!this.listaInstancias.contains(objeto))) {
			this.listaInstancias.add(objeto);
		}
	}

	/*
	 * This method performs addition of instances without performing validation of existence 
	 * in the list of instances
	 * @param an array of strings
	 */
	public void addInstanciaIgual(String campo[]) {
		O objeto = fazerNovaInstancia(campo);
		if((!iguais(objeto, objetoVazio))) {
			this.listaInstancias.add(objeto);
		}
	}
	
	/*
	 * This method formalizes the registration of a list of instances
	 */
	public void cadastrarInstancias() throws ParseException {
		this.basicoDAO.registerObjectArrayListOnParse(this.listaInstancias);
	}
	
	/*
	 * This method performs a cleaning at the array of instances to register
	 */
	public void resetar() {
		this.listaInstancias.clear();
	}
	
	/*
	 * This method makes a new instance and returns the instantiated object
	 * @param an array of strings
	 * @return an object of any class
	 */
	private O fazerNovaInstancia(String campo[]) {
		O objeto = novaInstancia();
		this.indicesParse.iniciarInstancia(objeto, campo);
		return objeto;
	}
	
}