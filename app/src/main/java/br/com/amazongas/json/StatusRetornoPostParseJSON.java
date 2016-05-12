package br.com.amazongas.json;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.amazongas.model.StatusRetornoPost;
import br.com.amazongas.webservice.UtilWS;


public class StatusRetornoPostParseJSON {
	
	public static boolean parseDados(HttpResponse hr) throws JSONException{
		if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String jsonst = UtilWS.inputStreamToString(hr);
			if (jsonst != null && !jsonst.equals("")) {
				JSONObject jso = new JSONObject(jsonst);
				
				StatusRetornoPost.setStatus(jso.getString("status"));
				StatusRetornoPost.setMsg(jso.getString("msg"));
				
				return true;
			}
		} 
		return false;
		
	}

}
