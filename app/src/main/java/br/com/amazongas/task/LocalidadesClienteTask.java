package br.com.amazongas.task;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.LocalidadesClienteParseJSON;
import br.com.amazongas.model.LocalidadesCliente;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;



public class LocalidadesClienteTask extends AsyncTask<String, Void, List<LocalidadesCliente>> {
	
	public interface LocalidadesClienteResultListener{
		public void LocalidadesClienteResult(List<LocalidadesCliente> result);
	}
	
	private LocalidadesClienteResultListener listener;
	private List<LocalidadesCliente> hc = null;
	
	public LocalidadesClienteTask(LocalidadesClienteResultListener listener) {
		this.listener = listener;
	}

	@Override
	protected List<LocalidadesCliente> doInBackground(String... params) {
		int i = 0;
		int codConsumidor = Integer.parseInt(params[0]);
		Log.i(Constants.TAG,"LocalidadesClienteTask >>> Historico doInBackground - "+codConsumidor);
		
		HttpResponse hr = null;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
			Log.i(Constants.TAG,"Historico doInBackground >> LocalidadesClienteTask >> "+i);
					try {
						hr = UtilWS.getListaLocalidadesCliente(codConsumidor);
						Log.i(Constants.TAG,"LocalidadesClienteTask >>> Historico doInBackground hr "+hr.getStatusLine().getStatusCode());
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							Log.i(Constants.TAG,"LocalidadesClienteTask >>> HttpStatus.SC_OK) "+hr.getStatusLine().getStatusCode());
							hc = LocalidadesClienteParseJSON.parseDados(hr);
							Log.i(Constants.TAG,"Historico doInBackground hc"+hc);
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
					
				Log.e(Constants.TAG, "Historico Mensagem ERRO "+Statics.mensagemErro);
				if(Statics.mensagemErro != null){
					i++;
				}else{
					//i = Constants.LOOP_TASK;]
					break;
				}
			}
		return hc;	
			
	}

	@Override
	protected void onPostExecute(List<LocalidadesCliente> result) {
		listener.LocalidadesClienteResult(result);
		Log.i(Constants.TAG,"Historico onPostExecute result"+result);
	}

}
