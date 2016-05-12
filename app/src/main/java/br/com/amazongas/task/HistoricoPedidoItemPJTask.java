package br.com.amazongas.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import android.util.Log;
import br.com.amazongas.json.HistoricoPedidoItemPJParseJSON;
import br.com.amazongas.json.HistoricoPedidoItemParseJSON;
import br.com.amazongas.json.HistoricoPedidoParseJSON;
import br.com.amazongas.json.LoginParseJSON;
import br.com.amazongas.json.PedidoParseJSON;
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.HistoricoPedido;
import br.com.amazongas.model.HistoricoPedidoItens;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.util.Constants;
import br.com.amazongas.util.Statics;
import br.com.amazongas.webservice.UtilWS;


public class HistoricoPedidoItemPJTask extends AsyncTask<String, Void, List<HistoricoPedidoItens>> {
	
	public interface HistoricoPedidoItemPJResultListener{
		public void HistoricoPedidoItemPJResult(List<HistoricoPedidoItens> result);
	}
	
	private HistoricoPedidoItemPJResultListener listener;
	private List<HistoricoPedidoItens> hc = null;
	
	public HistoricoPedidoItemPJTask(HistoricoPedidoItemPJResultListener listener) {
		this.listener = listener;
	}

	@Override
	protected List<HistoricoPedidoItens> doInBackground(String... params) {
		int i = 0;
		int pedido = Integer.parseInt(params[0]);
		HttpResponse hr = null;
		
		while(i < Constants.LOOP_TASK){
			Statics.mensagemErro = null;
			Log.i(Constants.TAG,"Historico doInBackground "+i);
					try {
						hr = UtilWS.getListaHistoricoPedidoItemPJ(pedido);
						Log.i(Constants.TAG,"Historico doInBackground hr"+hr.getStatusLine().getStatusCode());
						
						if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
							hc = HistoricoPedidoItemPJParseJSON.parseDados(hr);
							Log.i(Constants.TAG,"Historico doInBackground hc"+hc);
							if(hc == null) Statics.mensagemErro = Constants.ERRO_DADOS_WEBSERVICE;// else break;
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
					//i = Constants.LOOP_TASK;
					break;
				}
			}
		return hc;	
			
	}

	@Override
	protected void onPostExecute(List<HistoricoPedidoItens> result) {
		listener.HistoricoPedidoItemPJResult(result);
		Log.i(Constants.TAG,"Historico onPostExecute result"+result);
	}

}
