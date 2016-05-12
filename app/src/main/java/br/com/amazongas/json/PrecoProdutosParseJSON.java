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
import br.com.amazongas.model.PrecoProdutos;
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class PrecoProdutosParseJSON {

	public static ArrayList<PrecoProdutos> parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		ArrayList<PrecoProdutos> objClass = new ArrayList<PrecoProdutos>();

		Log.e(Constants.TAG, "BairrosParseJSON - "+hr.getStatusLine().getStatusCode());
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					PrecoProdutos obj = new PrecoProdutos();

					obj.setCodProduto(userJSON.getInt("codproduto"));
					obj.setPreco(userJSON.getDouble("preco"));

					objClass.add(obj);
				}
			}
		}

		return objClass;
	}
}
