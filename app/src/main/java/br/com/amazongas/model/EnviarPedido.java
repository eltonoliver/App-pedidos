package br.com.amazongas.model;

public class EnviarPedido extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private int codConsumidor;
	private int codLocalidade;
	private int codProduto;
	private int qtd;
	private double valor;
	private String obsTroco;
	
	public int getCodConsumidor() {
		return codConsumidor;
	}
	public void setCodConsumidor(int codConsumidor) {
		this.codConsumidor = codConsumidor;
	}
	
	public int getCodLocalidade() {
		return codLocalidade;
	}
	public void setCodLocalidade(int codLocalidade) {
		this.codLocalidade = codLocalidade;
	}
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getTotal(){
		return (this.qtd * this.valor);
	}
	public String getObsTroco() {
		return obsTroco;
	}
	public void setObsTroco(String obsTroco) {
		this.obsTroco = obsTroco;
	}
	

}
