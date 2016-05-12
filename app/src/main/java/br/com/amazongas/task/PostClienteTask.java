package br.com.amazongas.task;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.BairrosParseJSON;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class PostClienteTask extends AsyncTask<String, Void, Boolean> {
	
	public interface ClienteResultListener{
		public void ClienteResult(boolean result);
	}
	
	private ClienteResultListener listener;
	private ArrayList<Clientes> hc = null;
	private Clientes clientes;
	
	public PostClienteTask(ClienteResultListener listener, Clientes clientes) {
		this.listener = listener;
		this.clientes = clientes;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		int i = 0;
		boolean retorno = true;
		HttpResponse hr = null;
		String tipo = params[0];
		
		Log.i(Constants.TAG, "clientetask");
		while(i < Constants.LOOP_TASK){
			Log.i(Constants.TAG, "cliente loop - "+i);
			Statics.mensagemErro = null;
			retorno = true;
			Log.e(Constants.TAG, "Mensagem ERRO inicio "+Statics.mensagemErro);
			
					try {
						hr = UtilWS.postCadastroConsumidor(clientes, tipo);
						Log.e(Constants.TAG, "ClienteTask doInBackground hr "+hr);
						
					    if(hr.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
					    	if(hr.getStatusLine().getStatusCode() == 512){
					    		if(tipo.equals("I")) Statics.mensagemErro = "CNPJ/CPF e/ou Telefone já cadastrados!";
					    		if(tipo.equals("E")) Statics.mensagemErro = "Telefone já cadastrados!";
						    	retorno = false;
						    	break;
					    	} else {
					    		Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
					    		retorno = false;
					    		break;
					    	}
					    } else break;
						
					} catch (Exception e) {
						e.printStackTrace();
						Statics.mensagemErro = "3 - "+Constants.ERRO_DADOS_WEBSERVICE;	
				    	retorno = false;
					}
					
				Log.e(Constants.TAG, "Mensagem ERRO "+Statics.mensagemErro);
				if(Statics.mensagemErro != null){
					i++;
				}else{
					//i = Constants.LOOP_TASK;
					break;
				}
			}
		return retorno;	
			
	}

	@Override
	protected void onPostExecute(Boolean result) {
		Log.e(Constants.TAG, "BairroTask onPostExecute RESULT - "+result);
		listener.ClienteResult(result);
	}

}
