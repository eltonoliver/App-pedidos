package br.com.amazongas.task;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.json.BairrosParseJSON;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class SalvaBairroTask extends AsyncTask<String, Void, Boolean> {
	
	public interface SalvaBairroResultListener{
		public void SalvaBairroResult(boolean result);
	}
	
	private SalvaBairroResultListener listener;
	private ArrayList<Bairros> hc = null;
	private Context context;
	private boolean resultado = false;

	
	public SalvaBairroTask(SalvaBairroResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		int i = 0;
		String sUF = params[0];
		HttpResponse hr = null;
		
		Log.e(Constants.TAG, "BairroTask");
		while(i < Constants.LOOP_TASK){
			Log.e(Constants.TAG, "BairroTask loop - "+i);
			Statics.mensagemErro = null;
			Log.e(Constants.TAG, "Mensagem ERRO inicio "+Statics.mensagemErro);
			
					try {
						hr = UtilWS.getListaBairros(sUF);
						Log.e(Constants.TAG, "BairroTask doInBackground hr "+hr);
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = BairrosParseJSON.parseDados(hr);
							Log.e(Constants.TAG, "BairrosParseJSON.parseDados(hr)"+hc);
							if(hc.isEmpty()) Statics.mensagemErro = "1 - "+Constants.ERRO_DADOS_WEBSERVICE; //else break;
							break;
							
						} else {
								Statics.mensagemErro = "2 - "+Constants.ERRO_DADOS_WEBSERVICE;	
								break;
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						Statics.mensagemErro = "3 - "+Constants.ERRO_DADOS_WEBSERVICE;	
					}
					
				Log.e(Constants.TAG, "Mensagem ERRO "+Statics.mensagemErro);
				if(Statics.mensagemErro != null){
					i++;
				}else{
					//i = Constants.LOOP_TASK;
					break;
				}
			}
		
		if(hc != null) {
			resultado = carregaBairro(hc);
		} 
		
		return resultado;
			
	}

	@Override
	protected void onPostExecute(Boolean result) {
		Log.i(Constants.TAG, "SalvaBairroTask onPostExecute RESULT >>>>> - "+result);
		listener.SalvaBairroResult(result);
		//BDDataManager bd = new BDDataManager(context);

//		if(result != null){
//			for(Bairros b:result){
//				if(b.getNomeBairro() != null && b.getNomeBairro() != "" && b.getNomeBairro() != "null"){
//				  bd.inserirBairro(b);
//				}
//			}
//			listener.SalvaBairroResult(true);
//		} else {
//			listener.SalvaBairroResult(false);
//		}
	}
	
	private boolean carregaBairro(ArrayList<Bairros> result){
		Log.i(Constants.TAG, "SalvaBairroTask - carregaBairro >>>>> - "+result);
		BDDataManager bd = new BDDataManager(context);

		if(result != null){
			for(Bairros b:result){
				if(b.getNomeBairro() != null && b.getNomeBairro() != "" && b.getNomeBairro() != "null"){
				  bd.inserirBairro(b);
				}
			}
			return true;
		} else {
 		   return false;
		}
	}

}
