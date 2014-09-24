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
	private Integer year;
	private int indexCampaignYear;
	private int indexNumberCampaign;
	private int indexPositionCampaign;
	private int indexUnitFederationCampaign;
	private int indexDocumentNumber;
	private int indexDate;
	private int indexValue;
	private int indexTypeOfFinancialTransaction;
	private int indexFormOfPayment;
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
		
		if(validIndex(this.indexCampaignYear)) {
			campaign.setCampaignYear(Integer.parseInt(field[this.indexCampaignYear]));
		}
		if(validIndex(this.indexNumberCampaign)) {
			campaign.setCampaignCandidateNumber(Integer.parseInt(field[this.indexNumberCampaign]));
		}
		if(validIndex(this.indexPositionCampaign)) {
			Position position = new Position();
			position.setPositionDescription(field[this.indexPositionCampaign]);
			campaign.setCampaignPosition(position);
		}
		if(validIndex(this.indexUnitFederationCampaign)) {
			campaign.setCampaignCountryState(field[this.indexUnitFederationCampaign]);
		}
		if(validIndex(this.indexDocumentNumber)) {
			financialTransaction.setFinancialTransactionDocumentNumber(field[this.indexDocumentNumber]);
		}
		if(validIndex(this.indexDate)) {
			financialTransaction.setFinancialTransactionDate(field[this.indexDate]);
		}
		if(validIndex(this.indexValue)) {
			float valor = Float.parseFloat(field[this.indexValue].replace(',', '.'));
			financialTransaction.setFinancialTransactionPrice(valor);
		}
		if(validIndex(this.indexTypeOfFinancialTransaction)) {
			financialTransaction.setFinancialTransactionType(field[this.indexTypeOfFinancialTransaction]);
		}
		if(validIndex(this.indexFormOfPayment)) {
			financialTransaction.setFinancialTransactionPaymentType(field[this.indexFormOfPayment]);
		}
		if(validIndex(this.indexDescription)) {
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