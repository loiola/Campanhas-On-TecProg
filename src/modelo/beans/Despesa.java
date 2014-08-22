package modelo.beans;

public class Despesa extends MovimentacaoFinanceira {
	
	// Constant
	public static final Object OBJETO_VAZIO = null; 
	
	// Attributes
	private String tipoDocumento;
	private Fornecedor fornecedor;
	
	// Empty Constructor
	public Despesa(){
		this.tipoDocumento = STRING_VAZIO;
		this.fornecedor = (Fornecedor) OBJETO_VAZIO;
	}
	
	// Getters and Setters
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
