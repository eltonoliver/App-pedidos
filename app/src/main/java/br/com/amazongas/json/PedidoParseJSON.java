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
import br.com.amazongas.model.Pedido;
import br.com.amazongas.model.PedidoItens;
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class PedidoParseJSON {

	public static ArrayList<Pedido> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<Pedido> objClass = new ArrayList<Pedido>();
		ArrayList<PedidoItens> objClassItem = new ArrayList<PedidoItens>();

		Log.e(Constants.TAG, "BairrosParseJSON - "+hr.getStatusLine().getStatusCode());
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					Pedido obj = new Pedido();

					obj.setCodPedido(userJSON.getInt("codpedidovenda"));
					obj.setData(userJSON.getString("data"));
					obj.setHora(userJSON.getString("hora"));
					obj.setStatus(userJSON.getString("status"));
					obj.setQtdTotal(userJSON.getInt("qtd"));
					obj.setValorTotal(userJSON.getDouble("preco"));
					obj.setRevenda(userJSON.getString("revenda"));
					obj.setTelefone(userJSON.getString("telefone"));
					
					JSONArray arrayItem = userJSON.getJSONArray("telefone");
					for (int i2 = 0; i2 < arrayItem.length(); i2++) {
						JSONObject itemJSON = arrayItem.getJSONObject(i2);
						PedidoItens objItem = new PedidoItens();
						objItem.setNome(itemJSON.getString("nomeproduto"));
						objItem.setQtd(itemJSON.getInt("quantidade"));
						objItem.setValor(itemJSON.getDouble("preco"));
						
						objClassItem.add(objItem);
					}
					obj.setItems(objClassItem);
					
					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
