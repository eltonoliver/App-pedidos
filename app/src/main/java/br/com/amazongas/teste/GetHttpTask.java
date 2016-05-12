package br.com.amazongas.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.util.Xml.Encoding;

public class GetHttpTask {
	// GetTask(context, List<classe>, webservice, tipo(get/post), 
	
	private Context context;
	
	//WS
    private static ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    private static ProgressDialog progressDialog;
    
    //Constantes
        

    
    
	// Construtor -----------------------------------------------------------------------------------
    
	public  GetHttpTask(Context context){
		this.context = context;
	}
	
	// Retorna lista
	public HttpResponse getList()  throws Exception {
		params.clear();
		//addNameValuePair("uf","AM");
		return postExecute(Constants.URL+"?rquest=listarevendas_consumidor");
	}	
	
	
	
	
	
	// Metodos Auxiliares ------------------------------------------------------------------------------
	
	private void addNameValuePair(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }	
	
	 private HttpParams getHttpParams() {
         HttpParams htpp = new BasicHttpParams();
         HttpConnectionParams.setConnectionTimeout(htpp, Constants.CONN_TIMEOUT);
         HttpConnectionParams.setSoTimeout(htpp, Constants.SOCKET_TIMEOUT);
         return htpp;
     }
      
	private  HttpResponse postExecute(String url) {
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
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		Log.e(Constants.TAG, "inputStreamToString - "+ total.toString());
        return total.toString();
    }

}
