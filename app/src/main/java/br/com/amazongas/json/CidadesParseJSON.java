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
import br.com.amazongas.webservice.UtilWS;


public class CidadesParseJSON {

	public static ArrayList<Cidades> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<Cidades> objClass = new ArrayList<Cidades>();

		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					Cidades obj = new Cidades();

					obj.setNome(userJSON.getString("nomecidade"));
					obj.setUf(userJSON.getString("uf"));
					obj.setCodcidade(userJSON.getString("codcidade"));

					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
