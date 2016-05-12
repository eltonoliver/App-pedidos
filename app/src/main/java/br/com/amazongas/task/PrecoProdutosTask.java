package br.com.amazongas.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.db.BDDataManager;
import br.com.amazongas.json.BairrosParseJSON;
import br.com.amazongas.json.PrecoProdutosParseJSON;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.PrecoProdutos;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class PrecoProdutosTask extends AsyncTask<String, Void, List<PrecoProdutos>> {
	
	public interface PrecoProdutosResultListener{
		public void PrecoProdutosResult(List<PrecoProdutos> result);
	}
	
	private PrecoProdutosResultListener listener;
	private ArrayList<PrecoProdutos> hc = null;
	private Context context;

	
	public PrecoProdutosTask(PrecoProdutosResultListener listener) {
		this.listener = listener;
		this.context = (Context) listener;
	}

	@Override
	protected List<PrecoProdutos> doInBackground(String... params) {
		int i = 0;
		String sUF = params[0];
		HttpResponse hr = null;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
					try {
						hr = UtilWS.getListaPreco(sUF);
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = PrecoProdutosParseJSON.parseDados(hr);
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
	protected void onPostExecute(List<PrecoProdutos> result) {
		listener.PrecoProdutosResult(result);
	}

}
