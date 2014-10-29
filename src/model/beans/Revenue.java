package model.beans;

public class Revenue extends FinancialTransaction {

	/*
	 * Model Class Revenue.java This class is responsible for getting the 
	 * Receipts's informations
	 */
	 
	// Constants 
	public static final Donor EMPTY_CLASS_DONOR = new Donor();
	
	// Attributes
	
	// Attributes that characterizes receipt electoral of revenue
	private String revenueElectoralReceipt;
	
	// Attributes that characterizes donor of revenue for campaign
	private Donor revenueDonor;
	
	// Empty Constructor
	public Revenue() {
		super();
		this.revenueElectoralReceipt = EMPTY_TYPE_STRING;
		this.revenueDonor = EMPTY_CLASS_DONOR;
	}
	
	// Getters and Setters
	public String getRevenueElectoralReceipt() {
		return revenueElectoralReceipt;
	}
	
	public void setRevenueElectoralReceipt(String revenueElectoralReceipt) {
		this.revenueElectoralReceipt = revenueElectoralReceipt;
	}
	
	public Donor getRevenueDonor() {
		return revenueDonor;
	}
	
	public void setRevenueDonor(Donor revenueDonor) {
		this.revenueDonor = revenueDonor;
	}	
}