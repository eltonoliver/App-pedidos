package br.com.amazongas.model;

public class HistoricoPedidoItens extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private int qtd;
	private double preco;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getTotal(){
		return (this.qtd * this.preco);
	}

}
