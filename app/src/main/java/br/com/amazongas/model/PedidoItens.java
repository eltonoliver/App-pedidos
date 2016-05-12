package br.com.amazongas.model;

public class PedidoItens extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int qtd;
	private double valor;
	
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getTotal() {
		return this.qtd * this.valor;
	}

}
