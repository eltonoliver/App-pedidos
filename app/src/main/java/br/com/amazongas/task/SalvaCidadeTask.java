package br.com.amazongas.task;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.json.BairrosParseJSON;
import br.com.amazongas.json.CidadesParseJSON;
import br.com.amazongas.model.Cidades;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class SalvaCidadeTask extends AsyncTask<String, Void, ArrayList<Cidades>> {
	
	public interface SalvaCidadeResultListener{
		public void SalvaCidadeResult(boolean result);
	}
	
	private SalvaCidadeResultListener listener;
	private ArrayList<Cidades> hc = null;
	private Context context = null;
	
	public SalvaCidadeTask(SalvaCidadeResultListener listener) {
		this.listener = listener;
		this.context = (Activity) listener;
	}

	@Override
	protected ArrayList<Cidades> doInBackground(String... params) {
		int i = 0;
		String sUF = params[0];
		HttpResponse hr = null;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
					try {
						hr = UtilWS.getListaCidades(sUF);
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = CidadesParseJSON.parseDados(hr);
							if(hc.isEmpty()) Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE; //else break;
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
	protected void onPostExecute(ArrayList<Cidades> result) {
		Log.i(Constants.TAG, "******************** SalvaCidadeTask onPostExecute "+Statics.mensagemErro);
		
		BDDataManager bd = new BDDataManager(context);
		if(result != null){
		  for(Cidades b: result){
			  bd.inserirCidade(b);
		  }
		  listener.SalvaCidadeResult(true);
		} else {
		  listener.SalvaCidadeResult(false);
		}
	}

}
