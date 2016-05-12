package br.com.amazongas.model;

public class Bairros extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private int codBairro;
	private String nomeBairro;
	private String estadoBairro;
	private String codcidade;
	
	public int getCodBairro() {
		return codBairro;
	}
	public void setCodBairro(int codBairro) {
		this.codBairro = codBairro;
	}
	public String getNomeBairro() {
		return nomeBairro;
	}
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}
	public String getEstadoBairro() {
		return estadoBairro;
	}
	public void setEstadoBairro(String estadoBairro) {
		this.estadoBairro = estadoBairro;
	}
	public String getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(String codcidade) {
		this.codcidade = codcidade;
	}
	
	

}
