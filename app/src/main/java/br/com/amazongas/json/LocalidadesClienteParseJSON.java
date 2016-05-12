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
import br.com.amazongas.model.LocalidadesCliente;
import br.com.amazongas.model.Pedido;
import br.com.amazongas.model.PedidoItens;
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class LocalidadesClienteParseJSON {

	public static ArrayList<LocalidadesCliente> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<LocalidadesCliente> objClass = new ArrayList<LocalidadesCliente>();

		Log.e(Constants.TAG, "BairrosParseJSON - "+hr.getStatusLine().getStatusCode());
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					LocalidadesCliente obj = new LocalidadesCliente();

					obj.setCodigo(userJSON.getInt("codlocalidade"));
					obj.setNome(userJSON.getString("nomelocalidade"));
					obj.setEndereco(userJSON.getString("endereco"));
					obj.setBairro(userJSON.getString("nomebairro"));
					obj.setTelefone(userJSON.getString("telefone1"));
					
					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
