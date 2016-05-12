package br.com.amazongas.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.HistoricoPedidoPJGranelParseJSON;
import br.com.amazongas.json.HistoricoPedidoPJParseJSON;
import br.com.amazongas.json.HistoricoPedidoParseJSON;
import br.com.amazongas.json.LoginParseJSON;
import br.com.amazongas.json.PedidoParseJSON;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.HistoricoPedido;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class HistoricoPedidoPJGranelTask extends AsyncTask<String, Void, List<HistoricoPedido>> {
	
	public interface HistoricoPedidoPJGranelResultListener{
		public void HistoricoPedidoPJGranelResult(List<HistoricoPedido> result);
	}
	
	private HistoricoPedidoPJGranelResultListener listener;
	private List<HistoricoPedido> hc = null;
	
	public HistoricoPedidoPJGranelTask(HistoricoPedidoPJGranelResultListener listener) {
		this.listener = listener;
	}

	@Override
	protected List<HistoricoPedido> doInBackground(String... params) {
		int i = 0;
		int codConsumidor = Integer.parseInt(params[0]);
		int opcao = Integer.parseInt(params[1]);
		HttpResponse hr = null;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
			Log.i(Constants.TAG,"Historico doInBackground "+i);
					try {
						hr = UtilWS.getListaHistoricoPedidoPJGranel(codConsumidor, opcao);
						Log.i(Constants.TAG,"Historico doInBackground hr"+hr.getStatusLine().getStatusCode());
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = HistoricoPedidoPJGranelParseJSON.parseDados(hr);
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
	protected void onPostExecute(List<HistoricoPedido> result) {
		listener.HistoricoPedidoPJGranelResult(result);
		Log.i(Constants.TAG,"Historico onPostExecute result"+result);
	}

}
