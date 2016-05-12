package br.com.amazongas.json;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;
import br.com.amazongas.model.Bairros;
import br.com.amazongas.model.HistoricoPedido;
import br.com.amazongas.model.HistoricoPedidoItens;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.model.PedidoItens;
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class HistoricoPedidoItemParseJSON {

	public static ArrayList<HistoricoPedidoItens> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<HistoricoPedidoItens> objClass = new ArrayList<HistoricoPedidoItens>();
		//ArrayList<HistoricoPedidoItens> objClassItem = new ArrayList<HistoricoPedidoItens>();

		Log.e(Constants.TAG, "BairrosParseJSON - "+hr.getStatusLine().getStatusCode());
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					HistoricoPedidoItens obj = new HistoricoPedidoItens();

					obj.setNome(userJSON.getString("nomeproduto"));
					obj.setQtd(userJSON.getInt("quantidade"));
					obj.setPreco(userJSON.getDouble("preco"));
					
					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
