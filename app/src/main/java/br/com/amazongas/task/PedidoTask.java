package br.com.amazongas.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.LoginParseJSON;
import br.com.amazongas.json.PedidoParseJSON;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class PedidoTask extends AsyncTask<String, Void, List<Pedido>> {
	
	public interface PedidoResultListener{
		public void PedidoResult(List<Pedido> result);
	}
	
	private PedidoResultListener listener;
	private List<Pedido> hc = null;
	
	public PedidoTask(PedidoResultListener listener) {
		this.listener = listener;
	}

	@Override
	protected List<Pedido> doInBackground(String... params) {
		int i = 0;
		int codConsumidor = Integer.parseInt(params[0]);
		HttpResponse hr = null;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
			Log.i(Constants.TAG,"doInBackground "+i);
					try {
						hr = UtilWS.getListaHistorico(codConsumidor);
						Log.i(Constants.TAG,"doInBackground hr"+hr.getStatusLine().getStatusCode());
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = PedidoParseJSON.parseDados(hr);
							Log.i(Constants.TAG,"doInBackground hc"+hc);
							if(hc == null) Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE; //else break;
							break;
							
						} else {
								Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
								break;
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
					}
					
				Log.e(Constants.TAG, "Mensagem ERRO "+Statics.mensagemErro);
				if(Statics.mensagemErro != null){
					i++;
				}else{
					//i = Constants.LOOP_TASK;
					break;
				}
			}
		return hc;	
			
	}

	@Override
	protected void onPostExecute(List<Pedido> result) {
		listener.PedidoResult(result);
		Log.i(Constants.TAG,"onPostExecute result"+result);
	}

}
