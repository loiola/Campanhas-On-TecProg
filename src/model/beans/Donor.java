package model.beans;

public class Donor {

	/*
	 * Model Class Donor.java This class is responsable for getting the Donor's
	 * informations
	 */

	// Constants
	public static final String EMPTY_TYPE_STRING = "";

	// Attributes
	private String donorPersonRegister;
	private String donorName;
	private String donorCountryState;
	private String donorRegisterSituation;

	// Empty Constructor
	public Donor() {
		this.donorPersonRegister = EMPTY_TYPE_STRING;
		this.donorName = EMPTY_TYPE_STRING;
		this.donorCountryState = EMPTY_TYPE_STRING;
		this.donorRegisterSituation = EMPTY_TYPE_STRING;
	}

	// Getters and Setters
	public String getDonorPersonRegister() {
		return donorPersonRegister;
	}

	public void setDonorPersonRegister(String donorPersonRegister) {
		this.donorPersonRegister = donorPersonRegister;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDonorCountryState() {
		return donorCountryState;
	}

	public void setDonorCountryState(String donorCountryState) {
		this.donorCountryState = donorCountryState;
	}

	public String getDonorRegisterSituation() {
		return donorRegisterSituation;
	}

	public void setDonorRegisterSituation(String donorRegisterSituation) {
		this.donorRegisterSituation = donorRegisterSituation;
	}

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Donor)) {
			return false;
		} else {

			Donor otherDonor = (Donor) object;

			// Variable that stores the logic state of the comparison between
			// two donors
			boolean auxiliaryReturn = this.getDonorName().equalsIgnoreCase(
					otherDonor.getDonorName())
					&& this.getDonorPersonRegister().equalsIgnoreCase(
							otherDonor.getDonorPersonRegister());

			return auxiliaryReturn;
		}
	}
}
