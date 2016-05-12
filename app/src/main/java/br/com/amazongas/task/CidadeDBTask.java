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


public class CidadeDBTask extends AsyncTask<String, Void, ArrayList<Cidades>> {
	
	public interface CidadeResultListener{
		public void CidadeResult(ArrayList<Cidades> result);
	}
	
	private CidadeResultListener listener;
	private ArrayList<Cidades> hc = null;
	private Context context;

	
	public CidadeDBTask(CidadeResultListener listener) {
		this.listener = listener;
		this.context = (Activity) listener;
	}

	@Override
	protected ArrayList<Cidades> doInBackground(String... params) {
//		int i = 0;
//		String sUF = params[0];
//		HttpResponse hr = null;
//		
//		while(i < Constants.LOOP_TASK){
//			Statics.mensagemErro = null;
//					try {
//						hr = UtilWS.getListaCidades(sUF);
//						
//						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//							hc = CidadesParseJSON.parseDados(hr);
//							if(hc.isEmpty()) Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE; else i = Constants.LOOP_TASK;	
//							
//						} else {
//								Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
//						}
//						
//					} catch (Exception e) {
//						e.printStackTrace();
//						Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
//					}
//					
//				Log.e(Constants.TAG, "Mensagem ERRO "+Statics.mensagemErro);
//				if(Statics.mensagemErro != null){
//					i++;
//				}else{
//					i = Constants.LOOP_TASK;
//				}
//			}
		try {
		  BDDataManager bd = new BDDataManager(context);
		  hc = (ArrayList<Cidades>) bd.selectCidades();
		} catch (Exception e) {
			e.printStackTrace();
			Statics.mensagemErro = Constants.ERRO_DADOS_CRUD;
		}		
		return hc;	
			
	}

	@Override
	protected void onPostExecute(ArrayList<Cidades> result) {
		listener.CidadeResult(result);
	}

}
