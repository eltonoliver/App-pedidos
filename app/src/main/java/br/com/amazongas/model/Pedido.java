package br.com.amazongas.model;

import java.util.ArrayList;

public class Pedido extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private int codPedido;
	private String data;
	private String hora;
	private String status;
	private int qtdTotal;
	private double valorTotal;
	private String revenda;
	private String telefone;
	private ArrayList<PedidoItens> items;
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQtdTotal() {
		return qtdTotal;
	}
	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getRevenda() {
		return revenda;
	}
	public void setRevenda(String revenda) {
		this.revenda = revenda;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public ArrayList<PedidoItens> getItems() {
		return items;
	}
	public void setItems(ArrayList<PedidoItens> items) {
		this.items = items;
	}
	
	
}
