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
	private Integer ano;
	private int indiceCampanhaAno;
	private int indiceCampanhaNumero;
	private int indiceCampanhaCargo;
	private int indiceCampanhaUf;
	private int indiceNumeroDocumento;
	private int indiceData;
	private int indiceValor;
	private int indiceTipoMovimentacao;
	private int indiceFormaPagamento;
	private int indiceDescricao;

	// Constructors
	public FinancialTransactionParseIndex(String ano) {
		this.ano = Integer.valueOf(ano);
		this.indiceCampanhaAno = INVALID_INDEX;
		this.indiceCampanhaNumero = INVALID_INDEX;
		this.indiceCampanhaCargo = INVALID_INDEX;
		this.indiceCampanhaUf = INVALID_INDEX;
		this.indiceNumeroDocumento = INVALID_INDEX;
		this.indiceData = INVALID_INDEX;
		this.indiceValor = INVALID_INDEX;
		this.indiceTipoMovimentacao = INVALID_INDEX;
		this.indiceFormaPagamento = INVALID_INDEX;
		this.indiceDescricao = INVALID_INDEX;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the financial transactions in the file
	 * @param an instance of any class
	 * @param an array of strings
	 */
	@Override
	protected void setValidIndex(O objeto, String[] campo) {
		FinancialTransaction financialTransaction = (FinancialTransaction) objeto;
		
		Campaign campaign = new Campaign();
		campaign.setCampaignYear(ano);
		
		if(validIndex(this.indiceCampanhaAno)) {
			campaign.setCampaignYear(Integer.parseInt(campo[this.indiceCampanhaAno]));
		}
		if(validIndex(this.indiceCampanhaNumero)) {
			campaign.setCampaignCandidateNumber(Integer.parseInt(campo[this.indiceCampanhaNumero]));
		}
		if(validIndex(this.indiceCampanhaCargo)) {
			Position position = new Position();
			position.setPositionDescription(campo[this.indiceCampanhaCargo]);
			campaign.setCampaignPosition(position);
		}
		if(validIndex(this.indiceCampanhaUf)) {
			campaign.setCampaignCountryState(campo[this.indiceCampanhaUf]);
		}
		if(validIndex(this.indiceNumeroDocumento)) {
			financialTransaction.setFinancialTransactionDocumentNumber(campo[this.indiceNumeroDocumento]);
		}
		if(validIndex(this.indiceData)) {
			financialTransaction.setFinancialTransactionDate(campo[this.indiceData]);
		}
		if(validIndex(this.indiceValor)) {
			float valor = Float.parseFloat(campo[this.indiceValor].replace(',', '.'));
			financialTransaction.setFinancialTransactionPrice(valor);
		}
		if(validIndex(this.indiceTipoMovimentacao)) {
			financialTransaction.setFinancialTransactionType(campo[this.indiceTipoMovimentacao]);
		}
		if(validIndex(this.indiceFormaPagamento)) {
			financialTransaction.setFinancialTransactionPaymentType(campo[this.indiceFormaPagamento]);
		}
		if(validIndex(this.indiceDescricao)) {
			financialTransaction.setFinancialTransactionDescription(campo[this.indiceDescricao]);
		}
		financialTransaction.setFinancialTransactionCampaign(campaign);

	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of any class
	 */
	@Override
	protected void setEmptyInAllSetters(O objeto) {
		FinancialTransaction financialTransaction = (FinancialTransaction) objeto;
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
	protected boolean validIndex(int indice) {
		return indice > INVALID_INDEX;
	}

	// Mutators for indexes of the array of fields
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setIndiceCampanhaAno(int indiceCampanhaAno) {
		this.indiceCampanhaAno = indiceCampanhaAno;
	}
	
	public void setIndiceCampanhaNumero(int indiceCampanhaNumero) {
		this.indiceCampanhaNumero = indiceCampanhaNumero;
	}
	
	public void setIndiceCampanhaCargo(int indiceCampanhaCargo) {
		this.indiceCampanhaCargo = indiceCampanhaCargo;
	}
	public void setIndiceCampanhaUf(int indiceCampanhaUf) {
		this.indiceCampanhaUf = indiceCampanhaUf;
	}
	public void setIndiceNumeroDocumento(int indiceNumeroDocumento) {
		this.indiceNumeroDocumento = indiceNumeroDocumento;
	}

	public void setIndiceData(int indiceData) {
		this.indiceData = indiceData;
	}

	public void setIndiceValor(int indiceValor) {
		this.indiceValor = indiceValor;
	}

	public void setIndiceTipoMovimentacao(int indiceTipoMovimentacao) {
		this.indiceTipoMovimentacao = indiceTipoMovimentacao;
	}

	public void setIndiceFormaPagamento(int indiceFormaPagamento) {
		this.indiceFormaPagamento = indiceFormaPagamento;
	}

	public void setIndiceDescricao(int indiceDescricao) {
		this.indiceDescricao = indiceDescricao;
	}
	
}