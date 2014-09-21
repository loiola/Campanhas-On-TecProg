package parse.indices;

import modelo.beans.Campaign;
import modelo.beans.Position;
import modelo.beans.MovimentacaoFinanceira;

public class MovimentacaoFinanceiraIndicesParse<O> extends IndicesParse<O> {
	
	/*
	 * Class to control the contents of information inherent to the financial transactions
	 */

	// Constants
	public static final int INDICE_INVALIDO = -1;

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
	public MovimentacaoFinanceiraIndicesParse(String ano) {
		this.ano = Integer.valueOf(ano);
		this.indiceCampanhaAno = INDICE_INVALIDO;
		this.indiceCampanhaNumero = INDICE_INVALIDO;
		this.indiceCampanhaCargo = INDICE_INVALIDO;
		this.indiceCampanhaUf = INDICE_INVALIDO;
		this.indiceNumeroDocumento = INDICE_INVALIDO;
		this.indiceData = INDICE_INVALIDO;
		this.indiceValor = INDICE_INVALIDO;
		this.indiceTipoMovimentacao = INDICE_INVALIDO;
		this.indiceFormaPagamento = INDICE_INVALIDO;
		this.indiceDescricao = INDICE_INVALIDO;
	}
	
	/*
	 * This method formalizes the indices for reading the information about the financial transactions in the file
	 * @param an instance of any class
	 * @param an array of strings
	 */
	@Override
	protected void setIndicesValidos(O objeto, String[] campo) {
		MovimentacaoFinanceira movimentacaoFinanceira = (MovimentacaoFinanceira) objeto;
		
		Campaign campaign = new Campaign();
		campaign.setAno(ano);
		
		if(indiceValido(this.indiceCampanhaAno)) {
			campaign.setAno(Integer.parseInt(campo[this.indiceCampanhaAno]));
		}
		if(indiceValido(this.indiceCampanhaNumero)) {
			campaign.setNumeroCandidato(Integer.parseInt(campo[this.indiceCampanhaNumero]));
		}
		if(indiceValido(this.indiceCampanhaCargo)) {
			Position position = new Position();
			position.setDescricao(campo[this.indiceCampanhaCargo]);
			campaign.setCargo(position);
		}
		if(indiceValido(this.indiceCampanhaUf)) {
			campaign.setUf(campo[this.indiceCampanhaUf]);
		}
		if(indiceValido(this.indiceNumeroDocumento)) {
			movimentacaoFinanceira.setNumeroDocumento(campo[this.indiceNumeroDocumento]);
		}
		if(indiceValido(this.indiceData)) {
			movimentacaoFinanceira.setData(campo[this.indiceData]);
		}
		if(indiceValido(this.indiceValor)) {
			float valor = Float.parseFloat(campo[this.indiceValor].replace(',', '.'));
			movimentacaoFinanceira.setValor(valor);
		}
		if(indiceValido(this.indiceTipoMovimentacao)) {
			movimentacaoFinanceira.setTipoMovimentacao(campo[this.indiceTipoMovimentacao]);
		}
		if(indiceValido(this.indiceFormaPagamento)) {
			movimentacaoFinanceira.setFormaPagamento(campo[this.indiceFormaPagamento]);
		}
		if(indiceValido(this.indiceDescricao)) {
			movimentacaoFinanceira.setDescricao(campo[this.indiceDescricao]);
		}
		movimentacaoFinanceira.setCampanha(campaign);

	}

	/*
	 * This method ensures the boot empty content for attributes
	 * @param an instance of any class
	 */
	@Override
	protected void setVazioEmTodosOsSetters(O objeto) {
		MovimentacaoFinanceira movimentacaoFinanceira = (MovimentacaoFinanceira) objeto;
		movimentacaoFinanceira.setId(MovimentacaoFinanceira.INTEGER_VAZIO);
		movimentacaoFinanceira.setCampanha((Campaign)MovimentacaoFinanceira.OBJETO_VAZIO);
		movimentacaoFinanceira.setNumeroDocumento(MovimentacaoFinanceira.STRING_VAZIO);
		movimentacaoFinanceira.setData(MovimentacaoFinanceira.STRING_VAZIO);
		movimentacaoFinanceira.setValor(MovimentacaoFinanceira.FLOAT_VAZIO);
		movimentacaoFinanceira.setFormaPagamento(MovimentacaoFinanceira.STRING_VAZIO);
		movimentacaoFinanceira.setTipoMovimentacao(MovimentacaoFinanceira.STRING_VAZIO);
		movimentacaoFinanceira.setDescricao(MovimentacaoFinanceira.STRING_VAZIO);
	}
	
	/*
	 * This method validates an index
	 * @param an integer value
	 * @return a Boolean value
	 */
	protected boolean indiceValido(int indice) {
		return indice > INDICE_INVALIDO;
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