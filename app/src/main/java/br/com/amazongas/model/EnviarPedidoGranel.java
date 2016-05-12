package br.com.amazongas.model;

public class EnviarPedidoGranel extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private int codPedido;
	private int codCliente;
	private int codLocalidade;
	private String dataPedido;
	private String dataProgramacao;
	
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCodLocalidade() {
		return codLocalidade;
	}
	public void setCodLocalidade(int codLocalidade) {
		this.codLocalidade = codLocalidade;
	}
	public String getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}
	public String getDataProgramacao() {
		return dataProgramacao;
	}
	public void setDataProgramacao(String dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}


}
