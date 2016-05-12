package br.com.amazongas.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricoPedido extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String data;
	private String hora;
	private String status;
	private int qtd;
	private double total;
	private String revenda;
	private String telefone;
	private String obsTroco;
	private String entrega;
	private String tipo;
	private ArrayList<HistoricoPedidoItens> item;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
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
	
	public String getObsTroco() {
		return obsTroco;
	}
	public void setObsTroco(String obsTroco) {
		this.obsTroco = obsTroco;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ArrayList<HistoricoPedidoItens> getItem() {
		return item;
	}
	public void setItem(ArrayList<HistoricoPedidoItens> item) {
		this.item = item;
	}
	
	
	

}
