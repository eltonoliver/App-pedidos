package br.com.amazongas.model;

public class PrecoProdutos extends AbstractBean {
	private static final long serialVersionUID = 1L;
	
	private int codProduto;
	private double preco;
	
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

}
