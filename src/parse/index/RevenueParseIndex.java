package parse.index;

import model.beans.Donor;
import model.beans.Revenue;

public class RevenueParseIndex extends FinancialTransactionParseIndex<Revenue> {
	
	/*
	 * Class to control the contents of information inherent to the revenue
	 */

	// Attributes
	private int indiceReciboEleitoral;
	private int indiceDoadorNome;
	private int indiceDoadorCpfCnpj;
	
	// Constructors
	public RevenueParseIndex(String ano) {
		super(ano);
		this.indiceReciboEleitoral = INVALID_INDEX;
		this.indiceDoadorNome = INVALID_INDEX;
		this.indiceDoadorCpfCnpj = INVALID_INDEX; 

	}
	
	/*
	 * This method formalizes the indices for reading the information about the revenue in the file
	 * @param an instance of the Class Revenue
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(Revenue revenue, String[] campo) {
		super.setValidIndex(revenue, campo);
		
		Donor donor = new Donor();
		if(validIndex(this.indiceReciboEleitoral)) {
			revenue.setRevenueElectoralReceipt(campo[this.indiceReciboEleitoral]);
		}
		if(validIndex(this.indiceDoadorNome)) {
			donor.setDonorName(campo[this.indiceDoadorNome]);
		}
		if(validIndex(this.indiceDoadorCpfCnpj)) {
			donor.setDonorPersonRegister(campo[this.indiceDoadorCpfCnpj]);
		}
		revenue.setRevenueDonor(donor);

	}
	
	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of Class Revenue
	 */
	@Override
	protected void setEmptyInAllSetters(Revenue revenue) {
		super.setEmptyInAllSetters(revenue);

		revenue.setRevenueElectoralReceipt(Revenue.EMPTY_TYPE_STRING);
		revenue.setRevenueDonor((Donor)Revenue.EMPTY_OBJECT);
		
	}

	// Mutators for indexes of the array of fields
	public void setIndiceReciboEleitoral(int indiceReciboEleitoral) {
		this.indiceReciboEleitoral = indiceReciboEleitoral;
	}

	public void setIndiceDoadorNome(int indiceDoadorNome) {
		this.indiceDoadorNome = indiceDoadorNome;
	}
	
	public void setIndiceDoadorCpfCnpj(int indiceDoadorCpfCnpj) {
		this.indiceDoadorCpfCnpj = indiceDoadorCpfCnpj;
	}
	
}