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

import br.com.amazongas.model.Cidades;
import br.com.amazongas.model.Revendas;
import br.com.amazongas.webservice.UtilWS;


public class RevendasParseJSON {

	public static ArrayList<Revendas> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<Revendas> objClass = new ArrayList<Revendas>();

		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					Revendas obj = new Revendas();
						obj.setNomelocalidade(userJSON.getString("nomelocalidade"));
						obj.setEndereco(userJSON.getString("endereco"));
						obj.setBairro(userJSON.getString("nomebairro"));
						obj.setTelefone1(userJSON.getString("telefone1"));
						obj.setTelefone2(userJSON.getString("telefone2"));
						obj.setPontoreferencia(userJSON.getString("pontoreferencia"));
						obj.setCodcidade(userJSON.getString("codcidade"));
						obj.setLatitude(userJSON.getString("latiudeNumero"));
						obj.setLongitude(userJSON.getString("logitudenumero"));
						
						objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
