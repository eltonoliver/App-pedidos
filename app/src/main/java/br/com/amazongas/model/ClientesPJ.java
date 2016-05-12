package br.com.amazongas.model;

public class ClientesPJ extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private static int codCliente;
	private static String cpfcgc;
	private static String senha;
	private static String razaoSocial;
	private static String codCidade;
	private static int codBairro;
	private static String latitude;
	private static String longitude;
	
	public static int getCodCliente() {
		return codCliente;
	}
	public static void setCodCliente(int codCliente) {
		ClientesPJ.codCliente = codCliente;
	}
	public static String getCpfcgc() {
		return cpfcgc;
	}
	public static void setCpfcgc(String cpfcgc) {
		ClientesPJ.cpfcgc = cpfcgc;
	}
	public static String getSenha() {
		return senha;
	}
	public static void setSenha(String senha) {
		ClientesPJ.senha = senha;
	}
	public static String getRazaoSocial() {
		return razaoSocial;
	}
	public static void setRazaoSocial(String razaoSocial) {
		ClientesPJ.razaoSocial = razaoSocial;
	}
	public static String getCodCidade() {
		return codCidade;
	}
	public static void setCodCidade(String codCidade) {
		ClientesPJ.codCidade = codCidade;
	}
	public static int getCodBairro() {
		return codBairro;
	}
	public static void setCodBairro(int codBairro) {
		ClientesPJ.codBairro = codBairro;
	}
	public static String getLatitude() {
		return latitude;
	}
	public static void setLatitude(String latitude) {
		ClientesPJ.latitude = latitude;
	}
	public static String getLongitude() {
		return longitude;
	}
	public static void setLongitude(String longitude) {
		ClientesPJ.longitude = longitude;
	}

	

}
