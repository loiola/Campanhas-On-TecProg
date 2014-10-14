package parse.index;

import model.beans.Expense;
import model.beans.Supplier;

public class ExpenseParseIndex extends FinancialTransactionParseIndex<Expense> {
	
	/*
	 * Class to control the contents of information inherent to the expenses
	 */

	// Attributes
	private int indexNameSupplier;
	private int indexCpfCnpjSupplier;
	private int indexDocumentType;
	
	// Constructors
	public ExpenseParseIndex(String year) {
		super(year);
		this.indexNameSupplier = INVALID_INDEX;
		this.indexCpfCnpjSupplier = INVALID_INDEX;
		this.indexDocumentType = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the expenses in the file
	 * @param an instance of the Class Expense
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Expense expense, String[] field) {
		super.setValidIndex(expense, field);
		Supplier supplier = new Supplier();

		//Variable to store the result of index validation
		boolean validationResult;
		
		validationResult = validIndex(this.indexNameSupplier);
		if(validationResult) {
			supplier.setSupplierName(field[this.indexNameSupplier]);
		}
		
		validationResult = validIndex(this.indexCpfCnpjSupplier);
		if(validationResult) {
			supplier.setSupplierPersonRegister(field[this.indexCpfCnpjSupplier]);
		}
		
		validationResult = validIndex(this.indexDocumentType);
		if(validationResult) {
			expense.setExpenseDocumentType(field[this.indexDocumentType]);
		}
		expense.setExpenseSupplier(supplier);

	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Expense
	 */
	@Override
	protected void setEmptyInAllSetters(Expense expense) {
		super.setEmptyInAllSetters(expense);
		expense.setExpenseSupplier((Supplier)Expense.EMPTY_OBJECT);
		expense.setExpenseDocumentType(Expense.EMPTY_TYPE_STRING);
	}
	
	// Mutators for indexes of the array of fields
	public void setIndexNameSupplier(int indexNameSupplier) {
		this.indexNameSupplier = indexNameSupplier;
	}
	
	public void setIndexCpfCnpjSupplier(int indexCpfCnpjSupplier) {
		this.indexCpfCnpjSupplier = indexCpfCnpjSupplier;
	}

	public void setIndexDocumentType(int indexDocumentType) {
		this.indexDocumentType = indexDocumentType;
	}
	
}