package br.com.amazongas.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.BairrosParseJSON;
import br.com.amazongas.json.StatusRetornoPostParseJSON;
import br.com.amazongas.listener.PostResultListener;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.EnviarPedido;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class PostPedidoPJTask extends AsyncTask<String, Void, Boolean> {
	
	private PostResultListener listener;
	//private List<EnviarPedido> hc = null;
	private List<EnviarPedido> pedido;
	
	public PostPedidoPJTask(PostResultListener listener, List<EnviarPedido> pedido) {
		this.listener = listener;
		this.pedido = pedido;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		int i = 0;
		boolean retorno = true;
		HttpResponse hr = null;
		
		Log.i(Constants.TAG, "clientetask");
		while(i < Constants.LOOP_TASK){
			Log.i(Constants.TAG, "cliente loop - "+i);
			Statics.mensagemErro = null;
			retorno = true;
			Log.e(Constants.TAG, "Mensagem ERRO inicio "+Statics.mensagemErro);
			
					try {
						hr = UtilWS.postSalvaPedidoPJ(pedido);
						Log.e(Constants.TAG, "ClienteTask doInBackground hr "+hr);
						
					    if(hr.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
					    	if(hr.getStatusLine().getStatusCode() == 512){
					    		Statics.mensagemErro = "Pedido já cadastrado!";
						    	retorno = false;
						    	break;
					    	} else {
					    		Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
					    		retorno = false;
					    		break;
					    	}
					    } else {
					    	StatusRetornoPostParseJSON.parseDados(hr);
					    	break;
					    }
						
					} catch (Exception e) {
						e.printStackTrace();
						Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
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
		listener.postResult(result, Constants.POST_ENVIAR_PEDIDO);
	}

}
