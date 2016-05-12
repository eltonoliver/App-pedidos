package br.com.amazongas.model;

public class StatusRetornoPost extends AbstractBean {
	private static final long serialVersionUID = 1L;
	
	private static String status;
	private static String msg;
	
	
	public static String getStatus() {
		return status;
	}
	public static void setStatus(String status) {
		StatusRetornoPost.status = status;
	}
	public static String getMsg() {
		return msg;
	}
	public static void setMsg(String msg) {
		StatusRetornoPost.msg = msg;
	}
	
	public void limpar(){
		setStatus(null);
		setMsg(null);
	}

}
