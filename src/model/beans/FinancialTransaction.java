package model.beans;


public class FinancialTransaction {
	
	/*
	 * Model Class FinancialTransaction.java
	 * This class is responsable for getting the handling financial's informations
	 */
	 
	// Constants
	public static final String EMPTY_TYPE_STRING = "";
	public static final Integer EMPTY_TYPE_INTEGER = 0;
	public static final Float EMPTY_TYPE_FLOAT = (float) 0;
	public static final Object EMPTY_OBJECT = null;
	
	// Attributes
	private Integer financialTransactionIdentifier;
	private Campaign financialTransactionCampaign;
	private String financialTransactionDocumentNumber;
	private String financialTransactionDate;
	private Float financialTransactionPrice;
	private String financialTransactionType;
	private String financialTransactionPaymentType;
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
	
	//verify the relevance of method
	@Override
	public boolean equals(Object object) {
		return false;
	}
}
