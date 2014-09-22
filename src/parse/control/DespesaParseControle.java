package parse.control;

import model.beans.Expense;
import model.dao.ExpenseDAO;
import parse.indices.IndicesParse;

public class DespesaParseControle extends ParseControle<Expense> {
	
	/*
	 * Class used to control comparison and registration of instances of Class Expense
	 */

	// Constructors
	public DespesaParseControle(IndicesParse<Expense> indicesParse) {
		super(indicesParse, new ExpenseDAO());
	}

	/*
	 * This method instantiates an object of Class Expense
	 * @return an instance of Class Expense
	 */
	@Override
	public Expense novaInstancia() {
		Expense expense = new Expense();
		return expense;
	}

	/*
	 * This method checks if two instances are equal Class Expense
	 * @param two instances of Class Expense
	 * @return a boolean value
	 */
	@Override
	public boolean iguais(Expense objetoUm, Expense objetoDois) {
		return objetoUm.equals(objetoDois);
	}

}