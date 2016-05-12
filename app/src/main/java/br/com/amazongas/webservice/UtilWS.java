package br.com.amazongas.webservice;

import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.EnviarPedido;
import br.com.amazongas.model.EnviarPedidoGranel;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.SettingsHelper;
import br.com.amazongas.util.Statics;
import br.com.amazongas.util.Util;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.util.Log;
import android.util.Xml.Encoding;

public class UtilWS {
	// Parametros iniciais
    private static ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    private static ProgressDialog progressDialog;
	private static boolean resultado;
//	private static CustomDialogResultListener customDialogResultListenter;	
//	private static CustomDialogNumberResultListener customDialogNumberResultListener;
	private static AlertDialog alerta;

	
	
    // Consultas WS -----------------------------------------------------------------------------------------------------
    
    // Retorna os dados do login do revendedor
//	public static HttpResponse getLoginRevendedor(String base, String usuario, String senha)  throws Exception {
//		params.clear();
//		addNameValuePair("base",base);addNameValuePair("usuario",usuario);addNameValuePair("senha",senha);
//		return postExecute(Constants.URL+"?rquest=login_revendedor");
//	}

//	// Retorna os pedidos
//	public static HttpResponse getPedidoRevendedor(String codcliente, String status)  throws Exception {
//		params.clear();
//		addNameValuePair("codcliente",codcliente);
//		addNameValuePair("status",status);
//		return postExecute(Constants.URL+"?rquest=pedido_revendedor");
//	}
//
//	// Retorna os dados da pontuação do leal
//	public static HttpResponse getPontuacaoLealRevendedor(String codcliente, String ano)  throws Exception {
//		params.clear();
//		addNameValuePair("codcliente",codcliente);
//		addNameValuePair("ano",ano);
//		return postExecute(Constants.URL+"?rquest=pontuacaoleal_revendedor");
//	}
//	
//	// Retorna os itens dos pedidos
//	public static HttpResponse getPedidoItemRevendedor(String codpedido)  throws Exception {
//		params.clear();
//		addNameValuePair("codpedido",codpedido);
//		return postExecute(Constants.URL+"?rquest=pedidoitem_revendedor");
//	}
	
	// Retorna lista de bairros
	public static HttpResponse getListaBairros(String uf)  throws Exception {
		params.clear();
		addNameValuePair("uf","AM");
		return postExecute(Constants.URL+"?rquest=listabairros_consumidor");
	}
	
	// Retorna lista de cidades
	public static HttpResponse getListaCidades(String uf)  throws Exception {
		params.clear();
		addNameValuePair("uf","AM");
		return postExecute(Constants.URL+"?rquest=listacidades_consumidor");
	}
	
	// Retorna lista de Revendas
	public static HttpResponse getListaRevendas()  throws Exception {
		Log.i(Constants.TAG, "getListaRevendas >>>>> 2 ");
		params.clear();
		//addNameValuePair("uf","AM");
		return postExecute(Constants.URL+"?rquest=listarevendas_consumidor");
	}
	
	// Retorna lista de preço
	public static HttpResponse getListaPreco(String uf)  throws Exception {
		params.clear();
		addNameValuePair("uf",uf);
		return postExecute(Constants.URL+"?rquest=listapreco_consumidor");
	}
	
	// Retorna lista historico
	public static HttpResponse getListaHistorico(int codConsumidor)  throws Exception {
		params.clear();
		addNameValuePair("codconsumidor",String.valueOf(codConsumidor));
		return postExecute(Constants.URL+"?rquest=listahistorico_consumidor");
	}
	
	// Retorna lista historico
	public static HttpResponse getListaHistoricoPedido(int codConsumidor, int opcao)  throws Exception {
		Log.i("teste","getListaHistoricoPedido >>> "+codConsumidor+" / "+opcao);
		params.clear();
		addNameValuePair("codconsumidor",String.valueOf(codConsumidor));
		addNameValuePair("opcao",String.valueOf(opcao));
		return postExecute(Constants.URL+"?rquest=listahistoricoPedido_consumidor");
	}
	
	// Retorna lista historico PJ
	public static HttpResponse getListaHistoricoPedidoPJ(int codConsumidor, int opcao)  throws Exception {
		Log.i("teste","getListaHistoricoPedido >>> "+codConsumidor+" / "+opcao);
		params.clear();
		addNameValuePair("codconsumidor",String.valueOf(codConsumidor));
		addNameValuePair("opcao",String.valueOf(opcao));
		return postExecute(Constants.URL+"?rquest=listahistoricopedidopj_consumidor");
	}
	
	// Retorna lista historico botija e granel
	public static HttpResponse getListaHistoricoPedidoBotijaGranelPJ(int codConsumidor, int opcao)  throws Exception {
		Log.i("teste","getListaHistoricoPedido >>> "+codConsumidor+" / "+opcao);
		params.clear();
		addNameValuePair("codconsumidor",String.valueOf(codConsumidor));
		addNameValuePair("opcao",String.valueOf(opcao));
		return postExecute(Constants.URL+"?rquest=listahistoricopedidobotijagranelpj_consumidor");
	}
	
	// Retorna lista historico PJ Granel
	public static HttpResponse getListaHistoricoPedidoPJGranel(int codConsumidor, int opcao)  throws Exception {
		Log.i("teste","getListaHistoricoPedido >>> "+codConsumidor+" / "+opcao);
		params.clear();
		addNameValuePair("codconsumidor",String.valueOf(codConsumidor));
		addNameValuePair("opcao",String.valueOf(opcao));
		return postExecute(Constants.URL+"?rquest=listahistoricopedidopjgranel_consumidor");
	}

	// Retorna lista de localidades de cliente
	public static HttpResponse getListaLocalidadesCliente(int codConsumidor)  throws Exception {
		params.clear();
		addNameValuePair("codconsumidor",String.valueOf(codConsumidor));
		return postExecute(Constants.URL+"?rquest=listalocalidadecliente_consumidor");
	}
	
	// Retorna lista historico Item
	public static HttpResponse getListaHistoricoPedidoItem(int pedido)  throws Exception {
		params.clear();
		addNameValuePair("pedido",String.valueOf(pedido));
		return postExecute(Constants.URL+"?rquest=listahistoricopedidoitem_consumidor");
	}
	
	// Retorna lista historico Item PJ
	public static HttpResponse getListaHistoricoPedidoItemPJ(int pedido)  throws Exception {
		params.clear();
		addNameValuePair("pedido",String.valueOf(pedido));
		return postExecute(Constants.URL+"?rquest=listahistoricopedidoitempj_consumidor");
	}
	
	// Login do usuario
	public static HttpResponse getLogin(String login, String senha)  throws Exception {
		params.clear();
		addNameValuePair("login", login);
		addNameValuePair("senha", senha);
		return postExecute(Constants.URL+"?rquest=login_consumidor");
	}
	
	// Login do usuario pessoa jurídica
	public static HttpResponse getLoginPJ(String login, String senha)  throws Exception {
		Log.i(Constants.TAG, "getLoginPJ >>>>>>> ************* "+login+" - "+senha);
		params.clear();
		addNameValuePair("login", login);
		addNameValuePair("senha", senha);
		return postExecute(Constants.URL+"?rquest=loginpj_consumidor");
	}
	
	// Verifica Login do usuario
	public static HttpResponse getVerificaLogin(String login, String senha)  throws Exception {
		params.clear();
		addNameValuePair("login", login);
		addNameValuePair("senha", senha);
		return postExecute(Constants.URL+"?rquest=verificalogin_consumidor");
	}
	
	// Envia email para cliente PJ
	public static HttpResponse getEnviaEmailPJ(String cnpj)  throws Exception {
		params.clear();
		addNameValuePair("cnpj", cnpj);
		return postExecute(Constants.URL+"?rquest=verificaemailsenhapj_consumidor");
	}
    // Inclusões -----------------------------------------------------------------------------------------------------

	// Inclui o id gcm na base de dados
//	public static HttpResponse postRegistraGCM(String idgcm)  throws Exception {
//		params.clear();
//		addNameValuePair("codcliente",String.valueOf(Login.getCodcliente()));
//		addNameValuePair("idgcm",idgcm);
//		return postExecute(Constants.URL+"?rquest=registraidgcm_revendedor");
//	}
	
//	// Inclui o pedido de gás do revendedor
//	public static HttpResponse postPedidoGasRevendedor(String qtdp7, String qtdp13, String qtdp20, String qtdp45)  throws Exception {
//		params.clear();
//		addNameValuePair("codcliente",String.valueOf(Login.getCodcliente()));
//		addNameValuePair("qtdp7",qtdp7);
//		addNameValuePair("qtdp13",qtdp13);
//		addNameValuePair("qtdp20",qtdp20);
//		addNameValuePair("qtdp45",qtdp45);
//		return postExecute(Constants.URL+"?rquest=salvapedidogas_revendedor");
//	}
	
	
	// Inclui cadastro de cliente
	public static HttpResponse postCadastroConsumidor(Clientes obj, String tipo)  throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("cnpjcpf", obj.getCnpjCpf());
		jsonObject.accumulate("nome", obj.getNome());
		jsonObject.accumulate("endereco", obj.getEndereco());
		jsonObject.accumulate("numero", obj.getNumero());
		jsonObject.accumulate("codbairro", obj.getCodBairro());
		jsonObject.accumulate("cep", obj.getCep());
		jsonObject.accumulate("cidade", obj.getCidade());
		jsonObject.accumulate("estado", obj.getEstado());
		jsonObject.accumulate("telefone", obj.getTelefone());
		jsonObject.accumulate("dtnascimento", Util.dataInvertida(obj.getDtNascimento(), 1));
		jsonObject.accumulate("obsagenda", obj.getObsAgenda());
		jsonObject.accumulate("email", obj.getEmail());
		jsonObject.accumulate("codcidade", obj.getCodCidade());
		
		Log.i(Constants.TAG, "SALVA AGENDA - "+obj.getObsAgenda());
		Log.i(Constants.TAG, jsonObject.toString());
		
		params.clear();
		addNameValuePair("jsonObject",jsonObject.toString());
		HttpResponse hr = null;
		if(tipo.equals("I")) hr = postExecute(Constants.URL+"?rquest=salvacadconsumidor_consumidor");
		else
		if(tipo.equals("E")) hr =
		postExecute(Constants.URL+"?rquest=alteracadconsumidor_consumidor");
		return hr;
	}
	
	// Inclui pedido consumidor
	public static HttpResponse postSalvaPedido(List<EnviarPedido> obj)  throws Exception {
		JSONArray jsonObject = new JSONArray();
		for(EnviarPedido p : obj){
			JSONObject item = new JSONObject();
			item.put("codconsumidor", p.getCodConsumidor());
			item.put("codproduto", p.getCodProduto());
			item.put("codlocalidade", 0);
			item.put("qtd", p.getQtd());
			item.put("valor", p.getValor());
			item.put("total", p.getTotal());
			item.put("obstroco", p.getObsTroco());
						
			jsonObject.put(item);
			
			Log.i(Constants.TAG, "pedido ************* "+p.getCodProduto());
		}
		
		Log.i(Constants.TAG, "JSON ************* "+jsonObject.toString());
		params.clear();
		addNameValuePair("jsonObject",jsonObject.toString());
		HttpResponse hr = null;
		hr = postExecute(Constants.URL+"?rquest=salvapedido_consumidor");
		return hr;
	}
	
	// Inclui pedido consumidor pj
	public static HttpResponse postSalvaPedidoPJ(List<EnviarPedido> obj)  throws Exception {
		JSONArray jsonObject = new JSONArray();
		for(EnviarPedido p : obj){
			JSONObject item = new JSONObject();
			item.put("codconsumidor", p.getCodConsumidor());
			item.put("codlocalidade", p.getCodLocalidade());
			item.put("codproduto", p.getCodProduto());
			item.put("qtd", p.getQtd());
			item.put("valor", p.getValor());
			item.put("total", p.getTotal());
			item.put("obstroco", p.getObsTroco());
						
			jsonObject.put(item);
			
			Log.i(Constants.TAG, "pedido ************* "+p.getCodProduto());
		}
		
		Log.i(Constants.TAG, "JSON ************* "+jsonObject.toString());
		params.clear();
		addNameValuePair("jsonObject",jsonObject.toString());
		HttpResponse hr = null;
		hr = postExecute(Constants.URL+"?rquest=salvapedidopj_consumidor");
		return hr;
	}
	
	// Inclui pedido granel
	public static HttpResponse postSalvaPedidoGranel(List<EnviarPedidoGranel> obj)  throws Exception {
		JSONArray jsonObject = new JSONArray();
		for(EnviarPedidoGranel p : obj){
			JSONObject item = new JSONObject();
			item.put("codpedido", p.getCodPedido());
			item.put("codcliente", p.getCodCliente());
			item.put("codlocalidade", p.getCodLocalidade());
			item.put("datapedido", Util.dataInvertida(p.getDataPedido(),1));
			item.put("dataprogramacao", Util.dataInvertida(p.getDataProgramacao(),1));
						
			jsonObject.put(item);
			
			Log.i(Constants.TAG, "pedido ************* "+p.getCodPedido());
			Log.i(Constants.TAG, "data ************* "+p.getDataPedido());
			Log.i(Constants.TAG, "prog ************* "+p.getDataProgramacao());
		}
		
		Log.i(Constants.TAG, "JSON ************* "+jsonObject.toString());
		params.clear();
		addNameValuePair("jsonObject",jsonObject.toString());
		HttpResponse hr = null;
		hr = postExecute(Constants.URL+"?rquest=salvapedidogranel_consumidor");
		return hr;
	}

	
	
    // Alterações -----------------------------------------------------------------------------------------------------

	// Altera o status do pedido
//		public static HttpResponse postAjustaStatusPedido(String codpedido, String status)  throws Exception {
//			params.clear();
//			addNameValuePair("codpedido",codpedido);
//			addNameValuePair("status",status);
//			addNameValuePair("codcliente",String.valueOf(Login.getCodcliente()));
//			return postExecute(Constants.URL+"?rquest=salvastatuspedido_revendedor");
//		}

		
	// Exclusões -----------------------------------------------------------------------------------------------------
	

		
	// Classes auxiliares -------------------------------------------------------------------------------
	private static void addNameValuePair(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }	
	
	 private static HttpParams getHttpParams() {
         HttpParams htpp = new BasicHttpParams();
         HttpConnectionParams.setConnectionTimeout(htpp, Constants.CONN_TIMEOUT);
         HttpConnectionParams.setSoTimeout(htpp, Constants.SOCKET_TIMEOUT);
         return htpp;
     }
      
	private static HttpResponse postExecute(String url) {
		//addNameValuePair("serial",Login.getserialAparelho());
		//Log.e("AAA", "Serial: "+Login.getserialAparelho());
		
		HttpClient httpclient = new DefaultHttpClient(getHttpParams());
		HttpResponse response = null;
		HttpPost httppost = new HttpPost(url);
		try{
			httppost.setEntity(new UrlEncodedFormEntity(params, Encoding.UTF_8.toString()));
			response = httpclient.execute(httppost);
			Log.e("SPLASH","postExecute OK - "+response.getStatusLine().getStatusCode());
		} catch (Exception e){
			e.printStackTrace();
			Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;
			Log.e("SPLASH","postExecute ERRO - "+response.getStatusLine().getStatusCode());
		}
        return response;
	}
	
//	private static HttpResponse postExecuteJson(String url,  JSONObject jsonObject) {
//		
//		HttpClient httpclient = new DefaultHttpClient(getHttpParams());
//		HttpResponse response = null;
//		HttpPost httppost = new HttpPost(url);
//		try{
//			String json = jsonObject.toString();
//			StringEntity se = new StringEntity(json);
//			httppost.setEntity(se);
//			httppost.setHeader("Accept", "application/json");
//			httppost.setHeader("Content-type", "application/json");
//			response = httpclient.execute(httppost);
//			Log.e("SPLASH","postExecuteJson OK - "+response.getStatusLine().getStatusCode());
//		} catch (Exception e){
//			e.printStackTrace();
//			Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;
//			Log.e("SPLASH","postExecuteJson ERRO - "+response.getStatusLine().getStatusCode());
//		}
//        return response;
//	}
	
//	private static HttpResponse postExecuteJson(String url,  JSONObject jsonObject) {
//		//addNameValuePair("serial",Login.getserialAparelho());
//		//Log.e("AAA", "Serial: "+Login.getserialAparelho());
//		addNameValuePair("jsonObject",jsonObject.toString());
//		HttpClient httpclient = new DefaultHttpClient(getHttpParams());
//		HttpResponse response = null;
//		HttpPost httppost = new HttpPost(url);
//		try{
//			httppost.setEntity(new UrlEncodedFormEntity(params, Encoding.UTF_8.toString()));
//			response = httpclient.execute(httppost);
//			Log.e("SPLASH","postExecute OK - "+response.getStatusLine().getStatusCode());
//		} catch (Exception e){
//			e.printStackTrace();
//			Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;
//			Log.e("SPLASH","postExecute ERRO - "+response.getStatusLine().getStatusCode());
//		}
//        return response;
//	}
	
	
	public static String inputStreamToString(HttpResponse response) {
        String line;
        StringBuilder total = new StringBuilder();
		InputStream is = null;
		try {
			is = response.getEntity().getContent();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
        	int i = 1;
            while ((line = rd.readLine()) != null) {
            	Log.e(Constants.TAG, "LINHA >>> "+i+" - "+ line.toString());
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;
        }
		Log.e(Constants.TAG, "inputStreamToString - "+ total.toString());
        return total.toString();
    }
	
	
}
	


