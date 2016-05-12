package br.com.amazongas.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.json.BairrosParseJSON;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class BairroDBTask extends AsyncTask<String, Void, List<Bairros>> {
	
	public interface BairroResultListener{
		public void BairroResult(List<Bairros> result);
	}
	
	private BairroResultListener listener;
	private List<Bairros> hc = null;
	private Context context;

	
	public BairroDBTask(BairroResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected List<Bairros> doInBackground(String... params) {
		//int i = 0;
		//String sUF = params[0];
//		HttpResponse hr = null;
//		
//		Log.e(Constants.TAG, "BairroTask");
//		while(i < Constants.LOOP_TASK){
//			Log.e(Constants.TAG, "BairroTask loop - "+i);
//			Statics.mensagemErro = null;
//			Log.e(Constants.TAG, "Mensagem ERRO inicio "+Statics.mensagemErro);
//			
//					try {
//						hr = UtilWS.getListaBairros(sUF);
//						Log.e(Constants.TAG, "BairroTask doInBackground hr "+hr);
//						
//						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//							hc = BairrosParseJSON.parseDados(hr);
//							Log.e(Constants.TAG, "BairrosParseJSON.parseDados(hr)"+hc);
//							if(hc.isEmpty()) Statics.mensagemErro = "1 - "+Constants.ERRO_DADOS_WEBSERVICE; else i = Constants.LOOP_TASK;
//							
//						} else {
//								Statics.mensagemErro = "2 - "+Constants.ERRO_DADOS_WEBSERVICE;	
//						}
//						
//					} catch (Exception e) {
//						e.printStackTrace();
//						Statics.mensagemErro = "3 - "+Constants.ERRO_DADOS_WEBSERVICE;	
//					}
//					
//				Log.e(Constants.TAG, "Mensagem ERRO "+Statics.mensagemErro);
//				if(Statics.mensagemErro != null){
//					i++;
//				}else{
//					i = Constants.LOOP_TASK;
//				}
//			}
		
		try{
		  BDDataManager bd = new BDDataManager(context);
		  hc = bd.selectBairros();
		} catch (Exception e) {
			e.printStackTrace();
			Statics.mensagemErro = Constants.ERRO_DADOS_CRUD;
		}
		
		return hc;	
			
	}

	@Override
	protected void onPostExecute(List<Bairros> result) {
		Log.e(Constants.TAG, "BairroTask onPostExecute RESULT - "+result);
		listener.BairroResult(result);
	}

}
