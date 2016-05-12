package br.com.amazongas.task;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.LoginParseJSON;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class VerificaLoginTask extends AsyncTask<String, Void, String> {
	
	public interface VerificaLoginResultListener{
		public void VerificaLoginResult(String result);
	}
	
	private VerificaLoginResultListener listener;
	private ArrayList<Clientes> hc = null;
	
	public VerificaLoginTask(VerificaLoginResultListener listener) {
		this.listener = listener;
	}

	@Override
	protected String doInBackground(String... params) {
		int i = 0;
		String slogin = params[0];
		String ssenha = params[1];
		HttpResponse hr = null;
		String resultado = "ERRO";
		
		Log.i(Constants.TAG,"doInBackground parametros "+slogin+" - "+ssenha);
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
			Log.i(Constants.TAG,"doInBackground "+i);
					try {
						hr = UtilWS.getVerificaLogin(slogin, ssenha);
						Log.i(Constants.TAG,"doInBackground hr"+hr.getStatusLine().getStatusCode());
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							resultado = "OK";
							//i = Constants.LOOP_TASK;
							break;
						}
						else if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT){
							resultado = "NO";
							//i = Constants.LOOP_TASK;
							break;
						}
						else {
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
		return resultado;	
			
	}

	@Override
	protected void onPostExecute(String result) {
		listener.VerificaLoginResult(result);
		Log.i(Constants.TAG,"onPostExecute result"+result);
	}

}
