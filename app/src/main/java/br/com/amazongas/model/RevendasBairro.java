package br.com.amazongas.model;

public class RevendasBairro extends AbstractBean {
	
	private static final long serialVersionUID = 1L;
	
	private String bairro;
	private int qtd;
	private String codcidade;
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public String getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(String codcidade) {
		this.codcidade = codcidade;
	}
	
	
	

}
