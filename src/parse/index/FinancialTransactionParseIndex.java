package parse.index;

import model.beans.Campaign;
import model.beans.FinancialTransaction;
import model.beans.Position;

public class FinancialTransactionParseIndex<O> extends ParseIndex<O> {
	
	/*
	 * Class to control the contents of information inherent to the financial transactions
	 */

	// Constants
	public static final int INVALID_INDEX = -1;

	// Attributes
	
	// Variable to represent the year of financial transactions
	private Integer year;
	
	// Variable to the index of the year of the election campaign
	private int indexCampaignYear;
	
	// Variable to the index number of the candidate related to the campaign
	private int indexNumberCampaign;
	
	// Variable to the index position of the candidate related to the campaign
	private int indexPositionCampaign;
	
	// Variable to the index unit federation of the campaign - country state
	private int indexUnitFederationCampaign;
	
	// Variable to the index number of the financial transaction document
	private int indexDocumentNumber;
	
	// Variable to the index date of the financial transaction
	private int indexDate;
	
	// Variable to the index value of the financial transaction
	private int indexValue;
	
	// Variable to the index of the type of financial transaction
	private int indexTypeOfFinancialTransaction;
	
	// Variable for the rate of payment in financial transaction
	private int indexFormOfPayment;
	
	// Variable to the index description of the financial transaction
	private int indexDescription;

	// Constructors
	public FinancialTransactionParseIndex(String year) {
		this.year = Integer.valueOf(year);
		this.indexCampaignYear = INVALID_INDEX;
		this.indexNumberCampaign = INVALID_INDEX;
		this.indexPositionCampaign = INVALID_INDEX;
		this.indexUnitFederationCampaign = INVALID_INDEX;
		this.indexDocumentNumber = INVALID_INDEX;
		this.indexDate = INVALID_INDEX;
		this.indexValue = INVALID_INDEX;
		this.indexTypeOfFinancialTransaction = INVALID_INDEX;
		this.indexFormOfPayment = INVALID_INDEX;
		this.indexDescription = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the financial transactions in the file
	 * @param an instance of any class
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(O object, String[] field) {
		FinancialTransaction financialTransaction = (FinancialTransaction) object;
		
		Campaign campaign = new Campaign();
		campaign.setCampaignYear(year);
		
		//Variable to store the result of index validation
		boolean validationResult;
		
		validationResult = validIndex(this.indexCampaignYear);
		if(validationResult) {
			campaign.setCampaignYear(Integer.parseInt(field[this.indexCampaignYear]));
		}
		
		validationResult = validIndex(this.indexNumberCampaign);
		if(validationResult) {
			campaign.setCampaignCandidateNumber(Integer.parseInt(field[this.indexNumberCampaign]));
		}
		
		validationResult = validIndex(this.indexPositionCampaign);
		if(validationResult) {
			Position position = new Position();
			position.setPositionDescription(field[this.indexPositionCampaign]);
			campaign.setCampaignPosition(position);
		}
		
		validationResult = validIndex(this.indexUnitFederationCampaign);
		if(validationResult) {
			campaign.setCampaignCountryState(field[this.indexUnitFederationCampaign]);
		}
		
		validationResult = validIndex(this.indexDocumentNumber);
		if(validationResult) {
			financialTransaction.setFinancialTransactionDocumentNumber(field[this.indexDocumentNumber]);
		}
		
		validationResult = validIndex(this.indexDate);
		if(validationResult) {
			financialTransaction.setFinancialTransactionDate(field[this.indexDate]);
		}
		
		validationResult = validIndex(this.indexValue);
		if(validationResult) {
			float valor = Float.parseFloat(field[this.indexValue].replace(',', '.'));
			financialTransaction.setFinancialTransactionPrice(valor);
		}
		
		validationResult = validIndex(this.indexTypeOfFinancialTransaction);
		if(validationResult) {
			financialTransaction.setFinancialTransactionType(field[this.indexTypeOfFinancialTransaction]);
		}
		
		validationResult = validIndex(this.indexFormOfPayment);
		if(validationResult) {
			financialTransaction.setFinancialTransactionPaymentType(field[this.indexFormOfPayment]);
		}
		
		validationResult = validIndex(this.indexDescription);
		if(validationResult) {
			financialTransaction.setFinancialTransactionDescription(field[this.indexDescription]);
		}
		
		financialTransaction.setFinancialTransactionCampaign(campaign);

	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of any class
	 */
	@Override
	protected void setEmptyInAllSetters(O object) {
		FinancialTransaction financialTransaction = (FinancialTransaction) object;
		financialTransaction.setFinancialTransactionIdentifier(FinancialTransaction.EMPTY_TYPE_INTEGER);
		financialTransaction.setFinancialTransactionCampaign((Campaign)FinancialTransaction.EMPTY_OBJECT);
		financialTransaction.setFinancialTransactionDocumentNumber(FinancialTransaction.EMPTY_TYPE_STRING);
		financialTransaction.setFinancialTransactionDate(FinancialTransaction.EMPTY_TYPE_STRING);
		financialTransaction.setFinancialTransactionPrice(FinancialTransaction.EMPTY_TYPE_FLOAT);
		financialTransaction.setFinancialTransactionPaymentType(FinancialTransaction.EMPTY_TYPE_STRING);
		financialTransaction.setFinancialTransactionType(FinancialTransaction.EMPTY_TYPE_STRING);
		financialTransaction.setFinancialTransactionDescription(FinancialTransaction.EMPTY_TYPE_STRING);
	}
	
	/*
	 * This method validates an index
	 * @param an integer value
	 * @return a Boolean value
	 */
	protected boolean validIndex(int index) {
		return index > INVALID_INDEX;
	}

	// Mutators for indexes of the array of fields
	public void setYear(Integer year) {
		this.year = year;
	}

	public void setIndexCampaignYear(int indexCampaignYear) {
		this.indexCampaignYear = indexCampaignYear;
	}
	
	public void setIndexNumberCampaign(int indexNumberCampaign) {
		this.indexNumberCampaign = indexNumberCampaign;
	}
	
	public void setIndexPositionCampaign(int indexPositionCampaign) {
		this.indexPositionCampaign = indexPositionCampaign;
	}
	public void setIndexUnitFederationCampaign(int indexUnitFederationCampaign) {
		this.indexUnitFederationCampaign = indexUnitFederationCampaign;
	}
	public void setIndexDocumentNumber(int indexDocumentNumber) {
		this.indexDocumentNumber = indexDocumentNumber;
	}

	public void setIndexDate(int indexDate) {
		this.indexDate = indexDate;
	}

	public void setIndexValue(int indexValue) {
		this.indexValue = indexValue;
	}

	public void setIndexTypeOfFinancialTransaction(int indexTypeOfFinancialTransaction) {
		this.indexTypeOfFinancialTransaction = indexTypeOfFinancialTransaction;
	}

	public void setIndexFormOfPayment(int indexFormOfPayment) {
		this.indexFormOfPayment = indexFormOfPayment;
	}

	public void setIndexDescription(int indexDescription) {
		this.indexDescription = indexDescription;
	}
	
}