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


public class LoginTask extends AsyncTask<String, Void, Boolean> {
	
	public interface LoginResultListener{
		public void LoginResult(boolean result);
	}
	
	private LoginResultListener listener;
	private boolean hc = false;
	
	public LoginTask(LoginResultListener listener) {
		this.listener = listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		int i = 0;
		String slogin = params[0];
		String ssenha = params[1];
		HttpResponse hr = null;
		Log.i(Constants.TAG,"doInBackground parametros "+slogin+" - "+ssenha);
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
			Log.i(Constants.TAG,"doInBackground "+i);
					try {
						hr = UtilWS.getLogin(slogin, ssenha);
						Log.i(Constants.TAG,"doInBackground hr"+hr.getStatusLine().getStatusCode());
						
						Log.i(Constants.TAG,"doInBackground hr"+hr.getStatusLine().getStatusCode());
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = LoginParseJSON.parseDados(hr);
							Log.i(Constants.TAG,"doInBackground hc"+hc);
							if(hc != true) Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE; else break; //i = Constants.LOOP_TASK;	
							
						} else if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT){
								Statics.mensagemErro = Constants.MSG_SEM_LOGIN;	
								break;
						} else {
								Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;	
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
	protected void onPostExecute(Boolean result) {
		listener.LoginResult(result);
		Log.i(Constants.TAG,"onPostExecute result"+result);
	}

}
