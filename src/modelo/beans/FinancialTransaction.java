package modelo.beans;


public class FinancialTransaction {
	
	/*
	 * Model Class FinancialTransaction.java
	 * This class is responsable for getting the handling financial's informations
	 */
	 
	// Constants
	public static final String STRING_VAZIO = "";
	public static final Integer INTEGER_VAZIO = 0;
	public static final Float FLOAT_VAZIO = (float) 0;
	public static final Object OBJETO_VAZIO = null;
	
	// Attributes
	private Integer id;
	private Campaign campaign;
	private String numeroDocumento;
	private String data;
	private Float valor;
	private String tipoMovimentacao;
	private String formaPagamento;
	private String descricao;
	
	// Empty Constructor
	public FinancialTransaction(){
		this.id = INTEGER_VAZIO;
		this.campaign = (Campaign) OBJETO_VAZIO;
		this.numeroDocumento = STRING_VAZIO;
		this.data = STRING_VAZIO;
		this.valor = FLOAT_VAZIO;
		this.descricao = STRING_VAZIO;
		this.tipoMovimentacao = STRING_VAZIO;
		this.formaPagamento = STRING_VAZIO;
	}

	// Getters and Setters
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Campaign getCampanha() {
		return campaign;
	}

	public void setCampanha(Campaign campaign) {
		this.campaign = campaign;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	@Override
	public boolean equals(Object object) {
		return false;
	}
}
