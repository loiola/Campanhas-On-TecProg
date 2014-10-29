package model.beans;


public class FinancialTransaction {
	
	/*
	 * Model Class FinancialTransaction.java
	 * This class is responsible for getting the handling financial's informations
	 */
	 
	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final Integer EMPTY_TYPE_INTEGER = 0;
	public static final Float EMPTY_TYPE_FLOAT = (float) 0;
	public static final Object EMPTY_OBJECT = null;
	
	// Attributes
	
	// Unique attribute that identify a financial transaction
	private Integer financialTransactionIdentifier;
	
	// Attribute that characterizes financial transactions of candidate's campaign 
	private Campaign financialTransactionCampaign;
	
	// Attribute that characterizes document's number of financial transaction
	private String financialTransactionDocumentNumber;
	
	// Attribute that characterizes the date when occurred financial transaction
	private String financialTransactionDate;
	
	// Attribute that characterizes the value in real [R$] of financial transaction
	private Float financialTransactionPrice;
	
	// Attribute that characterizes type of financial transaction
	private String financialTransactionType;
	
	// Attribute that characterizes form of payment of financial transaction
	private String financialTransactionPaymentType;
	
	// Attribute that characterizes description of financial transaction
	private String financialTransactionDescription;
	
	// Empty Constructor
	public FinancialTransaction(){
		this.financialTransactionIdentifier = EMPTY_TYPE_INTEGER;
		this.financialTransactionCampaign = (Campaign) EMPTY_OBJECT;
		this.financialTransactionDocumentNumber = EMPTY_TYPE_STRING;
		this.financialTransactionDate = EMPTY_TYPE_STRING;
		this.financialTransactionPrice = EMPTY_TYPE_FLOAT;
		this.financialTransactionDescription = EMPTY_TYPE_STRING;
		this.financialTransactionType = EMPTY_TYPE_STRING;
		this.financialTransactionPaymentType = EMPTY_TYPE_STRING;
	}

	// Getters and Setters
	public String getFinancialTransactionDocumentNumber() {
		return financialTransactionDocumentNumber;
	}

	public void setFinancialTransactionDocumentNumber(String financialTransactionDocumentNumber) {
		this.financialTransactionDocumentNumber = financialTransactionDocumentNumber;
	}

	public Float getFinancialTransactionPrice() {
		return financialTransactionPrice;
	}

	public void setFinancialTransactionPrice(Float financialTransactionPrice) {
		this.financialTransactionPrice = financialTransactionPrice;
	}

	public String getFinancialTransactionDescription() {
		return financialTransactionDescription;
	}

	public void setFinancialTransactionDescription(String financialTransactionDescription) {
		this.financialTransactionDescription = financialTransactionDescription;
	}

	public Integer getFinancialTransactionIdentifier() {
		return financialTransactionIdentifier;
	}

	public void setFinancialTransactionIdentifier(Integer financialTransactionIdentifier) {
		this.financialTransactionIdentifier = financialTransactionIdentifier;
	}

	public Campaign getFinancialTransactionCampaign() {
		return financialTransactionCampaign;
	}

	public void setFinancialTransactionCampaign(Campaign financialTransactionCampaign) {
		this.financialTransactionCampaign = financialTransactionCampaign;
	}

	public String getFinancialTransactionDate() {
		return financialTransactionDate;
	}

	public void setFinancialTransactionDate(String financialTransactionDate) {
		this.financialTransactionDate = financialTransactionDate;
	}

	public String getFinancialTransactionType() {
		return financialTransactionType;
	}

	public void setFinancialTransactionType(String financialTransactionType) {
		this.financialTransactionType = financialTransactionType;
	}

	public String getFinancialTransactionPaymentType() {
		return financialTransactionPaymentType;
	}

	public void setFinancialTransactionPaymentType(String financialTransactionPaymentType) {
		this.financialTransactionPaymentType = financialTransactionPaymentType;
	}
	
	/*
	 * This method verified if an object provided is an instance of financial transaction
	 * @param an object for comparison 
	 * @return boolean with the result of comparison
	 */
	@Override
	public boolean equals(Object object) {
		//verify the relevance of method
		return false;
	}
}