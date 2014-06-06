package modelo.beans;

import java.util.Calendar;

public class MovimentacaoFinanceira {
	
	public static final String STRING_VAZIO = "";
	public static final Integer INTEGER_VAZIO = 0;
	public static final Float FLOAT_VAZIO = (float) 0;
	public static final Boolean BOOLEAN_VAZIO = false;
	public static final Calendar CALENDAR_VAZIO = null;

	private String horaRegistro;
	private Boolean entregaEmConjunto;
	private String numeroDocumento;
	private Integer ano;
	private Float valor;
	private String fonte;
	private String tipo;
	private String especie;
	private String descricao;

	public MovimentacaoFinanceira(){
		this.horaRegistro = STRING_VAZIO;
		this.entregaEmConjunto = BOOLEAN_VAZIO;
		this.numeroDocumento = STRING_VAZIO;
		this.ano = INTEGER_VAZIO;
		this.valor = FLOAT_VAZIO;
		this.fonte = STRING_VAZIO;
		this.tipo = STRING_VAZIO;
		this.especie = STRING_VAZIO;
		this.descricao = STRING_VAZIO;
	}

	@Override
	public boolean equals(Object object) {
		if( !(object instanceof MovimentacaoFinanceira)) {
			return false;
		}
		
		MovimentacaoFinanceira outraMovimentacao = (MovimentacaoFinanceira) object;
		
		return 	this.horaRegistro.equalsIgnoreCase(outraMovimentacao.getHoraRegistro()) &&
				this.entregaEmConjunto.equals(outraMovimentacao.isEntregaEmConjunto()) &&
				this.numeroDocumento.equalsIgnoreCase(outraMovimentacao.getNumeroDocumento()) &&
				this.ano.equals(outraMovimentacao.getAno()) &&
				this.valor.equals(outraMovimentacao.getValor()) &&
				this.fonte.equalsIgnoreCase(outraMovimentacao.getFonte()) &&
				this.tipo.equalsIgnoreCase(outraMovimentacao.getTipo()) &&
				this.especie.equalsIgnoreCase(outraMovimentacao.getEspecie()) &&
				this.descricao.equalsIgnoreCase(outraMovimentacao.getDescricao());
	}
	
	public String getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public Boolean isEntregaEmConjunto() {
		return entregaEmConjunto;
	}

	public void setEntregaEmConjunto(Boolean entregaEmConjunto) {
		this.entregaEmConjunto = entregaEmConjunto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
}