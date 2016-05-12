package br.com.amazongas.model;

public class Cidades extends AbstractBean {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String uf;
	private String codcidade;
	private String codibge;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(String codcidade) {
		this.codcidade = codcidade;
	}
	public String getCodibge() {
		return codibge;
	}
	public void setCodibge(String codibge) {
		this.codibge = codibge;
	}
	

}
