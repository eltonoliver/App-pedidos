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
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class BairrosParseJSON {

	public static ArrayList<Bairros> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<Bairros> objClass = new ArrayList<Bairros>();

		Log.e(Constants.TAG, "BairrosParseJSON - "+hr.getStatusLine().getStatusCode());
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					Bairros obj = new Bairros();

					obj.setCodBairro(userJSON.getInt("codbairro"));
					obj.setNomeBairro(userJSON.getString("nomebairro"));

					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
