package br.com.amazongas.model;

public class Revendas extends AbstractBean {
	
	private static final long serialVersionUID = 1L;
	
	private String nomelocalidade;
	private String endereco;
	private String bairro;
	private String telefone1;
	private String telefone2;
	private String pontoreferencia;
	private String codcidade;
	private String latitude;
	private String longitude;
	
	public String getNomelocalidade() {
		return nomelocalidade;
	}
	public void setNomelocalidade(String nomelocalidade) {
		this.nomelocalidade = nomelocalidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getPontoreferencia() {
		return pontoreferencia;
	}
	public void setPontoreferencia(String pontoreferencia) {
		this.pontoreferencia = pontoreferencia;
	}
	public String getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(String codcidade) {
		this.codcidade = codcidade;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	

}
