package br.com.amazongas.task;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.json.RevendasParseJSON;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class SalvaRevendaTask extends AsyncTask<String, Void, Boolean> {
	
	public interface SalvaRevendaResultListener{
		public void SalvaRevendaResult(boolean result);
	}
	
	private SalvaRevendaResultListener listener;
	private ArrayList<Revendas> hc = null;
	private Context context = null;
	
	public SalvaRevendaTask(SalvaRevendaResultListener listener) {
		this.listener = listener;
		this.context = (Activity) listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		int i = 0;
		HttpResponse hr = null;
		boolean resultado = false;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
					try {
						hr = UtilWS.getListaRevendas();
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = RevendasParseJSON.parseDados(hr);
							if(hc.isEmpty()) Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE; // else break;
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
		if(hc != null){
			resultado = carregaRevenda(hc);
		}
		
		return resultado;	
			
	}

	@Override
	protected void onPostExecute(Boolean result) {
		Log.i(Constants.TAG, "SalvaCidadeTask onPostExecute "+Statics.mensagemErro);
		listener.SalvaRevendaResult(result);
		
	}
	
	private boolean carregaRevenda(ArrayList<Revendas> result){
		Log.i(Constants.TAG, "******************* Carrega Revenda - "+result);
		BDDataManager bd = new BDDataManager(context);

		if(result != null){
			for(Revendas b:result){
				  bd.inserirRevenda(b);
			}
			return true;
		} else {
 		   return false;
		}
	}

}
