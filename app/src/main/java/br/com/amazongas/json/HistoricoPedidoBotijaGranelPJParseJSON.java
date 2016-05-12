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


public class HistoricoPedidoBotijaGranelPJParseJSON {

	public static ArrayList<HistoricoPedido> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<HistoricoPedido> objClass = new ArrayList<HistoricoPedido>();
		ArrayList<HistoricoPedidoItens> objClassItem = new ArrayList<HistoricoPedidoItens>();

		Log.e(Constants.TAG, "BairrosParseJSON - "+hr.getStatusLine().getStatusCode());
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					HistoricoPedido obj = new HistoricoPedido();

					obj.setCodigo(userJSON.getInt("codpedidovenda"));
					obj.setData(userJSON.getString("data")); //+ " "+userJSON.getString("hora"));
					obj.setEntrega(userJSON.getString("entrega"));
					obj.setTipo(userJSON.getString("tipo"));
					
					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
