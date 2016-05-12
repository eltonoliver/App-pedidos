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
import br.com.amazongas.util.Constants;
import br.com.amazongas.webservice.UtilWS;


public class LoginParseJSON {

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

					Clientes.setCodConsumidor(userJSON.getInt("codconsumidor"));
					Clientes.setCnpjCpf(userJSON.getString("cnpjcpf").trim());
					Clientes.setNome(userJSON.getString("nome").trim());
					Clientes.setEndereco(userJSON.getString("endereco").trim());
					Clientes.setNumero(userJSON.getString("numero").trim());
					Clientes.setCodBairro(userJSON.getInt("codbairro"));
					Clientes.setCep(userJSON.getString("cep").trim());
					Clientes.setCidade(userJSON.getString("cidade").trim());
					Clientes.setEstado(userJSON.getString("estado").trim());
					Clientes.setTelefone(userJSON.getString("telefone").trim());
					Clientes.setDtNascimento(userJSON.getString("dtnascimento").trim());
					Clientes.setObsAgenda(userJSON.getString("obsagenda").trim());
					Clientes.setNomebairro(userJSON.getString("nomebairro").trim());
					Clientes.setCodCidade(userJSON.getString("codcidade").trim());
					Clientes.setEmail(userJSON.getString("email").trim());
					Clientes.setLatitude(userJSON.getString("latitude").trim());
					Clientes.setLongitude(userJSON.getString("longitude").trim());
					//objClass.add(obj);
					resultado = true;
					Log.i(Constants.TAG,"LoginParseJSON nomebairro - "+Clientes.getNomebairro());
					
				}
			}
		}

		return resultado;
	}
}
