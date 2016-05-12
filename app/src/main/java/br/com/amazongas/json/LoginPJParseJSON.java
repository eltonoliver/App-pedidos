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
import br.com.amazongas.model.Clientes;
import br.com.amazongas.model.ClientesPJ;
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class LoginPJParseJSON {

	public static boolean parseDados(HttpResponse hr) throws JSONException, ParseException, IOException {
		//ArrayList<Clientes> objClass = new ArrayList<Clientes>();
		boolean resultado = false;
		
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONArray array = ((JSONArray) new JSONTokener(jsonst).nextValue());
				
				for (int i = 0; i < array.length(); i++) {
					JSONObject userJSON = array.getJSONObject(i);
					//Clientes obj = null;// = new Clientes();

					ClientesPJ.setCodCliente(userJSON.getInt("codcliente"));
					ClientesPJ.setCpfcgc(userJSON.getString("cpfcgc"));
					ClientesPJ.setSenha(userJSON.getString("senha"));
					ClientesPJ.setRazaoSocial(userJSON.getString("razaosocial"));
					ClientesPJ.setCodCidade(userJSON.getString("codcidade"));
					ClientesPJ.setCodBairro(userJSON.getInt("codbairro"));
					ClientesPJ.setLatitude(userJSON.getString("latiudenumero"));
					ClientesPJ.setLongitude(userJSON.getString("logitudenumero"));
					
					//objClass.add(obj);
					resultado = true;
					Log.i(Constants.TAG,"LoginParseJSON nomebairro - "+Clientes.getNomebairro());
					
				}
			}
		}

		return resultado;
	}
}
